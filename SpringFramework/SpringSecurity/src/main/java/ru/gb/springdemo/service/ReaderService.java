package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader getReaderById(long id) {
        return readerRepository.getReaderById(id);
    }

    public void deleteReaderById(long id) {
        readerRepository.deleteById(id);
    }

    public Reader createReader(String name) {
        return readerRepository.createReader(name);
    }

    public List<Reader> getReaders() {
        return readerRepository.getAllReaders();
    }
}
