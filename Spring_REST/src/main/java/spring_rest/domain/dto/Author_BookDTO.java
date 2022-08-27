package spring_rest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import spring_rest.domain.entity.Author;
import spring_rest.domain.entity.Author_Book;
import spring_rest.domain.entity.Book;

/**
 * this class is to convert the request body for updating author_book table
 * when we add rows into author_book table, we have author_id and book_id
 * but the design of Entity Author_Book doesn't have those two attribute
 * instead, it has an author and a book as two object
 *
 * this class serve as a fake Author_Book and transfer the necessary info into
 * a real Author_Book object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author_BookDTO {
    private Integer author_id;
    private Integer book_id;

    private Author_Book author_book = new Author_Book();

    public Author_Book getAuthor_book() {
        Author a = new Author();
        a.setId(author_id);
        Book b = new Book();
        b.setId(book_id);
        author_book.setAuthor(a);
        author_book.setBook(b);
        return author_book;
    }
}
