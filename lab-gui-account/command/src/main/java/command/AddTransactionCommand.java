package command;

import model.Account;
import model.Transaction;

public class AddTransactionCommand implements Command{

    private Transaction transactionToAdd;
    private Account account;

    public AddTransactionCommand(Transaction transactionToAdd, Account account) {
        this.transactionToAdd = transactionToAdd;
        this.account = account;
    }

    @Override
    public String getName() {
        return "New transaction: " + transactionToAdd.toString();
    }

    @Override
    public void execute() {
        account.addTransaction(transactionToAdd);
    }
}
