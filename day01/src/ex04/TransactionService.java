package ex04;

import java.util.UUID;

public class TransactionService {
    private UsersArrayList usersArrayList = new UsersArrayList();

    public UsersArrayList getUsersArrayList(){
        return usersArrayList;
    }

    public void addUser(User user) {
        usersArrayList.addUser(user);
    }

    public int getUserBalance(int id){
        return usersArrayList.searchUserById(id).getBalance();
    }

    public void performTransaction(int senderId, int receiverId, int amount) {
        if(amount > getUserBalance(senderId) || senderId == receiverId || amount < 0){
            throw new IllegalTransactionException("Amount exceeds the maximum allowed");
        }
        User sender = usersArrayList.searchUserById(senderId);
        User receiver = usersArrayList.searchUserById(receiverId);

        Transaction debit = new Transaction(sender, receiver, Transaction.TransferCategory.DEBIT, amount);
        Transaction credit = new Transaction(receiver, sender, Transaction.TransferCategory.CREDIT, -amount);

        credit.setId(debit.getId());

        sender.getTransactionList().addTransaction(debit);
        receiver.getTransactionList().addTransaction(credit);

        sender.increaseCurrentBalance(-amount);
        receiver.increaseCurrentBalance(amount);
    }

    public Transaction[] getTransListOfUser(int id){
        return usersArrayList.searchUserById(id).getTransactionList().toArray();
    }

    public void removeTransactionOfUser(int idUser, UUID idTransaction){
        usersArrayList.searchUserById(idUser).getTransactionList().deleteTransaction(idTransaction);
    }

    public Transaction[] getInvalidTransactions(){
        TransactionLinkedList allTransactions = new TransactionLinkedList();
        for(int i = 0; i < usersArrayList.getAmountUsers(); i++){
            User currUser = usersArrayList.searchUserByIndex(i);
            if(currUser != null){
                int size = currUser.getTransactionList().getSize();
                for(int j = 0; j < size; j++){
                    allTransactions.addTransaction(currUser.getTransactionList().toArray()[j]);
                }
            }
        }
        TransactionLinkedList invalidTransactions = new TransactionLinkedList();
        for(int i = 0; i < allTransactions.getSize(); i++){
            int count = 0;
            for(int j = 0; j < allTransactions.getSize(); j++){
                if(allTransactions.toArray()[i].getId() == allTransactions.toArray()[j].getId()){
                    count++;
                }
            }
            if(count != 2){
                invalidTransactions.addTransaction(allTransactions.toArray()[i]);
            }
        }
        return invalidTransactions.toArray();
    }
}
