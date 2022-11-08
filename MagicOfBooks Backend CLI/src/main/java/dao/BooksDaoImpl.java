package dao;

import entities.Books;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import entities.Logger;

import java.util.List;
// All the operations on books are performed here
public class BooksDaoImpl implements BooksDao {
    //saving logs locally
    static LogData log = new LogData();
    //saving logs to db
    Logger logger = new Logger();

    // initializing session factory
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public void addBook(Books book) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            logger.setTime(log.logDate);
            logger.setLogMessage(book.getBookName() + " Added successfully");
            session.save(book); //Adding book to DB
            session.save(logger); // storing logs to DB
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close(); // closing the session
        }

    }

    @Override
    public void deleteBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Books book = session.get(Books.class, bookId); // Fetching book from DB
            if (book == null) {
                System.out.println("Book not available");
            } else {
                session.delete(book); // deleting from DB
                logger.setTime(log.logDate);
                logger.setLogMessage(book.getBookName() + " Deleted successfully");
                session.save(logger); //saving logs
                tx.commit();
                System.out.println("Book deleted successfully");
                log.storeData(book.getBookName() + " book deleted successfully."); // saving logs locally
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void updateBook(int bookId, int price, int sales) {

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Books books = session.get(Books.class, bookId);
            if (books == null) {
                System.out.println("Book not available");
            } else {
                books.setPrice(price);
                books.setSales(sales);
                session.update(books); // updating book
                logger.setTime(log.logDate);
                logger.setLogMessage(books.getBookName() + " Updated successfully");
                session.save(logger); //saving log
                tx.commit();
                log.storeData(books.getBookName() + " is updated");
                System.out.println("Book updated successfully");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public List<Books> displayBooks() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Books> books = session.createQuery("select b from entities.Books b").list();
        tx.commit();
        session.close();
        return books;
    }

    @Override
    public int countBooks() {
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            Query<Books> query = session.createQuery("select b from entities.Books b");
            return (int) query.list().stream().count();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Books> displayByGenre(String tempGenre) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            List<Books> books = session.createQuery("from Books b where b.genre = :tempGenre").setParameter("tempGenre", tempGenre).list();
            tx.commit();
            return books;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Books> arrangeLowToHigh() {
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            Query<Books> query = session.createQuery("select b from entities.Books b order by b.price asc");
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Books> arrangeHighToLow() {
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            Query<Books> query = session.createQuery("select b from entities.Books b order by b.price desc");
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Books> arrangeBestSelling() {
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();

            Query<Books> query = session.createQuery("select b from entities.Books b order by b.sales desc");

            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Books displayById(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            Books books = session.get(Books.class, bookId);

            return books;
        } finally {
            session.close();
        }
    }
}