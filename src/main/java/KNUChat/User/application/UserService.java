package KNUChat.User.application;

import KNUChat.User.dto.request.UserProfileRequest;
import KNUChat.User.dto.request.UserRequest;
import KNUChat.User.dto.response.UserProfileResponse;
import KNUChat.User.entity.*;
import KNUChat.User.exception.NotFoundException;
import KNUChat.User.repository.*;
import KNUChat.User.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Long createUser(UserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .build();

        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public Long createUserProfile(UserProfileRequest request) {
        User user = updateUserFrom(request.getUserDto());

        Profile profile = buildProfileFrom(request.getProfileDto(), user);
        profileRepository.save(profile);
        departmentRepository.saveAll(buildDepartmentsFrom(request.getDepartmentDtos(), profile));
        if (request.getCertificationDtos() != null)
            certificationRepository.saveAll(buildCertificationFrom(request.getCertificationDtos(), profile));
        if (request.getUrlDtos() != null)
            urlRepository.saveAll(buildUrlDtoFrom(request.getUrlDtos(), profile));

        return user.getId();
    }

    public User updateUserFrom(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User"));

        user.update(userDto);

        return user;
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

    public List<Certification> buildCertificationFrom(List<CertificationDto> certificationDtos, Profile profile) {
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

    public List<Url> buildUrlDtoFrom(List<UrlDto> urlDtos, Profile profile) {
        return urlDtos.stream()
                .map(urlDto -> Url.builder()
                        .link(urlDto.getLink())
                        .profile(profile)
                        .build())
                .collect(Collectors.toList());
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

    public List<UrlDto> findAllUrlDtoByProfile(Long profileId) {
        return urlRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(UrlDto::from)
                .collect(Collectors.toList());
    }

    public boolean updateUserProfile(UserProfileRequest request) {
        updateUserFrom(request.getUserDto());

        return true;
    }

    public Long updateProfile(ProfileDto profileDto, Long userId) {
        Profile profile = profileRepository.findByUser_Id(userId).orElseThrow(() -> new NotFoundException("Profile"));

        return profile.getId();
    }

    public boolean updateDepartments() {
        return true;
    }

    public boolean updateCertifications() {
        return true;
    }

    public boolean updateUrls() {
        return true;
    }
}