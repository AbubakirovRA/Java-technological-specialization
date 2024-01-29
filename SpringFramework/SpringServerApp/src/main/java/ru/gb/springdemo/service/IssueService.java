package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    // Добавление поля для максимального количества книг у пользователя
    @Value("${application.issue.max-allowed-books:1}")
    private int maxAllowedBooks;

    /**
     * Метод для выдачи книги читателю.
     *
     * @param request Запрос на выдачу
     * @return Выдача
     */
    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }

        // Проверка, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        if (getActiveIssuesByReaderId(request.getReaderId()).size() >= maxAllowedBooks) {
            throw new IllegalStateException("Превышено максимальное количество книг у пользователя");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    /**
     * Метод для закрытия выдачи книги.
     *
     * @param issueId Идентификатор выдачи
     */
    public void closeIssue(Long issueId) {
        Issue issue = issueRepository.getIssueById(issueId);

        if (issue == null) {
            throw new NoSuchElementException("Не найдена выдача с идентификатором \"" + issueId + "\"");
        }

        // Проверка, что выдача еще не закрыта
        if (issue.getReturnedAt() != null) {
            throw new IllegalStateException("Выдача с идентификатором \"" + issueId + "\" уже закрыта");
        }

        // Установка даты возврата
        issue.setReturnedAt(LocalDateTime.now());
        issueRepository.update(issue);
    }

    /**
     * Метод для получения списка активных выдач по читателю.
     *
     * @param readerId Идентификатор читателя
     * @return Список активных выдач
     */
    public List<Issue> getActiveIssuesByReaderId(Long readerId) {
        return issueRepository.getActiveIssuesByReaderId(readerId);
    }
}
