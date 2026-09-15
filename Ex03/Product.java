package ra.entity;

import java.util.Scanner;

public class Product implements IShop {

    // Thuộc tính
    private String productId;
    private String productName;
    private float price;
    private String description;
    private int catalogId;
    private int productStatus;

    // Constructor không tham số
    public Product() {
    }

    // Constructor đầy đủ tham số
    public Product(String productId, String productName,
                   float price, String description,
                   int catalogId, int productStatus) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    // Getter / Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    // Nhập sản phẩm
    public void inputData(Scanner scanner,
                          Product[] arrProduct,
                          int indexProduct,
                          Categories[] arrCategories,
                          int indexCatalog) {

        // =========================
        // Nhập mã sản phẩm
        // =========================
        while (true) {

            System.out.print("Nhập mã sản phẩm: ");
            String id = scanner.nextLine().trim();

            if (!id.matches("^[CSA][0-9]{3}$")) {
                System.out.println(
                        "Mã sản phẩm phải có dạng C001, S001 hoặc A001!");
                continue;
            }

            boolean exists = false;

            for (int i = 0; i < indexProduct; i++) {

                if (arrProduct[i] != null
                        && arrProduct[i].getProductId()
                        .equalsIgnoreCase(id)) {

                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Mã sản phẩm đã tồn tại!");
            } else {
                productId = id;
                break;
            }
        }

        // =========================
        // Nhập tên sản phẩm
        // =========================
        while (true) {

            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println(
                        "Tên sản phẩm phải từ 10 đến 50 ký tự!");
                continue;
            }

            boolean exists = false;

            for (int i = 0; i < indexProduct; i++) {

                if (arrProduct[i] != null
                        && arrProduct[i].getProductName()
                        .equalsIgnoreCase(name)) {

                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Tên sản phẩm đã tồn tại!");
            } else {
                productName = name;
                break;
            }
        }

        // =========================
        // Nhập giá
        // =========================
        while (true) {

            try {
                System.out.print("Nhập giá sản phẩm: ");
                float inputPrice = Float.parseFloat(scanner.nextLine());

                if (inputPrice <= 0) {
                    System.out.println("Giá sản phẩm phải lớn hơn 0!");
                    continue;
                }

                price = inputPrice;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Giá phải là số!");
            }
        }

        // =========================
        // Mô tả
        // =========================
        System.out.print("Nhập mô tả sản phẩm: ");
        description = scanner.nextLine();

        // =========================
        // Chọn danh mục
        // =========================
        while (true) {

            System.out.println("\n===== DANH MỤC ĐANG CÓ =====");

            boolean hasCategory = false;

            for (int i = 0; i < indexCatalog; i++) {

                if (arrCategories[i] != null) {

                    hasCategory = true;

                    System.out.println(
                            arrCategories[i].getCatalogId()
                                    + " - "
                                    + arrCategories[i].getCatalogName());
                }
            }

            if (!hasCategory) {
                System.out.println("Chưa có danh mục!");
                return;
            }

            try {

                System.out.print("Nhập mã danh mục: ");
                int inputCatalogId =
                        Integer.parseInt(scanner.nextLine());

                boolean found = false;

                for (int i = 0; i < indexCatalog; i++) {

                    if (arrCategories[i] != null
                            && arrCategories[i].getCatalogId()
                            == inputCatalogId) {

                        catalogId = inputCatalogId;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                }

                System.out.println("Mã danh mục không tồn tại!");

            } catch (NumberFormatException e) {

                System.out.println("Mã danh mục phải là số!");
            }
        }

        // =========================
        // Trạng thái sản phẩm
        // =========================
        while (true) {

            try {

                System.out.print(
                        "Nhập trạng thái (0-Đang bán, 1-Hết hàng, 2-Không bán): ");

                int status =
                        Integer.parseInt(scanner.nextLine());

                if (status >= 0 && status <= 2) {

                    productStatus = status;
                    break;
                }

                System.out.println(
                        "Trạng thái chỉ được nhập 0, 1 hoặc 2!");

            } catch (NumberFormatException e) {

                System.out.println("Trạng thái phải là số!");
            }
        }
    }

    // Hiển thị
    @Override
    public void displayData() {

        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá: " + price);
        System.out.println("Mô tả: " + description);
        System.out.println("Mã danh mục: " + catalogId);

        String status;

        switch (productStatus) {

            case 0:
                status = "Đang bán";
                break;

            case 1:
                status = "Hết hàng";
                break;

            default:
                status = "Không bán";
        }

        System.out.println("Trạng thái: " + status);
        System.out.println("-----------------------------");
    }
}
