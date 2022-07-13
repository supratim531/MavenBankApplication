package com.company.bankapp.transactions;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.helpers.Helper;
import com.company.bankapp.models.Account;

public class UpdateAccount {

	public static Account updateAccount(Integer accountNo, Account account)
			throws HibernateException, AccountNotFoundException {
		try (Session session = Helper.getSession()) {
			Transaction transaction = session.beginTransaction();
			Account _account = session.find(Account.class, accountNo);

			if (_account == null) {
				String msg = "No account exists of account number " + accountNo;
				throw new AccountNotFoundException(msg);
			}

			Account oldAccount = new Account();
			oldAccount.setAccountNo(accountNo);
			oldAccount.setHolderName(_account.getHolderName());
			oldAccount.setHolderEmail(_account.getHolderEmail());
			oldAccount.setAccountBalance(_account.getAccountBalance());

			_account.setAccountNo(accountNo);
			_account.setHolderName(account.getHolderName());
			_account.setHolderEmail(account.getHolderEmail());

			session.saveOrUpdate(_account);
			transaction.commit();
			System.out.println("----- Account number " + accountNo + " updated at " + new Date() + " -----");
			System.out.println("----- Your old account: " + oldAccount.toString() + " -----");
			System.out.println("----- Your updated account: " + _account.toString() + " -----");
			session.clear();
			return _account;
		}
	}

}
