package servlet.controller.sculptureOfsculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.entity.Sculptor;
import servlet.entity.Sculpture;
import servlet.service.SculptorService;
import servlet.service.SculptureService;
import servlet.service.impl.SculptorServiceImpl;
import servlet.service.impl.SculptureServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptorSculptureReviewController extends HttpServlet {
    SculptorService sculptorService = new SculptorServiceImpl();
    SculptureService sculptureService = new SculptureServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<head>");
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
            writer.write("</head>");
            writer.write("<body>");

            writer.write("<div style=\"display: flex; flex-direction: column;\">");

            writer.write("<div style = 'width: 100%; display: flex;'>");
            writer.write("<div>");
            writer.write("<h1 style=\"margin-left: 20px;\">All Sculptors</h1>");
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
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("</div>");

            writer.write("<div>");
            writer.write("<h1 style=\"margin-left: 23px;\">All Sculptures</h1>");
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
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("</div>");
            writer.write("</div>");

            writer.write("<h3>If you want to add a Sculpture to the Sculptor:</h3>");
            writer.write("<a href=\"/servlet_hw13/sculptorSculpture-add\">Add a Sculpture to the Sculptor</a>");

            writer.write("<h3>If you want to List all Sculptures of a Sculptor:<h3>");
            writer.write("<a href=\"/servlet_hw13/sculptorSculpture-list\">List all Sculptures of a Sculptor</a>");

            writer.write("<h3>If you want to Edit Sculptors:</h3>");
            writer.write("<a href=\"/servlet_hw13/sculptor\">Edit Sculptors</a>");

            writer.write("<h3>If you want to Edit Sculptures:</h3>");
            writer.write("<a href=\"/servlet_hw13/sculpture\">Edit Sculptures</a>");

            writer.write("</div>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }
}
