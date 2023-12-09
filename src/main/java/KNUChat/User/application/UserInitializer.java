package KNUChat.User.application;

import KNUChat.User.dto.CertificationDto;
import KNUChat.User.dto.DepartmentDto;
import KNUChat.User.dto.ProfileDto;
import KNUChat.User.dto.UserDto;
import KNUChat.User.dto.request.UserCreateRequest;
import KNUChat.User.dto.request.UserProfileCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInitializer implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) {

        UserProfileCreateRequest request;

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(1L, "김이나", "FEMALE", "kim111@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 4, "202003", "202402", "대한민국의 작사가"),
                new ArrayList<>(List.of(new DepartmentDto("예술대학", null, "미술학과", "BASIC"), new DepartmentDto("예술대학", null, "음악학과", "MINOR"))),
                new ArrayList<>(List.of(new CertificationDto("TOEIC", "980", "2023.03.18", "2028.03.17"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(2L, "박진수", "MALE", "park2@knu.ac.kr"),
                new ProfileDto((short) 19L, "GRADUATION", (short) 0, "201903", "202302", "농업분야 전문가"),
                new ArrayList<>(List.of(new DepartmentDto("농업생명과학대학", null, "원예과학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("한국농림수산식품인증원 자격증", null, "2022.06.15", "2027.06.14"))),
                null
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(3L, "이영호", "MALE", "lee321@knu.ac.kr"),
                new ProfileDto((short) 21L, "ATTENDING", (short) 5, "202101", "202502", "대한민국의 소방관"),
                new ArrayList<>(List.of(new DepartmentDto("IT대학", "전자공학부", null, "BASIC"), new DepartmentDto("IT대학", "컴퓨터학부", null, "PLURAL"))),
                new ArrayList<>(List.of(new CertificationDto("소방안전관리자 자격증", null,"2021.08.20", "2026.08.19"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(4L, "정미경", "FEMALE", "jung4@knu.ac.kr"),
                new ProfileDto((short) 19L, "GRADUATION", (short) 0, "201901", "202309", "글로벌 마케팅 전문가"),
                new ArrayList<>(List.of(new DepartmentDto("경상대학", "경영학부", null, "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("Google Ads 인증", null,"2020.12.10", "2023.12.09"))),
                new ArrayList<>(List.of("https://www.naver.com", "https://www.daum.net"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(5L, "홍길동", "MALE", "hong5@knu.ac.kr"),
                new ProfileDto((short) 18L, "GRADUATION", (short) 0, "201803", "202202", "IT 전문가"),
                new ArrayList<>(List.of(new DepartmentDto("IT대학", "컴퓨터학부", "심화컴퓨터공학전공", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("정보처리기사 자격증", null,"2019.05.28", "2022.05.27"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(6L, "이지영", "FEMALE", "lee6@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 4, "202003", "202403", "대한민국의 디자이너"),
                new ArrayList<>(List.of(new DepartmentDto("예술대학", null, "디자인학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("Adobe Photoshop 인증", null,"2021.02.15", "2024.02.14"))),
                new ArrayList<>(List.of("https://www.naver.com", "https://www.daum.net"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(7L, "김철수", "MALE", "kim7@knu.ac.kr"),
                new ProfileDto((short) 18L, "GRADUATION", (short) 0, "201803", "202202", "금융 전문가"),
                new ArrayList<>(List.of(new DepartmentDto("경상대학", "경영학부", null, "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("CFA", "Level 1", "2020.09.25", "2024.09.24"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(8L, "이나영", "FEMALE", "lee8@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 4, "202003", "202402", "대한민국의 영화감독"),
                new ArrayList<>(List.of(new DepartmentDto("예술대학", null, "미술학과", "BASIC"))),
                null,
                new ArrayList<>(List.of("https://www.naver.com", "https://www.daum.net"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(9L, "정호진", "MALE", "jung9@knu.ac.kr"),
                new ProfileDto((short) 15L, "GRADUATION", (short) 0, "201503", "201902", "의사"),
                new ArrayList<>(List.of(new DepartmentDto("의과대학", null, "의학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("의사 면허증", null, "2020.07.20", "2030.07.19"))),
                new ArrayList<>(List.of("https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(10L, "이영자", "FEMALE", "lee10@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 4, "202003", "202402", "대한민국의 변호사"),
                new ArrayList<>(List.of(new DepartmentDto("간호대학", null, "간호학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("간호사 자격증", null, "2022.04.05", "2027.04.04"))),
                new ArrayList<>(List.of("https://www.naver.com", "https://www.daum.net"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(11L, "이영희", "FEMALE", "lee88@knu.ac.kr"),
                new ProfileDto((short) 16L, "GRADUATION", (short) 0, "201601", "202002", "마케터"),
                new ArrayList<>(List.of(new DepartmentDto("경상대학", "경영학부", null, "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("TOEFL", "110", "2023.02.18", "2028.02.17"))),
                new ArrayList<>(List.of("https://www.twitter.com", "https://www.linkedin.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(12L, "김철수", "MALE", "kim123@knu.ac.kr"),
                new ProfileDto((short) 19L, "ATTENDING", (short) 3, "201901", "202502", "디자이너"),
                new ArrayList<>(List.of(new DepartmentDto("예술대학", null, "미술학과", "BASIC"), new DepartmentDto("예술대학", null, "디자인학과", "PLURAL"))),
                new ArrayList<>(List.of(new CertificationDto("TOEFL", "120", "2023.02.18", "2028.02.15"))),
                new ArrayList<>(List.of("https://www.facebook.com", "https://www.instagram.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(13L, "이지은", "FEMALE", "lee66@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 3, "202003", "202402", "대하민국의 국어 교사"),
                new ArrayList<>(List.of(new DepartmentDto("사범대학", null, "국어교육학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("교원자격증", null, "2023.02.18", "2028.02.17"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(14L, "김현수", "MALE", "kim53@knu.ac.kr"),
                new ProfileDto((short) 18L, "GRADUATION", (short) 0, "201803", "202202", "소프트웨어 개발자"),
                new ArrayList<>(List.of(new DepartmentDto("IT대학", "컴퓨터학부", "심화컴퓨터공학전공", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("ORACLE JAVA 자격증", null, "2022.11.12", "2027.11.11"), new CertificationDto("정보처리기사 자격증", null,"2020.06.22", "2024.06.21"))),
                new ArrayList<>(List.of("https://www.github.com/kim5", "https://www.linkedin.com/in/kim5"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(15L, "정수진", "FEMALE", "jung8@knu.ac.kr"),
                new ProfileDto((short) 16L, "GRADUATION", (short) 0, "201603", "202002", "패션 디자이너"),
                new ArrayList<>(List.of(new DepartmentDto("예술대학", null, "디자인학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("패션디자인자격증", null, "2019.09.20", "2024.09.19"))),
                new ArrayList<>(List.of("https://www.instagram.com/jung8_design", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(16L, "박지훈", "MALE", "park76@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 3, "202003", "202402", "의학 전공 학생"),
                new ArrayList<>(List.of(new DepartmentDto("의과대학", null, "의학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("생명과학자격증", null, "2021.05.10", "2026.05.09"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(17L, "조은영", "FEMALE", "cho9@knu.ac.kr"),
                new ProfileDto((short) 15L, "GRADUATION", (short) 0, "201503", "202002", "프리랜서 기자"),
                new ArrayList<>(List.of(new DepartmentDto("사회과학대학", null, "미디어커뮤니케이션학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("언론사 기자 자격증", null, "2020.01.15", "2025.01.14"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(18L, "이민호", "MALE", "lee10@knu.ac.kr"),
                new ProfileDto((short) 20L, "ATTENDING", (short) 3, "202003", "202502", null),
                new ArrayList<>(List.of(new DepartmentDto("IT대학", "컴퓨터학부", "글로벌SW융합전공", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("TOEFL", "150", "2023.02.18", "2028.02.17"))),
                new ArrayList<>(List.of("https://www.google.com", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(19L, "김나영", "FEMALE", "kim11@knu.ac.kr"),
                new ProfileDto((short) 12L, "GRADUATION", (short) 0, "201202", "201602", "영화 감독"),
                new ArrayList<>(List.of(new DepartmentDto("예술대학", null, "음악학과", "BASIC"), new DepartmentDto("예술대학", null, "미술학과", "MINOR"))),
                new ArrayList<>(List.of(new CertificationDto("영화제작자격증", null, "2017.07.20", "2022.07.19"))),
                new ArrayList<>(List.of("https://www.instagram.com/kim11_movies", "https://www.naver.com"))
        );
        userService.createUserProfile(request);

        userService.createUser(new UserCreateRequest("temp"));
        request = new UserProfileCreateRequest(
                new UserDto(20L, "박현진", "MALE", "park12@knu.ac.kr"),
                new ProfileDto((short) 22L, "LEAVE_OF_ABSENCE", (short) 3, "202203", "202702", null),
                new ArrayList<>(List.of(new DepartmentDto("사회과학대학", null, "정치외교학과", "BASIC"))),
                new ArrayList<>(List.of(new CertificationDto("TOEFL", "110", "2023.02.18", "2028.02.17"))),
                null
        );
        userService.createUserProfile(request);


    }
}
