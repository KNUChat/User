package KNUChat.User.repository;

import KNUChat.User.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    Profile getProfileByUserId(String userID);
}
