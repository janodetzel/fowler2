package dhbw.fowler2.theatre;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        PerformanceCalculator performanceCalculator = new PerformanceCalculator();

        for (var performance : invoice.performances) {
            volumeCredits += calculateVolumeCredits(performance, plays);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", getPlay(plays, performance).name, getNumberFormat(Locale.US)
                    .format(performanceCalculator
                            .calculateAmount(getPlay(plays, performance), performance) / 100), performance.audience);
            totalAmount += performanceCalculator.calculateAmount(getPlay(plays, performance), performance);
        }
        result += String.format("Amount owed is %s\n", getNumberFormat(Locale.US).format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    public Play getPlay(Map<String, Play> plays, Performance performance) {
        return plays.get(performance.playID);
    }

    public NumberFormat getNumberFormat(Locale locale){
        return NumberFormat.getCurrencyInstance(locale);
    }

    public int calculateVolumeCredits(Performance performance, Map<String, Play> plays) {
        int volumeCredits = 0;
        volumeCredits += Math.max(performance.audience - 30, 0);
        if ("comedy" == getPlay(plays, performance).type) volumeCredits += Math.floor(performance.audience / 5);

        return volumeCredits;
    }
}
