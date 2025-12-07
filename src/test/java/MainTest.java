import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainMethod() {
        // Arrange
        String[] args = {};

        // Act
        Main.main(args);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Top Student:"));
        assertTrue(output.contains("Average GPA:"));
    }

    @Test
    public void testMainMethodWithNullArgs() {
        // Arrange
        String[] args = null;

        // Act
        Main.main(args);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Top Student:"));
        assertTrue(output.contains("Average GPA:"));
    }

    @Test
    public void testMainMethodWithEmptyArgs() {
        // Arrange
        String[] args = {};

        // Act
        Main.main(args);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Top Student:"));
        assertTrue(output.contains("Average GPA:"));
    }

    @Test
    public void testMainMethodWithArgs() {
        // Arrange
        String[] args = {"test", "arguments"};

        // Act
        Main.main(args);

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Top Student:"));
        assertTrue(output.contains("Average GPA:"));
    }

    @Test
    public void testMainConstructor() {
        // Arrange & Act
        Main main = new Main();

        // Assert
        assertNotNull(main);
    }
}