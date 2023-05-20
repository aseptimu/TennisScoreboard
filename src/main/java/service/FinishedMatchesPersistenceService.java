package service;

import dao.MatchDAO;
import dto.Match;

public class FinishedMatchesPersistenceService {
    public void persist(Match match) {
        MatchDAO matchDAO = new MatchDAO();

        matchDAO.create(match);
    }
}
