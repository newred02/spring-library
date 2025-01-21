package spring.library.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestOfLoanAndReturn {

    private Long memberId;

    public RequestOfLoanAndReturn(Long memberId) {
        this.memberId = memberId;
    }
}
