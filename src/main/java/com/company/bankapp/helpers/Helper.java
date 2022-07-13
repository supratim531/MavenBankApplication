package com.company.bankapp.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Helper {

	private static SessionFactory factory;

	private static synchronized SessionFactory getSessionFactory() {
		if (factory == null)
			factory = new Configuration().configure().buildSessionFactory();
		return factory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

}
