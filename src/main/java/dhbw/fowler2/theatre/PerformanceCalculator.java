package dhbw.fowler2.theatre;

import dhbw.fowler2.theatre.Plays.Play;

public class PerformanceCalculator {

    public int calculateAmount(Play play, Performance performance) {
        var calculatedAmount = 0;

        switch (play.type) {
            case "tragedy":
                calculatedAmount = 40000;
                if (performance.audience > 30) {
                    calculatedAmount += 1000 * (performance.audience - 30);
                }
                break;
            case "comedy":
                calculatedAmount = 30000;
                if (performance.audience > 20) {
                    calculatedAmount += 10000 + 500 * (performance.audience - 20);
                }
                calculatedAmount += 300 * performance.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return calculatedAmount;


    }


}
