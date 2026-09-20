import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import testdata.RebalanceTestData;

class RebalancingCalculatorTest {

    private static final double TOTAL_ASSETS = 100000;

    @Test
    void shouldBuyIBMWhenBelowTarget() {

        // IBM is below its target allocation, so the expected action is BUY.
        String action = RebalancingCalculator.getAction(
                RebalanceTestData.IBM.targetPercent(),
                RebalanceTestData.IBM.currentPercent());

        double shares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                RebalanceTestData.IBM.targetPercent(),
                RebalanceTestData.IBM.currentPercent(),
                RebalanceTestData.IBM.unitPrice());

        // Verify the application returns the expected action and share quantity.
        assertEquals("BUY", action);
        assertEquals(66.6667, shares, 0.0001);
    }

    @Test
    void shouldSellORCLWhenAboveTarget() {

        // ORCL is above its target allocation, so the expected action is SELL.
        String action = RebalancingCalculator.getAction(
                RebalanceTestData.ORCL.targetPercent(),
                RebalanceTestData.ORCL.currentPercent());

        double shares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                RebalanceTestData.ORCL.targetPercent(),
                RebalanceTestData.ORCL.currentPercent(),
                RebalanceTestData.ORCL.unitPrice());

        // Verify the application returns the expected action and share quantity.
        assertEquals("SELL", action);
        assertEquals(45.4545, shares, 0.0001);
    }

    @Test
    void shouldTakeNoActionWhenMSFTIsAtTarget() {

        // MSFT is already at its target allocation, so no trade should be needed.
        String action = RebalancingCalculator.getAction(
                RebalanceTestData.MSFT.targetPercent(),
                RebalanceTestData.MSFT.currentPercent());

        double shares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                RebalanceTestData.MSFT.targetPercent(),
                RebalanceTestData.MSFT.currentPercent(),
                RebalanceTestData.MSFT.unitPrice());

        // Verify that no trade is recommended.
        assertEquals("NO ACTION", action);
        assertEquals(0, shares, 0.0001);
    }

    @Test
    void shouldCalculateCompleteCRDExample() {

        // Verify the expected action for all five securities.
        assertEquals(
                "BUY",
                RebalancingCalculator.getAction(
                        RebalanceTestData.IBM.targetPercent(),
                        RebalanceTestData.IBM.currentPercent()));

        assertEquals(
                "NO ACTION",
                RebalancingCalculator.getAction(
                        RebalanceTestData.MSFT.targetPercent(),
                        RebalanceTestData.MSFT.currentPercent()));

        assertEquals(
                "SELL",
                RebalancingCalculator.getAction(
                        RebalanceTestData.ORCL.targetPercent(),
                        RebalanceTestData.ORCL.currentPercent()));

        assertEquals(
                "NO ACTION",
                RebalancingCalculator.getAction(
                        RebalanceTestData.AAPL.targetPercent(),
                        RebalanceTestData.AAPL.currentPercent()));

        assertEquals(
                "NO ACTION",
                RebalancingCalculator.getAction(
                        RebalanceTestData.HD.targetPercent(),
                        RebalanceTestData.HD.currentPercent()));

        // Verify the expected share quantities for all five securities.
        assertEquals(
                66.6667,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        RebalanceTestData.IBM.targetPercent(),
                        RebalanceTestData.IBM.currentPercent(),
                        RebalanceTestData.IBM.unitPrice()),
                0.0001);

        assertEquals(
                0,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        RebalanceTestData.MSFT.targetPercent(),
                        RebalanceTestData.MSFT.currentPercent(),
                        RebalanceTestData.MSFT.unitPrice()),
                0.0001);

        assertEquals(
                45.4545,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        RebalanceTestData.ORCL.targetPercent(),
                        RebalanceTestData.ORCL.currentPercent(),
                        RebalanceTestData.ORCL.unitPrice()),
                0.0001);

        assertEquals(
                0,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        RebalanceTestData.AAPL.targetPercent(),
                        RebalanceTestData.AAPL.currentPercent(),
                        RebalanceTestData.AAPL.unitPrice()),
                0.0001);

        assertEquals(
                0,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        RebalanceTestData.HD.targetPercent(),
                        RebalanceTestData.HD.currentPercent(),
                        RebalanceTestData.HD.unitPrice()),
                0.0001);
    }

    @Test
    void shouldReturnFractionalSharesWhenAllowed() {

        // Fractional shares are allowed for this test.
        // Verify that the application returns fractional quantities
        // instead of rounding to whole shares.
        double ibmShares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                RebalanceTestData.IBM.targetPercent(),
                RebalanceTestData.IBM.currentPercent(),
                RebalanceTestData.IBM.unitPrice());

        double orclShares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                RebalanceTestData.ORCL.targetPercent(),
                RebalanceTestData.ORCL.currentPercent(),
                RebalanceTestData.ORCL.unitPrice());

        // Verify the expected fractional share quantities.
        assertEquals(66.6667, ibmShares, 0.0001);
        assertEquals(45.4545, orclShares, 0.0001);
    }
}