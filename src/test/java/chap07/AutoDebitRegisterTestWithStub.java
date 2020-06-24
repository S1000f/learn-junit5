package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static chap07.CardValidity.INVALID;
import static chap07.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTestWithStub {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository repository;

    @BeforeEach
    public void setUp() {
        stubValidator = new StubCardNumberValidator();
        repository = new StubAutoDebitInfoRepository();
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

    @Test
    public void whenAlreadyRegisteredThenUpdateInfoInstead() {
        repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        AutoDebitReq req  = new AutoDebitReq("user1", "123456789012");
        RegisterResult registerResult = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    public void whenItIsNewThenNewInfoWillBeRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "111222333444");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("111222333444", saved.getCardNumber());
    }
}
