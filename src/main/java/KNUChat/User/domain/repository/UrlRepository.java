package KNUChat.User.domain.repository;

import KNUChat.User.domain.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findAllByProfileId(Long profileId);

    void deleteAllByProfileId(Long profileId);
}
