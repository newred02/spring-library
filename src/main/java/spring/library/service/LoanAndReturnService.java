package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import spring.library.controller.request.RequestOfLoanAndReturn;
import spring.library.domain.Book;
import spring.library.domain.LoanAndReturn;
import spring.library.domain.Member;
import spring.library.dto.LoanAndReturnDto;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanAndReturnRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanAndReturnService {

    private final LoanAndReturnRepository loanAndReturnRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ResourceUrlProvider resourceUrlProvider;

    public List<LoanAndReturnDto> findLoanBookList(Long memberId) {
        List<LoanAndReturnDto> loanAndReturnDtoList = loanAndReturnRepository.findByMember_IdAndIsReturnedIsFalse(memberId).stream().map(LoanAndReturnDto::from).toList();
        return loanAndReturnDtoList;
    }

    public List<LoanAndReturnDto> findLoanAndReturnHistory(Long memberId) {
        List<LoanAndReturnDto> loanAndReturnDtoList = loanAndReturnRepository.findByMember_Id(memberId).stream().map(LoanAndReturnDto::from).toList();
        return loanAndReturnDtoList;
    }

    public LoanAndReturnDto loanBook(Long bookId, RequestOfLoanAndReturn request) {
        Book requestBook = bookRepository.findById(bookId).orElse(null);
        Member requestMember = memberRepository.findById(request.getMemberId()).orElse(null);
        LoanAndReturnDto loanAndReturnDto = null;
        Boolean canLoan = checkPossibilityToLoanBook(requestMember);
        if(canLoan) {
            if(requestBook.getStatus().equals("대출가능")){
                requestBook.setStatus("대출중");
                loanAndReturnDto = LoanAndReturnDto.from(loanAndReturnRepository.save(LoanAndReturn.from(requestBook, requestMember)));
            }else{
                throw new RuntimeException("이미 대출 중이거나 삭제되어 대출 불가한 책입니다.");
            }
        }else{
            throw new RuntimeException("잔여 대출 수가 없습니다.");
        }
        return loanAndReturnDto;
    }

    public Boolean checkPossibilityToLoanBook(Member requestMember) {
        Long numberOfLoanBooks = numberOfLoanBooks = numberOfLoanBook(requestMember.getId());
        if(requestMember.getFeature().equals("학생") && (numberOfLoanBooks < 10)){
            return true;
        } else if (requestMember.getFeature().equals("교수") && (numberOfLoanBooks < 20)) {
            return true;
        } else if(requestMember.getFeature().equals("관리자") && (numberOfLoanBooks < 100)) {
            return true;
        }else {
            return false;
        }
    }

    public Long numberOfLoanBook(Long memberId) {
        List<LoanAndReturnDto> loanAndReturnDtoList = findLoanBookList(memberId);
        return (long)loanAndReturnDtoList.size();
    }

    @Transactional
    public LoanAndReturnDto returnLoanBook(Long bookId, RequestOfLoanAndReturn request) {
        LoanAndReturn loanAndReturn = loanAndReturnRepository.findByMember_IdAndBook_IdAndIsReturnedIsFalse(request.getMemberId(), bookId);
        loanAndReturn.setIsReturned(true);
        loanAndReturn.getBook().setStatus("대출가능");

        return LoanAndReturnDto.from(loanAndReturn);
    }

    @Transactional
    public LoanAndReturnDto renewalDueDate(Long bookId, RequestOfLoanAndReturn request) {
        LoanAndReturn loanAndReturn = loanAndReturnRepository.findByMember_IdAndBook_IdAndIsReturnedIsFalse(request.getMemberId(), bookId);
        Boolean renewalCountIsZero = checkRenewalCount(loanAndReturn);
        Boolean dueDateIsNow = checkDueDate(loanAndReturn);
        if(renewalCountIsZero) {
            if(dueDateIsNow) {
                LocalDate renewalDueDate = loanAndReturn.getDueDate().plusDays(5);
                loanAndReturn.setDueDate(renewalDueDate);
                loanAndReturn.setRenewalCount(1L);
            }else{
                throw new RuntimeException("대출연장은 반납일 당일에만 가능합니다.");
            }
        }else{
            throw new RuntimeException("대출연장은 1회만 가능합니다.");
        }
        return LoanAndReturnDto.from(loanAndReturn);
    }

    public Boolean checkDueDate(LoanAndReturn loanAndReturn) {
        if(loanAndReturn.getDueDate().isEqual(LocalDate.now())) {
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkRenewalCount(LoanAndReturn loanAndReturn) {
        if(loanAndReturn.getRenewalCount() == 0L){
            return true;
        }else{
            return false;
        }
    }

}
