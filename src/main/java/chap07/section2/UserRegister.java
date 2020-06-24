package chap07.section2;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public void register(String id, String password, String email) {
        if (passwordChecker.checkPasswordWeak(password)) {
            throw new WeakPasswordException();
        }
    }
}
