# CRD Rebalancing Assessment - Manual Test Cases

## Manual Test Cases

1. Verify that the rebalance results include all five securities: IBM, MSFT, ORCL, AAPL, and HD.

2. Verify that IBM has a **BUY** action since the current allocation is 10% and the target is 20%.

3. Verify that the calculated quantity for IBM is **66.6667 shares** when fractional shares are supported.

4. Verify that ORCL has a **SELL** action since the current allocation is 30% and the target is 20%.

5. Verify that the calculated quantity for ORCL is **45.4545 shares** when fractional shares are supported.

6. Verify that MSFT, AAPL, and HD have **NO ACTION** and a quantity of 0 since they are already at their 20% target.

7. Verify that after the rebalance, IBM is at $20,000 and ORCL is at $20,000.

8. Verify that after applying the trades, the target variance is 0 for all securities when fractional shares are supported.

9. Verify the behavior when fractional shares are not supported and whole-share rounding is required.

10. Verify that the application shows any remaining variance after whole-share rounding instead of incorrectly showing zero variance.

11. Verify that the rebalance results show the security, action (BUY/SELL/NO ACTION), and number of shares.

12. Verify that the account balance is **greater than or equal to $0** at the end of the rebalance.


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

