package service;

import dao.PlayersDAO;
import dto.Match;
import dto.score.Scorer;
import model.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<UUID, Match> currentMatches = new ConcurrentHashMap<>();
    public UUID newMatch(String player1Name, String player2Name) {
        PlayersDAO playersDAO = new PlayersDAO();
        Player player1 = new Player();
        player1.setName(player1Name);
        Player player2 = new Player();
        player2.setName(player2Name);
        Match match = new Match(player1, player2);
        UUID uuid = match.getUuid();
        currentMatches.put(uuid, match);
        return uuid;
    }

    public Optional<Match> getMatch(UUID uuid) {
        return Optional.of(currentMatches.get(uuid));
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

    public void remove(UUID uuid) {
        currentMatches.remove(uuid);
    }
}

