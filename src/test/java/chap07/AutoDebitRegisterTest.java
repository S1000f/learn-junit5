package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.THEFT;
import static chap07.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {

    private AutoDebitRegister register;

    @BeforeEach
    public void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Disabled
    @Test
    public void validCard() {
        AutoDebitReq req = new AutoDebitReq("user1","1234123412341234");
        RegisterResult registerResult = this.register.register(req);

        assertEquals(VALID, registerResult.getValidity());
    }

    @Disabled
    @Test
    public void theftCard() {
        AutoDebitReq req = new AutoDebitReq("user1","1234567890123456");
        RegisterResult registerResult = this.register.register(req);

        assertEquals(THEFT, registerResult.getValidity());
    }
}
