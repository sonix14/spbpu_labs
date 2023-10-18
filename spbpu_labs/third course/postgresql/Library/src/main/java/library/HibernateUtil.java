package autopark;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static{
        try{
            sessionFactory = new Configuration().configure()
                    .addAnnotatedClass(autopark.model.Clients.class)
                    .addAnnotatedClass(autopark.model.BookTypes.class)
                    .addAnnotatedClass(autopark.model.Books.class)
                    .addAnnotatedClass(autopark.model.Journal.class)
                    .buildSessionFactory();

        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static <T> List<T> getAll(Class<T> ofClass){
        Session session = getSessionFactory().openSession();
        Query<T> query = session.createQuery("from " + ofClass.getSimpleName(), ofClass);
        List<T> answer = query.list();
        session.close();
        return answer;
    }

}
