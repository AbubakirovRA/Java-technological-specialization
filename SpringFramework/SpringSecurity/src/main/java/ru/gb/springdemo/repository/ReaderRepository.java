package ru.gb.springdemo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader getReaderById(Long id);
    void deleteReaderById(Long id);

    Reader createReader(String name);

    List<Reader> getAllReaders();
}
