package service;

import dto.Match;
import dto.score.GameStage;
import dto.score.Score;
import dto.score.Scorer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchCalculationServiceTest {
    Match match;
    MatchScoreCalculationService service;

    @BeforeEach
    void initMatch() {
        service = new MatchScoreCalculationService();
        match = new Match();
    }

    @Test
    void AdvantagePoint() {
        match.getFirstPlayerScore().setPoint(Score.Point.FORTY);
        match.getSecondPlayerScore().setPoint(Score.Point.FORTY);
        match.setWinner(Scorer.FIRST_PLAYER);
        service.calculate(match);
        Assertions.assertEquals(Score.Point.ADVANTAGE, match.getFirstPlayerScore().getPoint());
        Assertions.assertEquals(Score.Point.FORTY, match.getSecondPlayerScore().getPoint());
    }
    @Test
    void GameStageDeuce() {
        match.setWinner(Scorer.SECOND_PLAYER);
        match.getFirstPlayerScore().setPoint(Score.Point.FORTY);
        match.getSecondPlayerScore().setPoint(Score.Point.FORTY);
        service.calculate(match);
        Assertions.assertEquals(GameStage.POINT, match.getStage());
    }

    @Test
    void WinGameByPoint() {
        match.setWinner(Scorer.FIRST_PLAYER);
        match.getFirstPlayerScore().setPoint(Score.Point.FORTY);
        match.getSecondPlayerScore().setPoint(Score.Point.LOVE);
        service.calculate(match);
        Assertions.assertEquals(GameStage.GAME_WON, match.getStage());
        Assertions.assertEquals(Score.Point.LOVE, match.getFirstPlayerScore().getPoint());
        Assertions.assertEquals(Score.Point.LOVE, match.getSecondPlayerScore().getPoint());
    }

    @Test
    void TieBreak() {
        match.setWinner(Scorer.FIRST_PLAYER);
        match.getFirstPlayerScore().setPoint(Score.Point.FORTY);
        match.getSecondPlayerScore().setPoint(Score.Point.LOVE);
        match.getFirstPlayerScore().setGame(5);
        match.getSecondPlayerScore().setGame(6);
        service.calculate(match);
        Assertions.assertEquals(GameStage.TIE_BREAK, match.getStage());
        Assertions.assertEquals(0, match.getFirstPlayerScore().getTieBreak());
        Assertions.assertEquals(0, match.getSecondPlayerScore().getTieBreak());
    }

    @Test
    void TieBreakScore() {
        match.setWinner(Scorer.FIRST_PLAYER);
        match.setStage(GameStage.TIE_BREAK);
        service.calculate(match);
        Assertions.assertEquals(GameStage.TIE_BREAK, match.getStage());
        Assertions.assertEquals(1, match.getFirstPlayerScore().getTieBreak());
        Assertions.assertEquals(0, match.getSecondPlayerScore().getTieBreak());
    }

    @Test
    void GameWonByTieBreak() {
        match.setWinner(Scorer.FIRST_PLAYER);
        match.setStage(GameStage.TIE_BREAK);
        match.getFirstPlayerScore().setTieBreak(6);
        match.getFirstPlayerScore().setTieBreak(6);
        service.calculate(match);
        Assertions.assertEquals(GameStage.SET_WON, match.getStage());
        Assertions.assertEquals(1, match.getFirstPlayerScore().getSet());
        Assertions.assertEquals(0, match.getSecondPlayerScore().getSet());
    }

    @Test
    void setWon() {
        match.setWinner(Scorer.FIRST_PLAYER);
        match.getFirstPlayerScore().setPoint(Score.Point.FORTY);
        match.getSecondPlayerScore().setPoint(Score.Point.LOVE);
        match.getFirstPlayerScore().setGame(6);
        match.getSecondPlayerScore().setGame(5);
        service.calculate(match);
        Assertions.assertEquals(GameStage.SET_WON, match.getStage());
        Assertions.assertEquals(1, match.getFirstPlayerScore().getSet());
        Assertions.assertEquals(0, match.getSecondPlayerScore().getSet());
    }

}
