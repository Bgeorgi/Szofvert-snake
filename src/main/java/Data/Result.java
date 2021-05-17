package Data;


import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * Egy olyan osztály amelynek adattagjai a játékos által jászott játék eredmményeit tartalmazza.
 */


@Data
@Entity
@Builder
public class Result {

    /*
     * A játékos id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

    /*
     * A játékos neve.
     */
    @Column(nullable = false)
     private String player;


    /*
     * A játékos által elért pontszám.
     */
    @Column(nullable = false)
     private int score;



    public Result(){

    }

    public Result(long id, String player, int score) {
        this.id = id;
        this.player = player;
        this.score = score;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
