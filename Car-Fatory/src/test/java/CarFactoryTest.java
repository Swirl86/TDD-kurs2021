import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarFactoryTest {

    private CarFactory carFactory;
    private Car carVolvo, carSaab;

    @Before
    public void init() throws IllegalArgumentException {
        carFactory = new CarFactory();
        carVolvo = carFactory.createCarModel("Volvo", "Red");
        carSaab = carFactory.createCarModel("Saab", "Blue");
    }

    @Test
    public void new_car_from_factory() throws IllegalArgumentException {

        assertEquals("Red", carVolvo.getCarColor());
        assertEquals("Volvo", carVolvo.getCarModel());
        assertFalse((carVolvo.getCarColor().length() == 4), "This will fail");

        Car car2 = carFactory.createCarModel("Volvo");
        assertEquals("Black", car2.getCarColor());
        assertFalse((car2.getCarColor().length() == 4), "This will fail");
    }

    @Test
    public void new_car_from_factory_with_another_model() throws IllegalArgumentException {

        assertNotNull(carSaab);
        assertEquals("Blue", carSaab.getCarColor());
        assertEquals("Saab", carSaab.getCarModel());
        assertFalse((carSaab.getCarColor().length() == 5), "This will fail");

        Car carDifferentConstructor = carFactory.createCarModel("Saab");
        assertEquals("White", carDifferentConstructor.getCarColor());
        assertFalse((carDifferentConstructor.getCarColor().length() == 4), "This will fail");
    }

    @Test
    public void test_illegal_argument_exception() {

        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class,
                        () -> carFactory.createCarModel("Volkswagen"));

        assertEquals("No such model!", illegalArgumentException.getMessage());

    }

    @Test
    public void new_car_with_engine() {

        assertNotNull(carVolvo);
        assertEquals("V4 2.5D", carVolvo.getCarEngine());
        assertTrue((carVolvo.getCarEngine().length() > 1), "This will PASS");
    }

}