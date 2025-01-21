package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.RequestOfPurchaseRequest;
import spring.library.controller.request.RequestOfPurchaseRequestForUpdate;
import spring.library.controller.response.ResponseOfPurchaseRequest;
import spring.library.controller.response.ResponseOfPurchaseRequestWithMemberId;
import spring.library.domain.PurchaseRequest;
import spring.library.dto.PurchaseRequestDto;
import spring.library.service.PurchaseRequestService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PurchaseRequestController {

    private final PurchaseRequestService purchaseRequestService;

    @PostMapping("/books/purchase-requests")
    public ResponseEntity<String> addPurchaseRequest(@RequestBody RequestOfPurchaseRequest request) {
        String reply = purchaseRequestService.createPurchaseRequest(request);
        return ResponseEntity.ok().body(reply);
    }

    @GetMapping("/books/purchase-requests")
    public ResponseEntity<List<ResponseOfPurchaseRequest>> getPurchaseRequest(@RequestParam Long memberId) {
        System.out.println("memberId: " + memberId);
        List<ResponseOfPurchaseRequest> responseOfPurchaseRequests = purchaseRequestService.findPurchaseRequestList(memberId).stream().map(ResponseOfPurchaseRequest::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(responseOfPurchaseRequests);
    }

    @PutMapping("/books/purchase-requests")
    public ResponseEntity<ResponseOfPurchaseRequestWithMemberId> updatePurchaseRequest(@RequestBody RequestOfPurchaseRequestForUpdate request) {
        PurchaseRequestDto purchaseRequestDto = purchaseRequestService.updatePurchaseRequestProcess(request);
        return ResponseEntity.ok().body(ResponseOfPurchaseRequestWithMemberId.from(purchaseRequestDto));
    }

}
