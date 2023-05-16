package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

}
