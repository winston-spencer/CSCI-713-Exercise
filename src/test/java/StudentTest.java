import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    public void testConstructor() {
        // Arrange
        String expectedName = "John Doe";
        int expectedAge = 20;
        double expectedGpa = 3.5;

        // Act
        Student student = new Student(expectedName, expectedAge, expectedGpa);

        // Assert
        assertEquals(expectedName, student.getName());
        assertEquals(expectedAge, student.age);
        assertEquals(expectedGpa, student.getGpa());
    }

    @RepeatedTest(10)
    public void testConstructorWithRandomData() {
        // Arrange
        Student randomStudent = StudentArb.generate();

        // Act
        Student student = new Student(randomStudent.name, randomStudent.age, randomStudent.gpa);

        // Assert
        assertEquals(randomStudent.name, student.getName());
        assertEquals(randomStudent.age, student.age);
        assertEquals(randomStudent.gpa, student.getGpa());
    }

    @Test
    public void testConstructorWithNullName() {
        // Arrange
        String nullName = null;
        int age = 20;
        double gpa = 3.5;

        // Act
        Student student = new Student(nullName, age, gpa);

        // Assert
        assertNull(student.getName());
        assertEquals(age, student.age);
        assertEquals(gpa, student.getGpa());
    }

    @Test
    public void testConstructorWithEmptyName() {
        // Arrange
        String emptyName = "";
        int age = 25;
        double gpa = 2.8;

        // Act
        Student student = new Student(emptyName, age, gpa);

        // Assert
        assertEquals(emptyName, student.getName());
        assertEquals(age, student.age);
        assertEquals(gpa, student.getGpa());
    }

    @RepeatedTest(10)
    public void testGetName() {
        // Arrange
        Student student = StudentArb.generate();
        String expectedName = student.name;

        // Act
        String actualName = student.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetNameWithNullValue() {
        // Arrange
        Student student = new Student(null, 20, 3.0);

        // Act
        String name = student.getName();

        // Assert
        assertNull(name);
    }

    @RepeatedTest(10)
    public void testSetAgeValid() {
        // Arrange
        Student student = StudentArb.generate();
        int newAge = StudentArb.generateValidAge();

        // Act
        student.setAge(newAge);

        // Assert
        assertEquals(newAge, student.age);
    }

    @Test
    public void testSetAgeNegative() {
        // Arrange
        Student student = StudentArb.generate();
        int negativeAge = -5;

        // Act
        student.setAge(negativeAge);

        // Assert
        assertEquals(0, student.age); // Bug: Should handle this case properly
    }

    @Test
    public void testSetAgeZero() {
        // Arrange
        Student student = StudentArb.generate();
        int zeroAge = 0;

        // Act
        student.setAge(zeroAge);

        // Assert
        assertEquals(0, student.age);
    }

    @Test
    public void testSetAgeVeryHigh() {
        // Arrange
        Student student = StudentArb.generate();
        int veryHighAge = 150;

        // Act
        student.setAge(veryHighAge);

        // Assert
        assertEquals(veryHighAge, student.age); // Bug: Should validate upper bound
    }

    @Test
    public void testSetAgeEdgeCases() {
        // Arrange
        Student student = StudentArb.generate();

        // Act & Assert - Test boundary values
        student.setAge(1);
        assertEquals(1, student.age);

        student.setAge(120);
        assertEquals(120, student.age);
    }

    @RepeatedTest(10)
    public void testGetGpa() {
        // Arrange
        Student student = StudentArb.generate();
        double expectedGpa = student.gpa;

        // Act
        double actualGpa = student.getGpa();

        // Assert
        assertEquals(expectedGpa, actualGpa, 0.001);
    }

    @RepeatedTest(10)
    public void testSetGpa() {
        // Arrange
        Student student = StudentArb.generate();
        double newGpa = StudentArb.generateValidGpa();

        // Act
        student.setGpa(newGpa);

        // Assert
        assertEquals(newGpa, student.getGpa(), 0.001);
    }

    @Test
    public void testSetGpaEdgeCases() {
        // Arrange
        Student student = StudentArb.generate();

        // Act & Assert - Test boundary values
        student.setGpa(0.0);
        assertEquals(0.0, student.getGpa(), 0.001);

        student.setGpa(4.0);
        assertEquals(4.0, student.getGpa(), 0.001);
    }

    @Test
    public void testSetGpaInvalidValues() {
        // Arrange
        Student student = StudentArb.generate();

        // Act & Assert - Test invalid values (no validation exists)
        student.setGpa(-1.0);
        assertEquals(-1.0, student.getGpa(), 0.001);

        student.setGpa(5.0);
        assertEquals(5.0, student.getGpa(), 0.001);
    }

    @Test
    public void testPrintStudentInfo() {
        // Arrange
        Student student = new Student("Alice Johnson", 22, 3.7);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        student.printStudentInfo();

        // Assert
        System.setOut(originalOut);
        String expectedOutput = "Alice Johnson 22 3.7" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @RepeatedTest(10)
    public void testPrintStudentInfoWithRandomData() {
        // Arrange
        Student student = StudentArb.generate();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        student.printStudentInfo();

        // Assert
        System.setOut(originalOut);
        String expectedOutput = student.name + " " + student.age + " " + student.gpa + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintStudentInfoWithNullName() {
        // Arrange
        Student student = new Student(null, 20, 3.0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        student.printStudentInfo();

        // Assert
        System.setOut(originalOut);
        String expectedOutput = "null 20 3.0" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPublicFields() {
        // Arrange
        Student student = new Student("Test Student", 21, 3.2);
        
        // Act - Direct field access (testing the public field design flaw)
        student.name = "Modified Name";
        student.age = 25;
        student.gpa = 3.8;

        // Assert
        assertEquals("Modified Name", student.name);
        assertEquals("Modified Name", student.getName()); // Should be consistent
        assertEquals(25, student.age);
        assertEquals(3.8, student.gpa);
        assertEquals(3.8, student.getGpa()); // Should be consistent
    }

    @Test
    public void testFieldEncapsulationIssue() {
        // Arrange
        Student student = StudentArb.generate();

        // Act - Demonstrate encapsulation problem
        student.name = null; // Direct field manipulation bypasses any validation
        student.age = -100;  // Direct field manipulation bypasses setAge validation
        student.gpa = -5.0;  // Direct field manipulation bypasses any potential validation

        // Assert - This demonstrates the encapsulation issues
        assertNull(student.getName());
        assertEquals(-100, student.age);
        assertEquals(-5.0, student.getGpa());
    }
}