package servlet.controller.sculptureOfsculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.service.SculptorService;
import servlet.service.SculptureService;
import servlet.service.impl.SculptorServiceImpl;
import servlet.service.impl.SculptureServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListAllSculpturesOfSculptor extends HttpServlet {
    SculptorService sculptorService = new SculptorServiceImpl();
    SculptureService sculptureService = new SculptureServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Long> sculptorIds = sculptorService.getAllSculptorIds();
        List<Long> sculptureIds = sculptureService.getAllSculptureIds();

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

            writer.write("td:nth-child(3) {");
            writer.write("    padding-left: 20px;");
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

            writer.write("<h1>List of Sculptor-Sculpture Relations</h1>");
            writer.write("<table>");
            writer.write("<tr>");
            writer.write("<th>Sculptor ID</th>");
            writer.write("<th>Sculpture ID</th>");
            writer.write("</tr>");

            for (int i = 0; i < Math.min(sculptorIds.size(), sculptureIds.size()); i++) {
                writer.write("<tr>");
                writer.write("<td>");
                writer.write(String.valueOf(sculptorIds.get(i)));
                writer.write("</td>");
                writer.write("<td>");
                writer.write(String.valueOf(sculptureIds.get(i)));
                writer.write("</td>");

                writer.write("<td>");
                writer.write("<a href='/servlet_hw13/sculptorSculpture-delete?sculptorId=" + sculptorIds.get(i) + "&sculptureId=" + sculptureIds.get(i) + "'>Delete</a>");
                writer.write("</td>");

                writer.write("</tr>");
            }

            writer.write("</table>");

            writer.write("<form method='get' action='/servlet_hw13/sculptorSculpture'>");
            writer.write("<input type='submit' value='Back to Sculptor-Sculpture review'/>");
            writer.write("</form>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }
}

