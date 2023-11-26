package KNUChat.User.application;

import KNUChat.User.dto.request.UserProfileCreateRequest;
import KNUChat.User.dto.request.UserProfileUpdateRequest;
import KNUChat.User.dto.request.UserCreateRequest;
import KNUChat.User.dto.response.UserBatchResponse;
import KNUChat.User.dto.response.UserProfileResponse;
import KNUChat.User.dto.response.UserSearchDto;
import KNUChat.User.entity.*;
import KNUChat.User.exception.NotFoundException;
import KNUChat.User.repository.*;
import KNUChat.User.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final CertificationRepository certificationRepository;
    @Autowired
    private final UrlRepository urlRepository;

    public Long createUser(UserCreateRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .build();

        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public Long createUserProfile(UserProfileCreateRequest request) {
        User user = updateUserFrom(request.getUserDto());

        Profile profile = buildProfileFrom(request.getProfileDto(), user);
        profileRepository.save(profile);
        departmentRepository.saveAll(buildDepartmentsFrom(request.getDepartmentDtos(), profile));
        if (request.getCertificationDtos() != null)
            certificationRepository.saveAll(buildCertificationsFrom(request.getCertificationDtos(), profile));
        if (request.getUrlDtos() != null)
            urlRepository.saveAll(buildUrlsFrom(request.getUrlDtos(), profile));

        return user.getId();
    }

    public Profile buildProfileFrom(ProfileDto profileDto, User user) {
        Profile profile = Profile.builder()
                .stdNum(profileDto.getStdNum())
                .academicStatus(AcademicStatus.valueOf(profileDto.getAcademicStatus()))
                .grade(profileDto.getGrade())
                .admissionDate(profileDto.getAdmissionDate())
                .graduateDate(profileDto.getGraduateDate())
                .introduction(profileDto.getIntroduction())
                .user(user)
                .build();

        return profile;
    }

    public List<Department> buildDepartmentsFrom(List<DepartmentDto> departmentDtos, Profile profile) {
        List<Department> departments = departmentDtos.stream()
                .map(departmentDto -> Department.builder()
                        .college(departmentDto.getCollege())
                        .department(departmentDto.getDepartment())
                        .major(departmentDto.getMajor())
                        .depCategory(DepCategory.valueOf(departmentDto.getDepCategory()))
                        .profile(profile)
                        .build())
                .collect(Collectors.toList());

        return departments;
    }

    public List<Certification> buildCertificationsFrom(List<CertificationDto> certificationDtos, Profile profile) {
        return certificationDtos.stream()
                .map(certificationDto -> Certification.builder()
                        .name(certificationDto.getName())
                        .achievement(certificationDto.getAchievement())
                        .obtainDate(certificationDto.getObtainDate())
                        .expireDate(certificationDto.getExpireDate())
                        .profile(profile)
                        .build())
                .collect(Collectors.toList());
    }

    public List<Url> buildUrlsFrom(List<String> urlDtos, Profile profile) {
        return urlDtos.stream()
                .map(urlDto -> Url.builder()
                        .link(urlDto)
                        .profile(profile)
                        .build())
                .collect(Collectors.toList());
    }

    public List<UserSearchDto> getPaging(String major, int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Department> departmentPage = departmentRepository.findByMajor(major, pageable);

        List<UserSearchDto> userSearchDtos = departmentPage.stream()
                .map(department -> {
                    Profile profile = department.getProfile();
                    User user = profile.getUser();
                    List<Department> departments = departmentRepository.findAllByProfileId(profile.getId());
                    return UserSearchDto.from(user, profile, departments);
                })
                .toList();

        return userSearchDtos;
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfileByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        Profile profile = profileRepository.findByUser_Id(userId).orElseThrow(() -> new NotFoundException("Profile"));

        return new UserProfileResponse(
                UserDto.from(user),
                ProfileDto.from(profile),
                getAllDepartmentDtoByProfile(profile.getId()),
                findAllCertificationDtoByProfile(profile.getId()),
                findAllUrlDtoByProfile(profile.getId())
        );
    }

    public List<DepartmentDto> getAllDepartmentDtoByProfile(Long profileId) {
        List<Department> departments = departmentRepository.findAllByProfileId(profileId);
        if (departments.isEmpty())
            throw new NotFoundException("Department");

        return departments.stream()
                .map(DepartmentDto::from)
                .collect(Collectors.toList());
    }

    public List<CertificationDto> findAllCertificationDtoByProfile(Long profileId) {
        return certificationRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(CertificationDto::from)
                .collect(Collectors.toList());
    }

    public List<String> findAllUrlDtoByProfile(Long profileId) {
        return urlRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(Url::getLink)
                .collect(Collectors.toList());
    }

    public boolean updateUserProfile(UserProfileUpdateRequest request) {
        User user = updateUserFrom(request.getUserDto());
        Profile profile = updateProfile(request.getProfileDto(), user);
        updateDepartments(request.getDepartmentDtos(), profile);
        updateCertifications(request.getCertificationDtos(), profile);
        updateUrls(request.getUrlDtos(), profile);

        return true;
    }

    public User updateUserFrom(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User"));

        user.update(userDto);

        return user;
    }

    public Profile updateProfile(ProfileDto profileDto, User user) {
        Profile profile = profileRepository.findByUser_Id(user.getId()).orElseThrow(() -> new NotFoundException("Profile"));
        if (profileDto == null)
            return profile;

        profile.update(profileDto, user);

        return profile;
    }

    public List<Department> updateDepartments(List<DepartmentDto> departmentDtos, Profile profile) {
        List<Department> departments = departmentRepository.findAllByProfileId(profile.getId());
        if (departments.isEmpty())
            throw new NotFoundException("Department");

        return departments;
    }

    public List<Certification> updateCertifications(List<CertificationDto> certificationDtos, Profile profile) {
        List<Certification> certifications = certificationRepository.findAllByProfileId(profile.getId());
        if (certifications.isEmpty())
            throw new NotFoundException("Certification");

        return certifications;
    }

    public List<Url> updateUrls(List<String> urlDtos, Profile profile) {
        List<Url> urls = urlRepository.findAllByProfileId(profile.getId());
        if (urls.isEmpty())
            throw new NotFoundException("URL");

        return urls;
    }
}