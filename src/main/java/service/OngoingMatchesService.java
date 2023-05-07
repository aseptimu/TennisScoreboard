package service;

import dao.PlayersDAO;
import model.Match;
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
        Match match = new Match(player1, player2);
        UUID uuid = UUID.randomUUID();
        currentMatches.put(uuid, match);
        return uuid;
    }

    public Match getMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }
}

