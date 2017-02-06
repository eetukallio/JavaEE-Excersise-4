
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Eetu Kallio on 31.1.2017
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {

    @EJB
    public JPAManager handler;

    @Resource
    UserTransaction userTransaction;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmailMessage mail = new EmailMessage();

        try(PrintWriter pw = response.getWriter()) {

           userTransaction.begin();
            mail = new EmailMessage(request.getParameter("from"),request.getParameter("to"),
                    request.getParameter("subject"), request.getParameter("message"));
            handler.createOrUpdate(mail);
            userTransaction.commit();
            pw.print("<html><body><script>alert('" + "Mail sent!"  + "');\n " +
                    "window.location.href='localhost:8080/lesson4/emailFormTemplate.html';</script></body></html>");


        } catch (Exception e) {

            e.printStackTrace();
            PrintWriter pw = response.getWriter();
            pw.print("<script>alert('" + mail.getError()  + "');</script>");
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
