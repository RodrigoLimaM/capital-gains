package br.com.capitalgains.service;

import br.com.capitalgains.domain.OperationContext;

enum Operation {
    BUY(new BuyOperationHandler()),
    SELL(new SellOperationHandler());

    Operation(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    private final OperationHandler operationHandler;

    public OperationContext handleOperation(final OperationContext operationContext) {
        return operationHandler.handle(operationContext);
    }

    public static Operation getOperation(final String operation) {
        return Operation.valueOf(operation.toUpperCase());
    }
}
