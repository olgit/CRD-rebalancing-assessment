package enums;

public enum Security {

    IBM(20, 10, 150),
    MSFT(20, 20, 90),
    ORCL(20, 30, 220),
    AAPL(20, 20, 450),
    HD(20, 20, 70);

    private final double targetPercent;
    private final double currentPercent;
    private final double unitPrice;

    Security(double targetPercent, double currentPercent, double unitPrice) {
        this.targetPercent = targetPercent;
        this.currentPercent = currentPercent;
        this.unitPrice = unitPrice;
    }

    public double getTargetPercent() {
        return targetPercent;
    }

    public double getCurrentPercent() {
        return currentPercent;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
