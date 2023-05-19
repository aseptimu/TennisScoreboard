package dto;

import dto.score.Score;
import lombok.Getter;
import lombok.Setter;
import dto.score.GameStage;
import dto.score.Scorer;
import entities.Player;

import java.util.UUID;

@Getter
public class Match {
    @Setter
    private UUID uuid;
    @Setter
    private Player player1;
    @Setter
    private Player player2;
    private final Score firstPlayerScore = new Score();
    private final Score secondPlayerScore = new Score();
    @Setter
    private Scorer winner = Scorer.FIRST_PLAYER;
    @Setter
    private GameStage stage = GameStage.POINT;

    public void setDefaultSet() {
        firstPlayerScore.setSet(0);
        secondPlayerScore.setSet(0);
    }

    public void setDefaultGame() {
        firstPlayerScore.setGame(0);
        secondPlayerScore.setGame(0);
    }

    public void setDefaultTieBreak() {
        firstPlayerScore.setTieBreak(0);
        secondPlayerScore.setTieBreak(0);
    }

    public void setDefaultPoint() {
        firstPlayerScore.setPoint(Score.Point.LOVE);
        secondPlayerScore.setPoint(Score.Point.LOVE);
    }

    public Match(Player player1, Player player2) {
        this.uuid = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
    }

    public Match() {
    }
}
