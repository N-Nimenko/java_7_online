package crud.jdbc.controller;

import crud.jdbc.entity.Company;
import crud.jdbc.entity.Director;
import crud.jdbc.service.CompanyService;
import crud.jdbc.service.DirectorOfCompanyService;
import crud.jdbc.service.DirectorService;
import framework.annotations.BeanClass;
import framework.annotations.InjectBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@BeanClass
public class ControllerImpl implements Controller {
    @InjectBean
    private DirectorService directorService;
    @InjectBean
    private CompanyService companyService;
    @InjectBean
    private DirectorOfCompanyService directorOfCompanyService;

    @Override
    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to application of finding directors and companies");
            System.out.println("Choose an operation:");
            String answer;
            menu();
            while ((answer = bufferedReader.readLine()) != null) {
                crud(bufferedReader, answer);
            }
        } catch (IOException e) {
            System.err.println("You entered wrong data, try again");
        }
    }

    public void menu() {
        System.out.println("***************************************************************************");
        System.out.println("| Option |                 Action                  |         Enter       |");
        System.out.println("|--------|-----------------------------------------|---------------------|");
        System.out.println("|   1    |           Create a director             |         1           |");
        System.out.println("|   2    |           Create a company              |         2           |");
        System.out.println("|   3    |       Add a company to the director     |         3           |");
        System.out.println("|   4    |            Update a company             |         4           |");
        System.out.println("|   5    |           Update a director             |         5           |");
        System.out.println("|   6    |      Update a director and its company  |         6           |");
        System.out.println("|   7    |             Delete a company            |         7           |");
        System.out.println("|   8    |            Delete a director            |         8           |");
        System.out.println("|   9    |      Delete a director from company     |         9           |");
        System.out.println("|  10    |       Delete a director and company     |        10           |");
        System.out.println("|  11    |           Find a certain company        |        11           |");
        System.out.println("|  12    |          Find a certain director        |        12           |");
        System.out.println("|  13    |           Find all the companies        |        13           |");
        System.out.println("|  14    |           Find all the directors        |        14           |");
        System.out.println("|  15    |        Display all companies' IDs       |        15           |");
        System.out.println("|  16    |        Display all directors' IDs       |        16           |");
        System.out.println("|  17    |     Display all companies of a director |        17           |");
        System.out.println("|  18    |     Display all directors in a company  |        18           |");
        System.out.println("|  19    |         Display all companies and       |        19           |");
        System.out.println("|        |                  directors              |                     |");
        System.out.println("|  20    |          Close the application          |         0           |");
        System.out.println("***************************************************************************");
    }

    public void crud(BufferedReader bufferedReader, String answer) {
        try {
            switch (answer) {
                case "1" -> createDirector(bufferedReader);
                case "2" -> createCompany(bufferedReader);
                case "3" -> addCompanyToDirector(bufferedReader);
                case "4" -> updateCompany(bufferedReader);
                case "5" -> updateDirector(bufferedReader);
                case "6" -> updateDirectorAndCompany(bufferedReader);
                case "7" -> deleteCompany(bufferedReader);
                case "8" -> deleteDirector(bufferedReader);
                case "9" -> deleteDirectorFromCompany(bufferedReader);
                case "10" -> deleteDirectorAndCompany(bufferedReader);
                case "11" -> findCompany(bufferedReader);
                case "12" -> findDirector(bufferedReader);
                case "13" -> findAllCompanies(bufferedReader);
                case "14" -> findAllDirectors(bufferedReader);
                case "15" -> displayAllCompaniesIds();
                case "16" -> displayAllDirectorsIds();
                case "17" -> displayAllCompaniesOfDirector(bufferedReader);
                case "18" -> displayAllDirectorsInCompany(bufferedReader);
                case "19" -> displayAllDirectorsAndCompanies(bufferedReader);
                case "0" -> {
                    System.out.println("Closing an application");
                    System.exit(0);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("You entered wrong number, try again");
        }
        menu();
    }

    public void createDirector(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the first name of the director:");
            String firstName = bufferedReader.readLine();
            System.out.println("Enter the last name of the director:");
            String lastName = bufferedReader.readLine();
            System.out.println("Enter the age of the director:");
            int age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the id of the director");
            Long id = Long.valueOf(bufferedReader.readLine());

            Director director = new Director();
            director.setId(id);
            director.setFirstName(firstName);
            director.setLastName(lastName);
            director.setAge(age);
            directorService.create(director);

            System.out.println("Director has been created successfully with ID: " + director.getId());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the director: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: age of the director should been a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the director: " + e.getMessage());
        }
    }

    public void createCompany(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the name of the company:");
            String companyName = bufferedReader.readLine();
            System.out.println("Enter the quantity of employees");
            int employeesQuantity = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the price of the company");
            int companyPrice = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the id of the company");
            Long id = Long.valueOf((bufferedReader.readLine()));

            Company company = new Company();
            company.setId(id);
            company.setName(companyName);
            company.setEmployeeQuantity(employeesQuantity);
            company.setCompanyPrice(companyPrice);
            companyService.create(company);

            System.out.println("Company has been created successfully with ID: " + company.getId());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the company: " + e.getMessage());
        }
    }

    public void addCompanyToDirector(BufferedReader bufferedReader) {
        try {
            displayAllCompaniesIds();
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            Long directorId = Long.valueOf(bufferedReader.readLine());
            System.out.println("Enter the ID of the company:");
            Long companyId = Long.valueOf(bufferedReader.readLine());

            directorOfCompanyService.create(directorId, companyId);

            System.out.println("Company has been added to the director successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the company to the director: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding the company to the director: " + e.getMessage());
        }
    }

    public void updateCompany(BufferedReader bufferedReader) {
        try {
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            String companyId = bufferedReader.readLine();
            if (companyId == null) {
                throw new RuntimeException("Invalid input: company's id is null");
            }

            Company company = companyService.findById(Long.valueOf(companyId));

            if (company != null) {
                System.out.println("Enter the new name of the company:");
                String newName = bufferedReader.readLine();
                company.setName(newName);
                System.out.println("Enter the new quantity of employee: ");
                int newEmployeeQuantity = Integer.parseInt(bufferedReader.readLine());
                company.setEmployeeQuantity(newEmployeeQuantity);
                System.out.println("Enter the new company price: ");
                int newCompanyPrice = Integer.parseInt(bufferedReader.readLine());
                company.setCompanyPrice(newCompanyPrice);
                companyService.update(company);

                System.out.println("Company has been updated successfully.");
            } else {
                System.out.println("Company not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the company: " + e.getMessage());
        }
    }

    public void updateDirector(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            String directorId = bufferedReader.readLine();
            if (directorId == null) {
                throw new RuntimeException("Invalid input: director's id is null");
            }

            Director director = directorService.findById(Long.valueOf(directorId));

            if (director != null) {
                System.out.println("Enter the new first name of the director:");
                String newFirstName = bufferedReader.readLine();
                System.out.println("Enter the new last name of the director:");
                String newLastName = bufferedReader.readLine();
                System.out.println("Enter the new age of the director:");
                int newAge = Integer.parseInt(bufferedReader.readLine());

                director.setFirstName(newFirstName);
                director.setLastName(newLastName);
                director.setAge(newAge);
                directorService.update(director);

                System.out.println("Director has been updated successfully.");
            } else {
                System.out.println("Director not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the director: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: years of experience should be a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the director: " + e.getMessage());
        }
    }

    private void updateDirectorAndCompany(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            String directorIdStr = bufferedReader.readLine();
            if (directorIdStr == null) {
                throw new RuntimeException("Invalid input: director's id is null");
            }

            Long directorId = Long.valueOf(directorIdStr);
            Director director = directorService.findById(directorId);

            if (director != null) {
                System.out.println("Enter the new first name of the director:");
                String newFirstName = bufferedReader.readLine();
                System.out.println("Enter the new last name of the director:");
                String newLastName = bufferedReader.readLine();
                System.out.println("Enter the new age of the director:");
                int newAge = Integer.parseInt(bufferedReader.readLine());

                director.setFirstName(newFirstName);
                director.setLastName(newLastName);
                director.setAge(newAge);
                directorService.update(director);

                System.out.println("Director has been updated successfully.");
            } else {
                System.out.println("Director not found.");
                return;
            }
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            String companyIdStr = bufferedReader.readLine();
            if (companyIdStr == null) {
                throw new RuntimeException("Invalid input: company's id is null");
            }

            Long companyId = Long.valueOf(companyIdStr);
            Company company = companyService.findById(companyId);

            if (company != null) {
                System.out.println("Enter the new name of the company:");
                String newName = bufferedReader.readLine();
                company.setName(newName);
                System.out.println("Enter the new quantity of employee: ");
                int newEmployeeQuantity = Integer.parseInt(bufferedReader.readLine());
                company.setEmployeeQuantity(newEmployeeQuantity);
                System.out.println("Enter the new company price: ");
                int newCompanyPrice = Integer.parseInt(bufferedReader.readLine());
                company.setCompanyPrice(newCompanyPrice);
                companyService.update(company);

                System.out.println("Company has been updated successfully.");
            } else {
                System.out.println("Company not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: years of experience should be a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void deleteCompany(BufferedReader bufferedReader) {
        try {
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            String companyId = bufferedReader.readLine();
            if (companyId == null) {
                throw new RuntimeException("Invalid input: company's id is null");
            }

            Company company = companyService.findById(Long.valueOf(companyId));

            if (company != null) {
                companyService.delete(Long.valueOf(companyId));
                System.out.println("Company has been deleted successfully.");
            } else {
                System.out.println("Company not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the company: " + e.getMessage());
        }
    }

    public void deleteDirector(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            String directorId = bufferedReader.readLine();
            if (directorId == null) {
                throw new RuntimeException("Invalid input: director's id is null");
            }

            Director director = directorService.findById(Long.valueOf(directorId));

            if (director != null) {
                directorService.delete(Long.valueOf(directorId));
                System.out.println("Director has been deleted successfully.");
            } else {
                System.out.println("Director not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the director: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the director: " + e.getMessage());
        }
    }

    public void deleteDirectorFromCompany(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            Long directorId = Long.valueOf(bufferedReader.readLine());
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            Long companyId = Long.valueOf(bufferedReader.readLine());

            directorOfCompanyService.deleteDirectorFromCompany(directorId, companyId);

            System.out.println("Director has been removed from the company successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the director from the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the director from the company: " + e.getMessage());
        }
    }

    public void deleteDirectorAndCompany(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            Long directorId = Long.valueOf(bufferedReader.readLine());
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            Long companyId = Long.valueOf(bufferedReader.readLine());

            directorOfCompanyService.deleteDirectorAndCompany(directorId, companyId);

            System.out.println("Director's association with the company has been deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the director's association with the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the director's association with the company: " + e.getMessage());
        }
    }

    public void findCompany(BufferedReader bufferedReader) {
        try {
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            String companyId = bufferedReader.readLine();
            if (companyId == null) {
                throw new RuntimeException("Invalid input: company's id is null");
            }

            Company company = companyService.findById(Long.valueOf(companyId));

            if (company != null) {
                System.out.println("Company found:");
                System.out.println("ID: " + company.getId());
                System.out.println("Name: " + company.getName());
                System.out.println("Employee quantity: " + company.getEmployeeQuantity());
                System.out.println("Company price: " + company.getCompanyPrice());
            } else {
                System.out.println("Company not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while finding the company: " + e.getMessage());
        }
    }

    public void findDirector(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            String directorId = bufferedReader.readLine();
            if (directorId == null) {
                throw new RuntimeException("Invalid input: director's id is null");
            }

            Director director = directorService.findById(Long.valueOf(directorId));

            if (director != null) {
                System.out.println("Director found:");
                System.out.println("ID: " + director.getId());
                System.out.println("First Name: " + director.getFirstName());
                System.out.println("Last Name: " + director.getLastName());
                System.out.println("Age: " + director.getAge());
            } else {
                System.out.println("Director not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding the director: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while finding the director: " + e.getMessage());
        }
    }

    private void findAllCompanies(BufferedReader bufferedReader) {
        List<Company> companies = companyService.findAll();
        for (Company company : companies) {
            System.out.println("Company information:");
            System.out.println("ID: " + company.getId());
            System.out.println("Name: " + company.getName());
            System.out.println("Employee quantity: " + company.getEmployeeQuantity());
            System.out.println("Company price: " + company.getCompanyPrice());
            System.out.println("-------------------------------------");
        }
    }

    private void findAllDirectors(BufferedReader bufferedReader) {
        List<Director> directors = directorService.findAll();
        for (Director director : directors) {
            System.out.println("Director information:");
            System.out.println("ID: " + director.getId());
            System.out.println("First Name: " + director.getFirstName());
            System.out.println("Last Name: " + director.getLastName());
            System.out.println("Age: " + director.getAge());
            System.out.println("-------------------------------------");
        }
    }

    public void displayAllCompaniesIds() {
        try {
            Company[] companies = companyService.findAll().toArray(new Company[0]);
            System.out.println("All Company IDs:");
            for (Company company : companies) {
                System.out.println(company.getId());
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying all company IDs: " + e.getMessage());
        }
    }

    public void displayAllDirectorsIds() {
        try {
            Director[] directors = directorService.findAll().toArray(new Director[0]);
            System.out.println("All Director IDs:");
            for (Director director : directors) {
                System.out.println(director.getId());
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying all director IDs: " + e.getMessage());
        }
    }

    public void displayAllCompaniesOfDirector(BufferedReader bufferedReader) {
        try {
            displayAllDirectorsIds();
            System.out.println("Enter the ID of the director:");
            Long directorId = Long.valueOf(bufferedReader.readLine());

            Company[] companies = directorOfCompanyService.findAllCompaniesByDirectorId(directorId);

            if (companies.length > 0) {
                System.out.println("All companies of the director:");
                for (Company company : companies) {
                    System.out.println("ID: " + company.getId());
                    System.out.println("Name: " + company.getName());
                    System.out.println("Employee quantity: " + company.getEmployeeQuantity());
                    System.out.println("Company price: " + company.getCompanyPrice());
                }
            } else {
                System.out.println("No companies found for the director.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while displaying all companies of the director: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying all companies of the director: " + e.getMessage());
        }
    }

    public void displayAllDirectorsInCompany(BufferedReader bufferedReader) {
        try {
            displayAllCompaniesIds();
            System.out.println("Enter the ID of the company:");
            Long companyId = Long.valueOf(bufferedReader.readLine());

            Director[] directors = directorOfCompanyService.findAllDirectorsByCompanyId(companyId);

            if (directors.length > 0) {
                System.out.println("All directors in the company:");
                for (Director director : directors) {
                    System.out.println("ID: " + director.getId());
                    System.out.println("First Name: " + director.getFirstName());
                    System.out.println("Last Name: " + director.getLastName());
                    System.out.println("Age: " + director.getAge());
                }
            } else {
                System.out.println("No directors found in the company.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while displaying all directors in the company: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying all directors in the company: " + e.getMessage());
        }
    }
    private void displayAllDirectorsAndCompanies(BufferedReader bufferedReader) {
        List<Company> companies = companyService.findAll();
        List<Director> directors = directorService.findAll();

        System.out.println("All the companies:");
        for (Company company : companies) {
            System.out.println("ID: " + company.getId());
            System.out.println("Name: " + company.getName());
            System.out.println("Employee quantity: " + company.getEmployeeQuantity());
            System.out.println("Company price: " + company.getCompanyPrice());
            System.out.println("-------------------------------------");
        }

        System.out.println("All the directors:");
        for (Director director : directors) {
            System.out.println("ID: " + director.getId());
            System.out.println("First Name: " + director.getFirstName());
            System.out.println("Last Name: " + director.getLastName());
            System.out.println("Age: " + director.getAge());
            System.out.println("-------------------------------------");
        }
    }
}



