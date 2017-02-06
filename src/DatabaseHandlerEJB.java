import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by eetukallio on 3.2.2017
 */
@Stateless
public class DatabaseHandlerEJB implements JPAManager {

    @PersistenceContext(unitName = "email")
    private EntityManager em;

    @Override
    public EmailMessage find(Object id) {
        return em.find(EmailMessage.class, id);
    }

    @Override
    public EmailMessage createOrUpdate(EmailMessage email) {
        return em.merge(email);
    }

    @Override
    public void remove(EmailMessage email) {
        em.remove(em.merge(email));
    }

    @Override
    public List<EmailMessage> findAll() {
        Query query = em.createQuery("select obj from email as obj");
        return query.getResultList();
    }
}
