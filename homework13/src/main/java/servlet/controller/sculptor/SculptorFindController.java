package servlet.controller.sculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.dto.SculptorResponseDto;
import servlet.facade.SculptorFacade;
import servlet.facade.impl.SculptorFacadeImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptorFindController extends HttpServlet {
    SculptorFacade sculptorFacade = new SculptorFacadeImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Find Sculptors</h1>");

            writer.write("<form method='post'>");
            writer.write("<table>");
            writer.write("<tr><td>Enter ID of Sculptor:</td><td><input type='text' name='Id'/></td></tr>");
            writer.write("<tr>");
            writer.write("<td><input type='submit' value='Find'/></td>");
            writer.write("</tr>");
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

        String id = req.getParameter("Id");
        SculptorResponseDto sculptorDto = sculptorFacade.findById(Long.valueOf(id));

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");

            if (sculptorDto != null) {
                writer.write("<h1>Sculptor Information</h1>");
                writer.write("<p>ID: " + sculptorDto.id() + "</p>");
                writer.write("<p>First Name: " + sculptorDto.firstName() + "</p>");
                writer.write("<p>Last Name: " + sculptorDto.lastName() + "</p>");
                writer.write("<p>Age: " + sculptorDto.age() + "</p>");
            } else {
                writer.write("<p>Sculptor not found</p>");
            }
            writer.write("<form method='get' action='/servlet_hw13/sculptor-find'>");
            writer.write("<input type='submit' value='Back to Sculptor Find'/>");
            writer.write("</form>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }
}
