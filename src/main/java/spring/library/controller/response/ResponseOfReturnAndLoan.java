package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.LoanAndReturnDto;

@Getter
@Setter
@Builder
public class ResponseOfReturnAndLoan {

    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String loanDate;
    private String dueDate;
    private Long renewalCount;

    public static ResponseOfReturnAndLoan from(LoanAndReturnDto loanAndReturnDto) {
        return ResponseOfReturnAndLoan.builder()
                .bookId(loanAndReturnDto.getBookId())
                .bookTitle(loanAndReturnDto.getBookTitle())
                .bookAuthor(loanAndReturnDto.getBookAuthor())
                .loanDate(loanAndReturnDto.getLoanDate().toString())
                .dueDate(loanAndReturnDto.getDueDate().toString())
                .renewalCount(loanAndReturnDto.getRenewalCount())
                .build();
    }
}
