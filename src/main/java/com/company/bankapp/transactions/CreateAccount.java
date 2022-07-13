package com.company.bankapp.transactions;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.bankapp.exceptions.MinimumBalanceException;
import com.company.bankapp.helpers.Helper;
import com.company.bankapp.models.Account;

public class CreateAccount {

	public static Account createAccount(Account account) throws HibernateException, MinimumBalanceException {
		try (Session session = Helper.getSession()) {
			Transaction transaction = session.beginTransaction();

			if (account.getAccountBalance() < 1000) {
				String msg = "You cannot open an account with less than 1000 rupees.";
				throw new MinimumBalanceException(msg);
			}

			session.save(account);
			transaction.commit();
			session.clear();
			System.out.println("----- Account created [" + account.toString() + "] at " + new Date() + " -----");
			return account;
		}
	}

}
