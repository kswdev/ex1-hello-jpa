package hellojpa;

import domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {

            Movie movie = new Movie();
            movie.setDirector("Tom");
            movie.setActor("Click");
            movie.setName("바람");
            movie.setPrice(10000);

            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            System.out.println("==========================");

            Movie findMovie = entityManager.find(Movie.class, movie.getId());
            System.out.println(findMovie);

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
