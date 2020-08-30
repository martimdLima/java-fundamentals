package entity_class;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class Service {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public User findById(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {
            // fetch a new user using its id
            return em.find(User.class, id); // always the primary key
        } finally {
            // make sure we close the database connection
            if (em != null) {
                em.close();
            }
        }
    }


    public User saveOrUpdate(User user) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin(); // open transaction
            User savedUser = em.merge(user);
            em.getTransaction().commit(); // close transaction
            return savedUser;

        } catch (RollbackException ex) {

            // something went wrong, make sure db is consistent
            em.getTransaction().rollback();
            return null;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
