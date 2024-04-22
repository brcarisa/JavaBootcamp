package ex05;

import java.util.UUID;

public class TransactionLinkedList implements TransactionsList {
    private Transaction start;
    private Transaction end;
    private int size = 0;


    @Override
    public void addTransaction(Transaction newTransaction) {
        if(start == null){
            start = newTransaction;
        } else {
            end.setNext(newTransaction);
        }
        end = newTransaction;
        size++;
    }

    @Override
    public void deleteTransaction(UUID id) {
        Transaction buff;
        Transaction prev;
        if(start != null){
            buff = start.getNextTransaction();
            prev = start;
            if(prev.getId() == id){
                start = buff;
                size--;
                return;
            }
            while (buff != null){
                if(buff.getId() == id){
                    prev.setNext(buff.getNextTransaction());
                    size--;
                    return;
                }
                prev = prev.getNextTransaction();
                buff = buff.getNextTransaction();
            }
        }

    }

    @Override
    public Transaction[] toArray(){
        if(start != null){
            Transaction[] arrayTransactions = new Transaction[size];
            Transaction buff = start;
            for(int i = 0; i < size; i++){
                arrayTransactions[i] = buff;
                buff = buff.getNextTransaction();
            }
            return arrayTransactions;
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    @Override
    public int getSize(){
        return size;
    }
}
