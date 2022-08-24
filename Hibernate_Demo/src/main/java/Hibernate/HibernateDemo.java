package Hibernate;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class HibernateDemo {

    private DataSource getDataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource() {
        };
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("root");
        dataSource.setPassword("123123");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hibernate");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
//        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("Hibernate");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        HibernateDemo jpaDemo = new HibernateDemo();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
//        insertToBook(em);
//        insertToBook(em);
        getBookById(em);
//        List<Author> tList = em.createQuery("select t from Author t join t.teacher_books ab").getResultList();
//        Author t = tList.get(0);
//        System.out.println("**************************************");
//        System.out.println("class is loaded : " + unitUtil.isLoaded(t));
//        System.out.println("collection is loaded : " + unitUtil.isLoaded(t, "teacher_books"));
//        List<Author_Book> teacher_books = t.getAuthor_books();
//        System.out.println("collection is loaded : " + unitUtil.isLoaded(teacher_books, "teacher_books"));
//        System.out.println(teacher_books);
//        System.out.println("collection is loaded : " + unitUtil.isLoaded(teacher_books, "teacher_books"));
//        System.out.println("**************************************");
    }

    private static void insertToBook(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Book b = new Book();
            b.setTitle("AB");
            b.setId(3);
            b.setYear(2002);
            em.merge(b);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

    }

    private static void getBookById(EntityManager em) {
        Query query = em.createQuery("select b from Book b left join fetch b.author_books ab where b.id = ?1");
        query.setParameter(1, 2);
        Book s = (Book)query.getSingleResult();
        System.out.println(s);
    }
//
    private static void addToJunctionTable1(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Book b = new Book();
            b.setTitle("AB");
            Author a = new Author();
            //persist t first to get new id
            em.persist(a);
            a.setName("1th teacher");
            //build connection between t and s
            Author_Book ab = new Author_Book();
            ab.setBook(b);
            ab.setAuthor(a);
            a.addAuthor_book(ab);

            em.persist(b);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void addToJunctionTable2(EntityManager em) {

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createNativeQuery("INSERT INTO AUTHOR_BOOK (AUTHOR_ID, BOOK_ID) VALUES (?, ?)");
            query.setParameter(1, 4);
            query.setParameter(2, 4);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void addToJunctionTable3(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Book s = em.find(Book.class, 14);
            Author t = em.find(Author.class, 9);
            Author_Book ab = new Author_Book();
            ab.setBook(s);
            ab.setAuthor(t);
            em.persist(ab);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void notOrphanRemoveBiRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("select b from Book b join fetch b.author_books ab where b.id = ?1");
            query.setParameter(1, 5);
            Book b = (Book) query.getSingleResult();
            Author a = em.find(Author.class, 3);
            List<Author_Book> teacher_books = new ArrayList<>();
            for(Author_Book ab: b.getAuthor_books()) {
                if(ab.getAuthor().getId().equals(a.getId())) {
                    teacher_books.add(ab);
                    em.remove(ab);
                }
            }
            b.getAuthor_books().removeAll(teacher_books);
            a.getAuthor_books().removeAll(teacher_books);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void notOrphanRemoveSingleRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("select b from Book b join fetch s.author_books ab where b.id = ?1");
            query.setParameter(1, 5);
            Book s = (Book) query.getSingleResult();
            for(Author_Book ab: s.getAuthor_books()) {
                em.remove(ab);
            }
            s.setAuthor_books(new ArrayList<>());
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void orphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select b from Book b join b.author_books ab where b.id = ?1");
        query.setParameter(1, 14);
        Book s = (Book) query.getSingleResult();
        Iterator<Author_Book> itr = s.getAuthor_books().iterator();
        while(itr.hasNext()) {
            Author_Book ab = itr.next();
            if(ab.getAuthor().getId().equals(9)) {
                itr.remove();
            }
        }
        tx.commit();
    }


    private static void withoutOrphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select b from Book b join fetch s.teacher_books ab where s.id = ?1");
        query.setParameter(1, "14");
        Book s = (Book) query.getSingleResult();
        Iterator<Author_Book> itr = s.getAuthor_books().iterator();
        while(itr.hasNext()) {
            Author_Book ab = itr.next();
            if(ab.getAuthor().getId().equals(9)) {
                itr.remove();
                em.remove(ab);
            }
        }
        tx.commit();
    }
}
