package CODSOFT;
import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        ATM atm = new ATM();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome to the ATM. Set your PIN (4 digits): ");
        String enteredPin = scanner.nextLine();

        if (atm.setPin(enteredPin)) {
            System.out.println("PIN set successfully.");
        } else {
            System.out.println("Invalid PIN. Exiting the ATM.");
            System.exit(0);
        }

        while (true) {
            System.out.print("Enter your PIN: ");
            enteredPin = scanner.nextLine();

            if (atm.validatePin(enteredPin)) {
                System.out.println("Welcome to the ATM");
                while (true) {
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            atm.checkBalance();
                            break;
                        case 2:
                            System.out.print("Enter the deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            atm.deposit(depositAmount);
                            break;
                        case 3:
                            System.out.print("Enter the withdrawal amount: ");
                            double withdrawalAmount = scanner.nextDouble();
                            atm.withdraw(withdrawalAmount);
                            break;
                        case 4:
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                }
            } else {
                System.out.println("Invalid PIN. Please try again.");
            }
        }
    }
}

class ATM {
    private double balance;
    private String pin;

    public ATM() {
        this.balance = 1000.0; // Initial balance
        this.pin = null;
    }

    public boolean setPin(String enteredPin) {
        if (enteredPin.length() == 4 && enteredPin.matches("\\d+")) {
            this.pin = enteredPin;
            return true;
        }
        return false;
    }

    public boolean validatePin(String enteredPin) {
        return enteredPin.equals(pin);
    }

    public void checkBalance() {
        System.out.println("Your balance is: " + balance + " RS");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("RS" + amount + " deposited successfully.");
            System.out.println("Your new balance is: " + balance + " RS");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("RS" + amount + " withdrawn successfully.");
            System.out.println("Your new balance is: " + balance + " RS");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}
