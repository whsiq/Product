import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product P1;

    @BeforeEach
    void setUp() {
        P1 = new Product("Pizza", "Plain Cheese Pizza", "000001", 9.99);
    }

    @Test
    void setName() {
        P1.setName("Ramen Noodles");
        assertEquals("Ramen Noodles", P1.getName());
    }

    @Test
    void setDescription() {
        P1.setDescription("Spicy ramen noodles");
        assertEquals("Spicy ramen noodles", P1.getDescription());
    }

    @Test
    void setID() {
        P1.setID("000002");
        assertEquals("000002", P1.getID());
    }

    @Test
    void setCost() {
        P1.setCost(1.99);
        assertEquals(1.99, P1.getCost());
    }

    @Test
    void toCSVDataRecord() {
        P1.setName("Ramen Noodles");
        P1.setDescription("Spicy ramen noodles");
        P1.setID("000002");
        P1.setCost(1.99);
        assertEquals("000002, Ramen Noodles, Spicy ramen noodles, 1.99", P1.toCSVDataRecord());
    }
}