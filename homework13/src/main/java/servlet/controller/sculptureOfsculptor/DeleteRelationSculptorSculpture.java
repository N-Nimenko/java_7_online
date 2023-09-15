package servlet.controller.sculptureOfsculptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.service.SculptorService;
import servlet.service.impl.SculptorServiceImpl;

import java.io.IOException;

public class DeleteRelationSculptorSculpture extends HttpServlet {

    SculptorService sculptorService = new SculptorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sculptorIdParam = req.getParameter("sculptorId");
        String sculptureIdParam = req.getParameter("sculptureId");

        if (sculptorIdParam == null || sculptureIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Long sculptorId = Long.parseLong(sculptorIdParam);
            Long sculptureId = Long.parseLong(sculptureIdParam);

            if (sculptorService.sculptorSculptureRelationExists(sculptorId, sculptureId)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Sculptor-Sculpture Relation deleted successfully");

                sculptorService.deleteSculptorSculptureRelation(sculptorId, sculptureId);

                resp.sendRedirect("/servlet_hw13/sculptorSculpture-list");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Sculptor-Sculpture Relation not found");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid ID format");
        }
    }
}

