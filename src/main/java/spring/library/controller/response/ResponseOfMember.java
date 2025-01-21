package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.controller.request.RequestOfMember;
import spring.library.dto.MemberDto;

@Builder
@Getter
@Setter
public class ResponseOfMember {

    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static ResponseOfMember from(MemberDto memberDto) {
        return ResponseOfMember.builder()
                .name(memberDto.getName())
                .idNumber(memberDto.getIdNumber())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
