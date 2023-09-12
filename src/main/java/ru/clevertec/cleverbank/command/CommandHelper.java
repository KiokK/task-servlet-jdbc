package ru.clevertec.cleverbank.command;

import ru.clevertec.cleverbank.command.impl.ExpensesCommand;
import ru.clevertec.cleverbank.command.impl.ExtractCommand;
import ru.clevertec.cleverbank.command.impl.LoginCommand;
import ru.clevertec.cleverbank.command.impl.TransactionCommand;
import ru.clevertec.cleverbank.command.impl.TransferMoneyCommand;
import ru.clevertec.cleverbank.command.impl.UnknownCommand;
import ru.clevertec.cleverbank.command.impl.admin.accounts.CreateAccountCommand;
import ru.clevertec.cleverbank.command.impl.admin.accounts.DeleteByIdAccountCommand;
import ru.clevertec.cleverbank.command.impl.admin.accounts.FindAllAccountCommand;
import ru.clevertec.cleverbank.command.impl.admin.accounts.FindByIdAccountCommand;
import ru.clevertec.cleverbank.command.impl.admin.accounts.UpdateAccountCommand;
import ru.clevertec.cleverbank.command.impl.admin.banks.CreateBankCommand;
import ru.clevertec.cleverbank.command.impl.admin.banks.DeleteByIdBankCommand;
import ru.clevertec.cleverbank.command.impl.admin.banks.FindAllBanksCommand;
import ru.clevertec.cleverbank.command.impl.admin.banks.FindByIdBankCommand;
import ru.clevertec.cleverbank.command.impl.admin.banks.UpdateBankCommand;
import ru.clevertec.cleverbank.command.impl.admin.transactions.CreateTransactionCommand;
import ru.clevertec.cleverbank.command.impl.admin.transactions.DeleteByIdTransactionCommand;
import ru.clevertec.cleverbank.command.impl.admin.transactions.FindAllTransactionsCommand;
import ru.clevertec.cleverbank.command.impl.admin.transactions.FindByIdTransactionCommand;
import ru.clevertec.cleverbank.command.impl.admin.transactions.UpdateTransactionCommand;
import ru.clevertec.cleverbank.command.impl.admin.user.CreateUserCommand;
import ru.clevertec.cleverbank.command.impl.admin.user.DeleteByIdUserCommand;
import ru.clevertec.cleverbank.command.impl.admin.user.FindAllUserCommand;
import ru.clevertec.cleverbank.command.impl.admin.user.FindByIdUserCommand;
import ru.clevertec.cleverbank.command.impl.admin.user.UpdateUserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandHelper() {
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.ACCOUNT_REPLENISHMENT, new TransactionCommand());
        commands.put(CommandName.ACCOUNT_DEBITING, new TransactionCommand());
        commands.put(CommandName.TRANSFER_TO_ANOTHER_ACCOUNT, new TransferMoneyCommand());
        commands.put(CommandName.EXTRACT, new ExtractCommand());
        commands.put(CommandName.EXPENSES, new ExpensesCommand());

        commands.put(CommandName.USERS_CREATE, new CreateUserCommand());
        commands.put(CommandName.USERS_UPDATE, new UpdateUserCommand());
        commands.put(CommandName.USERS_FIND_BY_ID, new FindByIdUserCommand());
        commands.put(CommandName.USERS_FIND_ALL, new FindAllUserCommand());
        commands.put(CommandName.USERS_DELETE_BY_ID, new DeleteByIdUserCommand());

        commands.put(CommandName.ACCOUNTS_CREATE, new CreateAccountCommand());
        commands.put(CommandName.ACCOUNTS_UPDATE, new UpdateAccountCommand());
        commands.put(CommandName.ACCOUNTS_FIND_BY_ID, new FindByIdAccountCommand());
        commands.put(CommandName.ACCOUNTS_FIND_ALL, new FindAllAccountCommand());
        commands.put(CommandName.ACCOUNTS_DELETE_BY_ID, new DeleteByIdAccountCommand());

        commands.put(CommandName.BANKS_CREATE, new CreateBankCommand());
        commands.put(CommandName.BANKS_UPDATE, new UpdateBankCommand());
        commands.put(CommandName.BANKS_FIND_BY_ID, new FindByIdBankCommand());
        commands.put(CommandName.BANKS_FIND_ALL, new FindAllBanksCommand());
        commands.put(CommandName.BANKS_DELETE_BY_ID, new DeleteByIdBankCommand());

        commands.put(CommandName.TRANSACTIONS_CREATE, new CreateTransactionCommand());
        commands.put(CommandName.TRANSACTIONS_UPDATE, new UpdateTransactionCommand());
        commands.put(CommandName.TRANSACTIONS_FIND_BY_ID, new FindByIdTransactionCommand());
        commands.put(CommandName.TRANSACTIONS_FIND_ALL, new FindAllTransactionsCommand());
        commands.put(CommandName.TRANSACTIONS_DELETE_BY_ID, new DeleteByIdTransactionCommand());
    }


    public Command getCommand(String commandName) {

        commandName = commandName.replace('-', '_').toUpperCase();
        CommandName key = CommandName.valueOf(commandName);

        Command command = commands.get(key);

        if (command == null) {
            command = new UnknownCommand();
        }

        return command;
    }
}
