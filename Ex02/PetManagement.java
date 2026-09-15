package ra.presentation;

import ra.entity.Cat;
import ra.entity.Dog;
import ra.entity.Pet;

import java.util.ArrayList;
import java.util.Scanner;

public class PetManagement {

    private static ArrayList<Pet> pets = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n************ QUẢN LÝ THÚ CƯNG ************");
            System.out.println("1. Hiển thị danh sách thú cưng");
            System.out.println("2. Thêm thú cưng");
            System.out.println("3. Gọi tiếng kêu");
            System.out.println("4. Xóa thú cưng");
            System.out.println("5. Tìm thú cưng theo tên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    displayPets();
                    break;

                case 2:
                    addPet(scanner);
                    break;

                case 3:
                    callSpeak(scanner);
                    break;

                case 4:
                    deletePet(scanner);
                    break;

                case 5:
                    searchPet(scanner);
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
    public static void displayPets() {

        if (pets.isEmpty()) {
            System.out.println("Danh sách thú cưng đang trống!");
            return;
        }

        System.out.println("\n===== DANH SÁCH THÚ CƯNG =====");

        for (Pet pet : pets) {
            pet.displayData();

            if (pet instanceof Dog) {
                System.out.println("Loài: Chó");
            } else if (pet instanceof Cat) {
                System.out.println("Loài: Mèo");
            }

            System.out.println("----------------------------");
        }
    }

    // 2. Thêm thú cưng
    public static void addPet(Scanner scanner) {

        System.out.println("\n===== THÊM THÚ CƯNG =====");
        System.out.println("1. Thêm chó");
        System.out.println("2. Thêm mèo");
        System.out.print("Lựa chọn: ");

        int choice = Integer.parseInt(scanner.nextLine());

        Pet pet;

        if (choice == 1) {
            pet = new Dog();
        } else if (choice == 2) {
            pet = new Cat();
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        // Nhập thông tin
        pet.inputData(scanner);

        // Kiểm tra mã đã tồn tại chưa
        if (findById(pet.getPetId()) != null) {
            System.out.println("Mã thú cưng đã tồn tại!");
            return;
        }

        pets.add(pet);

        System.out.println("Thêm thú cưng thành công!");
    }

    // 3. Gọi tiếng kêu
    public static void callSpeak(Scanner scanner) {

        System.out.print("Nhập mã thú cưng: ");
        String petId = scanner.nextLine();

        Pet pet = findById(petId);

        if (pet == null) {
            System.out.println("Mã thú cưng không tồn tại!");
            return;
        }

        // Đa hình
        pet.speak();
    }

    // 4. Xóa thú cưng
    public static void deletePet(Scanner scanner) {

        System.out.print("Nhập mã thú cưng cần xóa: ");
        String petId = scanner.nextLine();

        Pet pet = findById(petId);

        if (pet == null) {
            System.out.println("Mã thú cưng không tồn tại!");
            return;
        }

        pets.remove(pet);

        System.out.println("Xóa thú cưng thành công!");
    }

    // 5. Tìm thú cưng theo tên
    public static void searchPet(Scanner scanner) {

        System.out.print("Nhập tên thú cưng cần tìm: ");
        String keyword = scanner.nextLine();

        int count = 0;

        System.out.println("\n===== KẾT QUẢ TÌM KIẾM =====");

        for (Pet pet : pets) {

            if (pet.getPetName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                pet.displayData();

                if (pet instanceof Dog) {
                    System.out.println("Loài: Chó");
                } else if (pet instanceof Cat) {
                    System.out.println("Loài: Mèo");
                }

                System.out.println("----------------------------");

                count++;
            }
        }

        System.out.println("Tổng số thú cưng tìm thấy: " + count);
    }

    // Tìm theo mã
    public static Pet findById(String petId) {

        for (Pet pet : pets) {

            if (pet.getPetId().equalsIgnoreCase(petId)) {
                return pet;
            }
        }

        return null;
    }
}