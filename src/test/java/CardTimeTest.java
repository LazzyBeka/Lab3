import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTimeTest {
    Register register = new Register();

    @Test
    public void checkNoTime() {
        LocalDate date = LocalDate.now();
        register.createCard(1, date.plusDays(-5));
        boolean actual = register.cards.get(1).check(register);
        assertFalse(actual);
    }

    @Test
    public void checkTime() {
        LocalDate date = LocalDate.now();
        register.createCard(1, date.plusDays(5));
        boolean actual = register.cards.get(1).check(register);
        assertTrue(actual);
    }
}