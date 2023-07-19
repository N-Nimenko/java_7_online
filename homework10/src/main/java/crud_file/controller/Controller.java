package crud_file.controller;

import crud_file.db.ChiefStorage;
import crud_file.entity.Chief;
import crud_file.entity.Restaurant;
import crud_file.service.Service;
import crud_file.util.AppUtil;

import static crud_file.util.AppUtil.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
    public void start(){
        try{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(ANSI_BLUE  +  "|——————————————————————————————————————————————————————————————————————|");
            System.out.println(ANSI_BLUE  +  "|              Welcome to restaurants administration app               |");
            System.out.println(ANSI_YELLOW + "|——————————————————————————————————————————————————————————————————————|");
            System.out.println(ANSI_YELLOW + "Choose your option:");
        String answer;
        menu();
        while ((answer = bufferedReader.readLine()) != null) {
            crud(bufferedReader, answer);
        }
        } catch(IOException e){
            System.err.println(ANSI_RED + "An error happened: " + e.getMessage());
        }
    }

    public void menu() {
        System.out.println(ANSI_BLUE  +  "***********************************************************************************************");
        System.out.println(ANSI_BLUE  +  "*|—————|————————————————————————|——————————————————————————————|—————————————————————————————|*");
        System.out.println(ANSI_BLUE  +  "*|     |                        |  Restaurants Administration  |                             |*");
        System.out.println(ANSI_BLUE  +  "*|—————|————————————————————————|——————————————————————————————|—————————————————————————————|*");
        System.out.println(ANSI_BLUE  +  "*|  1. |                 * If you want to create a chief, enter 1 *                          |*");
        System.out.println(ANSI_BLUE  +  "*|  2. |                * If you want to create a restaurant, enter 2 *                      |*");
        System.out.println(ANSI_BLUE  +  "*|  3. |           * If you want to add a chief to the restaurant, enter 3 *                 |*");
        System.out.println(ANSI_BLUE  +  "*|  4. |                * If you want to update a restaurant, enter 4 *                      |*");
        System.out.println(ANSI_BLUE  +  "*|  5. |                   * If you want to update a chief, enter 5 *                        |*");
        System.out.println(ANSI_BLUE  +  "*|  6. |                 * If you want to delete a restaurant, enter 6 *                     |*");
        System.out.println(ANSI_BLUE  +  "*|  7. |                   * If you want to delete a chief, enter 7 *                        |*");
        System.out.println(ANSI_YELLOW + "*|  8. |          * If you want to delete a chief from a restaurant, enter 8 *               |*");
        System.out.println(ANSI_YELLOW + "*|  9. |            * If you want to find a certain restaurant, enter 9 *                    |*");
        System.out.println(ANSI_YELLOW + "*|  10.|               * If you want to find a certain chief, enter 10 *                     |*");
        System.out.println(ANSI_YELLOW + "*|  11.|          * If you want to display all restaurants' IDs, enter 11 *                  |*");
        System.out.println(ANSI_YELLOW + "*|  12.|               * If you want to display all chiefs' IDs, enter 12 *                  |*");
        System.out.println(ANSI_YELLOW + "*|  13.|      * If you want to display all the restaurant's of a certain chief, enter 13 *   |*");
        System.out.println(ANSI_YELLOW + "*|  14.| * If you want to display all the chiefs that are working in a restaurant, enter 14 *|*");
        System.out.println(ANSI_YELLOW + "*|  15.|              * If you want to close the application, enter 0 *                      |*");
        System.out.println(ANSI_YELLOW + "*|—————|—————————————————————————————————————————————————————————————————————————————————————|*");
        System.out.println(ANSI_YELLOW + "************************************************************************************************");
        System.out.print(ANSI_RESET);
    }

    public void crud(BufferedReader bufferedReader, String answer) {
        try {
            switch (answer) {
                case "1" -> createChief(bufferedReader);
                case "2" -> createRestaurant(bufferedReader);
                case "3" -> addChiefToRestaurant(bufferedReader);
                case "4" -> updateRestaurant(bufferedReader);
                case "5" -> updateChief(bufferedReader);
                case "6" -> deleteRestaurant(bufferedReader);
                case "7" -> deleteChief(bufferedReader);
                case "8" -> deleteChiefFromRestaurant(bufferedReader);
                case "9" -> findRestaurant(bufferedReader);
                case "10" -> findChief(bufferedReader);
                case "11" -> displayAllRestaurantIds();
                case "12" -> displayAllChiefIds();
                case "13" -> displayAllRestaurantsOfChief(bufferedReader);
                case "14" -> displayAllChiefsOfRestaurant(bufferedReader);
                case "0" -> {
                    System.out.println(ANSI_YELLOW + "Closing the application");
                    System.exit(0);
                }
            }
            menu();
        } catch (NumberFormatException e) {
            throw new RuntimeException(ANSI_RED + "An error occurred: " + e.getMessage());
        }
    }

    public void createChief(BufferedReader bufferedReader) {
        try {
            System.out.println(ANSI_YELLOW + "Enter the first name of the chief:");
            String firstName = bufferedReader.readLine();
            if (firstName == null || firstName.trim().isEmpty()) {
                throw new RuntimeException(ANSI_RED + "Invalid input: first name is empty");
            }
            System.out.println(ANSI_YELLOW + "Enter the last name of the chief:");
            String lastName = bufferedReader.readLine();
            if (lastName == null || lastName.trim().isEmpty()) {
                throw new RuntimeException(ANSI_RED + "Invalid input: last name is empty");
            }
            System.out.println(ANSI_YELLOW + "Enter the number of years the chief has been working:");
            String yearsOfExperienceString = bufferedReader.readLine();
            if (yearsOfExperienceString == null || yearsOfExperienceString.trim().isEmpty()) {
                throw new RuntimeException(ANSI_RED + "Invalid input: years of experience is empty");
            }
            int yearsOfExperience;
            try {
                yearsOfExperience = Integer.parseInt(yearsOfExperienceString);
            } catch (NumberFormatException e) {
                throw new RuntimeException(ANSI_RED + "Invalid input: years of experience is not a valid integer");
            }

            Chief chief = new Chief();
            chief.setId(ChiefStorage.getUUID());
            chief.setFirstName(firstName);
            chief.setLastName(lastName);
            chief.setYearsOfExperience(yearsOfExperience);

            Service.createChief(chief);
            System.out.println(generateTableResponse(ANSI_BLUE + "Chief created with ID: " + chief.getId()));
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while creating the chief: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while creating the chief: " + e.getMessage()));
        }
    }

    private void createRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(ANSI_YELLOW + "Enter the name of the restaurant:");
            String restaurantName = bufferedReader.readLine();
            if (restaurantName == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant name is null");
            }

            System.out.println(ANSI_YELLOW + "Enter the quantity of Michelin stars:");
            String quantityOfMichelinStarsString = bufferedReader.readLine();
            if (quantityOfMichelinStarsString == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: quantity of Michelin stars is null");
            }

            int quantityOfMichelinStars;
            try {
                quantityOfMichelinStars = Integer.parseInt(quantityOfMichelinStarsString);
            } catch (NumberFormatException e) {
                throw new RuntimeException(ANSI_RED + "Invalid input: quantity of Michelin stars is not a valid integer");
            }

            Restaurant restaurant = new Restaurant();
            restaurant.setId(ChiefStorage.getUUID());
            restaurant.setName(restaurantName);
            restaurant.setMichelinStars(quantityOfMichelinStars);

            Service.createRestaurant(restaurant);
            System.out.println(generateTableResponse(ANSI_BLUE + "Restaurant created with ID: " + restaurant.getId()));
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while creating the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while creating the restaurant: " + e.getMessage()));
        }
    }

    private void updateRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_RESTAURANT);
            String restaurantId = bufferedReader.readLine();
            if (restaurantId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant ID is null");
            }
            Restaurant restaurant = Service.findRestaurantById(restaurantId);
            if (restaurant != null) {
                System.out.println(ANSI_YELLOW + "Enter the new name of the restaurant:");
                String restaurantName = bufferedReader.readLine();
                System.out.println(ANSI_YELLOW + "Enter the new quantity of michelin stars:");
                String quantityOfMichelinStars = bufferedReader.readLine();
                restaurant.setName(restaurantName);
                restaurant.setMichelinStars(Integer.parseInt(quantityOfMichelinStars));
                Service.updateRestaurant(restaurant);
                System.out.println(generateTableResponse(ANSI_BLUE + "Restaurant has been updated successfully."));
            } else {
                System.out.println(generateTableResponse(ANSI_RED + "Restaurant not found."));
            }
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while updating the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while updating the restaurant: " + e.getMessage()));
        }
    }

    private void updateChief(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_CHIEF);
            String chiefId = bufferedReader.readLine();
            if (chiefId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: chief ID is null");
            }
            Chief chief = Service.findChiefById(chiefId);
            if (chief != null) {
                System.out.println(ANSI_YELLOW + "Enter the new first name of the chief:");
                String firstName = bufferedReader.readLine();
                System.out.println(ANSI_YELLOW + "Enter the new last name of the chief:");
                String lastName = bufferedReader.readLine();
                System.out.println(ANSI_YELLOW + "Enter the new years of experience");
                String yearsOfExperienceString = bufferedReader.readLine();
                if (yearsOfExperienceString == null) {
                    throw new RuntimeException(ANSI_RED + "Invalid input: years of experience is null");
                }
                int yearsOfExperience;
                try {
                    yearsOfExperience = Integer.parseInt(yearsOfExperienceString);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(ANSI_RED + "Invalid input: years of experience is not a valid integer");
                }
                chief.setFirstName(firstName);
                chief.setLastName(lastName);
                chief.setYearsOfExperience(yearsOfExperience);
                Service.updateChief(chief);
                System.out.println(generateTableResponse(ANSI_BLUE + "Chief has been updated successfully."));
            } else {
                System.out.println(generateTableResponse(ANSI_RED + "Chief not found."));
            }
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while updating the chief: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while updating the chief: " + e.getMessage()));
        }
    }

    private void deleteRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_RESTAURANT);
            String restaurantId = bufferedReader.readLine();
            if (restaurantId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant ID is null");
            }
            Restaurant restaurant = Service.findRestaurantById(restaurantId);
            if (restaurant != null) {
                Service.deleteRestaurantById(restaurantId);
                System.out.println(generateTableResponse(ANSI_BLUE+ "Restaurant has been deleted successfully."));
            } else {
                System.out.println(generateTableResponse(ANSI_RED + "Restaurant not found."));
            }
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while deleting the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while deleting the restaurant: " + e.getMessage()));
        }
    }

    private void deleteChief(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_CHIEF);
            String chiefId = bufferedReader.readLine();
            if (chiefId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: chief ID is null");
            }
            Chief chief = Service.findChiefById(chiefId);
            if (chief != null) {
                Service.deleteChiefById(chiefId);
                System.out.println(generateTableResponse(ANSI_BLUE + "Chief has been deleted successfully."));
            } else {
                System.out.println(generateTableResponse(ANSI_RED + "Chief not found."));
            }
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while deleting the chief: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while deleting the chief: " + e.getMessage()));
        }
    }

    private void deleteChiefFromRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_CHIEF);
            String chiefId = bufferedReader.readLine();
            if (chiefId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: chief ID is null");
            }
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_RESTAURANT);
            String restaurantId = bufferedReader.readLine();
            if (restaurantId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant ID is null");
            }
            Service.deleteChiefFromRestaurant(chiefId, restaurantId);
            System.out.println(generateTableResponse(ANSI_BLUE + "Chief has been fired from the restaurant successfully."));
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while deleting the chief from the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while deleting the chief from the restaurant: " + e.getMessage()));
        }
    }

    private void findRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_RESTAURANT);
            String restaurantId = bufferedReader.readLine();
            if (restaurantId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant ID is null");
            }
            Restaurant restaurant = Service.findRestaurantById(restaurantId);
            if (restaurant != null) {
                String content = ANSI_BLUE +"Restaurant found:\n" +
                        ANSI_BLUE +"ID: " + restaurant.getId() + "\n" +
                        ANSI_YELLOW + "Name: " + restaurant.getName() + "\n" +
                        ANSI_YELLOW +"Quantity of Michelin stars: " + restaurant.getMichelinStars();
                System.out.println(generateTableResponse(content));
            } else {
                System.out.println(generateTableResponse(ANSI_RED + "Restaurant not found."));
            }
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while finding the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while finding the restaurant: " + e.getMessage()));
        }
    }

    private void findChief(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_CHIEF);
            String chiefId = bufferedReader.readLine();
            if (chiefId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: chief ID is null");
            }
            Chief chief = Service.findChiefById(chiefId);
            if (chief != null) {
                String content = ANSI_BLUE + "Chief found:\n" +
                        ANSI_BLUE +"ID: " + chief.getId() + "\n" +
                        ANSI_BLUE +"First Name: " + chief.getFirstName() + "\n" +
                        ANSI_YELLOW +"Last Name: " + chief.getLastName() + "\n" +
                        ANSI_YELLOW + "Years of experience: " + chief.getYearsOfExperience();
                System.out.println(generateTableResponse(content));
            } else {
                System.out.println(generateTableResponse(ANSI_RED + "Chief not found."));
            }
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while finding the chief: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while finding the chief: " + e.getMessage()));
        }
    }

    private void displayAllRestaurantIds() {
        try {
            Restaurant[] restaurants = Service.findAllRestaurants().toArray(new Restaurant[0]);
            StringBuilder sb = new StringBuilder(ANSI_BLUE +"All Restaurant's IDs:\n");
            for (Restaurant restaurant : restaurants) {
                sb.append(restaurant.getId()).append("\n");
            }
            System.out.println(generateTableResponse(sb.toString()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while displaying all restaurant IDs: " + e.getMessage()));
        }
    }

    private void displayAllChiefIds() {
        try {
            Chief[] chiefs = Service.findAllChiefs().toArray(new Chief[0]);
            StringBuilder sb = new StringBuilder(ANSI_BLUE +"All Chief's IDs:\n");
            for (Chief chief : chiefs) {
                sb.append(chief.getId()).append("\n");
            }
            System.out.println(generateTableResponse(sb.toString()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while displaying all chief IDs: " + e.getMessage()));
        }
    }

    private void addChiefToRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_CHIEF);
            String chiefId = bufferedReader.readLine();
            if (chiefId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: chief ID is null");
            }
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_RESTAURANT);
            String restaurantId = bufferedReader.readLine();
            if (restaurantId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant ID is null");
            }
            Service.addChiefToRestaurant(chiefId, restaurantId);
            System.out.println(generateTableResponse(ANSI_BLUE +"Chief has been recruited to the restaurant successfully."));
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while adding the chief to the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while adding the chief to the restaurant: " + e.getMessage()));
        }
    }

    private void displayAllRestaurantsOfChief(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_CHIEF);
            String chiefId = bufferedReader.readLine();
            if (chiefId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: chief ID is null");
            }
            Restaurant[] restaurants = Service.findAllRestaurantsByChiefId(chiefId);
            StringBuilder sb = new StringBuilder(ANSI_BLUE +"All chief's restaurants:\n");
            for (Restaurant restaurant : restaurants) {
                sb.append(ANSI_BLUE +"ID: ").append(restaurant.getId()).append("\n");
                sb.append(ANSI_BLUE +"Name: ").append(restaurant.getName()).append("\n");
                sb.append(ANSI_YELLOW +"Quantity of Michelin stars: ").append(restaurant.getMichelinStars()).append("\n");
                sb.append(ANSI_YELLOW +"------------------------\n");
            }
            System.out.println(generateTableResponse(sb.toString()));
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while displaying all restaurants of the chief: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while displaying all restaurants of the chief: " + e.getMessage()));
        }
    }

    private void displayAllChiefsOfRestaurant(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.ANSI_YELLOW + FIND_BY_ID_MESSAGE_RESTAURANT);
            String restaurantId = bufferedReader.readLine();
            if (restaurantId == null) {
                throw new RuntimeException(ANSI_RED + "Invalid input: restaurant ID is null");
            }
            Chief[] chiefs = Service.findAllChiefsByRestaurantId(restaurantId);
            StringBuilder sb = new StringBuilder("All Chiefs in Restaurants:\n");
            for (Chief chief : chiefs) {
                sb.append(ANSI_BLUE +"ID: ").append(chief.getId()).append("\n");
                sb.append(ANSI_BLUE +"First Name: ").append(chief.getFirstName()).append("\n");
                sb.append(ANSI_YELLOW +"Last Name: ").append(chief.getLastName()).append("\n");
                sb.append(ANSI_YELLOW +"Years of experience: ").append(chief.getYearsOfExperience()).append("\n");
                sb.append(ANSI_YELLOW +"------------------------\n");
            }
            System.out.println(generateTableResponse(sb.toString()));
        } catch (IOException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while displaying all chiefs of the restaurant: " + e.getMessage()));
        } catch (RuntimeException e) {
            System.out.println(generateTableResponse(ANSI_RED + "An error occurred while displaying all chiefs of the restaurant: " + e.getMessage()));
        }
    }

    private String generateTableResponse(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BLUE + "***********************************************************************************************\n");
        sb.append(ANSI_BLUE + "*|—————|————————————————————————|——————————————————————————————|—————————————————————————————|*\n");
        sb.append(content + "\n");
        sb.append(ANSI_YELLOW + "*|—————|————————————————————————|——————————————————————————————|—————————————————————————————|*\n");
        sb.append(ANSI_YELLOW + "***********************************************************************************************\n");
        return sb.toString();
    }
}
