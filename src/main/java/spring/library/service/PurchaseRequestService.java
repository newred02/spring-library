package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.request.RequestOfPurchaseRequest;
import spring.library.controller.request.RequestOfPurchaseRequestForUpdate;
import spring.library.controller.response.ResponseOfPurchaseRequest;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.domain.PurchaseRequest;
import spring.library.dto.PurchaseRequestDto;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseRequestService {

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String createPurchaseRequest(RequestOfPurchaseRequest request) {
        String reply = null;
        Member requestMember = memberRepository.findById(request.getMemberId()).orElse(null);
        Book book = bookRepository.findByTitleAndAuthorAndPublisher(request.getTitle(), request.getAuthor(), request.getPublisher());
        if(book != null) {
            throw new RuntimeException("Book already exists");
        }
        PurchaseRequest purchaseRequest = purchaseRequestRepository.findByTitleAndAuthorAndPublisher(request.getTitle(), request.getAuthor(), request.getPublisher());
        if(purchaseRequest != null) {
            purchaseRequest.setPurchaseRequestCount(purchaseRequest.getPurchaseRequestCount() + 1);
            reply = "Purchase request already exists, so we just count up the request count";
            return reply;
        }else{
            purchaseRequestRepository.save(PurchaseRequest.from(request, requestMember));
            reply = "Purchase request created";
        }
        return reply;
    }

    public List<PurchaseRequestDto> findPurchaseRequestList(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("Member not found"));
        List<PurchaseRequestDto> purchaseRequestDtoList = null;
        if(member.getFeature().equals("관리자")){
            purchaseRequestDtoList = purchaseRequestRepository.findAll().stream().map(PurchaseRequestDto::from).collect(Collectors.toList());
        }else{
            purchaseRequestDtoList = purchaseRequestRepository.findByMemberId(memberId).stream().map(PurchaseRequestDto::from).collect(Collectors.toList());
        }
        return purchaseRequestDtoList;
    }

    @Transactional
    public PurchaseRequestDto updatePurchaseRequestProcess(RequestOfPurchaseRequestForUpdate request) {
        Member member = memberRepository.findById(request.getMemberId()).orElse(null);
        PurchaseRequest purchaseRequest = null;
        if(member != null && member.getFeature().equals("관리자")){
            purchaseRequest = purchaseRequestRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
            purchaseRequest.setDateOfProcess(request.getDateOfProcess());
            purchaseRequest.setProcessResult(request.getProcessResult());
        }else{
            throw new RuntimeException("관리자만이 정보를 수정할 수 있습니다.");
        }

        return PurchaseRequestDto.from(purchaseRequest);
    }

}
