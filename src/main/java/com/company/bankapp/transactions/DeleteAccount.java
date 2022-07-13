package com.company.bankapp.transactions;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.helpers.Helper;
import com.company.bankapp.models.Account;

public class DeleteAccount {

	public static String deleteAccount(Integer accountNo) throws HibernateException, AccountNotFoundException {
		try (Session session = Helper.getSession()) {
			Transaction transaction = session.beginTransaction();
			Account account = session.find(Account.class, accountNo);

			if (account == null) {
				String msg = "No account exists of account number " + accountNo;
				throw new AccountNotFoundException(msg);
			}

			session.delete(account);
			transaction.commit();
			session.clear();
			String msg = "Account number " + accountNo + " deleted successfully";
			System.out.println("----- " + msg + " at " + new Date() + " -----");
			return msg;
		}
	}

}
