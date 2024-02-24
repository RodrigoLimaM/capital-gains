package br.com.capitalgains.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import br.com.capitalgains.domain.OperationInfo;

class CalculatorService {

    private CalculatorService() {

    }

    public static final int DECIMAL_CASES = 2;

    static BigDecimal calculateActualProfit(final OperationInfo operationInfo, final BigDecimal actualWeightedAveragePrice) {
        return operationInfo.getOperationTotalValue()
                .subtract(
                        actualWeightedAveragePrice.multiply(BigDecimal.valueOf(operationInfo.quantity()))
                );
    }

    static BigDecimal calculateActualWeightedAveragePrice(final OperationInfo operationInfo, final BigDecimal actualWeightedAveragePrice, final long actualStockBalance) {
        final var numerator = actualWeightedAveragePrice.multiply(BigDecimal.valueOf(actualStockBalance))
                .add(operationInfo.unitCost().multiply(BigDecimal.valueOf(operationInfo.quantity())));

        final var denominator = BigDecimal.valueOf(actualStockBalance + operationInfo.quantity());

        return numerator.divide(denominator, DECIMAL_CASES, RoundingMode.HALF_UP);
    }

    static BigDecimal formatDecimalCases(final BigDecimal value) {
        return value.setScale(DECIMAL_CASES, RoundingMode.HALF_UP);
    }
}
