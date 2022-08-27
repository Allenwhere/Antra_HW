package spring_rest.service.impl;

import org.springframework.stereotype.Service;
import spring_rest.domain.dto.Author_BookResponseDTO;
import spring_rest.domain.entity.Author_Book;
import spring_rest.exception.ResourceNotFoundException;
import spring_rest.repository.Author_BookRepository;
import spring_rest.service.Author_BookService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class Author_BookServiceImpl implements Author_BookService {

    private final Author_BookRepository author_bookRepository;

    public Author_BookServiceImpl(Author_BookRepository author_bookRepository) {
        this.author_bookRepository = author_bookRepository;
    }


    @Override
    public Author_BookResponseDTO getById(Integer id) {
        Author_Book ab = author_bookRepository.getById(id);
        if(ab == null) {
            throw new ResourceNotFoundException("");
        }
        return new Author_BookResponseDTO(ab);
    }

    @Override
    public Collection<Author_BookResponseDTO> getAll() {
        return author_bookRepository.getAll()
                .stream()
                .map(Author_BookResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
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
    @Transactional
    @Override
    public int removeById(Integer id) {
        int result = author_bookRepository.removeById(id);
        if (result == -1) {
            throw new ResourceNotFoundException("");
        }
        return result;
    }
}
