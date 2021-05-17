package Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * Egy olyan osztály amelynek adattagjai a játékos által jászott játék eredmményeit tartalmazza.
 */


@Builder
@Data
public class Result {

    /*
     * A játékos sorszáma.
     */
    private Long id;

    /*
     * A játékos neve.
     */
    private String player;


    /*
     * A játékos által elért pontszám.
     */
    private int score;

    /*
     * Mikor fejezte be a játékot.
     */
    private String date;


    public Result(Long id, String player, int score, String date) {
        this.id = id;
        this.player = player;
        this.score = score;
        this.date = date;
    }
}
