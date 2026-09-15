package ra.presentation;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {

    // Mảng danh mục tối đa 100
    private static Categories[] arrCategories = new Categories[100];

    // Mảng sản phẩm tối đa 100
    private static Product[] arrProduct = new Product[100];

    // Số lượng danh mục hiện tại
    private static int indexCatalog = 0;

    // Số lượng sản phẩm hiện tại
    private static int indexProduct = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n*************** SHOP MENU ***************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    categoryMenu(scanner);
                    break;

                case 2:
                    productMenu(scanner);
                    break;

                case 3:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // =====================================================
    // CATEGORY MENU
    // =====================================================

    public static void categoryMenu(Scanner scanner) {

        while (true) {

            System.out.println(
                    "\n*************** CATEGORIES MENU ***************");

            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");

            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addCategories(scanner);
                    break;

                case 2:
                    displayCategories();
                    break;

                case 3:
                    updateCategory(scanner);
                    break;

                case 4:
                    deleteCategory(scanner);
                    break;

                case 5:
                    changeCategoryStatus(scanner);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // =====================================================
    // THÊM NHIỀU CATEGORY
    // =====================================================

    public static void addCategories(Scanner scanner) {

        if (indexCatalog >= 100) {
            System.out.println("Danh sách danh mục đã đầy!");
            return;
        }

        System.out.print("Bạn muốn thêm bao nhiêu danh mục: ");

        int number = Integer.parseInt(scanner.nextLine());

        if (number <= 0) {
            System.out.println("Số lượng phải lớn hơn 0!");
            return;
        }

        if (indexCatalog + number > 100) {
            System.out.println("Chỉ có thể thêm tối đa "
                    + (100 - indexCatalog) + " danh mục!");
            return;
        }

        for (int i = 0; i < number; i++) {

            System.out.println("\n===== DANH MỤC THỨ "
                    + (i + 1) + " =====");

            Categories category = new Categories();

            category.inputData(
                    scanner,
                    arrCategories,
                    indexCatalog);

            arrCategories[indexCatalog] = category;

            indexCatalog++;
        }

        System.out.println("Thêm danh mục thành công!");
    }

    // =====================================================
    // HIỂN THỊ CATEGORY
    // =====================================================

    public static void displayCategories() {

        if (indexCatalog == 0) {
            System.out.println("Chưa có danh mục!");
            return;
        }

        System.out.println("\n===== DANH SÁCH DANH MỤC =====");

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i] != null) {
                arrCategories[i].displayData();
            }
        }
    }

    // =====================================================
    // CẬP NHẬT CATEGORY
    // =====================================================

    public static void updateCategory(Scanner scanner) {

        System.out.print("Nhập mã danh mục cần cập nhật: ");

        int id = Integer.parseInt(scanner.nextLine());

        Categories category = findCategoryById(id);

        if (category == null) {
            System.out.println("Mã danh mục không tồn tại!");
            return;
        }

        System.out.print("Nhập tên danh mục mới: ");
        String name = scanner.nextLine().trim();

        while (name.length() == 0 || name.length() > 50) {

            System.out.println(
                    "Tên danh mục phải có tối đa 50 ký tự!");

            System.out.print("Nhập lại tên danh mục: ");
            name = scanner.nextLine().trim();
        }

        // Kiểm tra tên trùng
        while (isCategoryNameExists(name, id)) {

            System.out.println("Tên danh mục đã tồn tại!");

            System.out.print("Nhập lại tên danh mục: ");
            name = scanner.nextLine().trim();
        }

        category.setCatalogName(name);

        System.out.print("Nhập mô tả mới: ");
        category.setDescriptions(scanner.nextLine());

        while (true) {

            System.out.print(
                    "Nhập trạng thái mới (true/false): ");

            String status = scanner.nextLine();

            if (status.equalsIgnoreCase("true")) {

                category.setCatalogStatus(true);
                break;

            } else if (status.equalsIgnoreCase("false")) {

                category.setCatalogStatus(false);
                break;

            } else {

                System.out.println(
                        "Chỉ được nhập true hoặc false!");
            }
        }

        System.out.println("Cập nhật danh mục thành công!");
    }

    // =====================================================
    // XÓA CATEGORY
    // =====================================================

    public static void deleteCategory(Scanner scanner) {

        System.out.print("Nhập mã danh mục cần xóa: ");

        int id = Integer.parseInt(scanner.nextLine());

        int categoryIndex = findCategoryIndexById(id);

        if (categoryIndex == -1) {

            System.out.println("Mã danh mục không tồn tại!");
            return;
        }

        // Kiểm tra category có sản phẩm hay không
        if (hasProductByCategoryId(id)) {

            System.out.println(
                    "Không thể xóa danh mục vì danh mục đang chứa sản phẩm!");

            return;
        }

        // Dồn mảng sang trái
        for (int i = categoryIndex; i < indexCatalog - 1; i++) {

            arrCategories[i] = arrCategories[i + 1];
        }

        arrCategories[indexCatalog - 1] = null;

        indexCatalog--;

        System.out.println("Xóa danh mục thành công!");
    }

    // =====================================================
    // ĐỔI TRẠNG THÁI CATEGORY
    // =====================================================

    public static void changeCategoryStatus(Scanner scanner) {

        System.out.print(
                "Nhập mã danh mục cần cập nhật trạng thái: ");

        int id = Integer.parseInt(scanner.nextLine());

        Categories category = findCategoryById(id);

        if (category == null) {

            System.out.println("Mã danh mục không tồn tại!");
            return;
        }

        category.setCatalogStatus(
                !category.isCatalogStatus());

        System.out.println(
                "Cập nhật trạng thái thành công!");

        System.out.println(
                "Trạng thái mới: "
                        + (category.isCatalogStatus()
                        ? "Hoạt động"
                        : "Không hoạt động"));
    }

    // =====================================================
    // PRODUCT MENU
    // =====================================================

    public static void productMenu(Scanner scanner) {

        while (true) {

            System.out.println(
                    "\n*************** PRODUCT MANAGEMENT ***************");

            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a - b");
            System.out.println("8. Thoát");

            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addProducts(scanner);
                    break;

                case 2:
                    displayProducts();
                    break;

                case 3:
                    sortProductsByPrice();
                    break;

                case 4:
                    updateProduct(scanner);
                    break;

                case 5:
                    deleteProduct(scanner);
                    break;

                case 6:
                    searchProductByName(scanner);
                    break;

                case 7:
                    searchProductByPrice(scanner);
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // =====================================================
    // THÊM PRODUCT
    // =====================================================

    public static void addProducts(Scanner scanner) {

        if (indexProduct >= 100) {

            System.out.println("Danh sách sản phẩm đã đầy!");
            return;
        }

        if (indexCatalog == 0) {

            System.out.println(
                    "Chưa có danh mục. Vui lòng tạo danh mục trước!");
            return;
        }

        System.out.print("Bạn muốn thêm bao nhiêu sản phẩm: ");

        int number = Integer.parseInt(scanner.nextLine());

        if (number <= 0) {

            System.out.println(
                    "Số lượng phải lớn hơn 0!");

            return;
        }

        if (indexProduct + number > 100) {

            System.out.println(
                    "Chỉ có thể thêm tối đa "
                            + (100 - indexProduct)
                            + " sản phẩm!");

            return;
        }

        for (int i = 0; i < number; i++) {

            System.out.println(
                    "\n===== SẢN PHẨM THỨ "
                            + (i + 1) + " =====");

            Product product = new Product();

            product.inputData(
                    scanner,
                    arrProduct,
                    indexProduct,
                    arrCategories,
                    indexCatalog);

            arrProduct[indexProduct] = product;

            indexProduct++;
        }

        System.out.println("Thêm sản phẩm thành công!");
    }

    // =====================================================
    // HIỂN THỊ PRODUCT
    // =====================================================

    public static void displayProducts() {

        if (indexProduct == 0) {

            System.out.println(
                    "Chưa có sản phẩm!");

            return;
        }

        System.out.println(
                "\n===== DANH SÁCH SẢN PHẨM =====");

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i] != null) {
                arrProduct[i].displayData();
            }
        }
    }

    // =====================================================
    // SORT PRODUCT
    // =====================================================

    public static void sortProductsByPrice() {

        if (indexProduct < 2) {

            System.out.println(
                    "Không đủ sản phẩm để sắp xếp!");

            return;
        }

        for (int i = 0; i < indexProduct - 1; i++) {

            for (int j = 0; j < indexProduct - i - 1; j++) {

                if (arrProduct[j].getPrice()
                        > arrProduct[j + 1].getPrice()) {

                    Product temp = arrProduct[j];

                    arrProduct[j] = arrProduct[j + 1];

                    arrProduct[j + 1] = temp;
                }
            }
        }

        System.out.println(
                "Đã sắp xếp sản phẩm theo giá tăng dần!");

        displayProducts();
    }

    // =====================================================
    // UPDATE PRODUCT
    // =====================================================

    public static void updateProduct(Scanner scanner) {

        System.out.print(
                "Nhập mã sản phẩm cần cập nhật: ");

        String id = scanner.nextLine();

        Product product = findProductById(id);

        if (product == null) {

            System.out.println(
                    "Mã sản phẩm không tồn tại!");

            return;
        }

        // Tên
        while (true) {

            System.out.print(
                    "Nhập tên sản phẩm mới: ");

            String name = scanner.nextLine().trim();

            if (name.length() < 10
                    || name.length() > 50) {

                System.out.println(
                        "Tên sản phẩm phải từ 10 đến 50 ký tự!");

                continue;
            }

            if (isProductNameExists(name, id)) {

                System.out.println(
                        "Tên sản phẩm đã tồn tại!");

                continue;
            }

            product.setProductName(name);
            break;
        }

        // Giá
        while (true) {

            try {

                System.out.print(
                        "Nhập giá sản phẩm mới: ");

                float price =
                        Float.parseFloat(scanner.nextLine());

                if (price <= 0) {

                    System.out.println(
                            "Giá phải lớn hơn 0!");

                    continue;
                }

                product.setPrice(price);
                break;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Giá phải là số!");
            }
        }

        // Mô tả
        System.out.print(
                "Nhập mô tả mới: ");

        product.setDescription(
                scanner.nextLine());

        // Chọn category
        while (true) {

            displayCategories();

            try {

                System.out.print(
                        "Nhập mã danh mục mới: ");

                int catalogId =
                        Integer.parseInt(scanner.nextLine());

                if (findCategoryById(catalogId) != null) {

                    product.setCatalogId(catalogId);
                    break;

                } else {

                    System.out.println(
                            "Mã danh mục không tồn tại!");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Mã danh mục phải là số!");
            }
        }

        // Status
        while (true) {

            try {

                System.out.print(
                        "Nhập trạng thái (0-Đang bán, 1-Hết hàng, 2-Không bán): ");

                int status =
                        Integer.parseInt(scanner.nextLine());

                if (status >= 0 && status <= 2) {

                    product.setProductStatus(status);
                    break;

                } else {

                    System.out.println(
                            "Chỉ được nhập 0, 1 hoặc 2!");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Trạng thái phải là số!");
            }
        }

        System.out.println(
                "Cập nhật sản phẩm thành công!");
    }

    // =====================================================
    // DELETE PRODUCT
    // =====================================================

    public static void deleteProduct(Scanner scanner) {

        System.out.print(
                "Nhập mã sản phẩm cần xóa: ");

        String id = scanner.nextLine();

        int productIndex =
                findProductIndexById(id);

        if (productIndex == -1) {

            System.out.println(
                    "Mã sản phẩm không tồn tại!");

            return;
        }

        for (int i = productIndex;
             i < indexProduct - 1;
             i++) {

            arrProduct[i] = arrProduct[i + 1];
        }

        arrProduct[indexProduct - 1] = null;

        indexProduct--;

        System.out.println(
                "Xóa sản phẩm thành công!");
    }

    // =====================================================
    // SEARCH PRODUCT BY NAME
    // =====================================================

    public static void searchProductByName(
            Scanner scanner) {

        System.out.print(
                "Nhập tên sản phẩm cần tìm: ");

        String keyword =
                scanner.nextLine().toLowerCase();

        int count = 0;

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i].getProductName()
                    .toLowerCase()
                    .contains(keyword)) {

                arrProduct[i].displayData();

                count++;
            }
        }

        System.out.println(
                "Tổng số sản phẩm tìm thấy: " + count);
    }

    // =====================================================
    // SEARCH PRODUCT BY PRICE
    // =====================================================

    public static void searchProductByPrice(
            Scanner scanner) {

        try {

            System.out.print(
                    "Nhập giá thấp nhất: ");

            float a =
                    Float.parseFloat(scanner.nextLine());

            System.out.print(
                    "Nhập giá cao nhất: ");

            float b =
                    Float.parseFloat(scanner.nextLine());

            if (a > b) {

                float temp = a;
                a = b;
                b = temp;
            }

            int count = 0;

            System.out.println(
                    "\n===== SẢN PHẨM TRONG KHOẢNG GIÁ =====");

            for (int i = 0; i < indexProduct; i++) {

                float price =
                        arrProduct[i].getPrice();

                if (price >= a && price <= b) {

                    arrProduct[i].displayData();

                    count++;
                }
            }

            System.out.println(
                    "Tổng số sản phẩm tìm thấy: "
                            + count);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Giá phải là số!");
        }
    }

    // =====================================================
    // FIND CATEGORY BY ID
    // =====================================================

    public static Categories findCategoryById(int id) {

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i] != null
                    && arrCategories[i].getCatalogId() == id) {

                return arrCategories[i];
            }
        }

        return null;
    }

    // =====================================================
    // FIND CATEGORY INDEX
    // =====================================================

    public static int findCategoryIndexById(int id) {

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i] != null
                    && arrCategories[i].getCatalogId() == id) {

                return i;
            }
        }

        return -1;
    }

    // =====================================================
    // CHECK CATEGORY NAME
    // =====================================================

    public static boolean isCategoryNameExists(
            String name,
            int currentId) {

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i] != null
                    && arrCategories[i]
                    .getCatalogId() != currentId
                    && arrCategories[i]
                    .getCatalogName()
                    .equalsIgnoreCase(name)) {

                return true;
            }
        }

        return false;
    }

    // =====================================================
    // CHECK CATEGORY HAS PRODUCT
    // =====================================================

    public static boolean hasProductByCategoryId(
            int catalogId) {

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i] != null
                    && arrProduct[i]
                    .getCatalogId() == catalogId) {

                return true;
            }
        }

        return false;
    }

    // =====================================================
    // FIND PRODUCT BY ID
    // =====================================================

    public static Product findProductById(
            String id) {

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i] != null
                    && arrProduct[i]
                    .getProductId()
                    .equalsIgnoreCase(id)) {

                return arrProduct[i];
            }
        }

        return null;
    }

    // =====================================================
    // FIND PRODUCT INDEX
    // =====================================================

    public static int findProductIndexById(
            String id) {

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i] != null
                    && arrProduct[i]
                    .getProductId()
                    .equalsIgnoreCase(id)) {

                return i;
            }
        }

        return -1;
    }

    // =====================================================
    // CHECK PRODUCT NAME
    // =====================================================

    public static boolean isProductNameExists(
            String name,
            String currentId) {

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i] != null
                    && !arrProduct[i]
                    .getProductId()
                    .equalsIgnoreCase(currentId)
                    && arrProduct[i]
                    .getProductName()
                    .equalsIgnoreCase(name)) {

                return true;
            }
        }

        return false;
    }
}
