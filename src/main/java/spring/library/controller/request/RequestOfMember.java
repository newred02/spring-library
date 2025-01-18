package spring.library.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOfMember {

    private Long idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public RequestOfMember(Long idNumber, String name, String feature, String email, String phoneNumber) {
        this.idNumber = idNumber;
        this.name = name;
        this.feature = feature;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
