import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardMoneyTest {

    Register register = new Register();

    @Test
    public void checkOutReg() {
        CardMoney card = new CardMoney(1, true, 5);
        boolean actual = card.check(register);
        assertFalse(actual);
    }

    @Test
    public void checkInReg() {
        register.createCard(2, true, 5);
        boolean actual = register.cards.get(2).check(register);
        assertTrue(actual);
    }

    @Test
    public void checkNoMoney() {
        register.createCard(2, false, 3);
        boolean actual = register.cards.get(2).check(register);
        assertFalse(actual);
    }

    @Test
    public void checkMoney() {
        register.createCard(2, false, 5);
        boolean actual = register.cards.get(2).check(register);
        assertTrue(actual);
    }

    @Test
    public void checkNoMoneyPilga() {
        register.createCard(2, true, 1);
        boolean actual = register.cards.get(2).check(register);
        assertFalse(actual);
    }

    @Test
    public void checkMoneyPilga() {
        register.createCard(2, true, 5);
        boolean actual = register.cards.get(2).check(register);
        assertTrue(actual);
    }
}