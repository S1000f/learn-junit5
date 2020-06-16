package chap07;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AutoDebitInfo {
    private String userId;
    private String cardNumber;
    private LocalDateTime registTime;
    private LocalDateTime updateTime;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime registTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registTime = registTime;
        this.updateTime = registTime;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        this.updateTime = LocalDateTime.now();
    }
}
