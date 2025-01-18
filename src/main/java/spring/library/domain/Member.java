package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.RequestOfMember;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static Member from(RequestOfMember request) {
        return Member.builder()
                .idNumber(request.getIdNumber())
                .name(request.getName())
                .feature(request.getFeature())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public void update(MemberDto memberDto) {
        this.idNumber = memberDto.getIdNumber();
        this.name = memberDto.getName();
        this.feature = memberDto.getFeature();
        this.email = memberDto.getEmail();
        this.phoneNumber = memberDto.getPhoneNumber();
    }

}
