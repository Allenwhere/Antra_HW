package spring_rest.service;

import org.springframework.stereotype.Service;
import spring_rest.domain.entity.Author_Book;

import java.util.Collection;

@Service
public interface Author_BookService {
    Author_Book getById(Integer id);
    Collection<Author_Book> getAll();
    int save(Author_Book author_book);
    /**
     * if the element is not found, throw exception.
     * @param id
     * @return
     *  id
     */
    int removeById(Integer id);
}
