package spring.library.dto;

import lombok.*;
import spring.library.controller.request.RequestOfMember;
import spring.library.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private Long idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .idNumber(member.getIdNumber())
                .name(member.getName())
                .feature(member.getFeature())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static MemberDto from(RequestOfMember request) {
        return MemberDto.builder()
                .idNumber(request.getIdNumber())
                .name(request.getName())
                .feature(request.getFeature())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public static List<MemberDto> from(List<Member> members) {
        return members.stream().map(MemberDto::from).collect(Collectors.toList());
    }

}
