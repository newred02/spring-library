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

    private Long idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static ResponseOfMember from(MemberDto memberDto) {
        return ResponseOfMember.builder()
                .idNumber(memberDto.getIdNumber())
                .name(memberDto.getName())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
