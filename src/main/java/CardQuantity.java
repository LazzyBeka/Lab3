import lombok.Data;

@Data
public class CardQuantity extends Card {
    private int quantity = 0;

    public CardQuantity(int id, int quantity) {
        this.setId(id);
        this.setQuantity(quantity);
    }

    public void extendCardQuantity(int quantity) {
        this.quantity += quantity;
    }

    public boolean check(Register register) {
        if (!super.check(register)) {
            return false;
        }

        if (this.getQuantity() >= 1) {
            register.visit(this, true);
            return true;
        }

        register.visit(this, false);
        return false;
    }
}
