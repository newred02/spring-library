package spring.library.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOfPurchaseRequestForUpdate {

    private Long memberId;
    private Long bookId;
    private String title;
    private String author;
    private String requestDate;
    private String dateOfProcess;
    private String processResult;

    public RequestOfPurchaseRequestForUpdate(Long memberId, Long bookId ,String title, String author, String requestDate, String dateOfProcess, String processResult) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.requestDate = requestDate;
        this.dateOfProcess = dateOfProcess;
        this.processResult = processResult;
    }
}
