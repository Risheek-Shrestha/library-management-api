package com.risheek.libraryapi;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library-management")
public class LibraryController {

    private final LibraryService libraryService;


    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

//    @GetMapping
//    public List<Book> getBooks(){return libraryService.getAllBooks();}

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id){
        return libraryService.getBookById(id);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getBook(Pageable pageable) {
        return ResponseEntity.ok(libraryService.getBookByPages(pageable));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.status(201).body(libraryService.addBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @Valid @RequestBody Book book) {
        return ResponseEntity.ok(libraryService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        libraryService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
