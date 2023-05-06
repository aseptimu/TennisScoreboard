package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
