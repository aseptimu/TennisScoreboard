package dto.score;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Score {
    public static final int LAST_SET = 3;
    public static final int LAST_GAME = 6;
    public static final int LAST_TIE_BREAK = 7;

    @Setter
    private Point point = Point.LOVE;
    private Integer tieBreak = 0;
    private Integer game = 0;
    private Integer set = 0;


    public void setSet(int set) {
        if (set >= 0 && set <= LAST_SET) {
            this.set = set;
        }
    }

    public void setGame(Integer game) {
        if (game >= 0 && game <= LAST_GAME) {
            this.game = game;
        }
    }

    public void setTieBreak(Integer tieBreak) {
        if (tieBreak >= 0 && tieBreak <= LAST_TIE_BREAK) {
            this.tieBreak = tieBreak;
        }
    }


    public void wonPoint() {
        switch (point) {
            case LOVE -> point = Point.FIFTEEN;
            case FIFTEEN -> point = Point.THIRTY;
            case THIRTY -> point = Point.FORTY;
            case FORTY -> point = Point.ADVANTAGE;
            case ADVANTAGE -> point = Point.GAME;
        }
    }

    public void wonGame() {
        if (game < LAST_GAME) {
            game++;
        }
    }

    public void wonSet() {
        if (set < LAST_SET) {
            set++;
        }
    }
    public void wonTieBreak() {
        if (tieBreak < LAST_TIE_BREAK) {
            tieBreak++;
        }
    }
    public enum Point {
        LOVE("0"), FIFTEEN("15"),
        THIRTY("30"), FORTY("40"),
        ADVANTAGE("Ad"), GAME("G");
        private final String score;

        Point(String score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return score;
        }
    }
}
