package testdata;

public class RebalanceTestData {

    public record SecurityData(
            String ticker,
            double targetPercent,
            double currentPercent,
            double unitPrice) {
    }

    public static final SecurityData IBM =
            new SecurityData("IBM", 20, 10, 150);

    public static final SecurityData MSFT =
            new SecurityData("MSFT", 20, 20, 90);

    public static final SecurityData ORCL =
            new SecurityData("ORCL", 20, 30, 220);

    public static final SecurityData AAPL =
            new SecurityData("AAPL", 20, 20, 450);

    public static final SecurityData HD =
            new SecurityData("HD", 20, 20, 70);
}