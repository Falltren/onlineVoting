public class ValidationData {

    public boolean validateCarCount(int count) {
        return count <= 99;
    }

    public boolean validateCarBrand(String brand) {
        return brand.matches("[A-zА-я-\\s]+");
    }

    public boolean validateCarModel(String model) {
        return model.matches("[A-zА-я\\d-]+");
    }

    public boolean validateNameLength(String name) {
        String[] words = name.split("\\s+");
        return words.length > 1 && words.length < 4;
    }
}
