package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.LoanAndReturn;
import spring.library.dto.BookDto;
import spring.library.dto.LoanAndReturnDto;

@Getter
@Setter
@Builder
public class ResponseOfReturnAndLoanHistory {

    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String loanDate;
    private String dueDate;
    private Long renewalCount;
    private Boolean isReturned;

    public static ResponseOfReturnAndLoanHistory from(LoanAndReturnDto loanAndReturnDto) {
        return ResponseOfReturnAndLoanHistory.builder()
                .bookId(loanAndReturnDto.getBookId())
                .bookTitle(loanAndReturnDto.getBookTitle())
                .bookAuthor(loanAndReturnDto.getBookAuthor())
                .loanDate(loanAndReturnDto.getLoanDate().toString())
                .dueDate(loanAndReturnDto.getDueDate().toString())
                .renewalCount(loanAndReturnDto.getRenewalCount())
                .isReturned(loanAndReturnDto.getIsReturned())
                .build();
    }
}
