package spring_rest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_rest.domain.entity.Book;
import spring_rest.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private EntityManager em;

    @Override
    public Book getById(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public Collection<Book> getAll() {
        Query query = em.createQuery("select b from Book b");
        return query.getResultList();
    }

    @Override
    public int save(Book book) {
        Book b = em.merge(book);
        return b.getId();
    }

    /**
     * if the element is not found, return -1.
     * @param id
     * @return
     *  id or -1
     */
    @Override
    public int removeById(Integer id) {
        Book b = getById(id);
        if(b == null) {
            return -1;
        }
        em.remove(b);
        return id;
    }
}
