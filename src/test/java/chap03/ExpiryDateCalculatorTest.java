package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    public void pay10000wonThenExpireOneMonthLater() {
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2019,3,1))
                    .payAmount(10_000)
                    .build(),
                LocalDate.of(2019,4,1)
        );
    }

    @Test
    public void payDayAndOneMonthLaterDayIsNotEqual() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,2,28)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,5,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,6,30)
        );
    }

    @Test
    public void pay10000wonWhenFirstPayDayAndSecondOneIsDiff() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019,3,31));

        PayData payData1 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData1, LocalDate.of(2019,3,30));
    }

    @Test
    public void eachPayment10000wonAddOneMonthOfExpireDate() {
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2019,3,1))
                    .payAmount(20_000)
                    .build(),
                LocalDate.of(2019,5,1)
        );

        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2019,3,1))
                    .payAmount(50_000)
                    .build(),
                LocalDate.of(2019,8,1)
        );
    }

    @Test
    public void whenFirstBillingDateAndSecondOneIsDiffThenPayMoreThan20000won() {
        assertExpiryDate(
                PayData.builder()
                    .firstBillingDate(LocalDate.of(2019,1,31))
                    .billingDate(LocalDate.of(2019,2,28))
                    .payAmount(20_000)
                    .build(),
                LocalDate.of(2019,4,30)
        );
    }

    @Test
    public void pay100_000wonThenExpireOneYearLater() {
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2019,1,28))
                    .payAmount(100_000)
                    .build(),
                LocalDate.of(2020,1,28)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpireDate(payData);
        assertEquals(expectedDate, realExpiryDate);
    }
}
