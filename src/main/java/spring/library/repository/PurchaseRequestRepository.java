package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;
import spring.library.domain.PurchaseRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
   Optional<PurchaseRequest> findByBookId(Long bookId);
   PurchaseRequest findByTitleAndAuthorAndPublisher(String title, String author, String publisher);
   List<PurchaseRequest> findByMemberId(Long memberId);

   PurchaseRequest findByTitleAndAuthor(String title, String author);
}
