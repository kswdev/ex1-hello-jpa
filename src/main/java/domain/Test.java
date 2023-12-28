package domain;

import javax.persistence.*;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name")
    private String test;

    public Test() { }

    public Test(String test) {
        this.test = test;
    }
}
