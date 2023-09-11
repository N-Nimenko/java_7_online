package module.controller;

import module.dto.*;
import module.entity.*;
import module.facade.*;
import module.facade.impl.*;
import module.service.*;
import module.service.impl.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

public class Controller {
    UserFacade userFacade = new UserFacadeImpl();
    AccountFacade accountFacade = new AccountFacadeImpl();
    ExpenseCategoryFacade expenseCategoryFacade = new ExpenseCategoryFacadeImpl();
    IncomeCategoryFacade incomeCategoryFacade = new IncomeCategoryFacadeImpl();
    UserService userService = new UserServiceImpl();
    AccountService accountService = new AccountServiceImpl();
    ExpenseCategoryService expenseCategoryService = new ExpenseCategoryServiceImpl();
    IncomeCategoryService incomeCategoryService = new IncomeCategoryServiceImpl();
    OperationService operationService = new OperationServiceImpl();
    PiggyBankService piggyBankService = new PiggyBankServiceImpl();
    PiggyBankFacade piggyBankFacade = new PiggyBankFacadeImpl();
    CsvService csvService = new CsvService();

    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to own finances application!");
            System.out.println("Choose your options:");
            String select;
            menu();
            while ((select = bufferedReader.readLine()) != null) {
                crud(bufferedReader, select);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void menu() {
        System.out.println("*********************************************************************************************************************************************************************************************************************************************");
        System.out.println("*                 Operations with User                   |**|                  Account Operations                    |**|                Operations with Expenses                |**|                Operations with Income                 |");
        System.out.println("*********************************************************************************************************************************************************************************************************************************************");
        System.out.println("| No. | Operations                                       |**| No. | Operations                                       |**| No. | Operations                                       |**| No. | Operations                                      |");
        System.out.println("|-----|--------------------------------------------------|**|-----|--------------------------------------------------|**|-----|------------------------------------------------- |**|-----|-------------------------------------------------|");
        System.out.println("|  1  | Create User and its Account                      |**|  8  | Create a New Account                             |**| 13  | Create an Expense category to a User             |**| 21  | Create an income category to a user             |");
        System.out.println("|  2  | Update User                                      |**|  9  | Add an Existing Account to a User                |**| 14  | Update an Expense category to a User             |**| 22  | Update an income category to a user             |");
        System.out.println("|  3  | Delete User with its Account                     |**| 10  | Delete relation between Account and User         |**| 15  | Delete all the Expenses of a User                |**| 23  | Delete all the incomes of a user                |");
        System.out.println("|  4  | Find certain User by id                          |**| 11  | Delete Account of a user                         |**| 16  | Delete a certain Expense of a User               |**| 24  | Delete a certain income of a user               |");
        System.out.println("|  5  | Find User by Account number                      |**| 12  | Show All Accounts of a User                      |**| 17  | Get total Expense of a User                      |**| 25  | Get total incomes of a user                     |");
        System.out.println("|  6  | List all the Users with their Accounts           |**|     |                                                  |**| 18  | List all Expenses of a User                      |**| 26  | List all incomes of a user                      |");
        System.out.println("|  7  | Sort all the Users by criteria                   |**|     |                                                  |**| 19  | Find similar Expenses for all Users              |**| 27  | Find similar incomes for all users              |");
        System.out.println("|     |                                                  |**|     |                                                  |**| 20  | Sort Expenses of a User by criteria              |**| 28  | Sort incomes of a user by criteria              |");
        System.out.println("*********************************************************************************************************************************************************************************************************************************************");
        System.out.println("*******************************************************************************************************************************************************************");
        System.out.println("*                Operations of a User              |**|               Operations with Piggy Bank               |**|              Operations with files            *");
        System.out.println("*******************************************************************************************************************************************************************");
        System.out.println("| No. | Operations                                 |**|| No. | Operations                                      |**|| No. | Operations                             |");
        System.out.println("|-----|--------------------------------------------|**||-----|-------------------------------------------------|**||-----|----------------------------------------|");
        System.out.println("| 29  | Send money from one to another             |**|| 30  | Create Piggy Bank balance                       |**|| 36  | Export Account statement in csv format |");
        System.out.println("|     |                                            |**|| 31  | Send money from account to Piggy Bank           |**||     |                                        |");
        System.out.println("|     |                                            |**|| 32  | Send money from Piggy bank to a certain Account |**||     |                                        |");
        System.out.println("|     |                                            |**|| 33  | Show all the users Piggy bank Balances          |**||     |                                        |");
        System.out.println("|     |                                            |**|| 34  | Add Piggy bank to Account                       |**||     |                                        |");
        System.out.println("|     |                                            |**|| 35  | Delete Piggy Bank                               |**||     |                                        |");
        System.out.println("*******************************************************************************************************************************************************************");
    }

    private void crud(BufferedReader bufferedReader, String select) {
        try {
            switch (select) {
                case "1" -> createUserAndItsAccount(bufferedReader);
                case "2" -> updateUser(bufferedReader);
                case "3" -> deleteUserWithItsAccount(bufferedReader);
                case "4" -> findUserById(bufferedReader);
                case "5" -> findUserByAccount(bufferedReader);
                case "6" -> listAllUsersWithAccounts();
                case "7" -> sortUsersByCriteria(bufferedReader);
                case "8" -> createNewAccount(bufferedReader);
                case "9" -> addExistingAccountToUser(bufferedReader);
                case "10" -> deleteRelationBetweenUserAndAccount(bufferedReader);
                case "11" -> deleteAccountOfUser(bufferedReader);
                case "12" -> showAllAccountsOfUser(bufferedReader);
                case "13" -> createExpenseCategoryToUser(bufferedReader);
                case "14" -> updateExpenseCategoryToUser(bufferedReader);
                case "15" -> deleteAllExpensesOfUser(bufferedReader);
                case "16" -> deleteCertainExpenseOfUser(bufferedReader);
                case "17" -> getTotalExpenseOfUser(bufferedReader);
                case "18" -> listAllExpensesOfUser(bufferedReader);
                case "19" -> findSimilarExpensesForAllUsers(bufferedReader);
                case "20" -> sortExpensesByCriteria(bufferedReader);
                case "21" -> createIncomeCategoryToUser(bufferedReader);
                case "22" -> updateIncomeCategoryToUser(bufferedReader);
                case "23" -> deleteAllIncomesOfUser(bufferedReader);
                case "24" -> deleteCertainIncomeOfUser(bufferedReader);
                case "25" -> getTotalIncomesOfUser(bufferedReader);
                case "26" -> listAllIncomesOfUser(bufferedReader);
                case "27" -> findSimilarIncomesForAllUsers(bufferedReader);
                case "28" -> sortIncomesByCriteria(bufferedReader);
                case "29" -> sendMoneyFromOneToAnother(bufferedReader);
                case "30" -> createPiggyBank(bufferedReader);
                case "31" -> sendMoneyToPiggyBank(bufferedReader);
                case "32" -> sendMoneyFromPiggyBankToAccount(bufferedReader);
                case "33" -> showAllPiggyBankBalances();
                case "34" -> addPiggyBankToAccount(bufferedReader);
                case "35" -> deletePiggyBank(bufferedReader);
                case "36" -> exportAccountStatementToCsv(bufferedReader);
                case "0" -> {
                    System.out.println("Closing an application");
                    System.exit(0);
                }
                default -> System.err.println("Invalid selection, please try again");
            }
        } catch (NumberFormatException e) {
            System.err.println("You entered a wrong number, try again");
        }
        menu();
    }

    private void deleteRelationBetweenUserAndAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete the relationship between a user and an account.");

            System.out.println("Enter the user ID for whom you want to delete the relation:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter the account ID for which you want to delete the relation:");
            Long accountId = Long.parseLong(bufferedReader.readLine());

            userService.deleteUserAccountRelation(userId, accountId);

            System.out.println("The relation between the user and the account has been deleted successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid user and account IDs.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void createNewAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to create a new account.");
            System.out.println("Enter the account number:");
            int accountNumber = Integer.parseInt(bufferedReader.readLine());

            if (accountService.isAccountNumberUnique(accountNumber)) {
                System.out.println("Enter the account balance:");
                double balance = Double.parseDouble(bufferedReader.readLine());

                AccountRequestDto accountRequestDto = new AccountRequestDto(accountNumber, balance);
                accountFacade.create(accountRequestDto);

                System.out.println("Account with number " + accountNumber + " and balance " + balance + " has been created successfully.");
            } else {
                System.out.println("Account number already exists. Please enter a different number.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the account: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid values for account number and balance.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the account: " + e.getMessage());
        }
    }

    public void exportAccountStatementToCsv(BufferedReader bufferedReader) {
        try {
            System.out.print("Enter an account number:");
            String accountNumber = bufferedReader.readLine();

            System.out.print("Enter start date in format: (YYYY-MM-DD HH:MM:SS):");
            String startDateStr = bufferedReader.readLine();
            Date startDate = parseDate(startDateStr);

            System.out.print("Enter end date in format: (YYYY-MM-DD HH:MM:SS):");
            String endDateStr = bufferedReader.readLine();
            Date endDate = parseDate(endDateStr);

            boolean success = csvService.exportAccountStatementToCsv(accountNumber, startDate, endDate);

            if (success) {
                System.out.println("Statement created successfully in .csv.");
            } else {
                System.err.println("Something went wrong");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private java.util.Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void deletePiggyBank(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete a piggy bank.");
            System.out.println("Enter piggy bank ID:");
            long piggyBankId = Long.parseLong(bufferedReader.readLine());

            piggyBankFacade.delete(piggyBankId);

            System.out.println("Piggy bank has been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid number for the piggy bank ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    private void createPiggyBank(BufferedReader bufferedReader){
        try {
            System.out.println("You selected to create a piggy bank.");
            System.out.println("Let's create a piggy bank!");

            System.out.println("Enter initial balance for the piggy bank:");
            double initialBalance = Double.parseDouble(bufferedReader.readLine());

            PiggyBankRequestDto dto = new PiggyBankRequestDto(initialBalance);
            piggyBankFacade.create(dto);

            System.out.println("Piggy bank has been created successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the piggy bank: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid number for the initial balance.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the piggy bank: " + e.getMessage());
        }
    }

    private void addPiggyBankToAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to add an existing piggy bank to an account.");
            listAllUsersWithAccounts();

            System.out.println("Enter the account ID to which you want to add to piggy bank:");
            Long accountId = Long.parseLong(bufferedReader.readLine());

            Account existingAccount = accountService.findById(accountId);
            if (existingAccount == null) {
                System.out.println("Account with ID " + accountId + " not found.");
                return;
            }

            System.out.println("Enter the piggy bank ID you want to add to the account:");
            Long piggyBankId = Long.parseLong(bufferedReader.readLine());

            PiggyBank existingPiggyBank = piggyBankService.findById(piggyBankId);
            if (existingPiggyBank == null) {
                System.out.println("Piggy bank with ID " + piggyBankId + " not found.");
                return;
            }

            piggyBankService.addPiggyBankToAccount(existingAccount, existingPiggyBank);

            System.out.println("Piggy bank has been successfully added to the account.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding a piggy bank to the account: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid IDs.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding a piggy bank to the account: " + e.getMessage());
        }
    }

    private void showAllPiggyBankBalances() {
        try {
            List<PiggyBank> piggyBanks = piggyBankService.findAll();

            if (piggyBanks.isEmpty()) {
                System.out.println("No PiggyBanks found.");
            } else {
                System.out.println("PiggyBank Balances:");
                for (PiggyBank piggyBank : piggyBanks) {
                    System.out.println("PiggyBank ID: " + piggyBank.getId() + ", Balance: " + piggyBank.getBalance());
                }
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while fetching piggy bank balances: " + e.getMessage());
        }
    }

    private void sendMoneyFromPiggyBankToAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to send money from piggy bank to account.");
            listAllUsersWithAccounts();
            System.out.println("Enter piggy bank ID:");
            long piggyBankId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter account ID:");
            long accountId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter amount to transfer:");
            double amount = Double.parseDouble(bufferedReader.readLine());

            PiggyBank piggyBank = piggyBankService.findById(piggyBankId);
            Account account = accountService.findById(accountId);

            if (piggyBank == null) {
                System.out.println("Piggy bank with ID " + piggyBankId + " not found.");
                return;
            }
            if (account == null) {
                System.out.println("Account with ID " + accountId + " not found.");
                return;
            }

            if (piggyBank.getBalance() < amount) {
                System.out.println("Not enough funds in piggy bank for the transfer.");
                return;
            }

            piggyBankService.sendMoneyToAccount(piggyBank, account, amount);

            System.out.println("Money has been transferred successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void sendMoneyToPiggyBank(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to send money to piggy bank.");

            System.out.println("Enter account ID:");
            long accountId = Long.parseLong(bufferedReader.readLine());
            Account account = accountService.findById(accountId);

            if (account == null) {
                System.out.println("Account with ID " + accountId + " not found.");
                return;
            }

            System.out.println("Enter piggy bank ID:");
            long piggyBankId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter amount to deposit:");
            double amount = Double.parseDouble(bufferedReader.readLine());

            PiggyBank piggyBank = piggyBankService.findById(piggyBankId);

            if (piggyBank == null) {
                System.out.println("Piggy bank with ID " + piggyBankId + " not found.");
                return;
            }

            piggyBankService.sendMoneyToPiggyBank(account, piggyBank, amount);

            System.out.println("Money has been deposited successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers for IDs and amount.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void sendMoneyFromOneToAnother(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to send money from one account to another.");
            listAllUsersWithAccounts();

            System.out.println("Enter the sender's account number:");
            int senderAccountNumber = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Enter the receiver's account number:");
            int receiverAccountNumber = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Enter the amount to send:");
            double amountToSend = Double.parseDouble(bufferedReader.readLine());

            System.out.println("Enter a comment for the operation:");
            String comment = bufferedReader.readLine();

            Account senderAccount = accountService.findByAccountNumberController(senderAccountNumber);
            Account receiverAccount = accountService.findByAccountNumberController(receiverAccountNumber);

            if (senderAccount == null || receiverAccount == null) {
                System.out.println("Sender or receiver account not found.");
                return;
            }

            if (senderAccount.getBalance() < amountToSend) {
                System.out.println("Sender does not have sufficient funds to make the transfer.");
                return;
            }

            operationService.createOperation(senderAccountNumber, receiverAccountNumber, amountToSend, comment);

            System.out.println("Money sent successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while sending money: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while sending money: " + e.getMessage());
        }
    }

    private void sortIncomesByCriteria(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to sort incomes by criteria.");
            System.out.print("Enter the sorting criteria (e.g., amount, incomeType): ");
            String sortingCriteria = bufferedReader.readLine();

            List<IncomeCategory> sortedIncomes;

            switch (sortingCriteria) {
                case "amount" -> sortedIncomes = incomeCategoryService.sortIncomesByAmount();
                case "incomeType" -> sortedIncomes = incomeCategoryService.sortIncomesByIncomeType();
                default -> {
                    System.out.println("Invalid sorting criteria. Please enter valid criteria.");
                    return;
                }
            }

            if (sortedIncomes.isEmpty()) {
                System.out.println("No incomes found.");
            } else {
                System.out.println("Sorted Incomes:");
                for (IncomeCategory income : sortedIncomes) {
                    System.out.println("ID: " + income.getId() + ", Amount: " + income.getIncomeAmount() + ", Type: " + income.getIncomeType());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while sorting incomes: " + e.getMessage());
        }
    }

    private void findSimilarIncomesForAllUsers(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to find similar incomes for all users.");
            System.out.print("Enter the criteria to find similar incomes (e.g., amount, incomeType): ");
            String searchCriteria = bufferedReader.readLine();

            List<IncomeCategory> similarIncomes;

            switch (searchCriteria) {
                case "amount" -> {
                    System.out.print("Enter the amount: ");
                    double targetAmount = Double.parseDouble(bufferedReader.readLine());
                    similarIncomes = incomeCategoryService.findSimilarIncomesByAmount(targetAmount);
                }
                case "incomeType" -> {
                    System.out.print("Enter the income type: ");
                    String targetIncomeType = bufferedReader.readLine();
                    similarIncomes = incomeCategoryService.findSimilarIncomesByIncomeType(targetIncomeType);
                }
                default -> {
                    System.out.println("Invalid search criteria. Please enter valid criteria.");
                    return;
                }
            }

            if (similarIncomes.isEmpty()) {
                System.out.println("No similar incomes found.");
            } else {
                System.out.println("Similar Incomes:");
                for (IncomeCategory income : similarIncomes) {
                    System.out.println("ID: " + income.getId() + ", Amount: " + income.getIncomeAmount() + ", Type: " + income.getIncomeType());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding similar incomes: " + e.getMessage());
        }
    }

    private void listAllIncomesOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to list all incomes of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to list incomes for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<IncomeCategory> incomeCategories = incomeCategoryService.findAllIncomeCategoriesByUserId(userId);

            if (incomeCategories.isEmpty()) {
                System.out.println("No income categories found for this user.");
                return;
            }

            System.out.println("Incomes for the user:");
            for (IncomeCategory category : incomeCategories) {
                System.out.println("Type: " + category.getIncomeType() + ", Amount: " + category.getIncomeAmount());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while listing incomes: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while listing incomes: " + e.getMessage());
        }
    }

    private void getTotalIncomesOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to get the total incomes of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to get total incomes for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<IncomeCategory> incomeCategories = incomeCategoryService.findAllIncomeCategoriesByUserId(userId);

            if (incomeCategories.isEmpty()) {
                System.out.println("No income categories found for this user.");
                return;
            }

            double totalIncome = 0.0;
            for (IncomeCategory category : incomeCategories) {
                totalIncome += category.getIncomeAmount();
            }

            System.out.println("Total income for the user: " + totalIncome);
        } catch (IOException e) {
            System.out.println("An error occurred while getting total incomes: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while getting total incomes: " + e.getMessage());
        }
    }

    private void deleteCertainIncomeOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete a certain income category of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to delete a certain income category for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<IncomeCategory> incomeCategories = incomeCategoryService.findAllIncomeCategoriesByUserId(userId);

            if (incomeCategories.isEmpty()) {
                System.out.println("No income categories found for this user.");
                return;
            }

            System.out.println("Income categories for the user:");
            for (IncomeCategory category : incomeCategories) {
                System.out.println("ID: " + category.getId() + ", Name: " + category.getIncomeType() + ", Amount: " + category.getIncomeAmount());
            }

            System.out.println("Enter the ID of the income category you want to delete:");
            Long categoryId = Long.parseLong(bufferedReader.readLine());

            incomeCategoryFacade.delete(categoryId);

            System.out.println("Income category has been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the income category: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the income category: " + e.getMessage());
        }
    }

    private void deleteAllIncomesOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete all incomes of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to delete all incomes for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            incomeCategoryService.deleteAllIncomeCategoriesByUserId(userId);

            System.out.println("All incomes of the user have been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting all incomes: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting all incomes: " + e.getMessage());
        }
    }

    private void updateIncomeCategoryToUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to update an income category for a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to update an income category for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            System.out.println("Enter the ID of the income category to update:");
            Long categoryId = Long.parseLong(bufferedReader.readLine());

            IncomeCategoryResponseDto existingCategory = incomeCategoryFacade.findById(categoryId);
            if (existingCategory == null) {
                System.out.println("Income category with ID " + categoryId + " not found.");
                return;
            }

            System.out.println("Enter the updated name of the income category:");
            String updatedIncomeType = bufferedReader.readLine();
            System.out.println("Enter the updated income amount:");
            double updatedIncomeAmount = Double.parseDouble(bufferedReader.readLine());

            IncomeCategoryRequestDto updatedIncomeCategoryRequestDto = new IncomeCategoryRequestDto(updatedIncomeType, updatedIncomeAmount);

            incomeCategoryFacade.update(updatedIncomeCategoryRequestDto, categoryId);

            System.out.println("Income category has been updated successfully for the user.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the income category: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the income category: " + e.getMessage());
        }
    }
    private void createIncomeCategoryToUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to create an income category for a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to create an income category for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            System.out.println("Enter the name of the income category:");
            String incomeType = bufferedReader.readLine();

            System.out.println("Enter the initial income amount:");
            double incomeAmount = Double.parseDouble(bufferedReader.readLine());

            IncomeCategoryRequestDto incomeCategoryRequestDto = new IncomeCategoryRequestDto(incomeType, incomeAmount);
            incomeCategoryRequestDto.setUserId(userId);
            incomeCategoryFacade.create(incomeCategoryRequestDto);

            System.out.println("Income category has been created successfully for the user.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the income category: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the income category: " + e.getMessage());
        }
    }

    private void sortExpensesByCriteria(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to sort expenses by criteria.");
            System.out.print("Enter the sorting criteria (e.g., amount, expenseType): ");
            String sortingCriteria = bufferedReader.readLine();

            List<ExpenseCategory> sortedExpenses;

            switch (sortingCriteria) {
                case "amount" -> sortedExpenses = expenseCategoryService.sortExpensesByAmount();
                case "expenseType" -> sortedExpenses = expenseCategoryService.sortExpensesByExpenseType();
                default -> {
                    System.out.println("Invalid sorting criteria. Please enter valid criteria.");
                    return;
                }
            }

            if (sortedExpenses.isEmpty()) {
                System.out.println("No expenses found.");
            } else {
                System.out.println("Sorted Expenses:");
                for (ExpenseCategory expense : sortedExpenses) {
                    System.out.println("ID: " + expense.getId() + ", Amount: " + expense.getExpenseAmount() + ", Type: " + expense.getExpenseType());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while sorting expenses: " + e.getMessage());
        }
    }

    private void findSimilarExpensesForAllUsers(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to find similar expenses for all users.");
            System.out.print("Enter the criteria to find similar expenses (e.g., amount, expenseType): ");
            String searchCriteria = bufferedReader.readLine();

            List<ExpenseCategory> similarExpenses;

            switch (searchCriteria) {
                case "amount" -> {
                    System.out.print("Enter the amount: ");
                    double amount = Double.parseDouble(bufferedReader.readLine());
                    similarExpenses = expenseCategoryService.findSimilarExpensesByAmount(amount);
                }
                case "expenseType" -> {
                    System.out.print("Enter the expense type: ");
                    String expenseType = bufferedReader.readLine();
                    similarExpenses = expenseCategoryService.findSimilarExpensesByExpenseType(expenseType);
                }
                default -> {
                    System.out.println("Invalid search criteria. Please enter valid criteria.");
                    return;
                }
            }

            if (similarExpenses.isEmpty()) {
                System.out.println("No similar expenses found.");
            } else {
                System.out.println("Similar Expenses:");
                for (ExpenseCategory expense : similarExpenses) {
                    System.out.println("ID: " + expense.getId() + ", Amount: " + expense.getExpenseAmount() + "Type: " + expense.getExpenseType());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding similar expenses: " + e.getMessage());
        }
    }

    private void listAllExpensesOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to list all expenses of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to list expenses for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<ExpenseCategory> expenseCategories = expenseCategoryService.findAllExpenseCategoriesByUserId(userId);

            if (expenseCategories.isEmpty()) {
                System.out.println("No expense categories found for this user.");
                return;
            }

            System.out.println("Expense categories for the user:");
            for (ExpenseCategory category : expenseCategories) {
                System.out.println("ID: " + category.getId() + ", Name: " + category.getExpenseType() + "Amount: " + category.getExpenseAmount());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while listing expenses: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while listing expenses: " + e.getMessage());
        }
    }

    private void getTotalExpenseOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the user ID to get the total expense for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            double totalExpense = 0.0;

            List<ExpenseCategory> expenseCategories = expenseCategoryService.findAllExpenseCategoriesByUserId(userId);

            for (ExpenseCategory category : expenseCategories) {
                totalExpense += getTotalExpenseOfCategory(category.getId());
            }

            System.out.println("Total expense for user with ID " + userId + ": " + totalExpense);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        }
    }

    private double getTotalExpenseOfCategory(Long categoryId) {
        double totalExpense = 0.0;

        List<ExpenseCategory> expenses = expenseCategoryService.findAllExpensesByCategoryId(categoryId);

        for (ExpenseCategory expense : expenses) {
            totalExpense += expense.getExpenseAmount();
        }

        return totalExpense;
    }

    private void deleteCertainExpenseOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete a certain expense category of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to delete a certain expense category for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<ExpenseCategory> expenseCategories = expenseCategoryService.findAllExpenseCategoriesByUserId(userId);

            if (expenseCategories.isEmpty()) {
                System.out.println("No expense categories found for this user.");
                return;
            }

            System.out.println("Expense categories for the user:");
            for (ExpenseCategory category : expenseCategories) {
                System.out.println("ID: " + category.getId() + ", Name: " + category.getExpenseType() + "Amount: " + category.getExpenseAmount());
            }

            System.out.println("Enter the ID of the expense category you want to delete:");
            Long categoryId = Long.parseLong(bufferedReader.readLine());

            expenseCategoryFacade.delete(categoryId);

            System.out.println("Expense category has been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the expense category: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the expense category: " + e.getMessage());
        }
    }

    private void deleteAllExpensesOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete all expenses of a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to delete all expenses for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            expenseCategoryService.deleteAllExpensesOfUser(userId);

            System.out.println("All expenses of the user have been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting all expenses: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting all expenses: " + e.getMessage());
        }
    }

    private void updateExpenseCategoryToUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to update an expense category for a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to update an expense category for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            System.out.println("Enter the ID of the expense category to update:");
            Long categoryId = Long.parseLong(bufferedReader.readLine());

            ExpenseCategoryResponseDto existingCategory = expenseCategoryFacade.findById(categoryId);
            if (existingCategory == null) {
                System.out.println("Expense category with ID " + categoryId + " not found.");
                return;
            }

            System.out.println("Enter the updated name of the expense category:");
            String updatedExpenseType = bufferedReader.readLine();
            System.out.println("Enter the updated expense amount:");
            double updatedExpenseAmount = Double.parseDouble(bufferedReader.readLine());

            ExpenseCategoryRequestDto updatedExpenseCategoryRequestDto = new ExpenseCategoryRequestDto(updatedExpenseType, updatedExpenseAmount);

            expenseCategoryFacade.update(updatedExpenseCategoryRequestDto, categoryId);

            System.out.println("Expense category has been updated successfully for the user.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the expense category: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the expense category: " + e.getMessage());
        }
    }

    private void createExpenseCategoryToUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to create an expense category for a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user ID to create an expense category for:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            UserResponseDto existingUser = userFacade.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            System.out.println("Enter the name of the expense category:");
            String expenseType = bufferedReader.readLine();
            System.out.println("Enter the initial expense amount:");
            double expenseAmount = Double.parseDouble(bufferedReader.readLine());

            ExpenseCategoryRequestDto expenseCategoryRequestDto = new ExpenseCategoryRequestDto(expenseType, expenseAmount);
            expenseCategoryRequestDto.setUserId(userId);
            expenseCategoryFacade.create(expenseCategoryRequestDto);

            System.out.println("Expense category has been created successfully for the user.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the expense category: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the expense category: " + e.getMessage());
        }
    }

    private void showAllAccountsOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to show all accounts of a user.");

            System.out.println("Enter the user ID to view their accounts:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            User existingUser = userService.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<Account> accounts = accountService.getAccountsByUserId(userId);
            if (!accounts.isEmpty()) {
                System.out.println("Accounts of the user:");
                for (Account account : accounts) {
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Account Balance: " + account.getBalance());
                    System.out.println("Account ID: " + account.getId());
                    System.out.println("-------------------------------------");
                }
            } else {
                System.out.println("No accounts found for this user.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while showing the accounts: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while showing the accounts: " + e.getMessage());
        }
    }

    private void deleteAccountOfUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete an account of a user.");

            System.out.println("Enter the user ID for whom you want to delete an account:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            User existingUser = userService.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            List<Account> accounts = accountService.getAccountsByUserId(userId);
            if (accounts.isEmpty()) {
                System.out.println("No accounts found for the user with ID " + userId + ".");
                return;
            }

            System.out.println("Accounts of the user:");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println((i + 1) + ". Account Number: " + accounts.get(i).getAccountNumber());
            }

            System.out.println("Enter the number of the account you want to delete:");
            int accountChoice = Integer.parseInt(bufferedReader.readLine()) - 1;

            if (accountChoice < 0 || accountChoice >= accounts.size()) {
                System.out.println("Invalid account number. Please select a valid account.");
                return;
            }

            Account accountToDelete = accounts.get(accountChoice);
            accountService.delete(accountToDelete.getId());

            System.out.println("Account with number " + accountToDelete.getAccountNumber() + " has been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the account: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid user ID and account number.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the account: " + e.getMessage());
        }
    }

    private void addExistingAccountToUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to add an existing account to a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user's ID to whom you want to add an account:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            User existingUser = userService.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }

            System.out.println("Enter the account ID you want to add to the user:");
            Long accountId = Long.parseLong(bufferedReader.readLine());

            Account existingAccount = accountService.findById(accountId);
            if (existingAccount == null) {
                System.out.println("Account with ID " + accountId + " not found.");
                return;
            }

            accountService.addAccountToUser(existingUser, existingAccount);
            System.out.println("Account has been successfully added to the user.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding an account to the user: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid IDs.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding an account to the user: " + e.getMessage());
        }
    }

    private void sortUsersByCriteria(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to sort users by criteria.");
            System.out.println("Choose a sorting criteria:");
            System.out.println("1. Sort by Name");
            System.out.println("2. Sort by Email");
            System.out.println("3. Sort by ID");
            System.out.println("4. Sort by Account");

            int choice = Integer.parseInt(bufferedReader.readLine());
            List<User> users = userService.findAll();

            switch (choice) {
                case 1 -> users.sort(Comparator.comparing(User::getUserName));
                case 2 -> users.sort(Comparator.comparing(User::getEmail));
                case 3 -> users.sort(Comparator.comparing(User::getId));
                case 4 -> users.sort(Comparator.comparing(user -> {
                    Long accountId = userService.getAccountIdByUserId(user.getId());
                    return accountId != null ? accountId : 0;
                }));
                default -> {
                    System.out.println("Invalid choice. Sorting canceled.");
                    return;
                }
            }

            System.out.println("Users sorted by selected criteria:");
            for (User user : users) {
                System.out.println("User ID: " + user.getId());
                System.out.println("User Name: " + user.getUserName());
                System.out.println("User Email: " + user.getEmail());
                System.out.println("User Password: " + user.getPassword());
                System.out.println("-------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while sorting users: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid choice.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while sorting users: " + e.getMessage());
        }
    }

    private void listAllUsersWithAccounts() {
        try {
            List<User> users = userService.findAll();
            if (users.isEmpty()) {
                System.out.println("No users found.");
                return;
            }

            for (User user : users) {
                System.out.println("User information:");
                System.out.println("ID: " + user.getId());
                System.out.println("User Name: " + user.getUserName());
                System.out.println("User Email: " + user.getEmail());
                System.out.println("User Password: " + user.getPassword());

                List<Account> userAccounts = accountService.getAccountsByUserId(user.getId());
                if (!userAccounts.isEmpty()) {
                    System.out.println("Account information:");
                    for (Account account : userAccounts) {
                        System.out.println("Account Number: " + account.getAccountNumber());
                        System.out.println("Account ID: " + account.getId());
                    }
                } else {
                    System.out.println("No accounts found for this user.");
                }

                System.out.println("-------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void findUserByAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to find a user by account.");
            System.out.println("Enter the account number to find the associated user:");

            int accountNumber = Integer.parseInt(bufferedReader.readLine());
            Long userId = userService.getUserIdByAccountNumber(accountNumber);

            if (userId != null) {
                User user = userService.findById(userId);
                System.out.println("User associated with account number " + accountNumber + ":");
                System.out.println("User ID: " + user.getId());
                System.out.println("User Name: " + user.getUserName());
                System.out.println("User Email: " + user.getEmail());
                System.out.println("User Password: " + user.getPassword());
            } else {
                System.out.println("No user found for account number " + accountNumber);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding the user by account: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid account number.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while finding the user by account: " + e.getMessage());
        }
    }

    private void findUserById(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to find a user by ID.");
            System.out.println("Enter the user's ID to find:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            User user = userService.findById(userId);

            if (user != null) {
                System.out.println("User information:");
                System.out.println("ID: " + user.getId());
                System.out.println("User Name: " + user.getUserName());
                System.out.println("User Email: " + user.getEmail());
                System.out.println("User Password: " + user.getPassword());
            } else {
                System.out.println("User with ID " + userId + " not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding the user: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while finding the user: " + e.getMessage());
        }
    }

    private void deleteUserWithItsAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to delete a user and its account.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user's ID to delete:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            User existingUser = userService.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }
            System.out.println("User details: " + existingUser);

            System.out.println("Are you sure you want to delete this user and its account? (yes/no):");
            String confirmation = bufferedReader.readLine().toLowerCase();

            if ("yes".equals(confirmation)) {
                Long accountId = userService.getAccountIdByUserId(userId);
                if (accountId != null) {
                    accountFacade.delete(accountId);
                    System.out.println("Account of the user has been deleted.");
                }

                userFacade.delete(userId);
                System.out.println("User has been deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the user and its account: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a valid user ID.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the user and its account: " + e.getMessage());
        }
    }

    private void updateUser(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected to update a user.");
            listAllUsersWithAccounts();

            System.out.println("Enter the user's ID to update:");
            Long userId = Long.parseLong(bufferedReader.readLine());

            User existingUser = userService.findById(userId);
            if (existingUser == null) {
                System.out.println("User with ID " + userId + " not found.");
                return;
            }
            System.out.println("User details: " + existingUser);

            System.out.println("Enter a new name of the user:");
            String newName = bufferedReader.readLine();
            System.out.println("Enter a new email:");
            String newEmail = bufferedReader.readLine();
            System.out.println("Enter a new password:");
            int newPassword = Integer.parseInt(bufferedReader.readLine());

            UserRequestDto updatedUserDto = new UserRequestDto(newName, newEmail, newPassword);
            userFacade.update(updatedUserDto, userId);
            System.out.println("User information has been updated successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while updating the user: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the user: " + e.getMessage());
        }
    }

    private void createUserAndItsAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("You selected creation of a user and its account");
            System.out.println("Let's create a user!");
            System.out.println("Enter user's full name:");
            String name = bufferedReader.readLine();
            System.out.println("Enter user's e-mail:");
            String email = bufferedReader.readLine();
            System.out.println("Enter user's password:");
            int password = Integer.parseInt(bufferedReader.readLine());
            UserRequestDto userRequestDto = new UserRequestDto(name, email, password);

            userFacade.create(userRequestDto);

            System.out.println("Let's create an account for the user!");
            System.out.println("Please, select your options:");
            System.out.println("If you want to create an account number on your own, enter 1");
            System.out.println("If you want to randomize user's account number, enter 2");
            int answer = Integer.parseInt(bufferedReader.readLine());

            int accountNumber;
            int randomAccountNumber;
            boolean isUnique;


            switch (answer) {
                case 1:
                    System.out.println("You chose to make a number of account on your own.");
                    System.out.println("Enter a number of your account: ");

                    do {
                        accountNumber = Integer.parseInt(bufferedReader.readLine());
                        isUnique = accountService.isAccountNumberUnique(accountNumber);

                        if (!isUnique) {
                            System.out.println("Account number already exists. Please enter a different number.");
                        }
                    } while (!isUnique);

                    AccountRequestDto accountRequestDto = new AccountRequestDto(accountNumber, 400.0);
                    accountFacade.create(accountRequestDto);
                    break;
                case 2:
                    System.out.println("You chose to randomize your account number.");

                    do {
                        randomAccountNumber = accountService.generateRandomAccountNumber();
                        isUnique = accountService.isAccountNumberUnique(randomAccountNumber);

                        if (!isUnique) {
                            System.out.println("Account number already exists. Generating a new one.");
                        }
                    } while (!isUnique);

                    AccountRequestDto randomAccountRequestDto = new AccountRequestDto(randomAccountNumber, 0.0);
                    accountFacade.create(randomAccountRequestDto);
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
            System.out.println("User and its account have been created successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while creating user and its account: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter valid numbers.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating user and its account: " + e.getMessage());
        }
    }
}
