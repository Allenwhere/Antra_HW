package spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_rest.domain.entity.Author_Book;
import spring_rest.exception.CommonErrorResponse;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.service.Author_BookService;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class Author_BookController {

    private final Author_BookService author_bookService;

    @Autowired
    public Author_BookController(Author_BookService author_bookService) {
        this.author_bookService = author_bookService;
    }

    @GetMapping("/author_book")
    public ResponseEntity<Collection<Author_Book>> getAll() {
        return new ResponseEntity<>(author_bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/author_book/{id}")
    public ResponseEntity<Author_Book> getAuthor_BookById(@PathVariable Integer id) {
        return new ResponseEntity<>(author_bookService.getById(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/author_book")
    public ResponseEntity<?> createAuthor(@RequestBody Author_Book ab){
        return new ResponseEntity<>(author_bookService.save(ab),HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/author_book/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id, @RequestBody Author_Book ab) {
        Author_Book ab1 = author_bookService.getById(id);
        ab1.setAuthor((ab.getAuthor() == null)?ab1.getAuthor():ab.getAuthor());
        ab1.setBook((ab.getBook() == null)?ab1.getBook():ab.getBook());
        return new ResponseEntity<>(author_bookService.save(ab1),HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/author_book/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
        return new ResponseEntity<>(author_bookService.removeById(id),HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler() {
        return new ResponseEntity<>(new CommonErrorResponse("Resource Not Found"), HttpStatus.NOT_FOUND);
    }
}
