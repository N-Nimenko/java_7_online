package servlet.controller.sculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.entity.Sculptor;
import servlet.service.SculptorService;
import servlet.service.impl.SculptorServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class SculptorDeleteController extends HttpServlet {
    SculptorService sculptorService = new SculptorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long id = Long.parseLong(idParam);
        Sculptor sculptor = sculptorService.findById(id);

        if (sculptor == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Delete Sculptors</h1>");

            writer.write("<form method=\"post\" action=\"/servlet_hw13/sculptor-delete\">");
            writer.write("<h3>Press Delete if you want to delete Sculptor<h3>");
            writer.write("<input type='hidden' name='id' value='" + sculptor.getId() + "'/>");
            writer.write("<input type='submit' value='Delete'/>");
            writer.write("</form>");

            writer.write("<div style='margin-top: 10px'></div>");
            writer.write("<form method='get' action='/servlet_hw13/sculptor'>");
            writer.write("<input type='submit' value='Back to Sculptor Review'/>");
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
            Sculptor sculptor = sculptorService.findById(id);

            if (sculptor != null) {
                sculptorService.delete(id);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Sculptor deleted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Sculptor not found");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid request");
        }
    }
}
