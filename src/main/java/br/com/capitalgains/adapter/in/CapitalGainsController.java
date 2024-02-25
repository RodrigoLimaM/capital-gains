package br.com.capitalgains.adapter.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import br.com.capitalgains.application.port.in.CalculateTaxesUseCase;
import br.com.capitalgains.domain.OperationInfo;
import br.com.capitalgains.service.CapitalGainsService;
import br.com.capitalgains.utils.JsonParser;

public class CapitalGainsController {

    private final CalculateTaxesUseCase calculateTaxesUseCase;

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public CapitalGainsController() {
        this.calculateTaxesUseCase = new CapitalGainsService();
    }

    public void calculateTaxes() throws IOException {
        readOperationInfos().forEach(operationInfos -> {
            final var taxes = calculateTaxesUseCase.calculateTaxes(operationInfos);
            try {
                System.out.println(JsonParser.parseOutput(taxes));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<List<OperationInfo>> readOperationInfos() throws IOException {
        List<List<OperationInfo>> operationInfos = new ArrayList<>();
        String input;
        do {
            input = getBufferedReader().readLine();
            if (isNotEndOfFile(input)) {
                operationInfos.add(JsonParser.parseInput(input));
            }
        } while (isNotEndOfFile(input));
        return operationInfos;
    }

    private static boolean isNotEndOfFile(String input) {
        return input != null && !input.isEmpty();
    }
}
