package spring.library.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.controller.request.RequestOfMember;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberDto> findAll(){
        List<MemberDto> members = memberRepository.findAll().stream().map(MemberDto::from).toList();
        return members;
    }

    public MemberDto findMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        return MemberDto.from(member);
    }

    public MemberDto createMember(RequestOfMember request) {
        MemberDto memberDto = MemberDto.from(memberRepository.save(Member.from(request)));
        return memberDto;
    }

    public ResponseEntity<Void> deleteMemberById(Long id) {
        memberRepository.findById(id).orElseThrow(()-> new RuntimeException("Member not found"));
        memberRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public MemberDto updateMember(Long id, RequestOfMember request) {
        Member member = memberRepository.findById(id).orElse(null);
        member.update(MemberDto.from(request));
        return MemberDto.from(member);
    }
}
