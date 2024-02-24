package br.com.capitalgains.service;

import java.math.BigDecimal;
import java.util.Arrays;
import br.com.capitalgains.domain.OperationInfo;
import br.com.capitalgains.domain.Tax;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalGainsServiceTest {

    CapitalGainsService capitalGainsService = new CapitalGainsService();

    @Test
    void case1() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
        );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 100),
                new OperationInfo("sell", BigDecimal.valueOf(15), 50),
                new OperationInfo("sell", BigDecimal.valueOf(15), 50)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case2() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(10000))),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
        );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(20), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(5), 5000)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case3() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(1000))
        ));

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(5), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(20), 3000)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case4() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
            );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("buy", BigDecimal.valueOf(25), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(15), 10000)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case5() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(10000)))
        );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("buy", BigDecimal.valueOf(25), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(15), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(25), 5000)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case6() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(3000)))
        );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(2), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(20), 2000),
                new OperationInfo("sell", BigDecimal.valueOf(20), 2000),
                new OperationInfo("sell", BigDecimal.valueOf(25), 1000)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case7() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(3000.00))),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(3700.00))),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO))
        );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(2), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(20), 2000),
                new OperationInfo("sell", BigDecimal.valueOf(20), 2000),
                new OperationInfo("sell", BigDecimal.valueOf(25), 1000),
                new OperationInfo("buy", BigDecimal.valueOf(20), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(15), 5000),
                new OperationInfo("sell", BigDecimal.valueOf(30), 4350),
                new OperationInfo("sell", BigDecimal.valueOf(30), 650)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

    @Test
    void case8() {
        final var expected = Arrays.asList(
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(80000.00))),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.ZERO)),
                new Tax(TestUtils.formatExpectedValues(BigDecimal.valueOf(60000.00)))
        );

        final var operationsInput = Arrays.asList(
                new OperationInfo("buy", BigDecimal.valueOf(10), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(50), 10000),
                new OperationInfo("buy", BigDecimal.valueOf(20), 10000),
                new OperationInfo("sell", BigDecimal.valueOf(50), 10000)
        );

        final var actual = capitalGainsService.calculateTaxes(operationsInput);

        assertEquals(expected, actual);
    }

}
