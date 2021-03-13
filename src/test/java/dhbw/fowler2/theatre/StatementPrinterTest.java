package dhbw.fowler2.theatre;

import dhbw.fowler2.theatre.Plays.Comedy;
import dhbw.fowler2.theatre.Plays.History;
import dhbw.fowler2.theatre.Plays.Play;

import java.util.List;
import java.util.Map;

import dhbw.fowler2.theatre.Plays.Tragedy;
import org.junit.Assert;
import org.junit.Test;

public class StatementPrinterTest {

    @Test
    public void printsStatements() {
        Map<String, Play> plays = Map.of("hamlet", new Tragedy("Hamlet"),
                "as-like", new Comedy("As You Like It"),
                "othello", new History("Romeo & Julia"));

        Invoice invoice = new Invoice("BigCo", List.of(new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        System.out.println("Mismatch result" + result);

        Assert.assertEquals("Statement print mismatch", "Statement for BigCo\n" +
                "  Hamlet: $650.00 (55 seats)\n" +
                "  As You Like It: $580.00 (35 seats)\n" +
                "  Romeo & Julia: $500.00 (40 seats)\n" +
                "Amount owed is $1,730.00\n" +
                "You earned 47 credits\n", result);
    }
}
