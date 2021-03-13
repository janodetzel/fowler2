package dhbw.fowler2.theatre.Plays;

import dhbw.fowler2.theatre.Performance;

public class History extends Play {


    public History(String name, String type) {
        super(name, type);
    }

    @Override
    public int calculateAmount(Performance performance) {
        int calculatedAmount = 40000;
        if (performance.audience > 30) {
            calculatedAmount += 1000 * (performance.audience - 30);
        };
        return calculatedAmount;
    }


}
