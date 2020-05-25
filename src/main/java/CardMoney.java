import lombok.Data;

@Data
public class CardMoney extends Card {
    private boolean exemption;
    private int money = 0;

    public CardMoney(int id, boolean exemption, int money) {
        this.setId(id);
        this.setExemption(exemption);
        this.setMoney(money);
    }

    public void extendCardMoney(int money) {
        this.money += money;
    }

    public boolean check(Register register) {
        if (!super.check(register)) {
            return false;
        }

        if (this.getMoney() >= 4) {
            register.visit(this, true);
            return true;
        }

        if (this.isExemption() && this.getMoney() >= 2) {
            register.visit(this, true);
            return true;
        }

        register.visit(this, false);
        return false;
    }
}
