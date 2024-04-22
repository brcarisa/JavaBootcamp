package ex04;


public class Program {
    public static void main(String[] args) {
        User us1 = new User("Danil", 5000);
        User us2 = new User("Ira", 5050);
        User us3 = new User("Ivan", 5020);

        TransactionService transactionService = new TransactionService();
        transactionService.addUser(us1);
        transactionService.addUser(us2);
        transactionService.addUser(us3);

        transactionService.performTransaction(us1.getIdentifier(), us2.getIdentifier(), 500);
        transactionService.performTransaction(us1.getIdentifier(), us3.getIdentifier(), 600);
        transactionService.performTransaction(us2.getIdentifier(), us1.getIdentifier(), 700);

        us1.printUserInfo();
        us2.printUserInfo();
        us3.printUserInfo();

        System.out.println("us1");
        us1.printTransactionsList();

        System.out.println("us2");
        us2.printTransactionsList();

        System.out.println("us3");
        us3.printTransactionsList();

        Transaction transaction1 = new Transaction(us1, us2, Transaction.TransferCategory.DEBIT, 400);
        Transaction transaction2 = new Transaction(us1, us3, Transaction.TransferCategory.DEBIT, 300);

        us1.getTransactionList().addTransaction(transaction1);
        us1.getTransactionList().addTransaction(transaction2);

        Transaction[] invalidTransactions = transactionService.getInvalidTransactions();

        System.out.println("us1 after adding invalid transactions");
        us1.printTransactionsList();
        System.out.println(" id = 2 - balance:" + transactionService.getUserBalance(2));
        System.out.println(" User = " + us3.getName() +" - balance:" + transactionService.getUserBalance(3));
        System.out.println();
        for(int i = 0; i < invalidTransactions.length; i++){
            invalidTransactions[i].printTransactionInfo();
        }

        for(int i = 0; i < invalidTransactions.length; i++){
            transactionService.removeTransactionOfUser(invalidTransactions[i].getSender().getIdentifier(), invalidTransactions[i].getId());
        }

        System.out.println();
        System.out.println("After remove invalid transactions");
        us1.printTransactionsList();

        try{
            transactionService.performTransaction(us2.getIdentifier(), us2.getIdentifier(), 50);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        Transaction[] transactions = transactionService.getTransListOfUser(us1.getIdentifier());

        for(int i = 0; i < transactions.length; i++){
            transactions[i].printTransactionInfo();
        }
    }
}

