package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.RequestOfBook;
import spring.library.controller.request.RequestOfLoanAndReturn;
import spring.library.dto.LoanAndReturnDto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanAndReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private Long renewalCount;
    private Boolean isReturned;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public static LoanAndReturn from(Book requestBook, Member requestMember) {
        if(requestMember.getFeature().equals("학생")){
            return LoanAndReturn.builder()
                    .member(requestMember)
                    .book(requestBook)
                    .loanDate(LocalDate.now())
                    .dueDate(LocalDate.now().plusDays(10))
                    .renewalCount(Long.valueOf(0))
                    .isReturned(false)
                    .build();
        } else if (requestMember.getFeature().equals("교수")) {
            return LoanAndReturn.builder()
                    .member(requestMember)
                    .book(requestBook)
                    .loanDate(LocalDate.now())
                    .dueDate(LocalDate.now().plusDays(30))
                    .renewalCount(Long.valueOf(0))
                    .isReturned(false)
                    .build();
        }else{
            return LoanAndReturn.builder()
                    .member(requestMember)
                    .book(requestBook)
                    .loanDate(LocalDate.now())
                    .dueDate(LocalDate.now().plusDays(110813))
                    .renewalCount(Long.valueOf(0))
                    .isReturned(false)
                    .build();
        }
    }


}
