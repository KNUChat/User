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

    @Transactional(readOnly = true)
    public UserProfileResponse findUserProfileById(String userId) {
        Profile profile = profileRepository.getReferenceById(userId);

        return new UserProfileResponse(
                UserDto.from(userRepository.getReferenceById(userId)),
                ProfileDto.from(profile),
                getDepartmentDtos(profile.getId()),
                getCertificationDtos(profile.getId()),
                getUrlDtos(profile.getId())
        );
    }

    public List<DepartmentDto> getDepartmentDtos(String profileId) {
        List<DepartmentDto> departmentDtos = departmentRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(DepartmentDto::from)
                .collect(Collectors.toUnmodifiableList());

        return departmentDtos;
    }

    public List<CertificationDto> getCertificationDtos(String profileId) {
        List<CertificationDto> certificationDtos = certificationRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(CertificationDto::from)
                .collect(Collectors.toUnmodifiableList());

        return certificationDtos;
    }

    public List<UrlDto> getUrlDtos(String profileId) {
        List<UrlDto> urlDtos = urlRepository
                .findAllByProfileId(profileId)
                .stream()
                .map(UrlDto::from)
                .collect(Collectors.toUnmodifiableList());

        return urlDtos;
    }
}
