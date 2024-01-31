package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    // Внесены изменения: разделение поля timestamp и добавление новых методов
    public List<Reader> getAllReaders() {
        return new ArrayList<>(readers);
    }

    public Reader getReaderById(long id) {
        return readers.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void deleteReaderById(long id) {
        readers.removeIf(reader -> Objects.equals(reader.getId(), id));
    }

    // Внесены изменения: добавлен метод для сохранения читателя
    public Reader save(Reader reader) {
        // Вместо генерации ID используем автоинкремент в классе Reader
        reader.setId(Reader.sequence++);
        readers.add(reader);
        return reader;
    }
}
