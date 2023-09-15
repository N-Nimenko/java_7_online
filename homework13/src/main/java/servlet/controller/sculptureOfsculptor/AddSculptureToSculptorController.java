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

public class AddSculptureToSculptorController extends HttpServlet {
    SculptureService sculptureService = new SculptureServiceImpl();
    SculptorService sculptorService = new SculptorServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        try(PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<style>");
            writer.write("label {");
            writer.write("    display: block;");
            writer.write("    margin-bottom: 10px;");
            writer.write("}");
            writer.write("input[type='submit'] {");
            writer.write("    margin-top: 10px;");
            writer.write("}");
            writer.write("</style>");
            writer.write("</head>");
            writer.write("<body>");

            writer.write("<h1>Add Sculpture to Sculptor</h1>");
            writer.write("<form method='post'>");

            writer.write("<label for='sculptorId'>Sculptor ID:</label>");
            writer.write("<input type='text' id='sculptorId' name='sculptorId'><br>");

            writer.write("<label for='sculptureId'>Sculpture ID:</label>");
            writer.write("<input type='text' id='sculptureId' name='sculptureId'><br>");

            writer.write("<input type='submit' value='Add'>");
            writer.write("</form>");

            writer.write("<form method='get' action='/servlet_hw13/sculptorSculpture'>");
            writer.write("<input type='submit' value='Back to Sculptor And Sculptures Review'/>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(201);

        String sculptorIdParam = req.getParameter("sculptorId");
        String sculptureIdParam = req.getParameter("sculptureId");

        if (sculptorIdParam != null && sculptureIdParam != null) {
            try {
                Long sculptorId = Long.parseLong(sculptorIdParam);
                Long sculptureId = Long.parseLong(sculptureIdParam);

                Sculptor existingSculptor = sculptorService.findById(sculptorId);
                Sculpture existingSculpture = sculptureService.findById(sculptureId);

                if (existingSculptor != null && existingSculpture != null) {
                    sculptureService.addSculptureToSculptor(existingSculptor, existingSculpture);
                    resp.getWriter().write("Sculpture added to Sculptor successfully");
                } else {
                    resp.getWriter().write("Sculptor or Sculpture not found");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Invalid ID format");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing required parameters (sculptorId, sculptureId)");
        }
        resp.sendRedirect("/servlet_hw13/sculptorSculpture-list");
    }
}

