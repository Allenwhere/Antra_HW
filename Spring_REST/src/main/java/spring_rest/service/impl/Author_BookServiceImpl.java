package spring_rest.service.impl;

import org.springframework.stereotype.Service;
import spring_rest.domain.entity.Author_Book;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.repository.Author_BookRepository;
import spring_rest.service.Author_BookService;

import java.util.Collection;

@Service
public class Author_BookServiceImpl implements Author_BookService {

    private final Author_BookRepository author_bookRepository;

    public Author_BookServiceImpl(Author_BookRepository author_bookRepository) {
        this.author_bookRepository = author_bookRepository;
    }


    @Override
    public Author_Book getById(Integer id) {
        Author_Book ab = author_bookRepository.getById(id);
        if(ab == null) {
            throw new ResourceNotFoundException("");
        }
        return ab;
    }

    @Override
    public Collection<Author_Book> getAll() {
        return author_bookRepository.getAll();
    }

    @Override
    public int save(Author_Book author_book) {
        return author_bookRepository.save(author_book);
    }

    /**
     * if the element is not found, throw exception.
     *
     * @param id
     * @return id
     */
    @Override
    public int removeById(Integer id) {
        int result = author_bookRepository.removeById(id);
        if (result == -1) {
            throw new ResourceNotFoundException("");
        }
        return result;
    }
}
