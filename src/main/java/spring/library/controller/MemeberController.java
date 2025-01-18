package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.RequestOfMember;
import spring.library.controller.response.ResponseOfMember;
import spring.library.controller.response.ResponseOfMemberWithId;
import spring.library.dto.MemberDto;
import spring.library.repository.MemberRepository;
import spring.library.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemeberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<ResponseOfMemberWithId>> getMembers() {
        List<ResponseOfMemberWithId> members = memberService.findAll().stream().map(ResponseOfMemberWithId::from).toList();
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<ResponseOfMember> getMemberWithId(@PathVariable Long id) {
        MemberDto memberDto = memberService.findMemberById(id);
        return ResponseEntity.ok().body(ResponseOfMember.from(memberDto));
    }

    @PostMapping("/members")
    public ResponseEntity<ResponseOfMember> createMember(@RequestBody RequestOfMember request) {
        MemberDto memberDto = memberService.createMember(request);
        return ResponseEntity.ok().body(ResponseOfMember.from(memberDto));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok().body("Delete Success!");
    }

    @PatchMapping("/members/{id}")
    public ResponseEntity<ResponseOfMember> updateMember(@PathVariable Long id, @RequestBody RequestOfMember request) {
        MemberDto memberDto = memberService.updateMember(id, request);
        return ResponseEntity.ok().body(ResponseOfMember.from(memberDto));
    }

}
