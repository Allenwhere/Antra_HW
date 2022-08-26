package spring_rest.repository;

import org.springframework.stereotype.Repository;
import spring_rest.domain.entity.Author;

import java.util.Collection;

@Repository
public interface AuthorRepository {
    Author getById(Integer id);
    Collection<Author> getAll();
    int save(Author author);
    /**
     * if the element is not found, return -1.
     * @param id
     * @return
     *  id or -1
     */
    int removeById(Integer id);
}
