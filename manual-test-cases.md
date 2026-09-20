# CRD Rebalancing Assessment - Manual Test Cases

## Manual Test Cases

1. Verify that the rebalance results include all five securities: IBM, MSFT, ORCL, AAPL, and HD.

2. Verify that IBM has a BUY action since current allocation is 10% and target is 20%.

3. Verify that IBM quantity is 66.6667 shares when fractional shares are supported.

4. Verify that ORCL has a SELL action since current allocation is 30% and target is 20%.

5. Verify that ORCL quantity is 45.4545 shares when fractional shares are supported.

6. Verify that MSFT, AAPL, and HD have NO ACTION and quantity 0 since they are already at their 20% target.

7. When fractional shares are supported, verify that IBM and ORCL each reach a $20,000 market value after the rebalance.

8. When fractional shares are supported, verify that target variance is 0% for all securities after the rebalance.

9. When fractional shares are not supported, verify that IBM is rounded to 67 shares BUY and ORCL to 45 shares SELL, assuming nearest-whole-share rounding.

10. When whole shares are used, verify that the application shows the remaining variance instead of incorrectly showing 0%.

11. Verify that the cash balance does not become negative after executing the rebalance.


## Expected Results

### Fractional Shares Allowed

| Security | Target % | Current % | Action | Shares to Buy/Sell | Final Variance |
|---|---:|---:|---|---:|---:|
| IBM | 20% | 10% | BUY | 66.6667 | 0% |
| MSFT | 20% | 20% | NO ACTION | 0 | 0% |
| ORCL | 20% | 30% | SELL | 45.4545 | 0% |
| AAPL | 20% | 20% | NO ACTION | 0 | 0% |
| HD | 20% | 20% | NO ACTION | 0 | 0% |

Expected result: All securities reach their 20% target and the target variance is 0%.

### Fractional Shares Not Allowed

| Security | Target % | Current % | Action | Shares | Expected Result |
|---|---:|---:|---|---:|---|
| IBM | 20% | 10% | BUY | Whole shares based on platform rounding | May have residual variance |
| MSFT | 20% | 20% | NO ACTION | 0 | 0% variance |
| ORCL | 20% | 30% | SELL | Whole shares based on platform rounding | May have residual variance |
| AAPL | 20% | 20% | NO ACTION | 0 | 0% variance |
| HD | 20% | 20% | NO ACTION | 0 | 0% variance |

