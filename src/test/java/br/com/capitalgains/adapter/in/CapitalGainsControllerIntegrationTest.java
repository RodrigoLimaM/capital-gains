package br.com.capitalgains.adapter.in;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import br.com.capitalgains.application.port.in.CalculateTaxesUseCase;
import br.com.capitalgains.service.CapitalGainsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalGainsControllerIntegrationTest {

    public static final String STOP_INPUT_VALUE = "";

    private final CapitalGainsController capitalGainsController;

    private final BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public CapitalGainsControllerIntegrationTest() {
        this.capitalGainsController = Mockito.spy(new CapitalGainsController());
    }

    @BeforeEach
    public void setup() throws IOException {
        Mockito.doReturn(bufferedReader).when(capitalGainsController).getBufferedReader();
        Mockito.doCallRealMethod().when(capitalGainsController).calculateTaxes();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    void case1() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case2() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":10000.00},{\"tax\":0.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case1AndCase2() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]\r\n" +
                "[{\"tax\":0.00},{\"tax\":10000.00},{\"tax\":0.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]")
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case3() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":1000.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 3000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case4() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case5() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":10000.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 5000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case6() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3000.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case7() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3000.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3700.00},{\"tax\":0.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}," +
                        "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 5000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 4350}," +
                        "{\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 650}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void case8() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":80000.00},{\"tax\":0.00},{\"tax\":60000.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}," +
                        "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}]")
                .thenReturn(STOP_INPUT_VALUE);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }

    @Test
    void shouldFinishExecution_whenInputIsNull() throws IOException {
        final var expected = "[{\"tax\":0.00},{\"tax\":80000.00},{\"tax\":0.00},{\"tax\":60000.00}]";

        Mockito.when(bufferedReader.readLine())
                .thenReturn("[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}," +
                        "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}," +
                        "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}]")
                .thenReturn(null);

        capitalGainsController.calculateTaxes();

        assertEquals(expected, byteArrayOutputStream.toString().trim());
    }
}
