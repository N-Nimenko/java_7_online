package servlet.controller.sculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.dto.SculptorRequestDto;
import servlet.entity.Sculptor;
import servlet.facade.SculptorFacade;
import servlet.facade.impl.SculptorFacadeImpl;
import servlet.service.SculptorService;
import servlet.service.impl.SculptorServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptorUpdateController extends HttpServlet {
    private final SculptorService sculptorService = new SculptorServiceImpl();
    private final SculptorFacade sculptorFacade = new SculptorFacadeImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);
        Sculptor sculptor = sculptorService.findById(id);

        try(PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Update Sculptors</h1>");

            writer.write("<form method=\"post\" action=\"/servlet_hw13/sculptor-update\">");
            writer.write("<table>");
            writer.write("<tr><td></td><td><input type='hidden' name='id' value='" + sculptor.getId() + "'/></td></tr>");
            writer.write("<tr><td>First_Name:</td><td><input type='text' name='firstName' value='" + sculptor.getFirstName() + "'/></td></tr>");
            writer.write("<tr><td>Last_Name:</td><td><input type='text' name='lastName' value='" + sculptor.getLastName() + "'/></td></tr>");
            writer.write("<tr><td>Age:</td><td><input type='text' name='age' value='" + sculptor.getAge() + "'/></td></tr>");
            writer.write("<tr><td><input type='submit' value='Update'/></td></tr>");
            writer.write("</table>");
            writer.write("</form>");

            writer.write("<form method='get' action='/servlet_hw13/sculptor'>");
            writer.write("<input type='submit' value='Back to Sculptor Review'/>");
            writer.write("</form>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(201);

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid ID");
            return;
        }

        Long id = Long.parseLong(idParam);
        Sculptor sculptor = sculptorService.findById(id);
        if (sculptor == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Sculptor not found");
            return;
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String ageParam = req.getParameter("age");

        if (firstName == null || lastName == null || ageParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing required parameters (firstName, lastName, age)");
            return;
        }

        try {
            int age = Integer.parseInt(ageParam);
            SculptorRequestDto updatedSculptorDto = new SculptorRequestDto(firstName, lastName, age);
            sculptorFacade.update(updatedSculptorDto, sculptor.getId());
            resp.sendRedirect("sculptor");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid age format");
        }
    }
}
