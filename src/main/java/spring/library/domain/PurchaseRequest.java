package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.RequestOfPurchaseRequest;
import spring.library.dto.PurchaseRequestDto;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private Long purchaseRequestCount;
    private String requestDate;
    private String dateOfProcess;
    private String processResult;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public static PurchaseRequest from(RequestOfPurchaseRequest request, Member requestMember) {
        return PurchaseRequest.builder()
                .member(requestMember)
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .purchaseRequestCount(request.getPurchaseRequestCount())
                .requestDate(LocalDate.now().toString())
                .dateOfProcess("")
                .processResult("신청")
                .build();
    }

}
