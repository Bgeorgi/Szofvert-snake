package Data;

import org.jdbi.v3.sqlobject.config.*;
import org.jdbi.v3.sqlobject.customizer.*;
import org.jdbi.v3.sqlobject.statement.*;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/*
 * DAO interfésze a {@link GameResult} osztálynak.
 */
@RegisterBeanMapper(Result.class)
public interface ResultDao {




    void setEntityManager(EntityManager test);

    /*
     * Ez a lekérdezés  létrehozza a result táblát.
     */
    @SqlUpdate("""
            CREATE TABLE IF NOT EXISTS result (
            id IDENTITY PRIMARY KEY,
            player VARCHAR NOT NULL,
            score INTEGER NOT NULL,
            date VARCHAR NOT NULL
        )
        """
    )
    void createTable();

    /*
     * Ez a lekérdezés új rekordot illeszt be.
     */
    @SqlUpdate("INSERT INTO result (id, player, score, date) VALUES (:id, :player, :score, :date)")
    @GetGeneratedKeys
    Long insert(@BindBean Result user);

    /*
     * Ez a lekérdezés visszaadja a tábla össze elemét egy Result típusú listában.
     */
    @SqlQuery("SELECT * FROM result")
    List<Result> list();

    /*
     * Ez a lekérdezés visszaadja a megadott id azonosítóhoz tartozó összes mezőt
     */
    @SqlQuery("SELECT * FROM result WHERE id = :id")
    Result findById(@Bind("id") long id);
}