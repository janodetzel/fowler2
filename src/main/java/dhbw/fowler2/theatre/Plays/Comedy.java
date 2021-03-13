package dhbw.fowler2.theatre.Plays;

import dhbw.fowler2.theatre.Performance;

public class Comedy extends Play {

    private int calculatedAmount = 30000;

    public Comedy(String name, String type) {
        super(name, type);
    }

    @Override
    public int calculateAmount(Performance performance) {
        if (performance.audience > 20) {
            calculatedAmount += 10000 + 500 * (performance.audience - 20);
        }
        calculatedAmount += 300 * performance.audience;
        return calculatedAmount;
    }


}
