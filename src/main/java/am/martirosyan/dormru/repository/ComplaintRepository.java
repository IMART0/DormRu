package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.Complaint;
import am.martirosyan.dormru.model.ComplaintStatus;
import am.martirosyan.dormru.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);

    List<Complaint> findByStatus(ComplaintStatus status);
}
