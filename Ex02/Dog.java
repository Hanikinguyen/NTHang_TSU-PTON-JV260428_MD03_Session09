package ra.entity;

public class Dog extends Pet {

    // Constructor không tham số
    public Dog() {
    }

    // Constructor đầy đủ tham số
    public Dog(String petId, String petName, int age) {
        super(petId, petName, age);
    }

    // Ghi đè speak()
    @Override
    public void speak() {
        System.out.println("Gâu gâu");
    }
}
