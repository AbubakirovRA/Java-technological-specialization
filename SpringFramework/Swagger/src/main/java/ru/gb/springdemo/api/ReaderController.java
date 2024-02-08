import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Operation(summary = "Get reader by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reader"),
            @ApiResponse(responseCode = "404", description = "Reader not found")
    })
    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id);
    }

    @Operation(summary = "Delete reader by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reader deleted"),
            @ApiResponse(responseCode = "404", description = "Reader not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReaderById(@PathVariable Long id) {
        readerService.deleteReaderById(id);
    }

    @Operation(summary = "Create a new reader")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader created")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reader createReader(@RequestBody Reader reader) {
        return readerService.createReader(reader);
    }

    @Operation(summary = "Get all readers")
    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }
}
