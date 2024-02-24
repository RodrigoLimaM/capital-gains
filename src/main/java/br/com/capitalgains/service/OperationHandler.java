package br.com.capitalgains.service;

import br.com.capitalgains.domain.OperationContext;

interface OperationHandler {

    OperationContext handle(final OperationContext operationContext);
}
