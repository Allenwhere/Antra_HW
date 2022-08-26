package spring_rest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_rest.domain.entity.Author_Book;
import spring_rest.domain.entity.Book;
import spring_rest.repository.Author_BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class Author_BookRepositoryImpl implements Author_BookRepository {

    @Autowired
    private EntityManager em;

    @Override
    public Author_Book getById(Integer id) {

        return em.find(Author_Book.class,id);
    }

    @Override
    public Collection<Author_Book> getAll() {
        Query query = em.createQuery("select ab from Author_Book ab");
        return query.getResultList();
    }

    @Override
    public int save(Author_Book author_book) {
        em.persist(author_book);
        return author_book.getId();
    }

    /**
     * if the element is not found, return -1.
     *
     * @param id
     * @return id or -1
     */
    @Override
    public int removeById(Integer id) {
        Author_Book ab = getById(id);
        if(ab == null) {
            return -1;
        }
        em.remove(ab);
        return id;
    }
}
