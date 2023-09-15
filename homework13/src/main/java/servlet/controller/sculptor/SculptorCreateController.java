package servlet.controller.sculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.dto.SculptorRequestDto;
import servlet.facade.SculptorFacade;
import servlet.facade.impl.SculptorFacadeImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptorCreateController extends HttpServlet {
    private final SculptorFacade sculptorFacade = new SculptorFacadeImpl();
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        try(PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>New Sculptors</h1>");
            writer.write("<form method=\"post\" action=\"/servlet_hw13/sculptor-new\">");

            writer.write("<table>");
            writer.write("<tr><td>First_Name:</td><td><input type='text' name='firstName'/></td></tr>");
            writer.write("<tr><td>Last_Name:</td><td><input type='text' name='lastName'/></td></tr>");
            writer.write("<tr><td>Age:</td><td><input type='text' name='age'/></td></tr>");
            writer.write("<tr><td><input type='submit' value='Create'/></td></tr>");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String ageParam = req.getParameter("age");

        if (firstName != null && lastName != null && ageParam != null) {
            try {
                int age = Integer.parseInt(ageParam);
                SculptorRequestDto dto = new SculptorRequestDto(firstName, lastName, age);
                sculptorFacade.create(dto);
                resp.sendRedirect("sculptor");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Invalid age format");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing required parameters (firstName, lastName, age)");
        }
    }
}
