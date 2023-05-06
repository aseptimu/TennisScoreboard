package model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="Matches")
public class Match {
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
}
