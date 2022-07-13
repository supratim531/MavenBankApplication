package com.company.bankapp.services;

import org.hibernate.HibernateException;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.exceptions.MinimumBalanceException;
import com.company.bankapp.exceptions.YearFormatException;
import com.company.bankapp.models.Account;
import com.company.bankapp.repositories.AccountDao;
import com.company.bankapp.transactions.CreateAccount;
import com.company.bankapp.transactions.DeleteAccount;
import com.company.bankapp.transactions.FixedDeposite;
import com.company.bankapp.transactions.ReadAccount;
import com.company.bankapp.transactions.UpdateAccount;

public class AccountDaoImpl implements AccountDao {

	@Override
	public Account createAccount(Account account) throws HibernateException, MinimumBalanceException {
		return CreateAccount.createAccount(account);
	}

	@Override
	public Account readAccount(Integer accountNo) throws HibernateException, AccountNotFoundException {
		return ReadAccount.readAccount(accountNo);
	}

	@Override
	public Account updateAccount(Integer accountNo, Account account)
			throws HibernateException, AccountNotFoundException {
		return UpdateAccount.updateAccount(accountNo, account);
	}

	@Override
	public String deleteAccount(Integer accountNo) throws HibernateException, AccountNotFoundException {
		return DeleteAccount.deleteAccount(accountNo);
	}

	@Override
	public String fixedDeposite(Integer accountNo, Long initialAmount, Integer year)
			throws AccountNotFoundException, MinimumBalanceException, YearFormatException, HibernateException {
		return FixedDeposite.fixedDeposite(accountNo, initialAmount, year);
	}

}
