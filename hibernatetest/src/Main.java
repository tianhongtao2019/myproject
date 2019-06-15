import com.tht.domain.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    private static  SessionFactory ourSessionFactory;
    private static  Session session =null;
    private static Transaction transaction =null;
    static {
        try {
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(final String[] args)  {
        //changeUsers(26,"丹凤","changename");
        queryUsersAll();
    }

    private static void addUsers(Users users) {
        session = ourSessionFactory.openSession();
        try {
        transaction = session.beginTransaction();
            session.save(users);
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void removeUsers(int id) {
        session = ourSessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Users users = session.get(Users.class, id);
            session.delete(users);
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void changeUsers(int id,String name ,String description) {
        session = ourSessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Users users = session.get(Users.class, id);
            users.setName(name);
            users.setDescription(description);
            session.update(users);
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void queryUsersAll() {
        session = ourSessionFactory.getCurrentSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from  Users order by id asc ");
            List<Users> list = query.list();
            for (Users users : list) {
                System.out.println(users.getId()+"  名字:"+users.getName()+"  描述:"+users.getDescription());
            }
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
