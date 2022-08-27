package spring_rest.domain.dto;

import spring_rest.domain.entity.Author;

public class AuthorResponseDTO {
    private Integer id;
    private String name;
    private String sex;

    public AuthorResponseDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.sex = author.getSex();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
