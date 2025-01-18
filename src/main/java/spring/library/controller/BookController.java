package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.RequestOfBook;
import spring.library.controller.response.ResponseOfBook;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<ResponseOfBook>> getAllBooks() {
        List<ResponseOfBook> responseOfBooks = bookService.findAllBook().stream().map(ResponseOfBook::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(responseOfBooks);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<ResponseOfBook> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.findBookById(id);
        return ResponseEntity.ok().body(ResponseOfBook.from(bookDto));
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<ResponseOfBook> updateBook(@PathVariable Long id, @RequestBody RequestOfBook request) {
        BookDto bookDto = bookService.updateBook(id, request);
        return ResponseEntity.ok().body(ResponseOfBook.from(bookDto));
    }

    @PostMapping("/books")
    public ResponseEntity<ResponseOfBook> createBook(@RequestBody RequestOfBook request) {
        BookDto bookDto = bookService.createBook(request);
        return ResponseEntity.ok().body(ResponseOfBook.from(bookDto));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().body("Delete Success!");
    }
}
