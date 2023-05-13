package service;

import dao.PlayersDAO;
import dto.Match;
import dto.score.Scorer;
import model.Matches;
import model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    Map<UUID, Match> currentMatches = new HashMap<>();
    public UUID newMatch(String player1Name, String player2Name) {
        PlayersDAO playersDAO = new PlayersDAO();
        Player player1 = playersDAO.addPlayer(player1Name);
        Player player2 = playersDAO.addPlayer(player2Name);
        Match matches = new Match(player1, player2);
        UUID uuid = UUID.randomUUID();
        currentMatches.put(uuid, matches);
        return uuid;
    }

    public Match getMatch(UUID uuid, String winner) {
        Match match = currentMatches.get(uuid);
        if (winner.equals("firstWon")) {
            match.setWinner(Scorer.FIRST_PLAYER);
        } else if (winner.equals("secondWon")) {
            match.setWinner(Scorer.SECOND_PLAYER);
        }
        return match;
    }
}

