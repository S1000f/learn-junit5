package chap07;

import lombok.Setter;

@Setter
public class StubCardNumberValidator extends CardNumberValidator {

    private String invalidNum;
    private String theftNum;


    @Override
    public CardValidity validate(String cardNumber) {
        if (invalidNum != null && invalidNum.equals(cardNumber)) {
            return CardValidity.INVALID;
        }

        if (theftNum != null && theftNum.equals(cardNumber)) {
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }
}
