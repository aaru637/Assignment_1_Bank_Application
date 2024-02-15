package com.team1.bank.service.onlineTransaction;

import java.time.LocalDateTime;

import com.team1.bank.concrete.BankStatement;
import com.team1.bank.concrete.Customer;
import com.team1.bank.repository.customer.CustomerRepository;

/**
 * OnlineTransactionService
 */
public class OnlineTransactionService {
    private CustomerRepository customerRepository = new CustomerRepository();

    /*
     * to make the online transaction with the sender , receiver and amount information.
     */

    public String onlineTransaction(Customer sender, String receiverUsername, double amount) {
        Customer receiver;
        receiver = customerRepository.getCustomer(receiverUsername);
        if (receiver == null) {
            return "customer-not-found";
        } else {
            if (sendTheAmount(sender, amount, receiverUsername).equals("sent-successfully")) {
                receiveTheAmount(receiver, amount, sender.getUsername());
                return "transaction-made-successfully.!!!";
            } else {
                return "low-balance";
            }
        }
    }

    /*
     * it is the private method to check the sender able to send the amount to the receiver.
     */
    private boolean senderBalanceCheck(Customer customer, double amount) {
        if (amount + 500 < customer.getBalance()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * it is also the private method to perform the operation of sending the amount to the receiver.
     */
    private String sendTheAmount(Customer customer, double amount, String receiverUsername) {
        if (senderBalanceCheck(customer, amount)) {
            double temp = customer.getBalance();
            customer.setBalance(temp - amount);
            customer.setStatements(new BankStatement(customer.getUsername(),
                    "online-transact-sent-to-' ".concat(receiverUsername).concat(" '"), amount, customer.getBalance(),
                    LocalDateTime.now(), customer.getBankName()));
            return "sent-successfully";
        } else {
            return "low-balance";
        }
    }

    /*
     * it is also the private method to write the receiver information on his account.
     */
    private void receiveTheAmount(Customer customer, double amount, String senderUsername) {
        customer.setBalance(customer.getBalance() + amount);
        customer.setStatements(new BankStatement(customer.getUsername(),
                "online-transact-receive-from-' ".concat(senderUsername).concat(" '"), amount, customer.getBalance(),
                LocalDateTime.now(), customer.getBankName()));
    }
}