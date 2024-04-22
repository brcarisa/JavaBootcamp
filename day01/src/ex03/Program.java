package ex03;


public class Program {
    public static void main(String[] args) {
        User us1 = new User("Bill1", 3010);
        User us2 = new User("Bill2", 1000);
        User us3 = new User("Bill3", 11000);
        User us4 = new User("Bill4", 1300);
        User us5 = new User("Bill5", 3020);

        UsersArrayList usersArrayList = new UsersArrayList();
        usersArrayList.addUser(us1);
        usersArrayList.addUser(us2);
        usersArrayList.addUser(us3);
        usersArrayList.addUser(us4);
        usersArrayList.addUser(us5);

        Transaction transaction1 = new Transaction(us1, us2, Transaction.TransferCategory.CREDIT, -100);
        Transaction transaction2 = new Transaction(us2, us3, Transaction.TransferCategory.DEBIT, 250);
        Transaction transaction3 = new Transaction(us4, us5, Transaction.TransferCategory.DEBIT, 500);

        TransactionLinkedList transactionLinkedList = new TransactionLinkedList();
        transactionLinkedList.addTransaction(transaction1);
        transactionLinkedList.addTransaction(transaction2);
        transactionLinkedList.addTransaction(transaction3);

        Transaction[] transactions = new Transaction[3];
        transactions = transactionLinkedList.toArray();

        for(Transaction transaction : transactions){
            transaction.printTransactionInfo();
        }

        transactionLinkedList.deleteTransaction(transaction2.getId());
        transactions = transactionLinkedList.toArray();
        System.out.println();

        for(Transaction transaction : transactions){
            transaction.printTransactionInfo();
        }



    }
}

