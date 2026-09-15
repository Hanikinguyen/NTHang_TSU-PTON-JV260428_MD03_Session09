package ra.entity;

import java.util.Scanner;

public class Categories implements IShop {

    // Thuộc tính
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    // Constructor không tham số
    public Categories() {
    }

    // Constructor đầy đủ tham số
    public Categories(int catalogId, String catalogName,
                      String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    // Getter / Setter
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    // Nhập thông tin danh mục
    public void inputData(Scanner scanner,
                          Categories[] arrCategories,
                          int index) {

        // Tự động sinh mã danh mục
        int maxId = 0;

        for (int i = 0; i < index; i++) {
            if (arrCategories[i] != null
                    && arrCategories[i].getCatalogId() > maxId) {

                maxId = arrCategories[i].getCatalogId();
            }
        }

        catalogId = maxId + 1;

        System.out.println("Mã danh mục: " + catalogId);

        // Nhập tên danh mục
        while (true) {

            System.out.print("Nhập tên danh mục: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Tên danh mục không được để trống!");
                continue;
            }

            if (name.length() > 50) {
                System.out.println("Tên danh mục tối đa 50 ký tự!");
                continue;
            }

            // Kiểm tra trùng tên
            boolean exists = false;

            for (int i = 0; i < index; i++) {

                if (arrCategories[i] != null
                        && arrCategories[i].getCatalogName()
                        .equalsIgnoreCase(name)) {

                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Tên danh mục đã tồn tại!");
            } else {
                catalogName = name;
                break;
            }
        }

        // Nhập mô tả
        System.out.print("Nhập mô tả: ");
        descriptions = scanner.nextLine();

        // Nhập trạng thái
        while (true) {

            System.out.print("Nhập trạng thái (true - hoạt động, false - không hoạt động): ");

            String status = scanner.nextLine().trim();

            if (status.equalsIgnoreCase("true")) {
                catalogStatus = true;
                break;
            }

            if (status.equalsIgnoreCase("false")) {
                catalogStatus = false;
                break;
            }

            System.out.println("Chỉ được nhập true hoặc false!");
        }
    }

    // Hiển thị
    @Override
    public void displayData() {

        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Tên danh mục: " + catalogName);
        System.out.println("Mô tả: " + descriptions);
        System.out.println("Trạng thái: "
                + (catalogStatus ? "Hoạt động" : "Không hoạt động"));
        System.out.println("-----------------------------");
    }
}
