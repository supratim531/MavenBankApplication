package com.company.bankapp.transactions;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.helpers.Helper;
import com.company.bankapp.models.Account;

public class ReadAccount {

	public static Account readAccount(Integer accountNo) throws HibernateException, AccountNotFoundException {
		try (Session session = Helper.getSession()) {
			Account account = session.find(Account.class, accountNo);

			if (account == null) {
				String msg = "No account exists of account number " + accountNo;
				throw new AccountNotFoundException(msg);
			}

			System.out.println("----- Your account: " + account.toString() + " -----");
			session.clear();
			return account;
		}
	}

}
