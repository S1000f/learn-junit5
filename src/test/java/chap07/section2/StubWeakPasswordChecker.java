package chap07.section2;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String password) {
        return weak;
    }

}