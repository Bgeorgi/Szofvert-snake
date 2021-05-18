package Data;


import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Builder
public class Result {

    /**
     * The player id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

    /**
     * The player name
     */
    @Column(nullable = false)
     private String player;


    /**
     * The player score
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
