package br.com.capitalgains;

import java.io.IOException;
import br.com.capitalgains.adapter.in.CapitalGainsController;

public class Main {
    public static void main(String[] args) throws IOException {
        new CapitalGainsController().calculateTaxes();
    }
}