package hellojpa.query.jpql.join;

import domain.member.Member;
import domain.member.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class FetchJoinExample {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("teamC");
            em.persist(team3);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setAge(30);

            member.changeTeam(team);
            member2.changeTeam(team);
            member3.changeTeam(team3);

            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            List<Member> resultList =
                    em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

            for (Member mem : resultList) {
                System.out.println("username : " + mem.getUsername() + " team name : " + mem.getTeam().getName());
                System.out.println();
            }

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
