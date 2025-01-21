package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.PurchaseRequestDto;

@Getter
@Setter
@Builder
public class ResponseOfPurchaseRequest {
    private Long bookId;
    private String title;
    private String author;
    private String requestDate;
    private String dateOfProcess;
    private String processResult;

    public static ResponseOfPurchaseRequest from(PurchaseRequestDto purchaseRequestDto) {
        return ResponseOfPurchaseRequest.builder()
                .bookId(purchaseRequestDto.getBookId())
                .title(purchaseRequestDto.getTitle())
                .author(purchaseRequestDto.getAuthor())
                .requestDate(purchaseRequestDto.getRequestDate())
                .dateOfProcess(purchaseRequestDto.getDateOfProcess())
                .processResult(purchaseRequestDto.getProcessResult())
                .build();
    }
}
