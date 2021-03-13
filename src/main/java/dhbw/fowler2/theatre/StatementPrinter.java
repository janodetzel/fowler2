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
            // add volume credits
            volumeCredits += Math.max(performance.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy" == getPlay(plays, performance).type) volumeCredits += Math.floor(performance.audience / 5);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", getPlay(plays, performance).name, NumberFormat.getCurrencyInstance(Locale.US).format(performanceCalculator.calculateAmount(getPlay(plays, performance), performance) / 100), performance.audience);
            totalAmount += performanceCalculator.calculateAmount(getPlay(plays, performance), performance);
        }
        result += String.format("Amount owed is %s\n", NumberFormat.getCurrencyInstance(Locale.US).format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    public Play getPlay(Map<String, Play> plays, Performance performance) {
        return plays.get(performance.playID);
    }

}
