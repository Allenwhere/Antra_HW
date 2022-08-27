package spring_rest.domain.dto;

import spring_rest.domain.entity.Author_Book;

public class Author_BookResponseDTO {
    private Integer id;
    private Integer author_id;
    private Integer book_id;

    public Author_BookResponseDTO(Author_Book ab) {
        this.id = ab.getId();
        this.author_id = ab.getAuthor().getId();
        this.book_id = ab.getBook().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
