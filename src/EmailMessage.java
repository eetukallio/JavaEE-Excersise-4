import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by eetukallio on 31.1.2017
 */
@Entity(name = "email")
@Table(name="emailMessage")
public class EmailMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="sender")
    private String from;
    @Column(name="receiver")
    private String to;
    @Column(name="topic")
    private String subject;
    @Column(name="message")
    private String message;
    @Transient
    private String error = "";

    public EmailMessage() {
    }

    public EmailMessage(String from, String to, String subject, String message) throws InvalidEmailException, InvalidSubjectException {
        setFrom(from);
        setTo(to);
        setSubject(subject);
        setMessage(message);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) throws InvalidEmailException {
        ValidateInput v = new ValidateInput();
        try {
            v.validateEmail(from);
            this.from = from;
        } catch (InvalidEmailException e) {
            error += e.getErrorMsg();
            throw new InvalidEmailException();
        }
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) throws InvalidEmailException {
        ValidateInput v = new ValidateInput();
        try {
            v.validateEmail(to);
            this.to = to;
        } catch (InvalidEmailException e) {
            error += " " + e.getErrorMsg();
            throw new InvalidEmailException();
        }
    }

    public String getSubject() {
        return subject;
    }



    public void setSubject(String subject) throws InvalidSubjectException {
        ValidateInput v = new ValidateInput();
        try {
            v.validateSubject(subject);
            this.subject = subject;
        } catch (InvalidSubjectException e) {
            error = " "+ e.getErrorMsg();
            throw new InvalidSubjectException();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public int getId() {
        return id;
    }


}
