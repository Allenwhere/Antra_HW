package spring_rest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_rest.domain.entity.Author;
import spring_rest.domain.entity.Author_Book;
import spring_rest.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @Autowired
    private EntityManager em;


    @Override
    public Author getById(Integer id) {
        return em.find(Author.class,id);
    }

    @Override
    public Collection<Author> getAll() {
        Query query = em.createQuery("select a from Author a");
        return query.getResultList();
    }

    @Override
    public int save(Author author) {
        // need to create a new instance as the managed entity
        // the original instance won't be changed, so you will get a 0 as id
        Author a = em.merge(author);
        return a.getId();
    }

    /**
     * if the element is not found, return -1.
     *
     * @param id
     * @return id or -1
     */
    @Override
    public int removeById(Integer id) {
        Author a = getById(id);
        if(a == null) {
            return -1;
        }
        em.remove(a);
        return id;
    }
}
