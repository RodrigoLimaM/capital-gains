package br.com.capitalgains.service;

import java.math.BigDecimal;
import br.com.capitalgains.domain.OperationContext;
import br.com.capitalgains.domain.Tax;

class BuyOperationHandler implements OperationHandler {

    @Override
    public OperationContext handle(final OperationContext operationContext) {
        final var operationInfo = operationContext.getOperationInfo();
        final var actualStockBalance = operationContext.getActualStockBalance();

        if (operationContext.isFirstIteration()) {
            operationContext.setActualWeightedAveragePrice(operationInfo.unitCost());
            operationContext.checkFirstIteration();
        } else {
            if (hasAllStocksSold(actualStockBalance)) {
                operationContext.setActualProfit(BigDecimal.ZERO);
            }
            operationContext.setActualWeightedAveragePrice(
                    CalculatorService.calculateActualWeightedAveragePrice(
                            operationInfo,
                            operationContext.getActualWeightedAveragePrice(),
                            actualStockBalance
                    )
            );
        }
        operationContext.incrementActualStockBalance(operationInfo.quantity());

        operationContext.setTax(new Tax(CalculatorService.formatDecimalCases(BigDecimal.ZERO)));
        return operationContext;
    }

    static boolean hasAllStocksSold(final long actualStockBalance) {
        return actualStockBalance == 0;
    }

}
