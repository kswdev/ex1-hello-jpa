package hellojpa.proxy;

import domain.member.Member;
import domain.member.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaProxy {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //
            Member member1 = em.find(Member.class, 3L);
            //Member member1 = em.getReference(Member.class, 3L);
            System.out.println(member1.getClass());
            System.out.println("id = " + member1.getId());
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(member1));
            //Hibernate.initialize(member1); //강제 초기화
            System.out.println("username = " + member1.getUsername());

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void prindMember(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
    }

    private static void prindMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
