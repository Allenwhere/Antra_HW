package spring_rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_rest.domain.dto.BookResponseDTO;
import spring_rest.domain.entity.Book;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.repository.BookRepository;
import spring_rest.service.BookService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDTO getById(Integer id) {
        Book b = bookRepository.getById(id);
        if (b == null) {
            throw new ResourceNotFoundException("");
        }
        return new BookResponseDTO(b);
    }

    @Override
    public Collection<BookResponseDTO> getAll() {
        return bookRepository.getAll()
                .stream()
                .map(BookResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
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
    @Transactional
    @Override
    public int removeById(Integer id) {
        int result = bookRepository.removeById(id);
        if (result == -1) {
            throw new ResourceNotFoundException("");
        }
        return result;
    }
}
