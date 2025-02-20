package databaseService;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Collections;
import java.util.List;


@AllArgsConstructor
public class DAO {
    private SessionFactory sessionFactory;

    public void saveGameToBase(GameNameEntity gameNameEntity) {
        try (Session session = sessionFactory.openSession()) {

            session.saveOrUpdate(gameNameEntity);


        } catch (HibernateException ex) {
            System.err.println("Błąd: " + ex.getMessage());
        }
    }


    public List<FieldEntity> getFieldListByGameName(String gameName) {
        List<FieldEntity> entityList = Collections.emptyList();
        try (Session session = sessionFactory.openSession()) {


            GameNameEntity gameNameEntity = session.createQuery(
                            "From GameNameEntity where gameName =:gameName", GameNameEntity.class)
                    .setParameter("gameName", gameName)
                    .getSingleResult();
            entityList = gameNameEntity.getFieldEnities();


        } catch (HibernateException ex) {
            System.err.println("Błąd: " + ex.getMessage());
        }
        return entityList;
    }

    public void removeGameByName(String gameName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            GameNameEntity game = session.createQuery(
                            "From GameNameEntity where gameName =:gameName", GameNameEntity.class)
                    .setParameter("gameName", gameName)
                    .getSingleResult();
            session.remove(game);

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Błąd: " + ex.getMessage());
        }
    }

    public List<String> getListOfGameNames() {
        List<String> gameNameList;
        try (Session session = sessionFactory.openSession()) {
            gameNameList = session.createQuery("Select name From GameNameEntity", String.class).getResultList();
        } catch (HibernateException ex) {
            System.err.println("Błąd: " + ex.getMessage());
            return Collections.emptyList();
        }
        return gameNameList;
    }

}
