package com.company.bankapp;

import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.bankapp.exceptions.AccountNotFoundException;
import com.company.bankapp.exceptions.MinimumBalanceException;
import com.company.bankapp.exceptions.YearFormatException;
import com.company.bankapp.helpers.Helper;
import com.company.bankapp.models.Account;
import com.company.bankapp.repositories.AccountDao;
import com.company.bankapp.services.AccountDaoImpl;

/**
 *
 * @author SUPRATIM
 *
 */
public class App {

	private static final AccountDao DAO = new AccountDaoImpl();

	private static void error(Throwable e) {
		System.out.println("----- " + e + " [" + new Date() + "] -----");
	}

	private static void createAccount() {
		try {

			Account account = new Account();

			account.setHolderName(JOptionPane.showInputDialog("Create New Account", "Type your name").toUpperCase());
			account.setHolderEmail(JOptionPane.showInputDialog("Create New Account", "Type your email").toLowerCase());
			account.setAccountBalance(
					Long.parseLong(JOptionPane.showInputDialog("Create New Account", "Enter amount to open account")));

			Account newAccount = DAO.createAccount(account);
			String msg = "Account created successfully" + "\nBank Passbook" + "\nAccount No: "
					+ newAccount.getAccountNo() + "\nAccount Holder: " + newAccount.getHolderName()
					+ "\nAccount Holder's Email: " + newAccount.getHolderEmail();
			JOptionPane.showMessageDialog(null, msg);

		} catch (MinimumBalanceException e) {
			error(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (HibernateException e) {
			error(e);
			String msg = "Internal Server Error (500)\nCause: ";
			JOptionPane.showMessageDialog(null, msg + e.getMessage());
		} catch (Exception e) {
			error(e);
		}
	}

	private static void readAccount() {
		try {

			String input = JOptionPane.showInputDialog("View Account", "Enter your account number to view");
			Integer accountNo = Integer.parseInt(input);
			Account account = DAO.readAccount(accountNo);
			String msg = "Account Details of " + account.getAccountNo() + "\nAccount Holder: " + account.getHolderName()
					+ "\nAccount Holder's Email: " + account.getHolderEmail() + "\nAccount Balance: "
					+ account.getAccountBalance();
			JOptionPane.showMessageDialog(null, msg);

		} catch (AccountNotFoundException e) {
			error(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NumberFormatException e) {
			error(e);
			JOptionPane.showMessageDialog(null, "Only number will be accepted");
		} catch (HibernateException e) {
			error(e);
			String msg = "Internal Server Error (500)\nCause: ";
			JOptionPane.showMessageDialog(null, msg + e.getMessage());
		} catch (Exception e) {
			error(e);
		}
	}

	private static void updateAccount() {
		try {

			String input = JOptionPane.showInputDialog("Update Account", "Enter your account number to update");
			Integer accountNo = Integer.parseInt(input);

			Account account = new Account();

			account.setHolderName(
					JOptionPane.showInputDialog("Update Account", "Type your updated name").toUpperCase());
			account.setHolderEmail(
					JOptionPane.showInputDialog("Update Account", "Type your updated email").toLowerCase());

			Account updatedAccount = DAO.updateAccount(accountNo, account);
			String msg = "Account updated successfully" + "\nUpdated Bank Passbook" + "\nAccount No: "
					+ updatedAccount.getAccountNo() + "\nAccount Holder: " + updatedAccount.getHolderName()
					+ "\nAccount Holder's Email: " + updatedAccount.getHolderEmail() + "\nAccount Balance: "
					+ updatedAccount.getAccountBalance();
			JOptionPane.showMessageDialog(null, msg);

		} catch (AccountNotFoundException e) {
			error(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NumberFormatException e) {
			error(e);
			JOptionPane.showMessageDialog(null, "Only number will be accepted");
		} catch (HibernateException e) {
			error(e);
			String msg = "Internal Server Error (500)\nCause: ";
			JOptionPane.showMessageDialog(null, msg + e.getMessage());
		} catch (Exception e) {
			error(e);
		}
	}

	private static void deleteAccount() {
		try {

			String input = JOptionPane.showInputDialog("Delete Account", "Enter your account number to delete");
			Integer accountNo = Integer.parseInt(input);
			int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to delete your account?",
					"Select what you want to delete or not?", JOptionPane.YES_NO_OPTION);
			if (confirmation == 0) {
				String msg = DAO.deleteAccount(accountNo);
				JOptionPane.showMessageDialog(null, msg);
			} else {
				JOptionPane.showMessageDialog(null, "You retain your account");
			}

		} catch (AccountNotFoundException e) {
			error(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NumberFormatException e) {
			error(e);
			JOptionPane.showMessageDialog(null, "Only number will be accepted");
		} catch (HibernateException e) {
			error(e);
			String msg = "Internal Server Error (500)\nCause: ";
			JOptionPane.showMessageDialog(null, msg + e.getMessage());
		} catch (Exception e) {
			error(e);
		}
	}

	private static void fixedDeposite() {
		try {

			String input = JOptionPane.showInputDialog("Fixed Deposite", "Enter your account number for FD");
			Integer accountNo = Integer.parseInt(input);
			input = JOptionPane.showInputDialog("Fixed Deposite: Give initial amount", "Type here");
			Long initialAmount = Long.parseLong(input);
			input = JOptionPane.showInputDialog("Fixed Deposite: Give duration in year", "Type here");
			Integer year = Integer.parseInt(input);

			String msg = DAO.fixedDeposite(accountNo, initialAmount, year);
			JOptionPane.showMessageDialog(null, msg);

		} catch (AccountNotFoundException | MinimumBalanceException | YearFormatException e) {
			error(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NumberFormatException e) {
			error(e);
			JOptionPane.showMessageDialog(null, "Only number will be accepted");
		} catch (HibernateException e) {
			error(e);
			String msg = "Internal Server Error (500)\nCause: ";
			JOptionPane.showMessageDialog(null, msg + e.getMessage());
		} catch (Exception e) {
			error(e);
		}
	}

	private static void init() {
		try (Session session = Helper.getSession()) {
			String msg = "----- Backend Development Server Running Successfully -----";
			Transaction transaction = session.beginTransaction();
			session.save(new Account(null, "DEV", 1000L, "dev@gmail.com"));
			session.save(new Account(null, "SHYAM", 1800L, "sam@gmail.com"));
			session.save(new Account(null, "ADI", 2000L, "adi@gmail.com"));
			session.save(new Account(null, "ADIL", 1200L, "adil@gmail.com"));
			transaction.commit();
			session.clear();
			System.out.println(msg);
		} catch (HibernateException e) {
			error(e);
			String msg = "Internal Server Error (500)\nCause: ";
			JOptionPane.showMessageDialog(null, msg + e.getMessage());
		} catch (Exception e) {
			error(e);
		}
	}

	private static void businessLogic() {
		init();
		while (true) {
			String options = "Create: C\n" + "Read: R\n" + "Update: U\n" + "Delete: D\n" + "Fixed Deposite: FD\n"
					+ "Exit: E or Q\n";
			String choice = JOptionPane.showInputDialog(options, "Type your choice");
			switch (choice.toUpperCase()) {
			case "CREATE", "C":
				createAccount();
				break;

			case "READ", "R":
				readAccount();
				break;

			case "UPDATE", "U":
				updateAccount();
				break;

			case "DELETE", "D":
				deleteAccount();
				break;

			case "FD":
				fixedDeposite();
				break;

			case "QUIT", "EXIT", "Q", "E":
				System.exit(0);

			default:
				JOptionPane.showMessageDialog(null, "Please enter the right choice");
			}
		}
	}

	public static void main(String[] args) {
		businessLogic();
	}

}
