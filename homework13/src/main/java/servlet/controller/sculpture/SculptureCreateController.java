package servlet.controller.sculpture;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.dto.SculptureRequestDto;
import servlet.facade.SculptureFacade;
import servlet.facade.impl.SculptureFacadeImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptureCreateController extends HttpServlet {
    private final SculptureFacade sculptureFacade = new SculptureFacadeImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>New Sculptures</h1>");

            writer.write("<form method=\"post\" action=\"/servlet_hw13/sculpture-new\">");
            writer.write("<table>");
            writer.write("<tr><td>Sculpture_Name:</td><td><input type='text' name='sculptureName'/></td></tr>");
            writer.write("<tr><td>Sculpture_Material:</td><td><input type='text' name='sculptureMaterial'/></td></tr>");
            writer.write("<tr><td>Sculpture_ArtStyle:</td><td><input type='text' name='sculptureArtStyle'/></td></tr>");
            writer.write("<tr><td>Publishing_Year:</td><td><input type='text' name='publishingYear'/></td></tr>");
            writer.write("<tr><td><input type='submit' value='Create'/></td></tr>");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String sculptureName = req.getParameter("sculptureName");
        String sculptureMaterial = req.getParameter("sculptureMaterial");
        String sculptureArtStyle = req.getParameter("sculptureArtStyle");
        String publishingYearParam = req.getParameter("publishingYear");

        if (sculptureName != null && sculptureMaterial != null && sculptureArtStyle != null && publishingYearParam != null) {
            try {
                int publishingYear = Integer.parseInt(publishingYearParam);
                SculptureRequestDto dto = new SculptureRequestDto(sculptureName, sculptureMaterial, sculptureArtStyle, publishingYear);
                sculptureFacade.create(dto);
                resp.sendRedirect("sculpture");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Invalid publishing year format");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing required parameters (sculptureName, sculptureMaterial, sculptureArtStyle, publishingYear)");
        }
    }
}
