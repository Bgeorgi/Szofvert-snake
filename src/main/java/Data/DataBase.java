package Data;


import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

import org.tinylog.Logger;

public abstract class DataBase<T> {



    private final Class<T> entityClass;

    public DataBase(){
        this.entityClass = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }


    /**
     * Uploads data to the Database
     * @param entity contains data
     */
    public void AddNewResult(T entity){
        EntityManager em = DataConnection.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            Logger.info("New entity added");
        }catch (Exception e){
            Logger.error("Failed to upload entity" + e.toString());
        }finally {
            em.close();
        }
    }

}
