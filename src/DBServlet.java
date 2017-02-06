import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by eetukallio on 31.1.2017
 */
@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {

    private DataSource ds;

    public void init (ServletConfig config) throws ServletException {

        try {

            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jbdc/MySQLSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()){
            out.println(derbyConnection());
        }
    }

    private String derbyConnection() {

        String result = "<pre>";

        String sql = "SELECT * FROM item";

        try(Connection c = ds.getConnection()) {
            try(Statement statement = c.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    result += rs.getString("id") + " "
                            + rs.getString("name") + " "
                            + rs.getString("manufacturer") + "\n";

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "</pre>";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }
}
