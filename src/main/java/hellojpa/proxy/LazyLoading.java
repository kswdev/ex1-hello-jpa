package hellojpa.proxy;

import domain.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class LazyLoading {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = em.find(Member.class, 1L);
            System.out.println(member1.getClass());
            System.out.println("id = " + member1.getId());
            System.out.println("username = " + member1.getUsername());
            System.out.println("team = " + member1.getTeam().getClass());

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
