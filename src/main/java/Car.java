import lombok.Data;

@Data
public class Car {
    private String name;
    private int rating;

    public Car(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name;
    }
}
