import lombok.Data;

import java.time.LocalDate;

@Data
public class LogEntry {
    private LocalDate date;
    private boolean successful;
}
