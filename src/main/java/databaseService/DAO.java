package databaseService;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class DAO {
    private SessionFactory sessionFactory;

    public void saveGameToBase(GameNameEntity gameNameEntity) {
        try (Session session = sessionFactory.openSession()) {

            List<String> gamenames = getListOfGameNames();
            Boolean isNameExist = false;
            for (String gamename : gamenames) {
                if (Objects.equals(gamename, gameNameEntity.getName())) {
                    session.update(gameNameEntity);
                    isNameExist = true;
                    break;

                }

            }
            if (!isNameExist){
                session.save(gameNameEntity);
            }


        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }


    public List<FieldEntity> getFieldListByGameName(String gameName) {
        List<FieldEntity> entityList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {


            String hql = "From GameNameEntity where gameName =:gameName";
            Query query = session.createQuery(hql);
            query.setParameter("gameName", gameName);
            GameNameEntity gameNameEntity = (GameNameEntity) query.uniqueResult();
            entityList = gameNameEntity.getFieldEnities();


        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return entityList;
    }

    public List<String> getListOfGameNames() {
        List<String> gameNameList;
        try (Session session = sessionFactory.openSession()) {
            gameNameList = session.createQuery("Select name From GameNameEntity", String.class).getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
        return gameNameList;
    }

}
