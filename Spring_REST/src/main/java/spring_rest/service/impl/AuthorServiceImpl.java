package spring_rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_rest.domain.entity.Author;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.repository.AuthorRepository;
import spring_rest.service.AuthorService;

import java.util.Collection;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Integer id) {
        Author a = authorRepository.getById(id);
        if (a == null) {
            throw new ResourceNotFoundException("");
        }
        return a;
    }

    @Override
    public Collection<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public int save(Author author) {
        return authorRepository.save(author);
    }

    /**
     * if the element is not found, throw exception.
     *
     * @param id
     * @return id
     */
    @Override
    public int removeById(Integer id) {
        int result = authorRepository.removeById(id);
        if (result == -1) {
            throw new ResourceNotFoundException("");
        }
        return result;
    }
}
