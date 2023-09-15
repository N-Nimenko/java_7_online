package servlet.controller.sculpture;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.dto.SculptureRequestDto;
import servlet.entity.Sculpture;
import servlet.facade.SculptureFacade;
import servlet.facade.impl.SculptureFacadeImpl;
import servlet.service.SculptureService;
import servlet.service.impl.SculptureServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptureUpdateController extends HttpServlet {
    private final SculptureService sculptureService = new SculptureServiceImpl();
    private final SculptureFacade sculptureFacade = new SculptureFacadeImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam);
        Sculpture sculpture = sculptureService.findById(id);

        try(PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Update Sculptures</h1>");

            writer.write("<form method=\"post\" action=\"/servlet_hw13/sculpture-update\">");
            writer.write("<table>");
            writer.write("<tr><td></td><td><input type='hidden' name='id' value='" + sculpture.getId() + "'/></td></tr>");
            writer.write("<tr><td>Sculpture Name:</td><td><input type='text' name='sculptureName' value='" + sculpture.getName() + "'/></td></tr>");
            writer.write("<tr><td>Sculpture Material:</td><td><input type='text' name='sculptureMaterial' value='" + sculpture.getMaterialOfSculpture() + "'/></td></tr>");
            writer.write("<tr><td>Sculpture Art Style:</td><td><input type='text' name='sculptureArtStyle' value='" + sculpture.getArtisticStyle() + "'/></td></tr>");
            writer.write("<tr><td>Publishing Year:</td><td><input type='text' name='publishingYear' value='" + sculpture.getYearOfPublishing() + "'/></td></tr>");
            writer.write("<tr><td><input type='submit' value='Update'/></td></tr>");
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

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid ID");
            return;
        }

        Long id = Long.parseLong(idParam);
        Sculpture sculpture = sculptureService.findById(id);
        if (sculpture == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Sculpture not found");
            return;
        }

        String sculptureName = req.getParameter("sculptureName");
        String sculptureMaterial = req.getParameter("sculptureMaterial");
        String sculptureArtStyle = req.getParameter("sculptureArtStyle");
        String publishingYearParam = req.getParameter("publishingYear");

        if (sculptureName == null || sculptureMaterial == null || sculptureArtStyle == null || publishingYearParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing required parameters (sculptureName, sculptureMaterial, sculptureArtStyle, publishingYear)");
            return;
        }

        try {
            int publishingYear = Integer.parseInt(publishingYearParam);
            SculptureRequestDto updatedSculptureDto = new SculptureRequestDto(sculptureName, sculptureMaterial, sculptureArtStyle, publishingYear);
            sculptureFacade.update(updatedSculptureDto, sculpture.getId());
            resp.sendRedirect("sculpture");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid publishing year format");
        }
    }
}
