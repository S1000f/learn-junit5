package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpireDate(PayData payData) {
        int addMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addMonths);
        } else {
            return payData.getBillingDate().plusMonths(addMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addMonths);
        final int dayOfFirstBillingDate = payData.getFirstBillingDate().getDayOfMonth();
        if (dayOfFirstBillingDate != candidateExp.getDayOfMonth()) {
            final int lengthOfCandidMonth = YearMonth.from(candidateExp).lengthOfMonth();
            if (lengthOfCandidMonth < dayOfFirstBillingDate) {
                return candidateExp.withDayOfMonth(lengthOfCandidMonth);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBillingDate);
        } else {
            return candidateExp;
        }
    }
}
