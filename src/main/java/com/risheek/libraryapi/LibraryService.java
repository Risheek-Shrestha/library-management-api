package com.risheek.libraryapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryManagementRepository libraryManagementRepository;

    public LibraryService(LibraryManagementRepository libraryManagementRepository) {
        this.libraryManagementRepository = libraryManagementRepository;
    }


    public List<Book> getAllBooks() {
        return libraryManagementRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return libraryManagementRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book addBook(Book book) {
        return libraryManagementRepository.save(book);
    }

    public Book updateBook(Integer id, Book update) {
        Book book = libraryManagementRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(update.getTitle());
        book.setAuthor(update.getAuthor());
        book.setPrice(update.getPrice());
        book.setAvailable(update.getAvailable());
        return libraryManagementRepository.save(book); // ← save added
    }

    public void deleteBook(Integer id) {
        if (!libraryManagementRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        libraryManagementRepository.deleteById(id);
    }
}
