package servlet.controller.sculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.entity.Sculptor;
import servlet.service.SculptorService;
import servlet.service.impl.SculptorServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptorReviewController extends HttpServlet {
    private final SculptorService sculptorService = new SculptorServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        try(PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");

            writer.write("<style>");
            writer.write("table {");
            writer.write("    border-collapse: separate;");
            writer.write("    border-spacing: 25px;");
            writer.write("}");
            writer.write("th, td {");
            writer.write("    text-align: left;");
            writer.write("    padding: 8px;");
            writer.write("}");
            writer.write("tr:nth-child(even) {");
            writer.write("    background-color: #f2f2f2;");
            writer.write("}");
            writer.write("th {");
            writer.write("    background-color: #4CAF50;");
            writer.write("    color: white;");
            writer.write("}");
            writer.write("</style>");

            writer.write("<h1>All Sculptors</h1>");
            writer.write("<table>");
            writer.write("<tr>");
            writer.write("<th>Id</th>");
            writer.write("<th>First_Name</th>");
            writer.write("<th>Last_Name</th>");
            writer.write("<th>Age</th>");
            writer.write("</tr>");
            for (Sculptor sculptor : sculptorService.findAll()) {
                writer.write("<tr>");
                writer.write("<td>");
                writer.write(sculptor.getId().toString());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(sculptor.getFirstName());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(sculptor.getLastName());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(Integer.toString(sculptor.getAge()));
                writer.write("</td>");
                writer.write("<td>");
                writer.write("<a href=\"/servlet_hw13/sculptor-update?id=" + sculptor.getId() + "\">Update</a>");
                writer.write("</td>");
                writer.write("<td>");
                writer.write("<a href=\"/servlet_hw13/sculptor-delete?id=" + sculptor.getId() + "\">Delete</a>");
                writer.write("</td>");
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("<h3>If you want to Create a new Sculptor:<h3>");
            writer.write("<a href=\"/servlet_hw13/sculptor-new\">Create new Sculptor</a>");
            writer.write("<h3>If you want to Find a certain Sculptor:<h3>");
            writer.write("<a href=\"/servlet_hw13/sculptor-find\">Find a Sculptor</a>");
            writer.write("<form method='get' action='/servlet_hw13/sculptorSculpture'>");
            writer.write("<input type='submit' value='Back to Sculptor And Sculptures Review'/>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }
}
