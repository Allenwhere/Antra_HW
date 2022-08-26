package spring_rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_rest.domain.entity.Book;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.repository.BookRepository;
import spring_rest.service.BookService;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Integer id) {
        Book b = bookRepository.getById(id);
        if (b == null) {
            throw new ResourceNotFoundException("");
        }
        return b;
    }

    @Override
    public Collection<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public int save(Book book) {
       return bookRepository.save(book);
    }

    /**
     * if the element is not found, throw exception.
     *
     * @param id
     * @return id
     */
    @Override
    public int removeById(Integer id) {
        int result = bookRepository.removeById(id);
        if (result == -1) {
            throw new ResourceNotFoundException("");
        }
        return result;
    }
}
