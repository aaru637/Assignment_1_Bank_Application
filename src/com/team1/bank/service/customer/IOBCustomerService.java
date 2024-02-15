package com.team1.bank.service.customer;

import java.time.LocalDateTime;
import java.util.List;

import com.team1.bank.concrete.BankStatement;
import com.team1.bank.concrete.Customer;
import com.team1.bank.concrete.interfaces.RBI;
import com.team1.bank.custom_exceptions.InvalidAmountException;
import com.team1.bank.custom_exceptions.ReachedDepositAttemptsException;
import com.team1.bank.custom_exceptions.ReachedWithdrawAttemptsException;
import com.team1.bank.repository.config.BankConfigData;
import com.team1.bank.repository.customer.CustomerRepository;
import com.team1.bank.service.admin.AdminService;
import com.team1.bank.service.helpSupport.HelpSupportService;
import com.team1.bank.utilities.Utils;

/**
 * IOBCustomerService
 */
public class IOBCustomerService implements RBI {
    private CustomerRepository customerRepository = new CustomerRepository();
    private AdminService adminService = new AdminService();
    private HelpSupportService hService = new HelpSupportService();
    private Customer customer;
    private double balance;
    private int attempts;

    /*
     * Refresh the class
     */
    public void refresh() {
        customer = new Customer();
        customerRepository = new CustomerRepository();
        balance = 0;
        attempts = 0;
    }

    /*
     * to get the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /*
     * to validate the customer credentials
     */
    public boolean login(String username, String password) {
        customer = customerRepository.getCustomer(username, password);
        if (customer != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * to show the customer account balance.
     */
    @Override
    public void showBalance() {
        Utils.dashDecorators();
        System.out.printf("Balance amount of the Customer : " + customer.getBalance());
        Utils.dashDecorators();
        Utils.singleSpaces();
    }

    /*
     * to deposit the amount to the customer account
     */
    @Override
    public boolean deposit(double amount) {
        if (customer.getDepositAttempts() > 0) {
            balance = customer.getBalance();
            balance += ((amount) - (amount * BankConfigData.IOB_DEPOSIT) / 100);
            customer.setBalance(balance);
            reduceDepositAttempts();
            BankStatement statement = new BankStatement(customer.getUsername(), "deposit", amount,
                    customer.getBalance(), LocalDateTime.now(), customer.getBankName());
            customer.setStatements(statement);
            return true;
        } else {
            throw new ReachedDepositAttemptsException("You used your 3 deposit attempts already ");
        }
    }

    /*
     * to withdraw the amount from the customer account.
     */
    @Override
    public boolean withdraw(double amount) {
        if (customer.getWithdrawAttempts() > 0) {
            if (minimumBalanceChecker(amount)) {
                reduceWithdrawAttempts();
                return true;
            } else {
                reduceWithdrawAttempts();
                throw new InvalidAmountException("You have low balance in your account. please mention other amounts.");
            }
        } else {
            throw new ReachedWithdrawAttemptsException("You used your 3 withdraw attempts already.");
        }
    }

    /*
     * to display the remaining deposit and withdraw attemtps of the customer
     */
    @Override
    public void remainingAttempts() {
        Utils.singleSpaces();
        System.out.println("There are " + customer.getDepositAttempts() + " Deposit attempts remaining.");
        Utils.singleSpaces();
        System.out.println("There are " + customer.getWithdrawAttempts() + " Withdraw attempts remaining.");
        Utils.singleSpaces();
        Utils.endDecorators();
    }

    /*
     * to display the statements of the customer.
     */
    @Override
    public void getCustomerStatements() {
        List<BankStatement> statements = customer.getStatements();
        for (BankStatement statement : statements) {
            System.out.println(statement);
        }
    }

    /*
     * to make the complaints
     */
    @Override
    public void makeComplaints(String complaint) {
        hService.makeComplaint(customer.getUsername(), adminService.getAdmin(customer.getBankName()).getUsername(),
                complaint);
    }

    /*
     * to get the complaints
     */
    @Override
    public void getComplaints() {
        hService.getComplaintsByCustomerUsername(customer.getUsername());
    }

    /*
     * used to check the minimum balance of the user.
     */
    private boolean minimumBalanceChecker(double amount) {
        balance = customer.getBalance() - BankConfigData.IOB_MINIMUM_BALANCE;
        amount = ((amount) - (amount * BankConfigData.IOB_WITHDRAW) / 100);
        if (amount < balance) {
            balance -= amount;
            customer.setBalance(balance);
            return true;
        } else {
            return false;
        }
    }

    /*
     * used to reduce the deposit attempts
     */
    private void reduceDepositAttempts() {
        attempts = customer.getDepositAttempts();
        customer.setDepositAttempts(--attempts);
    }

    /*
     * used to reduce the withdraw attempts
     */
    private void reduceWithdrawAttempts() {
        attempts = customer.getWithdrawAttempts();
        customer.setWithdrawAttempts(--attempts);
    }
}