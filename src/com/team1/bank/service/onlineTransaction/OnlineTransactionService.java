package com.team1.bank.service.onlineTransaction;

import java.time.LocalDateTime;
import com.team1.bank.concrete.BankStatement;
import com.team1.bank.concrete.Customer;
import com.team1.bank.custom_exceptions.CustomerNotFoundException;
import com.team1.bank.custom_exceptions.InvalidAmountException;
import com.team1.bank.custom_exceptions.ReachedWithdrawAttemptsException;
import com.team1.bank.repository.config.BankConfigData;
import com.team1.bank.repository.customer.CustomerRepository;

/**
 * OnlineTransactionService
 */
public class OnlineTransactionService {
    private CustomerRepository customerRepository = new CustomerRepository();

    /*
     * to make the online transaction with the sender , receiver and amount
     * information.
     */

    public String onlineTransaction(Customer sender, String receiverUsername, double amount) {
        Customer receiver;
        receiver = customerRepository.getCustomer(receiverUsername);
        if (receiver == null) {
            throw new CustomerNotFoundException("Customer Not Found");
        }
        String result = sendTheAmount(sender, amount, receiverUsername);
        if (result.equals("sent-successfully")) {
            receiveTheAmount(receiver, amount, sender.getUsername());
            return "transaction-made-successfully.!!!";
        } else if (result.equals("low-balance")) {
            throw new InvalidAmountException("Low Balance in your account.");
        } else if (result.equals("maximum-transaction-reached")) {
            throw new ReachedWithdrawAttemptsException("you reached the maximum online transaction limit.");
        } else {
            throw new InvalidAmountException("Entered amount is beyond the maximum transaction limit.");
        }
    }

    /*
     * it is the private method to check the sender able to send the amount to the
     * receiver.
     */
    private String senderBalanceCheck(Customer customer, double amount) {
        if (amount + 500 < customer.getBalance()) {
            if (amount + 500 < minimumTransactionPerDayByBank(customer)) {
                if (checkSenderMeetsTheTransactionLimit(customer, amount)) {
                    return "ok";
                } else {
                    return "maximum-transaction-achieved";
                }
            } else {
                return "entered-amount-is-beyond";
            }
        } else {
            return "low-balance";
        }
    }

    /*
     * it is also the private method to perform the operation of sending the amount
     * to the receiver.
     */
    private String sendTheAmount(Customer customer, double amount, String receiverUsername) {
        String result = senderBalanceCheck(customer, amount);
        if (result.equals("ok")) {
            double temp = customer.getBalance();
            customer.setBalance(temp - amount);
            customer.setStatements(new BankStatement(customer.getUsername(),
                    "online-transact-sent-to-' ".concat(receiverUsername).concat(" '"), amount, customer.getBalance(),
                    LocalDateTime.now(), customer.getBankName()));
            return "sent-successfully";
        } else {
            return result;
        }
    }

    /*
     * it is also the private method to write the receiver information on his
     * account.
     */
    private void receiveTheAmount(Customer customer, double amount, String senderUsername) {
        customer.setBalance(customer.getBalance() + amount);
        customer.setStatements(new BankStatement(customer.getUsername(),
                "online-transact-receive-from-' ".concat(senderUsername).concat(" '"), amount, customer.getBalance(),
                LocalDateTime.now(), customer.getBankName()));
    }

    /*
     * it is also the private method to decide the user bank
     */
    private int minimumTransactionPerDayByBank(Customer customer) {
        switch (customer.getBankName()) {
            case "iob":
                return BankConfigData.IOB_ONLINE_TRANSACTION_MAXIMUM_LIMIT;

            case "ib":
                return BankConfigData.IB_ONLINE_TRANSACTION_MAXIMUM_LIMIT;
        }
        return 0;
    }

    /*
     * to check the sender meet the online transaction maximum limit
     */
    private boolean checkSenderMeetsTheTransactionLimit(Customer customer, double amount) {
        return customer.getStatements().stream()
                .filter(statement -> statement.getType().contains("online-transact-sent-to"))
                .mapToInt(str -> (int) str.getAmount()).sum() < (amount + minimumTransactionPerDayByBank(customer))
                        ? true
                        : false;
    }
}