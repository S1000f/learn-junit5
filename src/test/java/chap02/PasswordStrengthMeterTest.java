package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expect) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expect, result);
    }

    @Test
    public void meetsAllCriteriaThenStrong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    public void meetsOtherCriteriaExceptLengthThenNormal() {
        assertStrength("ab1!A@2", PasswordStrength.NORMAL);
        assertStrength("cb1!@B", PasswordStrength.NORMAL);
    }

    @Test
    public void meetsOtherCriteriaExceptNumberThenNormal() {
        assertStrength("ab!@BCass", PasswordStrength.NORMAL);
    }

    @Test
    public void nullThenInvalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    public void emptyThenInvalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    public void meetsOtherCriteriaExceptUppercaseThenNormal() {
        assertStrength("as1!@f3ffaff", PasswordStrength.NORMAL);
    }

    @Test
    public void meetsOnlyLengthThenWeek() {
        assertStrength("asdfgrfarfaf", PasswordStrength.WEAK);
    }

    @Test
    public void meetsOnlyNumberThenWeak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    public void meetsOnlyUppercaseThenWeak() {
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }

    @Test
    public void meetsNothingThenWeak() {
        assertStrength("a", PasswordStrength.WEAK);
    }
}
