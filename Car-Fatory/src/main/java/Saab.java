public class Saab implements Car {

    private final String carModel = "Saab";
    private final String carEngine = "V4 2.3T";
    private String carColor;
    private String registrationNumber;

    public Saab() {
        setRegistrationNumber(randomRegistration());
        setCarColor("White");
    }
    public Saab(String carColor) {
        setRegistrationNumber(randomRegistration());
        setCarColor(carColor);
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String getCarModel() {
        return this.carModel;
    }

    public String getCarEngine() {
        return carEngine;
    }

    @Override
    public String getCarColor() {
        return this.carColor;
    }


    public String randomRegistration()
    {
        return ("" + (char) (getLetter()) + ((char) (getLetter())) +
                ((char) (getLetter())) + "" + getNumber() + getNumber() + getNumber());
    }

    public int getNumber() {
        return (int) (Math.random() * 10);
    }

    public int getLetter() {
        return 65 + (int) (Math.random() * (90 - 65));
    }

}