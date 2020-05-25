import lombok.Data;

import java.time.LocalDate;

@Data
public class CardTime extends Card {
    private LocalDate validTill = LocalDate.now();

    public CardTime(int id, LocalDate validTill) {
        this.setId(id);
        this.setValidTill(validTill);
    }

    public void extendCardTime(int days) {
        this.validTill = validTill.plusDays(days);
    }

    public boolean check(Register register) {
        LocalDate now = LocalDate.now();

        if (!super.check(register)) {
            return false;
        }

        if (this.getValidTill().isAfter(now)) {
            register.visit(this, true);
            return true;
        }

        register.visit(this, false);
        return false;
    }
}
