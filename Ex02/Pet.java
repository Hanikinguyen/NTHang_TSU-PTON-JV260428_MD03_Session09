package ra.entity;

import java.util.Scanner;

public abstract class Pet {

    // Thuộc tính
    private String petId;
    private String petName;
    private int age;

    // Constructor không tham số
    public Pet() {
    }

    // Constructor đầy đủ tham số
    public Pet(String petId, String petName, int age) {
        this.petId = petId;
        this.petName = petName;
        this.age = age;
    }

    // Getter / Setter
    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Nhập thông tin
    public void inputData(Scanner scanner) {

        System.out.print("Nhập mã thú cưng: ");
        petId = scanner.nextLine();

        System.out.print("Nhập tên thú cưng: ");
        petName = scanner.nextLine();

        System.out.print("Nhập tuổi: ");
        age = Integer.parseInt(scanner.nextLine());
    }

    // Hiển thị thông tin
    public void displayData() {

        System.out.println("Mã thú cưng: " + petId);
        System.out.println("Tên thú cưng: " + petName);
        System.out.println("Tuổi: " + age);
    }

    // Phương thức trừu tượng
    public abstract void speak();
}