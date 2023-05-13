package service;

import dto.Match;
import dto.score.GameStage;
import dto.score.Score;
import dto.score.Scorer;

public class MatchScoreCalculationService {

    public void calculate(Match match) {//TODO: setWinner earlier. Here we should know who is winner
        Score winner = match.getWinner() == Scorer.FIRST_PLAYER ?
                match.getFirstPlayerScore() : match.getSecondPlayerScore();
        Score looser = match.getWinner() == Scorer.FIRST_PLAYER ?
                match.getSecondPlayerScore() : match.getFirstPlayerScore();

        if (match.getStage() == GameStage.TIE_BREAK) {
            calculateTieBreak(winner, match);
        } else {
            calculatePoint(winner, looser, match);
        }
        if (match.getStage() == GameStage.GAME_WON) {
            match.setDefaultPoint();
            match.setDefaultTieBreak();
            calculateGame(winner, looser, match);
        }
        if (match.getStage() == GameStage.SET_WON) {
            match.setDefaultGame();
            calculateSet(winner, match);
        }
    }

    private void calculateSet(Score winner, Match match) {
        if (winner.getSet() == Score.LAST_SET - 1) {
            match.setStage(GameStage.PLAYER_WON);
        } else {
            winner.wonSet();
        }
    }

    private void calculateTieBreak(Score winner, Match match) {
        if (winner.getTieBreak() == Score.LAST_TIE_BREAK - 1) {
            match.setStage(GameStage.SET_WON);
        } else {
           winner.wonTieBreak();
        }
    }

    private void calculateGame(Score winner, Score looser, Match match) {
        if (winner.getGame() == Score.LAST_GAME) {
            match.setStage(GameStage.SET_WON);
        } else if (winner.getGame() == Score.LAST_GAME - 1 && looser.getGame() <= Score.LAST_GAME - 2) {
            match.setStage(GameStage.SET_WON);
        } else if (winner.getGame() == Score.LAST_GAME - 1 && looser.getGame() == Score.LAST_GAME) {
            match.setStage(GameStage.TIE_BREAK);
        } else {
            winner.wonGame();
        }
    }

    private void calculatePoint(Score winner, Score looser, Match match) {
        if (winner.getPoint() == Score.Point.FORTY && looser.getPoint().ordinal() < Score.Point.FORTY.ordinal()) {
            winner.setPoint(Score.Point.GAME);
            match.setStage(GameStage.GAME_WON);
        } else {
            winner.wonPoint();
            if (looser.getPoint() == Score.Point.ADVANTAGE) {
                looser.setPoint(Score.Point.FORTY);
            }
        }
        if (winner.getPoint() == Score.Point.GAME) {
            match.setStage(GameStage.GAME_WON);
        }
    }
}
