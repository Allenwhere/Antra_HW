package spring_rest.domain.dto;

import spring_rest.domain.entity.Book;

public class BookResponseDTO {
    private Integer id;
    private String title;
    private Integer year;

    public BookResponseDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();;
        this.year = book.getYear();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
