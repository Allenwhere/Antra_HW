package spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_rest.domain.dto.BookResponseDTO;
import spring_rest.domain.entity.Book;
import spring_rest.exception.CommonErrorResponse;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.service.BookService;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class BookController {

    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<Collection<BookResponseDTO>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Integer id) {
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }


    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book),HttpStatus.CREATED);
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book),HttpStatus.OK);
    }
    
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        return new ResponseEntity<>(bookService.removeById(id),HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler() {
        return new ResponseEntity<>(new CommonErrorResponse("Resource Not Found"), HttpStatus.NOT_FOUND);
    }
}
