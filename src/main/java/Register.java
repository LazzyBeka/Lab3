import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Register {
    Map<Integer, Card> cards = new HashMap<Integer, Card>();
    Map<Card, List<LogEntry>> cardsVisits = new HashMap<>();

    public CardTime createCard(int id, LocalDate date) {
        if (cards.get(id) == null) {
            CardTime timeCard = new CardTime(id, date);
            cards.put(id, timeCard);
            return timeCard;
        }
        return null;
    }

    public CardQuantity createCard(int id, int quantity) {
        if (cards.get(id) == null) {
            CardQuantity quantityCard = new CardQuantity(id, quantity);
            cards.put(id, quantityCard);
            return quantityCard;
        }
        return null;
    }

    public CardMoney createCard(int id, boolean exemption, int money) {
        if (cards.get(id) == null) {
            CardMoney moneyCard = new CardMoney(id, exemption, money);
            cards.put(id, moneyCard);
            return moneyCard;
        }
        return null;
    }

    public Card info(int id) {
        return cards.get(id);
    }

    public void visit(Card card, boolean success) {
        LogEntry visit = new LogEntry();
        visit.setDate(LocalDate.now());
        visit.setSuccessful(success);
        List<LogEntry> list = cardsVisits.computeIfAbsent(card, k -> new ArrayList<>());
        list.add(visit);
    }

    public void block(int id) {
        cards.get(id).block();
    }

    public List<LogEntry> visitInfoId(int id) {
        return cardsVisits.get(cards.get(id));
    }

    public Map<Card, List<LogEntry>> visitInfo() {
        return cardsVisits;
    }


    public Map<Card, List<LogEntry>> visitInfoType(Class clazz) {
         return cardsVisits
                    .entrySet()
                    .stream()
                    .filter(cardListEntry -> cardListEntry.getKey().getClass().equals(clazz))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

