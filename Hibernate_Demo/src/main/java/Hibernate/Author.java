package Hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Author_Book> author_books = new ArrayList<>();

    public List<Author_Book> getAuthor_books() {
        return author_books;
    }

    public void setAuthor_books(List<Author_Book> author_books) {
        this.author_books = author_books;
    }

    public void addAuthor_book(Author_Book ab) {
        this.author_books.add(ab);
    }

    @Override
    public String toString() {
        return "com.Antra.Hibernate.Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' + ", sex='" + sex + '\''+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
