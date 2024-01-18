package domain.item;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class Book extends Item {

    private String autor;
    private String isbn;
}
