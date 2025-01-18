package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.MemberDto;

@Builder
@Getter
@Setter
public class ResponseOfMemberWithId {

    private Long id;
    private Long idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static ResponseOfMemberWithId from(MemberDto memberDto) {
        return ResponseOfMemberWithId.builder()
                .id(memberDto.getId())
                .idNumber(memberDto.getIdNumber())
                .name(memberDto.getName())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
