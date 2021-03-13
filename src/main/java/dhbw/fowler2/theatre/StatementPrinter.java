package dhbw.fowler2.theatre;

import dhbw.fowler2.theatre.Plays.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private Invoice invoice = null;
    private Map<String, Play> plays = null;
    PerformanceCalculator performanceCalculator = new PerformanceCalculator();

    public String print(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
        var result = String.format("Statement for %s\n", invoice.customer);

        for (var performance : invoice.performances) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", getPlay(performance).name, getNumberFormat(Locale.US)
                    .format(performanceCalculator
                            .calculateAmount(getPlay(performance), performance) / 100), performance.audience);
        }
        result += String.format("Amount owed is %s\n", getNumberFormat(Locale.US).format(getTotalAmount() / 100));
        result += String.format("You earned %s credits\n", getTotalVolumeCredits());
        return result;
    }

    public Play getPlay(Performance performance) {
        return plays.get(performance.playID);
    }

    public NumberFormat getNumberFormat(Locale locale){
        return NumberFormat.getCurrencyInstance(locale);
    }

    public int calculateVolumeCredits(Performance performance) {
        int volumeCredits = 0;
        volumeCredits += Math.max(performance.audience - 30, 0);
        if ("comedy" == getPlay(performance).type) volumeCredits += Math.floor(performance.audience / 5);

        return volumeCredits;
    }

    private int getTotalAmount() {
        int result = 0;

        for (var performance : invoice.performances) {
            result += performanceCalculator.calculateAmount(getPlay(performance), performance);
        }

        return result;
    }

    private int getTotalVolumeCredits() {
        int result = 0;

        for (var performance : invoice.performances) {
            result += calculateVolumeCredits(performance);
        }

        return result;
    }
}
