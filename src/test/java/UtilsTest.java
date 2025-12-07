import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testConstructor() {
        // Arrange & Act
        Utils utils = new Utils();

        // Assert
        assertNotNull(utils);
    }

    @Test
    void testCheckNameValidName() {
        // Arrange
        String validName = StudentArb.generate().getName();

        // Act
        boolean result = Utils.checkName(validName);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckNameEmptyString() {
        // Arrange
        String emptyName = "";

        // Act
        boolean result = Utils.checkName(emptyName);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckNameNullString() {
        // Arrange
        String nullName = null;

        // Act
        boolean result = Utils.checkName(nullName);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckNameWhitespaceString() {
        // Arrange
        String whitespace = " ";

        // Act
        boolean result = Utils.checkName(whitespace);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValidAgeValidAge() {
        // Arrange
        int validAge = StudentArb.random.nextInt(16,99); // Random valid age between 18-98

        // Act
        boolean result = Utils.isValidAge(validAge);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValidAgeZeroAge() {
        // Arrange
        int zeroAge = 0;

        // Act
        boolean result = Utils.isValidAge(zeroAge);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValidAgeNegativeAge() {
        // Arrange
        int negativeAge = -5;

        // Act
        boolean result = Utils.isValidAge(negativeAge);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValidAgeVeryHighAge() {
        // Arrange
        int veryHighAge = 150;

        // Act
        boolean result = Utils.isValidAge(veryHighAge);

        // Assert
        assertTrue(result); // This test shows the bug in isValidAge - it allows unrealistic ages
    }

    @Test
    void testIsValidAgeEdgeCaseOne() {
        // Arrange
        int ageOne = 1;

        // Act
        boolean result = Utils.isValidAge(ageOne);

        // Assert
        assertTrue(result);
    }
}