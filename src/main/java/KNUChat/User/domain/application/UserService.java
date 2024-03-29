package KNUChat.User.domain.application;

import KNUChat.User.domain.dto.CertificationDto;
import KNUChat.User.domain.dto.DepartmentDto;
import KNUChat.User.domain.dto.ProfileDto;
import KNUChat.User.domain.dto.UserDto;
import KNUChat.User.domain.dto.response.UserBatchResponse;
import KNUChat.User.domain.entity.*;
import KNUChat.User.domain.repository.*;
import KNUChat.User.domain.dto.request.UserProfileCreateRequest;
import KNUChat.User.domain.dto.request.UserProfileUpdateRequest;
import KNUChat.User.domain.dto.request.UserCreateRequest;
import KNUChat.User.domain.dto.response.UserProfileResponse;
import KNUChat.User.domain.dto.response.UserSearchDto;
import KNUChat.User.global.exception.domain.NotFoundException;
import KNUChat.User.kafka.application.Logger;
import KNUChat.User.kafka.dto.LogType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final DepartmentRepository departmentRepository;
    private final CertificationRepository certificationRepository;
    private final UrlRepository urlRepository;

    private final Logger logger;

    public Long signIn(String email) {
        Long userId = findUserByEmail(email);
        if (userId == null) {
            userId = createUser(new UserCreateRequest(email));
            logger.sendMessage(LogType.INFO, "User signup", userId);
        }

        return userId;
    }

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

    public UserBatchResponse getUserBatch(String major, int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Department> departmentPage = departmentRepository.findByMajor(major, pageable);
        int totalPages = departmentPage.getTotalPages();

        List<UserSearchDto> userSearchDtos = departmentPage.stream()
                .map(department -> {
                    Profile profile = department.getProfile();
                    User user = profile.getUser();
                    List<Department> departments = departmentRepository.findAllByProfileId(profile.getId());
                    return UserSearchDto.from(user, profile, departments);
                })
                .toList();

        return UserBatchResponse.of(userSearchDtos, totalPages);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfileByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("Profile"));

        return new UserProfileResponse(
                UserDto.from(user),
                ProfileDto.from(profile),
                getAllDepartmentDtoByProfile(profile.getId()),
                findAllCertificationDtoByProfile(profile.getId()),
                findAllUrlDtoByProfile(profile.getId())
        );
    }

    public Long findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return user.get().getId();
        else
            return null;
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

    @Transactional
    public Long updateUserProfile(UserProfileUpdateRequest request) {
        User user = updateUserFrom(request.getUserDto());
        Profile profile = updateProfile(request.getProfileDto(), user);
        updateDepartments(request.getDepartmentDtos(), profile);
        updateCertifications(request.getCertificationDtos(), profile);
        updateUrls(request.getUrlDtos(), profile);

        return user.getId();
    }

    public User updateUserFrom(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User"));

        user.update(userDto);

        return user;
    }

    public Profile updateProfile(ProfileDto profileDto, User user) {
        Profile profile = profileRepository.findByUserId(user.getId()).orElseThrow(() -> new NotFoundException("Profile"));
        if (profileDto == null)
            return profile;

        profile.update(profileDto, user);

        return profile;
    }

    public List<Department> updateDepartments(List<DepartmentDto> departmentDtos, Profile profile) {
        departmentRepository.deleteAllByProfileId(profile.getId());
        List<Department> departments = buildDepartmentsFrom(departmentDtos, profile);
        departmentRepository.saveAll(departments);

        return departments;
    }

    public List<Certification> updateCertifications(List<CertificationDto> certificationDtos, Profile profile) {
        certificationRepository.deleteAllByProfileId(profile.getId());
        if (certificationDtos == null)
            return null;
        List<Certification> certifications = buildCertificationsFrom(certificationDtos, profile);
        certificationRepository.saveAll(certifications);

        return certifications;
    }

    public List<Url> updateUrls(List<String> urlDtos, Profile profile) {
        urlRepository.deleteAllByProfileId(profile.getId());
        if (urlDtos == null)
            return null;
        List<Url> urls = buildUrlsFrom(urlDtos, profile);
        urlRepository.saveAll(urls);

        return urls;
    }

    @Transactional
    public Long deleteUserProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User"));
        Long userId = user.getId();
        Optional<Profile> optionalProfile = profileRepository.findByUserId(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            departmentRepository.deleteAllByProfileId(profile.getId());
            certificationRepository.deleteAllByProfileId(profile.getId());
            urlRepository.deleteAllByProfileId(profile.getId());
            profileRepository.delete(profile);
        }
        userRepository.delete(user);

        return userId;
    }
}