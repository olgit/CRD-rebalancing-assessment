
# CRD Rebalancing Assessment

QA assessment for a portfolio rebalancing scenario.

## Overview

This project demonstrates:

- Manual test case design
- Business-rule validation
- Automated testing with Java and JUnit
- Validation of BUY, SELL, and NO ACTION scenarios
- Calculation of the number of shares required to reach the target allocation

## Business Logic

For each security:

- `Current % < Target %` → **BUY**
- `Current % > Target %` → **SELL**
- `Current % = Target %` → **NO ACTION**

The calculation is:

Target Value  = Total Assets × Target %
Current Value = Total Assets × Current %

Shares = |Target Value - Current Value| / Unit Price

## Fractional Shares

If fractional shares are supported, the application can calculate fractional buy and sell quantities and bring each security to its target allocation, resulting in zero target variance.

If fractional shares are not supported, the application must apply the platform’s configured whole-share rounding policy. The rebalance may not reach zero target variance, and a small residual amount—often cash or an allocation variance—may remain.

## Provided CRD Scenario

| Security | Target % | Current % | Unit Price | Action | Shares |
|----------|----------|----------|------------|--------|--------|
| IBM      | 20%      | 10%      | $150       | BUY | 66.6667 |
| MSFT     | 20%      | 20%      | $90        | NO ACTION | 0 |
| ORCL     | 20%      | 30%      | $220       | SELL | 45.4545 |
| AAPL     | 20%      | 20%      | $450       | NO ACTION | 0 |
| HD       | 20%      | 20%      | $70        | NO ACTION | 0 |

Total account assets: **$100,000**

## Assumptions

- Fractional-share support and the whole-share rounding policy must be confirmed with the financial platform.
- The provided unit price is used for the calculation.
- Transaction fees, taxes, slippage, and market-price changes are out of scope.
- Percentages are provided as values such as `20` for 20%.

## Project Structure

Security centralizes the securities and their test data so the same data can be reused across multiple test scenarios and updated in one place.

crd-alpha-rebalancing-assessment/
├── .gitignore
├── North American - Technical Assessment QA.docx
├── README.md
├── automated-test-cases.md
├── manual-test-cases.md
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       ├── RebalancingCalculator.java
    │       └── enums/
    │           └── Security.java
    └── test/
        └── java/
            └── RebalancingCalculatorTest.java
            
