package com.dk.bank.utilities;

/**
 * Utils
 */
public class Utils {

    public static void singleSpaces() {
        System.out.println();
    }

    public static void doubleSpaces() {
        singleSpaces();
        singleSpaces();
    }

    public static void dashDecorators() {
        singleSpaces();
        System.out.println("--------------------------------------------------");
        singleSpaces();
    }

    public static void endDecorators() {
        singleSpaces();
        System.out.println("----x----x----x----x----x----x----x----x----x----x----");
        singleSpaces();
    }

    public static void customerMenus() {
        System.out.println(
                "1.Show Balance.\n2.Deposit.\n3.Withdraw.\n4.Bank Statements.\n5.Help and Support.\n6.List the complaints.\n");
    }

    public static void adminMenus() {
        System.out.println(
                "1.Add Customer.\n2.Delete Customer.\n3.List Customers.\n4.Resolve Complaints\n5.View Complaints.\n");
    }

    public static void initialMenus() {
        System.out.println("1.Admin.\n2.Customer.");
    }
}