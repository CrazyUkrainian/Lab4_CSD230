package csd230.lab4.controllers;

import csd230.lab4.entities.Book;
import csd230.lab4.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/book")
public class BookRestController {
    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @CrossOrigin
    @GetMapping()
    List<Book> all() {
        List<Book> all = bookRepository.findAll();
        return all;
    }

    @GetMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Book getBook(@PathVariable int id) {
        Book book = bookRepository.findById(id);
        return book;
    }

    @PostMapping()
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    @PutMapping("/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {

        return bookRepository.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setIsbn(newBook.getIsbn());
                    book.setCopies(newBook.getCopies());
                    book.setDescription(newBook.getDescription());
                    book.setPrice(newBook.getPrice());
                    book.setTitle(newBook.getTitle());
                    book.setQuantity(newBook.getQuantity());

                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    return bookRepository.save(newBook);
                });
    }
    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
