import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import enums.Security;

class RebalancingCalculatorTest {

    private static final double TOTAL_ASSETS = 100000;

    @Test
    void shouldBuyIBMWhenBelowTarget() {

        // IBM is below its target allocation, so the expected action is BUY.
        String action = RebalancingCalculator.getAction(
                Security.IBM.getTargetPercent(),
                Security.IBM.getCurrentPercent());

        double shares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                Security.IBM.getTargetPercent(),
                Security.IBM.getCurrentPercent(),
                Security.IBM.getUnitPrice());

        // Verify the application returns the expected action and share quantity.
        assertEquals("BUY", action);
        assertEquals(66.6667, shares, 0.0001);
    }

    @Test
    void shouldSellORCLWhenAboveTarget() {

        // ORCL is above its target allocation, so the expected action is SELL.
        String action = RebalancingCalculator.getAction(
                Security.ORCL.getTargetPercent(),
                Security.ORCL.getCurrentPercent());

        double shares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                Security.ORCL.getTargetPercent(),
                Security.ORCL.getCurrentPercent(),
                Security.ORCL.getUnitPrice());

        // Verify the application returns the expected action and share quantity.
        assertEquals("SELL", action);
        assertEquals(45.4545, shares, 0.0001);
    }

    @Test
    void shouldTakeNoActionWhenMSFTIsAtTarget() {

        // MSFT is already at its target allocation, so no trade should be needed.
        String action = RebalancingCalculator.getAction(
                Security.MSFT.getTargetPercent(),
                Security.MSFT.getCurrentPercent());

        double shares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                Security.MSFT.getTargetPercent(),
                Security.MSFT.getCurrentPercent(),
                Security.MSFT.getUnitPrice());

        // Verify that no trade is recommended.
        assertEquals("NO ACTION", action);
        assertEquals(0, shares, 0.0001);
    }

    @Test
    void shouldCalculateCompleteCRDExample() {

        // Validate the expected share quantities for all five securities
        // from the CRD assessment example.

        assertEquals(
                66.6667,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        Security.IBM.getTargetPercent(),
                        Security.IBM.getCurrentPercent(),
                        Security.IBM.getUnitPrice()),
                0.0001);

        assertEquals(
                0,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        Security.MSFT.getTargetPercent(),
                        Security.MSFT.getCurrentPercent(),
                        Security.MSFT.getUnitPrice()),
                0.0001);

        assertEquals(
                45.4545,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        Security.ORCL.getTargetPercent(),
                        Security.ORCL.getCurrentPercent(),
                        Security.ORCL.getUnitPrice()),
                0.0001);

        assertEquals(
                0,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        Security.AAPL.getTargetPercent(),
                        Security.AAPL.getCurrentPercent(),
                        Security.AAPL.getUnitPrice()),
                0.0001);

        assertEquals(
                0,
                RebalancingCalculator.calculateShares(
                        TOTAL_ASSETS,
                        Security.HD.getTargetPercent(),
                        Security.HD.getCurrentPercent(),
                        Security.HD.getUnitPrice()),
                0.0001);
    }

    @Test
    void shouldReturnFractionalSharesWhenAllowed() {

        // Fractional shares are allowed for this test.
        // Verify that the application returns fractional quantities
        // instead of rounding to whole shares.

        double ibmShares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                Security.IBM.getTargetPercent(),
                Security.IBM.getCurrentPercent(),
                Security.IBM.getUnitPrice());

        double orclShares = RebalancingCalculator.calculateShares(
                TOTAL_ASSETS,
                Security.ORCL.getTargetPercent(),
                Security.ORCL.getCurrentPercent(),
                Security.ORCL.getUnitPrice());

        // Verify the expected fractional share quantities.
        assertEquals(66.6667, ibmShares, 0.0001);
        assertEquals(45.4545, orclShares, 0.0001);
    }
}