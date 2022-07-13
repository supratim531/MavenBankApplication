package com.company.bankapp.transactions;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.exceptions.MinimumBalanceException;
import com.company.bankapp.exceptions.YearFormatException;
import com.company.bankapp.helpers.Helper;
import com.company.bankapp.models.Account;

public class FixedDeposite {

	private static final Double INTEREST_RATE = 4.7;

	public static String fixedDeposite(Integer accountNo, Long initialAmount, Integer year)
			throws AccountNotFoundException, MinimumBalanceException, YearFormatException, HibernateException {
		try (Session session = Helper.getSession()) {
			Account account = session.find(Account.class, accountNo);

			if (account == null) {
				String msg = "No account exists of account number " + accountNo
						+ "\nYou have to create an account first for FD (Fixed Deposite)";
				throw new AccountNotFoundException(msg);
			}

			if (initialAmount < 5000) {
				String msg = "Minimum amount to fixed deposite is 5000 rupees";
				throw new MinimumBalanceException(msg);
			}

			if (year != 5 && year != 10) {
				String msg = "You can fixed deposite only for 5 or 10 years";
				throw new YearFormatException(msg);
			}

			Double finalAmount = initialAmount + (initialAmount * INTEREST_RATE * year) / 100;
			String msg = account.getHolderName() + " (Account No: " + account.getAccountNo() + ") You will get "
					+ finalAmount + " after " + year + " years with interest rate " + INTEREST_RATE;
			return msg;
		}
	}

}
