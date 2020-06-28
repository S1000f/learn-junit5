package chap07.section2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterTest {

    private UserRegister userRegister;
    private UserRegister userRegisterWithMockito;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private StubUserRepository stubUserRepository = new StubUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    private WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class);
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    public void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, stubUserRepository, spyEmailNotifier);
        userRegisterWithMockito = new UserRegister(
                mockPasswordChecker, stubUserRepository, mockEmailNotifier
        );
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    public void weakPassword() {
        stubWeakPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, ()-> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("약한 암호면 가입실패-mockito")
    @Test
    public void weakPasswordWithMockito() {

        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, ()-> {
            userRegisterWithMockito.register("id", "pw", "email");
        });
    }

    @DisplayName("회원가입시 암호검사 수행함-mockito")
    @Test
    public void checkPassword() {
        userRegisterWithMockito.register("id", "pw", "email");

        then(mockPasswordChecker).should().checkPasswordWeak(anyString());
    }

    @DisplayName("이미 같은 id가 존재하면 가입실패")
    @Test
    public void dupIdExists() {
        stubUserRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, ()-> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("중복 id가 없으면 가입성공")
    @Test
    public void noDupIdThenRegisterSuccessfully() {
        userRegister.register("id", "pw", "email");

        User savedUser = stubUserRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 발송함")
    @Test
    public void whenRegisterThenSendEmail() {
        userRegister.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }

    @DisplayName("가입하면 메일을 발송함-mockito")
    @Test
    public void whenRegisterSuccessThenSendEmail() {
        userRegisterWithMockito.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        String email = captor.getValue();
        assertEquals("email@email.com", email);
    }
}
