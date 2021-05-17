package Data;

import Data.Result;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class ResultRepository extends DataBase<Result> {

    public List<Result> Query() {
        EntityManager em = DataConnection.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Result> cq = cb.createQuery(Result.class);
        Root<Result> from = cq.from(Result.class);

        cq.select(from);

        try {
            Query q = em.createQuery(cq);
            Logger.info("Table loaded successfully");
            return q.getResultList();
        } catch (Exception e) {
            Logger.error("Select failed");
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

}
