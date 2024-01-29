package ru.gb.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader getReaderById(long id) {
        return readerRepository.getReaderById(id);
    }

    public void deleteReaderById(long id) {
        readerRepository.deleteReaderById(id);
    }

    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Reader> getReaders() {
        return readerRepository.getAllReaders();
    }
}

