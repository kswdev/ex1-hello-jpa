package hellojpa.value_type;

import domain.common.Adress;
import domain.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class valueExample {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Adress adress = new Adress("city", "street", "zipcode");

            Member member = new Member();
            member.setUsername("member1");
            member.setAdress(adress);
            em.persist(member);

            Adress copyAdress = new Adress(adress.getCity(), adress.getStreet(), adress.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAdress(copyAdress);
            em.persist(member2);

            System.out.println(adress.equals(copyAdress));

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
