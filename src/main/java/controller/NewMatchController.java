package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/new_match.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");
        OngoingMatchesService service = new OngoingMatchesService();
        UUID uuid = service.newMatch(player1, player2);
        System.out.println("context path: " + request.getContextPath());
        response.sendRedirect("/match-score?uuid=" + uuid);
    }
}
