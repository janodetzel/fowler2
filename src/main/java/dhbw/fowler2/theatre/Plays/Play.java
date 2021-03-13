package dhbw.fowler2.theatre.Plays;

import dhbw.fowler2.theatre.Performance;

public abstract class Play {

    public String name;
    public String type;

    public Play(String name) {
        this.name = name;
    }

    public abstract int calculateAmount(Performance performance);
}
