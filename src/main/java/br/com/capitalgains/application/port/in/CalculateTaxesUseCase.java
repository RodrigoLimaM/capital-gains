package br.com.capitalgains.application.port.in;

import java.util.List;
import br.com.capitalgains.domain.OperationInfo;
import br.com.capitalgains.domain.Tax;

public interface CalculateTaxesUseCase {

    List<Tax> calculateTaxes(final List<OperationInfo> operationsInput);
}
