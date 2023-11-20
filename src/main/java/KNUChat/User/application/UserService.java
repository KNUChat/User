package KNUChat.User.application;

import KNUChat.User.entity.*;
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

    @Transactional
    public boolean createUserProfile(UserProfileRequest request) {
        userRepository.save(buildUserFrom(request.getUserDto()));
        Profile profile = buildProfileFrom(request.getProfileDto());
        profileRepository.save(profile);
        departmentRepository.saveAll(buildDepartmentsFrom(request.getDepartmentDtos(), profile));
        certificationRepository.saveAll(buildCertificationFrom(request.getCertificationDtos(), profile));
        urlRepository.saveAll(buildUrlDtoFrom(request.getUrlDtos(), profile));

        return true;
    }

    public User buildUserFrom(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .gender(Gender.valueOf(userDto.getGender()))
                .email(userDto.getEmail())
                .build();

        return user;
    }

    public Profile buildProfileFrom(ProfileDto profileDto) {
        Profile profile = Profile.builder()
                .stdNum(profileDto.getStdNum())
                .academicStatus(profileDto.getAcademicStatus())
                .grade(profileDto.getGrade())
                .admissionDate(profileDto.getAdmissionDate())
                .graduateDate(profileDto.getGraduateDate())
                .introduction(profileDto.getIntroduction())
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
    public UserProfileResponse findUserProfileById(Long userId) {
        Profile profile = profileRepository.getProfileByUserId(userId);

        return new UserProfileResponse(
                UserDto.from(userRepository.getReferenceById(userId)),
                ProfileDto.from(profile),
                getDepartmentDtos(profile.getId()),
                getCertificationDtos(profile.getId()),
                getUrlDtos(profile.getId())
        );
    }

    public List<DepartmentDto> getDepartmentDtos(Long profileId) {
        List<DepartmentDto> departmentDtos = departmentRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(DepartmentDto::from)
                .collect(Collectors.toUnmodifiableList());

        return departmentDtos;
    }

    public List<CertificationDto> getCertificationDtos(Long profileId) {
        List<CertificationDto> certificationDtos = certificationRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(CertificationDto::from)
                .collect(Collectors.toUnmodifiableList());

        return certificationDtos;
    }

    public List<UrlDto> getUrlDtos(Long profileId) {
        List<UrlDto> urlDtos = urlRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(UrlDto::from)
                .collect(Collectors.toUnmodifiableList());

        return urlDtos;
    }
}
