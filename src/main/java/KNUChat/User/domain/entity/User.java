package KNUChat.User.domain.entity;

import KNUChat.User.domain.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NonNull
    @Column(name = "email")
    private String email;

    @Builder
    public User(String name, Gender gender, String email) {
        this.name = name;
        this.gender = gender;
        this.email = email;
    }

    public User update(UserDto userDto) {
        if (userDto.getName() != null) this.name = userDto.getName();
        if (userDto.getGender() != null) this.gender = Gender.valueOf(userDto.getGender());
        if (userDto.getEmail() != null) this.email = userDto.getEmail();

        return this;
    }
}
