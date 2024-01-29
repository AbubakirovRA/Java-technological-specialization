@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable Long id) {
        // Логика получения описания читателя по id
        Reader reader = readerService.getReaderById(id);
        return ResponseEntity.ok(reader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        // Логика удаления читателя по id
        readerService.deleteReaderById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        // Логика создания читателя
        Reader createdReader = readerService.createReader(reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReader);
    }
}
