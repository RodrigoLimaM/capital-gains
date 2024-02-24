package br.com.capitalgains.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestUtils {

    public static BigDecimal formatExpectedValues(final BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
