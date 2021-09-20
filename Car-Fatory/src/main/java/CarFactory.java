public class CarFactory {
    public Car createCarModel(String model) throws IllegalArgumentException {
        return switch (model.toLowerCase()) {
            case "volvo" -> new Volvo();
            case "saab" -> new Saab();
            default -> throw new IllegalArgumentException("No such model!");
        };
    }

    public Car createCarModel(String model, String color) throws IllegalArgumentException {
        return switch (model.toLowerCase()) {
            case "volvo" -> new Volvo(color);
            case "saab" -> new Saab(color);
            default -> throw new IllegalArgumentException("No such model!");
        };
    }
}
