package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.CardValidity.INVALID;
import static chap07.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTestWithStub {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private JpaAutoDebitInfoRepository repository;

    @BeforeEach
    public void setUp() {
        stubValidator = new StubCardNumberValidator();
        repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    public void invalidCard() {
        stubValidator.setInvalidNum("111122223333");

        AutoDebitReq reg = new AutoDebitReq("user1", "111122223333");
        RegisterResult registerResult = register.register(reg);

        assertEquals(INVALID, registerResult.getValidity());
    }

    @Test
    public void theftCard() {
        stubValidator.setTheftNum("1111222233334444");

        AutoDebitReq req = new AutoDebitReq("user2", "1111222233334444");
        RegisterResult result = register.register(req);

        assertEquals(THEFT, result.getValidity());
    }
}
