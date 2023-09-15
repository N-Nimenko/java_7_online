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

public class SculptureDeleteController extends HttpServlet {
    SculptureService sculptureService = new SculptureServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long id = Long.parseLong(idParam);
        Sculpture sculpture = sculptureService.findById(id);

        if (sculpture == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Delete Sculptures</h1>");

            writer.write("<form method=\"post\" action=\"/servlet_hw13/sculpture-delete\">");
            writer.write("<h3>Press Delete if you want to delete Sculpture<h3>");
            writer.write("<input type='hidden' name='id' value='" + sculpture.getId() + "'/>");
            writer.write("<input type='submit' value='Delete'/>");
            writer.write("</form>");

            writer.write("<div style='margin-top: 10px'></div>");
            writer.write("<form method='get' action='/servlet_hw13/sculpture'>");
            writer.write("<input type='submit' value='Back to Sculpture Review'/>");
            writer.write("</form>");

            writer.write("</body>");
            writer.write("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            Sculpture sculpture = sculptureService.findById(id);

            if (sculpture != null) {
                sculptureService.delete(id);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Sculpture deleted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Sculpture not found");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid request");
        }
        resp.sendRedirect("sculpture");
    }
}
