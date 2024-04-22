package ex04;

public class User {
    private final int identifier;
    private String name;
    private int balance;
    private TransactionsList transactionsList;

    public User(String name, int balance){
        this.identifier = UserIdsGenerator.getInstance().createNewId();
        if(name == null || balance < 0){
            System.err.println("Please add correct arguments");
        } else {
            this.transactionsList = new TransactionLinkedList();
            this.name = name;
            this.balance = balance;
        }
    }

    public void printUserInfo(){
        System.out.println("ID: " + identifier + " Name: " + name + " Balance: " + balance);
    }

    public int getBalance() {
        return balance;
    }

    protected void increaseCurrentBalance(int transactionAmount){
        this.balance = this.balance + transactionAmount;
    }

    public String getName() {
        return name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public TransactionsList getTransactionList(){
        return transactionsList;
    }

    public void printTransactionsList (){
        Transaction[] buff = getTransactionList().toArray();
        for(int i = 0; i < getTransactionList().getSize(); i++){
            buff[i].printTransactionInfo();
        }
    }
}