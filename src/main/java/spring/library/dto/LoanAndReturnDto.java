package spring.library.dto;

import lombok.*;
import spring.library.controller.request.RequestOfLoanAndReturn;
import spring.library.domain.Book;
import spring.library.domain.LoanAndReturn;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanAndReturnDto {

    private Long id;
    private Long memberId;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookStatus;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private Long renewalCount;
    private Boolean isReturned;

    public static LoanAndReturnDto from(LoanAndReturn loanAndReturn) {
        return LoanAndReturnDto.builder()
                .id(loanAndReturn.getId())
                .memberId(loanAndReturn.getMember().getId())
                .bookId(loanAndReturn.getBook().getId())
                .bookTitle(loanAndReturn.getBook().getTitle())
                .bookAuthor(loanAndReturn.getBook().getAuthor())
                .loanDate(loanAndReturn.getLoanDate())
                .dueDate(loanAndReturn.getDueDate())
                .renewalCount(loanAndReturn.getRenewalCount())
                .isReturned(loanAndReturn.getIsReturned())
                .build();
    }

    public static List<LoanAndReturnDto> from(List<LoanAndReturn> loanAndReturnList) {
        return loanAndReturnList.stream().map(LoanAndReturnDto::from).collect(Collectors.toList());
    }
}
