package controller;

import dto.Match;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScore", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(request.getParameter("uuid"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OngoingMatchesService ongoingService = new OngoingMatchesService();
        UUID matchUUID = UUID.fromString(request.getParameter("uuid"));
        String winner = request.getParameter("winner");
        Match match = ongoingService.getMatch(matchUUID, winner);
        MatchScoreCalculationService calculation = new MatchScoreCalculationService();
        calculation.calculate(match);
        FinishedMatchesPersistenceService persistence = new FinishedMatchesPersistenceService();
        persistence.persist(match);
    }
}
