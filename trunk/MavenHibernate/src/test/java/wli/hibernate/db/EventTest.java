package wli.hibernate.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import wli.hibernate.util.HibernateUtil;

public class EventTest {

	@Test
	public void testEventDateString() {
		Event evt = new Event();
		evt.setId(0);
		evt.setDate(new java.util.Date(System.currentTimeMillis()));
		evt.setTitle("Test");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			System.out.println("BBBBBBBBBBBBBBBBBBBBB");
			transaction = session.beginTransaction();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EVENT ["
					+ session.save(evt) + "] Saved.");
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("CCCCCCCCCCCCCCCCCCC");
	}

}
