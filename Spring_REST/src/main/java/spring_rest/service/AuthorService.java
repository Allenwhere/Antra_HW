package spring_rest.service;

import org.springframework.stereotype.Service;
import spring_rest.domain.dto.AuthorResponseDTO;
import spring_rest.domain.entity.Author;

import java.util.Collection;

@Service
public interface AuthorService {
    AuthorResponseDTO getById(Integer id);
    Collection<AuthorResponseDTO> getAll();
    int save(Author author);
    /**
     * if the element is not found, throw exception.
     * @param id
     * @return
     *  id
     */
    int removeById(Integer id);
}
