/**
 * Created by eetukallio on 31.1.2017.
 */
public class MyException extends Exception {




    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
