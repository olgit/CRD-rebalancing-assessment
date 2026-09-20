public class RebalancingCalculator {

    public static String getAction(double targetPercent, double currentPercent) {
        if (currentPercent < targetPercent) {
            return "BUY";
        } else if (currentPercent > targetPercent) {
            return "SELL";
        } else {
            return "NO ACTION";
        }
    }

    public static double calculateShares(
            double totalAssets,
            double targetPercent,
            double currentPercent,
            double unitPrice) {

        double targetValue = totalAssets * targetPercent / 100;
        double currentValue = totalAssets * currentPercent / 100;

        double difference = Math.abs(targetValue - currentValue);

        return difference / unitPrice;
    }
}