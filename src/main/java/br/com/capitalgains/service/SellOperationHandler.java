package br.com.capitalgains.service;

import br.com.capitalgains.domain.OperationContext;
import br.com.capitalgains.domain.Tax;

class SellOperationHandler implements OperationHandler {

    @Override
    public OperationContext handle(final OperationContext operationContext) {
        final var operationInfo = operationContext.getOperationInfo();
        final var actualWeightedAveragePrice = operationContext.getActualWeightedAveragePrice();

        operationContext.subtractActualStockBalance(operationInfo.quantity());
        operationContext.incrementActualProfit(CalculatorService.calculateActualProfit(operationInfo, actualWeightedAveragePrice));
        operationContext.setTax(
                new Tax(CalculatorService.formatDecimalCases(
                        operationInfo.getTaxes(actualWeightedAveragePrice, operationContext.getActualProfit()))
                )
        );

        return operationContext;
    }
}
