package Hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "publish_year")
    private Integer year;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Author_Book> author_books = new ArrayList<>();

    public List<Author_Book> getAuthor_books() {
        return author_books;
    }

    public void setAuthor_books(List<Author_Book> ab) {
        this.author_books = ab;
    }

    public void addAuthor_book(Author_Book ab) {
        this.author_books.add(ab);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' + ", Year of Publish='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
