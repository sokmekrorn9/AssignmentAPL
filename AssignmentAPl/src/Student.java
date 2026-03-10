import java.util.HashMap;
import java.util.Map;

class Student {
    private String studentId;
    private String name;
    private Map<String, Double> subjects; // Use subject as a key and score as value

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.subjects = new HashMap<>();
    }

    // Update or add a subject score
    public void updateScore(String subject, double score) {
        subjects.put(subject, score);
    }

    public void deleteSubject(String subject) {
        //Check subjects
        if (subjects.containsKey(subject)) {
            subjects.remove(subject);
            System.out.println("Subject (" + subject + ") is  removed.");
        } else {
            System.out.println("Subject not found.");
        }
    }

    // Convert score to grade point
    private double getGradePoint(double score) {
        if (score >= 90) return 4.0;
        else if (score >= 80) return 3.0;
        else if (score >= 70) return 2.0;
        else if (score >= 60) return 1.0;
        else return 0.0;
    }

    // Calculate GPA
    public double calculateGPA() {
        //Check subject condition
        if (subjects.isEmpty()) return 0.0;

        double totalGradePoints = 0.0;
        //Access each element to get score
        for (double score : subjects.values()) {
            totalGradePoints += getGradePoint(score);
        }
        return totalGradePoints / subjects.size();
    }

    public String getPassFailStatus() {
        return calculateGPA() >= 2.0 ? "Pass" : "Fail";
    }

    public void printTranscript() {
        System.out.println("\n--- Transcript ---");
        System.out.println("Name: " + name + " (ID: " + studentId + ")");
        System.out.println("Subjects:");

        if (subjects.isEmpty()) {
            System.out.println("  No subjects recorded.");
        } else {
            //Access each element to get key and value
            for (Map.Entry<String, Double> entry : subjects.entrySet()) {
                String subject = entry.getKey();
                double score = entry.getValue();
                System.out.println("  -" + subject + " : Score = " + score + " | Grade Point = " + getGradePoint(score));
            }
        }

        System.out.println("Total GPA: " + calculateGPA());
        System.out.println("Status: " + getPassFailStatus());
        System.out.println("------------------\n");
    }
}

// Class to manage the overall system and multiple students
class GPASystem {
    private Map<String, Student> students; //Use student id as key and Student class as value

    public GPASystem() {
        students = new HashMap<>();
    }

    public void addStudent(String studentId, String name) {
        if (!students.containsKey(studentId)) {
            students.put(studentId, new Student(studentId, name));
            System.out.println("Student " + name + " added successfully.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    public void updateScore(String studentId, String subject, double score) {
        Student student = students.get(studentId);
        student.updateScore(subject, score);
        System.out.println("Score updated for student id " + studentId + " in " + subject);
    }

    public void deleteSubject(String studentId, String subject) {
        Student student = students.get(studentId);
        student.deleteSubject(subject);
    }

    public void viewTranscript(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            student.printTranscript();
        } else {
            System.out.println("Error: Student not found.");
        }
    }

    public boolean isContainKey(String studentId){
        return students.containsKey(studentId) ? true : false;
    }
}
