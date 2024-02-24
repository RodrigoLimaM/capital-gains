package br.com.capitalgains.domain;

import java.math.BigDecimal;

public class OperationContext {
    private boolean isFirstIteration;
    private BigDecimal actualWeightedAveragePrice;
    private OperationInfo operationInfo;
    private long actualStockBalance;
    private BigDecimal actualProfit;
    private Tax tax;

    public OperationContext(
            boolean isFirstIteration,
            BigDecimal actualWeightedAveragePrice,
            OperationInfo operationInfo,
            long actualStockBalance,
            BigDecimal actualProfit,
            Tax tax
    ) {
        this.isFirstIteration = isFirstIteration;
        this.actualWeightedAveragePrice = actualWeightedAveragePrice;
        this.operationInfo = operationInfo;
        this.actualStockBalance = actualStockBalance;
        this.actualProfit = actualProfit;
        this.tax = tax;
    }

    public boolean isFirstIteration() {
        return isFirstIteration;
    }

    public BigDecimal getActualWeightedAveragePrice() {
        return actualWeightedAveragePrice;
    }

    public void setActualWeightedAveragePrice(BigDecimal actualWeightedAveragePrice) {
        this.actualWeightedAveragePrice = actualWeightedAveragePrice;
    }

    public OperationInfo getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(OperationInfo operationInfo) {
        this.operationInfo = operationInfo;
    }

    public long getActualStockBalance() {
        return actualStockBalance;
    }

    public BigDecimal getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(BigDecimal actualProfit) {
        this.actualProfit = actualProfit;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public void incrementActualStockBalance(final long quantity) {
        actualStockBalance += quantity;
    }

    public void subtractActualStockBalance(final long quantity) {
        actualStockBalance -= quantity;
    }

    public void incrementActualProfit(final BigDecimal profit) {
        actualProfit = actualProfit.add(profit);
    }

    public void checkFirstIteration() {
        isFirstIteration = false;
    }

    @Override
    public String toString() {
        return "OperationContext{" +
                "isFirstIteration=" + isFirstIteration +
                ", actualWeightedAveragePrice=" + actualWeightedAveragePrice +
                ", operationInfo=" + operationInfo +
                ", actualStockBalance=" + actualStockBalance +
                ", actualProfit=" + actualProfit +
                ", tax=" + tax +
                '}';
    }
}

