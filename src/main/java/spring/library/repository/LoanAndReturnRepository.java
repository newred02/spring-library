package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.LoanAndReturn;
import spring.library.dto.LoanAndReturnDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanAndReturnRepository extends JpaRepository<LoanAndReturn, Long> {

    Optional<LoanAndReturn> findById(Long id);

    List<LoanAndReturn> findByMember_Id(Long memberId);
    List<LoanAndReturn> findByMember_IdAndIsReturnedIsFalse(Long memberId);
    LoanAndReturn findByMember_IdAndBook_Id(Long memberId, Long bookId);
    LoanAndReturn findByBook_IdAndIsReturnedIsFalse(Long bookId);

    LoanAndReturn findByMember_IdAndBook_IdAndIsReturnedIsFalse(Long memberId, Long bookId);
}
