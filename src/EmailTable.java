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
import java.util.List;

/**
 * Created by eetukallio on 4.2.2017
 */
@WebServlet("/EmailTable")
public class EmailTable extends HttpServlet {

    @EJB
    private JPAManager jpaManager;

    @Resource
    private UserTransaction userTransaction;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try(PrintWriter pw = response.getWriter()) {

            pw.print(produceHtml());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    public String produceHtml(){

        String result = "<html>" +
                "<head>" +
                "<link rel=\"stylesheet\" href=\"https://unpkg.com/purecss@0.6.2/build/pure-min.css\" integrity=\"sha384-UQiGfs9ICog+LwheBSRCt1o5cbyKIHbwjWscjemyBMT9YCUMZffs6UqUTd0hObXD\" crossorigin=\"anonymous\">" +
                "</head>" +
                "<body>" +
                "<table class=\"pure-table pure-table-bordered\">" +
                "<tr>" +
                "<th>From</th>" +
                "<th>To</th>" +
                "<th>Subject</th>" +
                "<th>Message</th></tr>";

        List<EmailMessage> list = jpaManager.findAll();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getMessage().length() > 99){
                result += "<tr>" +
                        "<td>" + list.get(i).getFrom() + "</td>" +
                        "<td>" + list.get(i).getTo() + "</td>" +
                        "<td>" + list.get(i).getSubject() + "</td>" +
                        "<td>" + list.get(i).getMessage().substring(0,99) + "</td></tr>";
            }else {
                result += "<tr>" +
                        "<td>" + list.get(i).getFrom() + "</td>" +
                        "<td>" + list.get(i).getTo() + "</td>" +
                        "<td>" + list.get(i).getSubject() + "</td>" +
                        "<td>" + list.get(i).getMessage() + "</td></tr>";
            }

        }
        result += "</table></body></html>";

        return result;
    }
}
