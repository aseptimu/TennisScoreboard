package service;

import dao.MatchDAO;
import dto.Match;
import dto.score.Scorer;
import entities.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<UUID, Match> currentMatches = new ConcurrentHashMap<>();
    private Match finishedMatch;
    public UUID newMatch(String player1Name, String player2Name) {
        MatchDAO dao = new MatchDAO();
        Optional<Player> player1 = dao.getPlayerByName(player1Name);
        if (player1.isEmpty()) {
            player1 = Optional.of(new Player());
            player1.get().setName(player1Name);
        }
        Optional<Player> player2 = dao.getPlayerByName(player2Name);
        if (player2.isEmpty()) {
            player2 = Optional.of(new Player());
            player2.get().setName(player2Name);
        }
        Match match = new Match(player1.get(), player2.get());
        UUID uuid = match.getUuid();
        currentMatches.put(uuid, match);
        return uuid;
    }

    public Optional<Match> getMatch(UUID uuid) {
        return Optional.ofNullable(currentMatches.get(uuid));
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
        finishedMatch = currentMatches.remove(uuid);
    }

    public Match getFinishedMatch() {
        Match ret = finishedMatch;
        finishedMatch = null;
        return ret;
    }
}

