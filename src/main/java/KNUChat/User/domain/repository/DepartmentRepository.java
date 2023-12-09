package KNUChat.User.domain.repository;

import KNUChat.User.domain.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllByProfileId(Long profileId);

    Page<Department> findByMajor(String majoer, Pageable pageable);
}
