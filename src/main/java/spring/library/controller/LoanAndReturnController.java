package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.RequestOfLoanAndReturn;
import spring.library.controller.response.ResponseOfReturnAndLoan;
import spring.library.controller.response.ResponseOfReturnAndLoanHistory;
import spring.library.dto.LoanAndReturnDto;
import spring.library.service.LoanAndReturnService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanAndReturnController {

    private final LoanAndReturnService loanAndReturnService;

    @GetMapping("/books/checkout")
    public ResponseEntity<List<ResponseOfReturnAndLoan>> checkout(@RequestParam Long memberId) {
        List<ResponseOfReturnAndLoan> responseOfReturnAndLoanList = loanAndReturnService.findLoanBookList(memberId).stream().map(ResponseOfReturnAndLoan::from).toList();
        return ResponseEntity.ok().body(responseOfReturnAndLoanList);
    }

    @GetMapping("/books/history")
    public ResponseEntity<List<ResponseOfReturnAndLoanHistory>> findHistory(@RequestParam Long memberId) {
        List<ResponseOfReturnAndLoanHistory> responseOfReturnAndLoanHistories = loanAndReturnService.findLoanAndReturnHistory(memberId).stream().map(ResponseOfReturnAndLoanHistory::from).toList();
        return ResponseEntity.ok().body(responseOfReturnAndLoanHistories);
    }

    @PostMapping("/books/{bookId}/checkout")
    public ResponseEntity<ResponseOfReturnAndLoanHistory> loanBook(@PathVariable Long bookId ,@RequestBody RequestOfLoanAndReturn request) {
        ResponseOfReturnAndLoanHistory responseOfReturnAndLoan = ResponseOfReturnAndLoanHistory.from(loanAndReturnService.loanBook(bookId, request));
        return ResponseEntity.ok().body(responseOfReturnAndLoan);
    }

    @PutMapping("/books/{bookId}/return")
    public ResponseEntity<ResponseOfReturnAndLoanHistory> returnBook(@PathVariable Long bookId, @RequestBody RequestOfLoanAndReturn request) {
        LoanAndReturnDto loanAndReturnDto = loanAndReturnService.returnLoanBook(bookId, request);
        return ResponseEntity.ok().body(ResponseOfReturnAndLoanHistory.from(loanAndReturnDto));
    }

    @PutMapping("/books/{bookId}/renewal")
    public ResponseEntity<ResponseOfReturnAndLoanHistory> renewalDueDate(@PathVariable Long bookId, @RequestBody RequestOfLoanAndReturn request) {
        LoanAndReturnDto loanAndReturnDto = loanAndReturnService.renewalDueDate(bookId, request);
        return ResponseEntity.ok().body(ResponseOfReturnAndLoanHistory.from(loanAndReturnDto));
    }


}
