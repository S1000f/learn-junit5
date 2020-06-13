package chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        int meetsCount = getMeetsCount(s);

        if (meetsCount <= 1) {
            return PasswordStrength.WEAK;
        }

        if (meetsCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private int getMeetsCount(String s) {
        int meetsCount = 0;
        if (s.length() >= 8) {
            meetsCount++;
        }

        if (meetsContaingUppercase(s)) {
            meetsCount++;
        }

        if (meetsContainingNumberCriteria(s)) {
            meetsCount++;
        }

        return meetsCount;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch: s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }

        return false;
    }

    private boolean meetsContaingUppercase(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }

        return false;
    }
}
