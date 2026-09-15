package ra.presentation;

import ra.entity.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {

    // Danh sách sinh viên
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n************ QUẢN LÝ SINH VIÊN ************");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật thông tin sinh viên theo mã sinh viên");
            System.out.println("4. Xóa sinh viên theo mã sinh viên");
            System.out.println("5. Tìm sinh viên theo tên sinh viên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    displayStudents();
                    break;

                case 2:
                    addStudent(scanner);
                    break;

                case 3:
                    updateStudent(scanner);
                    break;

                case 4:
                    deleteStudent(scanner);
                    break;

                case 5:
                    searchStudent(scanner);
                    break;

                case 6:
                    System.out.println("Thoát chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // 1. Hiển thị danh sách
    public static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên đang trống!");
            return;
        }

        System.out.println("\n===== DANH SÁCH SINH VIÊN =====");

        for (Student student : students) {
            student.displayData();
        }
    }

    // 2. Thêm sinh viên
    public static void addStudent(Scanner scanner) {

        while (true) {

            Student student = new Student();

            student.inputData(scanner);

            students.add(student);

            System.out.println("Thêm sinh viên thành công!");

            System.out.print("Bạn có muốn thêm tiếp không? (Y/N): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    // 3. Cập nhật sinh viên
    public static void updateStudent(Scanner scanner) {

        System.out.print("Nhập mã sinh viên cần cập nhật: ");
        String studentId = scanner.nextLine();

        Student student = findById(studentId);

        if (student == null) {
            System.out.println("Mã sinh viên không tồn tại");
            return;
        }

        System.out.println("Nhập thông tin mới:");

        System.out.print("Nhập tên sinh viên: ");
        student.setStudentName(scanner.nextLine());

        System.out.print("Nhập tuổi: ");
        student.setAge(Integer.parseInt(scanner.nextLine()));

        System.out.print("Nhập chuyên ngành: ");
        student.setMajor(scanner.nextLine());

        System.out.println("Cập nhật sinh viên thành công!");
    }

    // 4. Xóa sinh viên
    public static void deleteStudent(Scanner scanner) {

        System.out.print("Nhập mã sinh viên cần xóa: ");
        String studentId = scanner.nextLine();

        Student student = findById(studentId);

        if (student == null) {
            System.out.println("Mã sinh viên không tồn tại");
            return;
        }

        students.remove(student);

        System.out.println("Xóa sinh viên thành công!");
    }

    // 5. Tìm sinh viên theo tên
    public static void searchStudent(Scanner scanner) {

        System.out.print("Nhập tên sinh viên cần tìm: ");
        String keyword = scanner.nextLine();

        int count = 0;

        System.out.println("\n===== KẾT QUẢ TÌM KIẾM =====");

        for (Student student : students) {

            if (student.getStudentName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                student.displayData();
                count++;
            }
        }

        System.out.println("Tổng số sinh viên tìm thấy: " + count);
    }

    // Tìm sinh viên theo mã
    public static Student findById(String studentId) {

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }

        return null;
    }
}