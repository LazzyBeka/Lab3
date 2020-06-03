public class Turnstile {

    public void visit(Card card, Register register) {
        if (card instanceof CardMoney) {
            CardMoney cardMoney = (CardMoney) card;
            if (cardMoney.isExemption()) {
                warn();
            }
        }

        if (card.check(register)) {
            if (card instanceof CardMoney) {
                CardMoney cardMoney = (CardMoney) card;
                if (cardMoney.isExemption()) {
                    cardMoney.extendCardMoney(-2);
                } else {
                    cardMoney.extendCardMoney(-4);
                }
            } else if (card instanceof CardQuantity) {
                CardQuantity cardQuantity = (CardQuantity) card;
                cardQuantity.extendCardQuantity(-1);
            }
        }
    }

    private void warn() {
        System.out.println("Warn");
    }

}
