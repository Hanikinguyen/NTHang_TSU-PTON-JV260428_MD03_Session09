package ra.entity;

import java.util.Scanner;

public class Student {
    // 1. Thuộc tính
    private String studentId;
    private String studentName;
    private int age;
    private String major;

    // 2. Constructor không tham số
    public Student() {
    }

    // 3. Constructor đầy đủ tham số
    public Student(String studentId, String studentName, int age, String major) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.major = major;
    }

    // 4. Getter / Setter
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // 5. Nhập thông tin sinh viên
    public void inputData(Scanner scanner) {
        System.out.print("Nhập mã sinh viên: ");
        studentId = scanner.nextLine();

        System.out.print("Nhập tên sinh viên: ");
        studentName = scanner.nextLine();

        System.out.print("Nhập tuổi: ");
        age = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập chuyên ngành: ");
        major = scanner.nextLine();
    }

    // 6. Hiển thị thông tin sinh viên
    public void displayData() {
        System.out.println("Mã SV: " + studentId);
        System.out.println("Tên SV: " + studentName);
        System.out.println("Tuổi: " + age);
        System.out.println("Chuyên ngành: " + major);
        System.out.println("----------------------------");
    }
}