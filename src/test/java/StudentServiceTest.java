
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

    private StudentService service;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @RepeatedTest(10)
    void testAddStudent() {
        // Arrange
        Student student = StudentArb.generate();
        double expectedAverageGpa = student.getGpa();
        double expectedDelta = 0.001;

        // Act
        service.addStudent(student);

        // Assert
        assertEquals(expectedAverageGpa, service.calculateAverageGpa(), expectedDelta);
    }

    @RepeatedTest(10)
    void testGetTopStudent() {
        // Arrange
        double topGpa = 3.9;
        double expectedDelta = 0.001;
        Student s1 = StudentArb.generateWithGpa(3.5);
        Student s2 = StudentArb.generateWithGpa(topGpa);
        Student s3 = StudentArb.generateWithGpa(3.2);
        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        // Act
        Student top = service.getTopStudent();

        // Assert
        assertEquals(s2.getName(), top.getName());
        assertEquals(topGpa, top.getGpa(), expectedDelta);
    }

    @Test
    void testGetTopStudentEmptyList() {
        // Arrange
        // Empty service

        // Act & Assert
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            service.getTopStudent();
        });

        // Verify the exception was actually thrown
        assertEquals(IndexOutOfBoundsException.class, exception.getClass());
    }

    @RepeatedTest(10)
    void testGetTopStudentSingleStudent() {
        // Arrange
        double expectedDelta = 0.001;
        Student student = StudentArb.generate();
        service.addStudent(student);

        // Act
        Student top = service.getTopStudent();

        // Assert
        assertEquals(student.getName(), top.getName());
        assertEquals(student.getGpa(), top.getGpa(), expectedDelta);
    }

    @RepeatedTest(10)
    void testCalculateAverageGpa() {
        // Arrange
        int listSize = StudentArb.random.nextInt(6) + 5; // Random size between 5 and 10
        double expectedDelta = 0.001;
        double totalGpa = 0.0;

        for (int i = 0; i < listSize; i++) {
            Student student = StudentArb.generate();
            service.addStudent(student);
            totalGpa += student.getGpa();
        }

        double expectedAverageGpa = totalGpa / listSize;

        // Act
        double avg = service.calculateAverageGpa();

        // Assert
        assertEquals(expectedAverageGpa, avg, expectedDelta);
    }

    @RepeatedTest(10)
    void testCalculateAverageGpaDifferentGpas() {
        // Arrange
        int listSize = StudentArb.random.nextInt(6) + 5; // Random size between 5 and 10
        double expectedDelta = 0.001;
        double totalGpa = 0.0;

        for (int i = 0; i < listSize; i++) {
            Student student = StudentArb.generate();
            service.addStudent(student);
            totalGpa += student.getGpa();
        }

        double expectedAverageGpa = totalGpa / listSize;

        // Act
        double avg = service.calculateAverageGpa();

        // Assert
        assertEquals(expectedAverageGpa, avg, expectedDelta);
    }

    @Test
    void testCalculateAverageGpaEmptyList() {
        // Arrange
        // Empty service
        double expectedAverageGpa = 0.0;
        double expectedDelta = 0.001;

        // Act
        double avg = service.calculateAverageGpa();

        // Assert
        assertEquals(expectedAverageGpa, avg, expectedDelta);
    }

    @Test
    void testRemoveStudentByName() {
        // Arrange
        int listSize = StudentArb.random.nextInt(6) + 5; // Random size between 5 and 10
        double expectedDelta = 0.001;
        Student[] students = new Student[listSize];
        double totalGpaBeforeRemoval = 0.0;

        for (int i = 0; i < listSize; i++) {
            students[i] = StudentArb.generate();
            service.addStudent(students[i]);
            totalGpaBeforeRemoval += students[i].getGpa();
        }

        // Select a random student to remove
        int removeIndex = StudentArb.random.nextInt(listSize);
        Student studentToRemove = students[removeIndex];
        String nameToRemove = studentToRemove.getName();

        // Calculate expected GPA after removal
        double totalGpaAfterRemoval = totalGpaBeforeRemoval - studentToRemove.getGpa();
        double expectedAverageGpa = totalGpaAfterRemoval / (listSize - 1);

        // Act
        service.removeStudentByName(nameToRemove);

        // Assert
        assertEquals(expectedAverageGpa, service.calculateAverageGpa(), expectedDelta);
    }

    @Test
    void testRemoveStudentByNameNotFound() {
        // Arrange
        int listSize = StudentArb.random.nextInt(6) + 5; // Random size between 5 and 10
        double expectedDelta = 0.001;
        double totalGpa = 0.0;

        for (int i = 0; i < listSize; i++) {
            Student student = StudentArb.generate();
            service.addStudent(student);
            totalGpa += student.getGpa();
        }

        double expectedAverageGpa = totalGpa / listSize;
        String nonExistentName = "NonExistentStudent";

        // Act
        service.removeStudentByName(nonExistentName);

        // Assert
        assertEquals(expectedAverageGpa, service.calculateAverageGpa(), expectedDelta);
    }

    @Test
    void testRemoveStudentByNameEmptyList() {
        // Arrange
        // Empty service
        double expectedAverageGpa = 0.0;
        double expectedDelta = 0.001;
        String nameToRemove = "Alice";

        // Act
        service.removeStudentByName(nameToRemove);

        // Assert
        assertEquals(expectedAverageGpa, service.calculateAverageGpa(), expectedDelta);
    }

    @Test
    void testGetTopStudentWithEqualGpas() {
        // Arrange
        double sameGpa = 3.5;
        double expectedDelta = 0.001;
        Student s1 = StudentArb.generateWithGpa(sameGpa);
        Student s2 = StudentArb.generateWithGpa(sameGpa); // Same GPA as s1
        Student s3 = StudentArb.generateWithGpa(3.2);     // Lower GPA
        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        // Act
        Student top = service.getTopStudent();

        // Assert - Should return the first student with the highest GPA
        // This tests the boundary condition where s.getGpa() > top.getGpa() vs >=
        assertEquals(s1.getName(), top.getName(), "Should return first student when GPAs are equal");
        assertEquals(sameGpa, top.getGpa(), expectedDelta);
    }
}
