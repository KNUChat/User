package KNUChat.User.domain.repository;

import KNUChat.User.domain.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findAllByProfileId(Long profileId);

    void deleteAllByProfileId(Long profileId);
}
