package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionService transactionService;
    private Scanner scanner;
    private final boolean devMode;
    public Menu(boolean devMode){
        transactionService = new TransactionService();
        scanner = new Scanner(System.in);
        this.devMode = devMode;
    }
    public void printMainMessage(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if(devMode){
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
        }
        System.out.println("7. Finish execution");

    }

    public void run() {
        while (true) {
            printMainMessage();
            int choice = choice();
            if (choice == 1) {
                addUser();
            } else if (choice == 2) {
                getUserBalances();
            } else if (choice == 3) {
                performTransaction();
            } else if (choice == 4) {
                viewTransactionsOfUser();
            }  else if (choice == 5) {
                removeUserTransactionByTransId();
            } else if (choice == 6) {
                checkTransValidity();
            } else {
                System.exit(0);
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    private int choice(){
        int choice;
        try {
            String inputString = scanner.nextLine().trim();
            choice = Integer.parseInt(inputString);
            if(!isValidChoice(choice)){
                throw new InvalidDataException();
            }
        } catch (RuntimeException exception){
            System.out.println("Invalid choice. Please enter correct number");
            choice = choice();
        }
        return choice;
    }

    private boolean isValidChoice(int choice){
        if(choice > 0 && choice < 8 && devMode){
            return true;
        }
        if(((choice > 0 && choice <= 4) || choice == 7) && !devMode){
            return true;
        }
        return false;
    }

    private void addUser(){
        System.out.println("Enter a user name and a balance");
        String inputString = scanner.nextLine().trim();

        try{
            String[] nameAndBalance = inputString.split("\\s+");
            if(nameAndBalance.length != 2){
                throw new InvalidDataException("Invalid input data");
            }
            String userName = nameAndBalance[0];
            int balance = parseBalance(nameAndBalance[1]);
            User user = new User(userName, balance);
            transactionService.addUser(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
        }  catch (NumberFormatException exception){
            System.out.println("Incorrect number in " + inputString);
            addUser();
        }catch (RuntimeException exception){
            System.out.println(exception.getMessage());
            addUser();
        }
    }

    private int parseBalance(String balance){
        int res = Integer.parseInt(balance);
        if(res < 0){
            throw new InvalidDataException("Balance must be positive");
        }
        return res;
    }

    private void getUserBalances(){
        System.out.println("Enter a user ID");
        String inputString = scanner.nextLine().trim();
        try{
            int userId = Integer.parseInt(inputString);
            User user = transactionService.getUsersArrayList().searchUserById(userId);
            System.out.println(user.getName() + " - " + user.getBalance());
        } catch (NumberFormatException exception){
            System.out.println("Incorrect id" + inputString);
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void performTransaction(){
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String inputString = scanner.nextLine().trim();
        try {
            String[] transInfo = inputString.split("\\s+");
            if(transInfo.length != 3){
                throw new InvalidDataException("Invalid data");
            }
            int senderId = Integer.parseInt(transInfo[0]);
            int receiverId = Integer.parseInt(transInfo[1]);
            int amount = Integer.parseInt(transInfo[2]);
            transactionService.performTransaction(senderId, receiverId, amount);
            System.out.println("The transfer is completed");
        } catch (NumberFormatException exception){
            System.out.println("Invalid data");
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void viewTransactionsOfUser(){
        System.out.println("Enter a user ID");
        String inputString = scanner.nextLine().trim();
        try {
            int userId = Integer.parseInt(inputString);
            Transaction[] userTransactions = transactionService.getTransListOfUser(userId);
            if(userTransactions.length == 0){
                return;
            }
            for(Transaction trans : userTransactions){
                trans.printTransactionInfo();
            }
        } catch (NumberFormatException exception){
            System.out.println("Invalid data in " + inputString);
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void removeUserTransactionByTransId(){
        System.out.println("Enter a user ID and a transfer ID");
        String inputString = scanner.nextLine().trim();
        try {
            String[] userIdAndTransId = inputString.split("\\s+");
            if(userIdAndTransId.length != 2){
                throw new InvalidDataException("Invalid data");
            }
            int userId = Integer.parseInt(userIdAndTransId[0]);
            UUID tranId = UUID.fromString(userIdAndTransId[1]);
            Transaction[] userTransactions = transactionService.getTransListOfUser(userId);
            Transaction deletedTrans = null;
            for (Transaction transaction : userTransactions){
                if(transaction.getId().equals(tranId)){
                    deletedTrans = transaction;
                }
            }
            if(deletedTrans == null){
                throw new InvalidDataException("Transaction with UUID - " + tranId + "not found of user with id - " + userId);
            }
            transactionService.removeTransactionOfUser(userId, tranId);
            System.out.println("Transfer to " + deletedTrans.getRecipient().getName() + "(id = " + deletedTrans.getRecipient().getIdentifier() + ") " + deletedTrans.getTransferAmount() + " removed");
        } catch (NumberFormatException exception){
            System.out.println("Invalid number in " + inputString);
        } catch (IllegalArgumentException exception){
            System.out.println("Incorrect UUID");
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }
    }

    private void checkTransValidity(){
        System.out.println("Check results");

        try {
            Transaction[] invalidTransactions = transactionService.getInvalidTransactions();
            for(Transaction transaction : invalidTransactions){
                User sender = transaction.getSender();
                User recipient = transaction.getRecipient();

                if(transaction.getTransferAmount() > 0){
                    System.out.println(recipient.getName() + "(id = " + recipient.getIdentifier() + ")" +
                            " has an unacknowledged transfer id = " + transaction.getId() + " from" +
                            sender.getName() + "(id =" + sender.getIdentifier() + ") for " + transaction.getTransferAmount());
                } else {
                    System.out.println(sender.getName() + "(id = " + sender.getIdentifier() + ")" +
                            " has an unacknowledged transfer id = " + transaction.getId() + " from" +
                            recipient.getName() + "(id =" + recipient.getIdentifier() + ") for " + transaction.getTransferAmount());
                }
            }
        } catch (RuntimeException exception){
            System.out.println(exception.getMessage());
            run();
        }

    }
}






















