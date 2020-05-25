import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TurnstileTest {
    Register register = new Register();
    Turnstile turnstile = new Turnstile();

    @Test
    public void visitSimp() {
        CardMoney card = register.createCard(9, false, 5);
        turnstile.visit(card, register);
        int actual = card.getMoney();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void visitSimpNo() {
        CardMoney card = register.createCard(10, false, 3);
        turnstile.visit(card, register);
        int actual = card.getMoney();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void visitQuantity() {
        CardQuantity card = register.createCard(13, 1);
        turnstile.visit(card, register);
        card.extendCardQuantity(3);
        int actual = card.getQuantity();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void visitQuantityNo() {
        CardQuantity card = register.createCard(14, 0);
        turnstile.visit(card, register);
        card.extendCardQuantity(2);
        int actual = card.getQuantity();
        int expected = 2;
        assertEquals(expected, actual);
    }


    @Test
    public void visitExemption() {
        CardMoney card = register.createCard(9, true, 3);
        turnstile.visit(card, register);
        int actual = card.getMoney();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void visitExemptionNo() {
        CardMoney card = register.createCard(9, true, 0);
        turnstile.visit(card, register);
        int actual = card.getMoney();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void visitTime() {
        CardTime card = register.createCard(9, LocalDate.now());
        card.extendCardTime(5);
        turnstile.visit(card, register);
        assertTrue(card.check(register));
    }

    @Test
    public void visitTimeNo() {
        CardTime card = register.createCard(9, LocalDate.now());
        card.extendCardTime(-5);
        turnstile.visit(card, register);
        assertFalse(card.check(register));

    }
}