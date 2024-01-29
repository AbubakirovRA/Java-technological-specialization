import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Модель для представления выдачи книги читателю.
 */
@Data
public class Issue {

    // Счетчик для генерации уникальных идентификаторов
    public static long sequence = 1L;

    // Идентификатор выдачи
    private final long id;

    // Идентификатор книги
    private final long bookId;

    // Идентификатор читателя
    private final long readerId;

    // Дата выдачи
    private final LocalDateTime issuedAt;

    // Дата возврата
    private LocalDateTime returnedAt;

    /**
     * Конструктор для создания новой выдачи книги.
     *
     * @param bookId   Идентификатор книги
     * @param readerId Идентификатор читателя
     */
    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issuedAt = LocalDateTime.now();
    }
}
