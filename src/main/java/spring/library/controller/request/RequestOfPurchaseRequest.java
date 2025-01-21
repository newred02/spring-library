package spring.library.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOfPurchaseRequest {

    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private Long purchaseRequestCount;

    public RequestOfPurchaseRequest(Long memberId, String title, String author, String publisher, Long publicationYear , Long purchaseRequestCount) {
        this.memberId = memberId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.purchaseRequestCount = purchaseRequestCount;
    }
}
