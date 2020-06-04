import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    Register register = new Register();

    @Test
    void testCreateCardQuantityNormal() {
        CardQuantity actual = register.createCard(1, 5);
        assertEquals(1,actual.getId());
        assertEquals(5,actual.getQuantity());
    }

    @Test
    void testCreateCardQuantityDuplicate() {
        register.createCard(2, 5);
        Card actual = register.createCard(2, 5);
        assertNull(actual);
    }

    @Test
    void testCreateCardDataNormal() {
        LocalDate today = LocalDate.now();
        LocalDate week = today.plusDays(7);
        CardTime actual = register.createCard(3, week);
        assertEquals(3,actual.getId());
        assertEquals(week,actual.getValidTill());
    }

    @Test
    void testCreateCardDataDuplicate() {
        LocalDate today = LocalDate.now();
        LocalDate week = today.plusDays(7);
        register.createCard(4, week);
        CardTime actual = register.createCard(4,week);
        assertNull(actual);
    }

    @Test
    void testCreateCardMoneyNormal() {
        CardMoney actual = register.createCard(5, true, 50);
        assertEquals(5,actual.getId());
        assertEquals(true,actual.isExemption());
        assertEquals(50,actual.getMoney());
    }

    @Test
    void testCreateCardMoneyDuplicate() {
        register.createCard(6, true, 5);
        CardMoney actual = register.createCard(6, true, 5);
        assertNull(actual);
    }

    @Test
    void testVisitQuantity() {
        Register register = new Register();
        CardQuantity card1 = register.createCard(1,1);
        register.visit(card1,true);
        register.visit(card1,false);
        List<LogEntry> actual = register.visitInfoId(card1.getId());
        assertEquals(2,actual.size());
        assertTrue(actual.get(0).isSuccessful());
        assertFalse(actual.get(1).isSuccessful());
    }

    @Test
    void testVisitInfo() {
        Register register = new Register();
        CardQuantity card1 = register.createCard(1,1);
        CardQuantity card2 = register.createCard(2,1);
        register.visit(card1,true);
        register.visit(card1,false);
        register.visit(card2,true);
        register.visit(card2,false);
        Map<Card,List<LogEntry>> actual = register.visitInfo();
        assertEquals(2,actual.size());
        assertEquals(2,actual.get(card1).size());
        assertEquals(2,actual.get(card2).size());
        assertTrue(actual.get(card1).get(0).isSuccessful());
        assertFalse(actual.get(card1).get(1).isSuccessful());
        assertTrue(actual.get(card2).get(0).isSuccessful());
        assertFalse(actual.get(card2).get(1).isSuccessful());
    }

    @Test
    void testInfoType() {
        Register register = new Register();
        CardQuantity card1 = register.createCard(1,1);
        CardQuantity card2 = register.createCard(2,1);
        CardMoney card3 = register.createCard(3,false,3);
        register.visit(card1,true);
        register.visit(card1,false);
        register.visit(card2,true);
        register.visit(card2,false);
        register.visit(card3,false);
        Map<Card,List<LogEntry>> actual = register.visitInfoType(CardQuantity.class);
        assertEquals(2,actual.size());
        assertEquals(2,actual.get(card1).size());
        assertEquals(2,actual.get(card2).size());
        assertTrue(actual.get(card1).get(0).isSuccessful());
        assertFalse(actual.get(card1).get(1).isSuccessful());
        assertTrue(actual.get(card2).get(0).isSuccessful());
        assertFalse(actual.get(card2).get(1).isSuccessful());
    }
}