package dhbw.fowler2.theatre.Plays;

import dhbw.fowler2.theatre.Performance;

public class Comedy extends Play {


    public Comedy(String name) {
        super(name);
        super.type = "comedy";
    }

    @Override
    public int calculateAmount(Performance performance) {
        int calculatedAmount = 30000;
        if (performance.audience > 20) {
            calculatedAmount += 10000 + 500 * (performance.audience - 20);
        }
        calculatedAmount += 300 * performance.audience;
        return calculatedAmount;
    }


}
