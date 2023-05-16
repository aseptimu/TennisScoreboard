package service;

import dao.PlayersDAO;
import dto.Match;

public class FinishedMatchesPersistenceService {
    public void persist(Match match) {
        PlayersDAO playersDAO = new PlayersDAO();
        playersDAO.addPlayer(match.getPlayer1());
        playersDAO.addPlayer(match.getPlayer2());
    }
}
