package hichat;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    void testPrintFarewell() {
        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the method
        Ui.printFarewell();

        // Expected output
        String expectedOutput = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        // Get actual output and trim extra newlines
        String actualOutput = outputStream.toString().trim();

        // Assert that the output matches
        assertEquals(expectedOutput, actualOutput);
    }
}