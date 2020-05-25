import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
abstract class Card {
    private int id;
    private boolean blocked = false;

    public void block() {
        this.blocked = true;
    }

    protected boolean check(Register register) {
        if (register.cards.get(this.getId()) == null) {
            register.visit(this, false);
            return false;
        }

        if (this.isBlocked()) {
            register.visit(this, false);
            return false;
        }
        return true;
    }
}