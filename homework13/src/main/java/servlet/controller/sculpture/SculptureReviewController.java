package servlet.controller.sculpture;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.entity.Sculpture;
import servlet.service.SculptureService;
import servlet.service.impl.SculptureServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptureReviewController extends HttpServlet {
    private final SculptureService sculptureService = new SculptureServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        try (PrintWriter writer = resp.getWriter()) {
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

            writer.write("<h1>All Sculptures</h1>");
            writer.write("<table>");
            writer.write("<tr>");
            writer.write("<th>Id</th>");
            writer.write("<th>Name</th>");
            writer.write("<th>Material</th>");
            writer.write("<th>Art Style</th>");
            writer.write("<th>Publishing Year</th>");
            writer.write("</tr>");
            for (Sculpture sculpture : sculptureService.findAll()) {
                writer.write("<tr>");
                writer.write("<td>");
                writer.write(sculpture.getId().toString());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(sculpture.getName());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(sculpture.getMaterialOfSculpture());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(sculpture.getArtisticStyle());
                writer.write("</td>");
                writer.write("<td>");
                writer.write(Integer.toString(sculpture.getYearOfPublishing()));
                writer.write("</td>");
                writer.write("<td>");
                writer.write("<a href=\"/servlet_hw13/sculpture-update?id=" + sculpture.getId() + "\">Update</a>");
                writer.write("</td>");
                writer.write("<td>");
                writer.write("<a href=\"/servlet_hw13/sculpture-delete?id=" + sculpture.getId() + "\">Delete</a>");
                writer.write("</td>");
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("<h3>If you want to Create a new Sculpture:</h3>");
            writer.write("<a href=\"/servlet_hw13/sculpture-new\">Create new Sculpture</a>");
            writer.write("<h3>If you want to Find a certain Sculpture:</h3>");
            writer.write("<a href=\"/servlet_hw13/sculpture-find\">Find a Sculpture</a>");
            writer.write("<form method='get' action='/servlet_hw13/sculptorSculpture'>");
            writer.write("<input type='submit' value='Back to Sculptor And Sculptures Review'/>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }
}
