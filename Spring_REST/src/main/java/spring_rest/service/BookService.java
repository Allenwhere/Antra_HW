package spring_rest.service;

import spring_rest.domain.dto.BookResponseDTO;
import spring_rest.domain.entity.Book;

import java.util.Collection;

public interface BookService {
    BookResponseDTO getById(Integer id);
    Collection<BookResponseDTO> getAll();
    int save(Book book);
    /**
     * if the element is not found, throw exception.
     * @param id
     * @return
     *  id
     */
    int removeById(Integer id);
}
