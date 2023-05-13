package model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Matches")
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "Player2")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;

    @Transient
    private int score;

    public Matches(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        score = 0;
    }

    public Matches() {

    }
}
