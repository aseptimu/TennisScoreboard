package service;

import dao.MatchDAO;
import dao.PlayersDAO;
import dto.Match;

public class FinishedMatchesPersistenceService {
    public void persist(Match match) {
        PlayersDAO playersDAO = new PlayersDAO();
        MatchDAO matchDAO = new MatchDAO();

//        playersDAO.create(match.getPlayer1());
//        playersDAO.create(match.getPlayer2());
//        match.setPlayer1(player1);
//        match.setPlayer2(player2);
        matchDAO.create(match);
    }
}
