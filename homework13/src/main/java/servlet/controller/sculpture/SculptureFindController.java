package servlet.controller.sculpture;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.dto.SculptureResponseDto;
import servlet.facade.SculptureFacade;
import servlet.facade.impl.SculptureFacadeImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptureFindController extends HttpServlet {
    SculptureFacade sculptureFacade = new SculptureFacadeImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Find Sculptures</h1>");

            writer.write("<form method='post'>");
            writer.write("<table>");
            writer.write("<tr><td>Enter ID of Sculpture:</td><td><input type='text' name='Id'/></td></tr>");
            writer.write("<tr>");
            writer.write("<td><input type='submit' value='Find'/></td>");
            writer.write("</tr>");
            writer.write("</table>");
            writer.write("</form>");

            writer.write("<form method='get' action='/servlet_hw13/sculpture'>");
            writer.write("<input type='submit' value='Back to Sculpture Review'/>");
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
        SculptureResponseDto sculptureDto = sculptureFacade.findById(Long.valueOf(id));

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");

            if (sculptureDto != null) {
                writer.write("<h1>Sculpture Information</h1>");
                writer.write("<p>ID: " + sculptureDto.id() + "</p>");
                writer.write("<p>Name: " + sculptureDto.name() + "</p>");
                writer.write("<p>Material: " + sculptureDto.materialOfSculpture() + "</p>");
                writer.write("<p>Art Style: " + sculptureDto.artisticStyle() + "</p>");
                writer.write("<p>Publishing Year: " + sculptureDto.yearOfPublishing() + "</p>");
            } else {
                writer.write("<p>Sculpture not found</p>");
            }
            writer.write("<form method='get' action='/servlet_hw13/sculpture-find'>");
            writer.write("<input type='submit' value='Back to Sculpture Find'/>");
            writer.write("</form>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }


}
