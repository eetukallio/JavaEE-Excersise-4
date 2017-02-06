import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eetukallio on 31.1.2017.
 */
public class ValidateInput {

    private Pattern emailPattern = Pattern.compile("^\\S+@\\S+$");
    private Pattern subjectPattern = Pattern.compile("^.+$");

    public boolean validateEmail(String input) throws InvalidEmailException {

        boolean valid = false;
        Matcher m = emailPattern.matcher(input);

        if (m.matches()) {

            valid = true;
        } else throw new InvalidEmailException();

        return valid;
    }

    public boolean validateSubject(String input) throws InvalidSubjectException {

        boolean valid = false;
        Matcher m = subjectPattern.matcher(input);

        if (m.matches()) {

            valid = true;
        } else throw new InvalidSubjectException();

        return valid;
    }

}
