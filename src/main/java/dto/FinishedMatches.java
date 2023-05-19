package dto;

import lombok.Getter;
import lombok.Setter;
import entities.Player;

@Getter
@Setter
public class FinishedMatches {
    private Player player1;
    private Player player2;
    private Player winner;
}
