package chap07;

import lombok.Getter;

@Getter
public class AutoDebitReq {
    private String userId;
    private String cardNumber;

    public AutoDebitReq(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }

}
