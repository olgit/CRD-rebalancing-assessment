# CRD Rebalancing Assessment - Automation Test Cases

The automated tests cover the main rebalancing scenarios and validate the action and number of shares returned by the application.

## Automated Test Cases

### ATC01 - Verify BUY action and shares for IBM

**Test data**
- Security: IBM
- Total Assets: $100,000
- Target: 20%
- Current: 10%
- Unit Price: $150

**Expected result**
- Action: BUY
- Shares: 66.6667

---

### ATC02 - Verify SELL action and shares for ORCL

**Test data**
- Security: ORCL
- Total Assets: $100,000
- Target: 20%
- Current: 30%
- Unit Price: $220

**Expected result**
- Action: SELL
- Shares: 45.4545

---

### ATC03 - Verify NO ACTION when security is at target

**Test data**
- Security: MSFT
- Total Assets: $100,000
- Target: 20%
- Current: 20%
- Unit Price: $90

**Expected result**
- Action: NO ACTION
- Shares: 0

---

### ATC04 - Verify complete portfolio results

Use the data from the CRD example and verify the output for all five securities.

| Security | Target % | Current % | Action | Expected Shares |
|---|---:|---:|---|---:|
| IBM | 20% | 10% | BUY | 66.6667 |
| MSFT | 20% | 20% | NO ACTION | 0 |
| ORCL | 20% | 30% | SELL | 45.4545 |
| AAPL | 20% | 20% | NO ACTION | 0 |
| HD | 20% | 20% | NO ACTION | 0 |

**Expected result**

All five securities return the expected action and share quantity.

---

### ATC05 - Verify fractional share calculation

**Assumption:** Fractional shares are allowed.

Verify that the application returns fractional share quantities instead of rounding to whole shares.

**Expected result**
- IBM: BUY 66.6667 shares
- ORCL: SELL 45.4545 shares

The automated test should allow for a small decimal precision difference when comparing the calculated result.

### ATC06 - Verify cash balance after rebalance

**Expected result**

IBM purchase value: $10,000
ORCL sale value: $10,000
Net cash impact: $0
Cash balance: >= $0

*Automation note: This scenario is currently covered as a manual business check because cash balance is not an output of the assessment implementation.