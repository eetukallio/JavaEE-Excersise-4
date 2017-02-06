import javax.ejb.Local;
import java.util.List;

/**
 * Created by eetukallio on 3.2.2017
 */
@Local
public interface JPAManager {

    public void remove(EmailMessage email);
    public EmailMessage createOrUpdate(EmailMessage email);
    public EmailMessage find(Object id);
    public List<EmailMessage> findAll();
}
