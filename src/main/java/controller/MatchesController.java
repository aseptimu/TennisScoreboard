package controller;

import dao.MatchDAO;
import entities.Matches;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesController", value = "/matches")
public class MatchesController extends HttpServlet {
    private static final int NUMBER_OF_MATCHES_ON_PAGE = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatchDAO dao = new MatchDAO();
        List<Matches> all = dao.getAll();

        String nameFilter = request.getParameter("filter_by_player_name");
        if (nameFilter != null && !nameFilter.isEmpty()) {
            all.removeIf(match -> !match.getPlayer1().getName().equals(nameFilter) &&
                    !match.getPlayer2().getName().equals(nameFilter));
        }

        String pageString = request.getParameter("page");
        int page = 1;
        if (pageString != null && !pageString.isEmpty()) {
            try {
                page = Integer.parseInt(pageString);
                if (page <= 0) {
                    page = 1;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        int listBegin = (page - 1) * NUMBER_OF_MATCHES_ON_PAGE;
        int listEnd = listBegin + NUMBER_OF_MATCHES_ON_PAGE;
        request.setAttribute("page", page);
        request.setAttribute("listBegin", listBegin);
        request.setAttribute("listEnd", listEnd);
        request.setAttribute("matches", all);
        request.getRequestDispatcher("matches.jsp").forward(request, response);
    }

}
