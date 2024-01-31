package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
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

    @Value("${application.issue.max-allowed-books:1}")
    private int maxAllowedBooks;

    public Issue issue(IssueRequest request) {
        validateBookAndReader(request.getBookId(), request.getReaderId());

        if (getActiveIssuesByReaderId(request.getReaderId()).size() >= maxAllowedBooks) {
            throw new IllegalStateException("Превышено максимальное количество книг у пользователя");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public void closeIssue(Long issueId) {
        Issue issue = issueRepository.getIssueById(issueId);

        if (issue == null) {
            throw new NoSuchElementException("Не найдена выдача с идентификатором \"" + issueId + "\"");
        }

        if (issue.getReturnedAt() != null) {
            throw new IllegalStateException("Выдача с идентификатором \"" + issueId + "\" уже закрыта");
        }

        issue.setReturnedAt(LocalDateTime.now());
        issueRepository.update(issue);
    }

    public List<Issue> getActiveIssuesByReaderId(Long readerId) {
        return issueRepository.getActiveIssuesByReaderId(readerId);
    }

    public Issue getIssueById(Long id) {
        return issueRepository.getIssueById(id);
    }

    private void validateBookAndReader(Long bookId, Long readerId) {
        if (readerRepository.getReaderById(readerId) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + readerId + "\"");
        }

        if (BookRepository.getBookById(bookId) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + bookId + "\"");
        }
    }

    public List<Issue> getAllIssues() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllIssues'");
    }
}
