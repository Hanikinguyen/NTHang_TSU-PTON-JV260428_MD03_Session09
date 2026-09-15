package ra.entity;

public class Cat extends Pet {

    // Constructor không tham số
    public Cat() {
    }

    // Constructor đầy đủ tham số
    public Cat(String petId, String petName, int age) {
        super(petId, petName, age);
    }

    // Ghi đè speak()
    @Override
    public void speak() {
        System.out.println("Meo meo");
    }
}
