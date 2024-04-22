package ex00;

public class Program {

    public static void main(String[] args) {
        User firstUser = new User("Danil", 1000);
        User secondUser = new User("Ira", 1000);
        User thirdUser = new User("Ivan", 1000);


        Transaction firstTransaction = new Transaction(firstUser, secondUser, Transaction.TransferCategory.DEBIT, 10);

        firstTransaction.printTransactionInfo();
    }
}
