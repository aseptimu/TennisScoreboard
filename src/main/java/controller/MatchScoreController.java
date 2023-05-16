package controller;

import dto.Match;
import dto.score.GameStage;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "MatchScore", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            System.err.println("error");
            return;
        }//TODO: error
        OngoingMatchesService matchesService = new OngoingMatchesService();
        Optional<Match> match = matchesService.getMatch(UUID.fromString(uuid));
        if (match.isEmpty()) {
            //TODO: берём из базы финальный счёт
        }
        request.setAttribute("match", match);
        request.getRequestDispatcher("match-score.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OngoingMatchesService ongoingService = new OngoingMatchesService();
        UUID matchUUID = UUID.fromString(request.getParameter("uuid"));
        String winner = request.getParameter("winner");
        Match match = ongoingService.getMatch(matchUUID, winner);
        MatchScoreCalculationService calculation = new MatchScoreCalculationService();
        calculation.calculate(match);
        if (match.getStage() == GameStage.PLAYER_WON) {
            ongoingService.remove(matchUUID);
            FinishedMatchesPersistenceService persistence = new FinishedMatchesPersistenceService();
            persistence.persist(match);
        }
        response.sendRedirect("/match-score?uuid=" + matchUUID);
    }
}
