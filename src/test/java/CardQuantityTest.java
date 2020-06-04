import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardQuantityTest {
    Register register = new Register();

    @Test
    public void checkNoQuantity() {
        register.createCard(7,  0);
        boolean actual = register.cards.get(7).check(register);
        assertFalse(actual);
    }

    @Test
    public void checkQuantity() {
        register.createCard(8, 4);
        boolean actual = register.cards.get(8).check(register);
        assertTrue(actual);
    }

}