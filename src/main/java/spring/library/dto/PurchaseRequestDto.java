package spring.library.dto;

import lombok.*;
import spring.library.domain.LoanAndReturn;
import spring.library.domain.PurchaseRequest;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDto {

    private Long bookId;
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private Long purchaseRequestCount;
    private String requestDate;
    private String dateOfProcess;
    private String processResult;

    public static PurchaseRequestDto from(PurchaseRequest purchaseRequest) {
        return PurchaseRequestDto.builder()
                .bookId(purchaseRequest.getBookId())
                .memberId(purchaseRequest.getMember().getId())
                .title(purchaseRequest.getTitle())
                .author(purchaseRequest.getAuthor())
                .publisher(purchaseRequest.getPublisher())
                .publicationYear(purchaseRequest.getPublicationYear())
                .purchaseRequestCount(purchaseRequest.getPurchaseRequestCount())
                .requestDate(purchaseRequest.getRequestDate())
                .dateOfProcess(purchaseRequest.getDateOfProcess())
                .processResult(purchaseRequest.getProcessResult())
                .build();
    }

    public static List<PurchaseRequestDto> from(List<PurchaseRequest> purchaseRequestList) {
        return purchaseRequestList.stream().map(PurchaseRequestDto::from).collect(Collectors.toList());
    }
}
