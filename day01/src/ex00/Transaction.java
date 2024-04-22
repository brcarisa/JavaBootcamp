package ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User sender;
    private User recipient;
    private TransferCategory category;
    private int transferAmount;

    public Transaction(User sender, User recipient, TransferCategory category, int transferAmount){
        if((category == TransferCategory.CREDIT && transferAmount > 0) || (category == TransferCategory.DEBIT && transferAmount < 0)){
            System.err.println("FATAL ERROR. Your transfer category is invalid");
        } else if((category == TransferCategory.CREDIT && -transferAmount > sender.getBalance()) || (category == TransferCategory.DEBIT && transferAmount > sender.getBalance())){
            System.err.println("FATAL ERROR. Too small balance.");
        } else {
            this.identifier = UUID.randomUUID();
            this.sender = sender;
            this.recipient = recipient;
            this.category = category;
            this.transferAmount = transferAmount;
        }
    }

    public void printTransactionInfo(){
        System.out.println("ID: " + identifier + " | sender name: " + sender.getName() + " | recipient name: " + recipient.getName() + " | category: " + category + " | amount: " + transferAmount);
    }

    public UUID getId(){
        return identifier;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }
    public int getTransferAmount() {
        return transferAmount;
    }

    public TransferCategory getCategory() {
        return category;
    }

    public enum TransferCategory{
        DEBIT,
        CREDIT
    }
}
