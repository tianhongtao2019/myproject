import com.tht.domain.Users;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
public class Test {
    public static void main(String[] args){

        Users usersEntity = new Users();
        usersEntity.setId("12");
        usersEntity.setAge(24);
        usersEntity.setHeight(122.1);
        usersEntity.setName("王丹凤");
        usersEntity.setPasswd("123");
        usersEntity.setSex("女");
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.save(usersEntity);
        transaction.commit();
        session.close();

    }
}
