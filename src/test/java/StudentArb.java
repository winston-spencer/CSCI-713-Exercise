import java.util.Random;

public class StudentArb {
    public static final Random random = new Random();
    
    private static final String[] FIRST_NAMES = {
        "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry",
        "Ivy", "Jack", "Kate", "Liam", "Maya", "Noah", "Olivia", "Paul",
        "Quinn", "Rachel", "Sam", "Tina", "Uma", "Victor", "Wendy", "Xavier",
        "Yara", "Zoe"
    };
    
    private static final String[] LAST_NAMES = {
        "Anderson", "Brown", "Clark", "Davis", "Evans", "Fisher", "Garcia",
        "Harris", "Johnson", "King", "Lewis", "Miller", "Nelson", "O'Connor",
        "Parker", "Quinn", "Robinson", "Smith", "Taylor", "Underwood",
        "Valdez", "Wilson", "Young", "Zhang"
    };

    /**
     * Generates a student with random name, age, and GPA
     */
    public static Student generate() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String fullName = firstName + " " + lastName;
        
        int age = random.nextInt(40) + 18; // Age between 18-57
        double gpa = Math.round((random.nextDouble() * 4.0) * 100.0) / 100.0; // GPA 0.00-4.00

        return new Student(fullName, age, gpa);
    }

    /**
     * Generates a student with specified GPA but random name and age
     */
    public static Student generateWithGpa(double gpa) {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String fullName = firstName + " " + lastName;
        
        int age = random.nextInt(40) + 18; // Age between 18-57
        
        return new Student(fullName, age, gpa);
    }

    /**
     * Generates a valid age (1-120)
     */
    public static int generateValidAge() {
        return random.nextInt(120) + 1; // Age between 1-120
    }

    /**
     * Generates a valid GPA (0.0-4.0)
     */
    public static double generateValidGpa() {
        return Math.round((random.nextDouble() * 4.0) * 100.0) / 100.0; // GPA 0.00-4.00
    }
}