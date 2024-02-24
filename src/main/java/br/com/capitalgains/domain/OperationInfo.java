package br.com.capitalgains.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OperationInfo(
        String operation,
        @JsonProperty("unit-cost")
        BigDecimal unitCost,
        long quantity
) {

        public BigDecimal getOperationTotalValue() {
                return unitCost.multiply(BigDecimal.valueOf(quantity));
        }

        public BigDecimal getTaxes(final BigDecimal actualWeightedAveragePrice, final BigDecimal profit) {
                if (isTaxFree(actualWeightedAveragePrice, profit)) {
                        return BigDecimal.ZERO;
                } else {
                        return profit.multiply(BigDecimal.valueOf(0.2));
                }
        }

        private boolean isTaxFree(final BigDecimal actualWeightedAveragePrice, final BigDecimal profit) {
                return profit.compareTo(BigDecimal.ZERO) <= 0 || getOperationTotalValue().compareTo(BigDecimal.valueOf(20000)) <= 0 || unitCost.compareTo(actualWeightedAveragePrice) < 0;
        }

}
