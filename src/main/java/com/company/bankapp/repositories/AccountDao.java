package com.company.bankapp.repositories;

import org.hibernate.HibernateException;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.exceptions.MinimumBalanceException;
import com.company.bankapp.exceptions.YearFormatException;
import com.company.bankapp.models.Account;

/**
 *
 * @author SUPRATIM
 *
 */
public interface AccountDao {

	/**
	 *
	 * @param account
	 * @return {@link Account}
	 * @throws HibernateException, MinimumBalanceException
	 * @summary Create a new account
	 *
	 */
	public Account createAccount(Account account) throws HibernateException, MinimumBalanceException;

	/**
	 *
	 * @param accountNo
	 * @return {@link Account}
	 * @throws HibernateException, AccountNotFoundException
	 * @summary Read an existing account
	 *
	 */
	public Account readAccount(Integer accountNo) throws HibernateException, AccountNotFoundException;

	/**
	 *
	 * @param accountNo, account
	 * @return {@link Account}
	 * @throws HibernateException, AccountNotFoundException
	 * @summary Update an existing account
	 *
	 */
	public Account updateAccount(Integer accountNo, Account account)
			throws HibernateException, AccountNotFoundException;

	/**
	 *
	 * @param accountNo
	 * @return {@link String}
	 * @throws HibernateException, AccountNotFoundException
	 * @summary Delete an existing account
	 *
	 */
	public String deleteAccount(Integer accountNo) throws HibernateException, AccountNotFoundException;

	/**
	 *
	 * @param accountNo, initialAmount, year
	 * @return {@link String}
	 * @throws YearFormatException, MinimumBalanceException,
	 *                              AccountNotFoundException, HibernateException
	 *
	 * @summary Fixed deposite for an account
	 */
	public String fixedDeposite(Integer accountNo, Long initialAmount, Integer year)
			throws AccountNotFoundException, MinimumBalanceException, YearFormatException, HibernateException;
}
