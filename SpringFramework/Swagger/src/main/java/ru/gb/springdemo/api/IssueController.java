import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @Operation(summary = "Get issue by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the issue"),
            @ApiResponse(responseCode = "404", description = "Issue not found")
    })
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    @Operation(summary = "Close issue by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Issue closed"),
            @ApiResponse(responseCode = "404", description = "Issue not found")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeIssue(@PathVariable Long id) {
        issueService.closeIssue(id);
    }

    // Другие методы контроллера...
}
