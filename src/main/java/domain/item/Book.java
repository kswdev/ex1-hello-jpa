package domain.item;


import javax.persistence.Entity;

@Entity
public class Book extends Item {

    private String autor;
    private String isbn;
}
