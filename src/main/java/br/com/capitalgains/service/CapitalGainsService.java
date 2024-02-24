package br.com.capitalgains.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import br.com.capitalgains.application.port.in.CalculateTaxesUseCase;
import br.com.capitalgains.domain.OperationContext;
import br.com.capitalgains.domain.OperationInfo;
import br.com.capitalgains.domain.Tax;

public class CapitalGainsService implements CalculateTaxesUseCase {

    @Override
    public List<Tax> calculateTaxes(final List<OperationInfo> operationsInput) {
        final var response = new ArrayList<Tax>();
        var operationContext = new OperationContext(true, BigDecimal.ZERO, null, 0, BigDecimal.ZERO, null);

        for (final OperationInfo operationInfo : operationsInput) {
            operationContext.setOperationInfo(operationInfo);
            operationContext = Operation.getOperation(operationInfo.operation()).handleOperation(operationContext);
            response.add(operationContext.getTax());
        }

        return response;
    }

}
