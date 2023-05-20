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
    OngoingMatchesService matchesService = new OngoingMatchesService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidString = request.getParameter("uuid");
        if (uuidString == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/errors/400.html").forward(request, response);
            return;
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/errors/400.html").forward(request, response);
            return;
        }
        Optional<Match> match = matchesService.getMatch(uuid);
        if (match.isEmpty()) {
            match = Optional.ofNullable(matchesService.getFinishedMatch());
            if (match.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("reason", "Match not found");
                request.getRequestDispatcher("/errors/404.jsp").forward(request, response);
                return;
            }
        }
        request.setAttribute("match", match.get());
        request.getRequestDispatcher("match-score.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID matchUUID = UUID.fromString(request.getParameter("uuid"));
        String winner = request.getParameter("winner");
        Match match = matchesService.getMatch(matchUUID, winner);
        MatchScoreCalculationService calculation = new MatchScoreCalculationService();
        calculation.calculate(match);
        if (match.getStage() == GameStage.PLAYER_WON) {
            matchesService.remove(matchUUID);
            FinishedMatchesPersistenceService persistence = new FinishedMatchesPersistenceService();
            persistence.persist(match);
        }
        response.sendRedirect("/match-score?uuid=" + matchUUID);
    }
}
