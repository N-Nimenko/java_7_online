package hibernate.controller;

import hibernate.entity.Property;
import hibernate.entity.Owner;
import hibernate.service.PropertyService;
import hibernate.service.OwnerService;
import hibernate.service.impl.PropertyServiceImpl;
import hibernate.service.impl.OwnerServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

public class Controller {
    private final OwnerService ownerService = new OwnerServiceImpl();
    private final PropertyService propertyService = new PropertyServiceImpl();

    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to application of finding owners and properties!");
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
        System.out.println("**********************************************************************");
        System.out.println("| Option |              Action                  |     Enter Key       |");
        System.out.println("|--------|--------------------------------------|---------------------|");
        System.out.println("|   1    |    Create an Owner                   |         1           |");
        System.out.println("|   2    |    Create a Property                 |         2           |");
        System.out.println("|   3    |    Add Property to Owner             |         3           |");
        System.out.println("|   4    |    Update a Property                 |         4           |");
        System.out.println("|   5    |    Update an Owner                   |         5           |");
        System.out.println("|   6    |    Update Owner and Property         |         6           |");
        System.out.println("|   7    |    Delete a Property                 |         7           |");
        System.out.println("|   8    |    Delete an Owner                   |         8           |");
        System.out.println("|   9    |    Remove Owner from Property        |         9           |");
        System.out.println("|  10    |    Delete Owner and Property         |        10           |");
        System.out.println("|  11    |    Find a Specific Property          |        11           |");
        System.out.println("|  12    |    Find a Specific Owner             |        12           |");
        System.out.println("|  13    |    List All Properties               |        13           |");
        System.out.println("|  14    |    List All Owners                   |        14           |");
        System.out.println("|  15    |    List IDs of All Properties        |        15           |");
        System.out.println("|  16    |    List IDs of All Owners            |        16           |");
        System.out.println("|  17    |    List Available Owners and         |        17           |");
        System.out.println("|        |    Their Properties                  |                     |");
        System.out.println("|  18    |    List Properties by Owner          |        18           |");
        System.out.println("|  19    |    List Owners by Properties         |        19           |");
        System.out.println("|  20    |    List Owners Sorted by Criteria    |        20           |");
        System.out.println("|  21    |    List Properties Sorted by Criteria|        21           |");
        System.out.println("|   0    |    Close Application                 |         0           |");
        System.out.println("***********************************************************************");
    }

    public void crud(BufferedReader bufferedReader, String answer) {
        try {
            switch (answer) {
                case "1" -> createOwner(bufferedReader);
                case "2" -> createProperty(bufferedReader);
                case "3" -> addPropertyToOwner(bufferedReader);
                case "4" -> updateProperty(bufferedReader);
                case "5" -> updateOwner(bufferedReader);
                case "6" -> updateOwnerAndProperty(bufferedReader);
                case "7" -> deleteProperty(bufferedReader);
                case "8" -> deleteOwner(bufferedReader);
                case "9" -> deleteOwnerFromProperty(bufferedReader);
                case "10" -> deleteOwnerAndProperty(bufferedReader);
                case "11" -> findProperty(bufferedReader);
                case "12" -> findOwner(bufferedReader);
                case "13" -> findAllProperties(bufferedReader);
                case "14" -> findAllOwners(bufferedReader);
                case "15" -> displayAllPropertiesIds();
                case "16" -> displayAllOwnersIds();
                case "17" -> displayAllAvailableOwnersAndProperties(bufferedReader);
                case "18" -> displayAllPropertiesByOwner(bufferedReader);
                case "19" -> displayAllOwnersByProperty(bufferedReader);
                case "20" -> displayAllOwnersSorted(bufferedReader);
                case "21" -> displayAllPropertiesSorted(bufferedReader);
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

    public void createOwner(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the first name of the owner:");
            String firstName = bufferedReader.readLine();
            System.out.println("Enter the last name of the owner:");
            String lastName = bufferedReader.readLine();
            System.out.println("Enter the age of the owner:");
            int age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the id of the owner");
            Long id = Long.valueOf(bufferedReader.readLine());

            Owner owner = new Owner();
            owner.setId(id);
            owner.setFirstName(firstName);
            owner.setLastName(lastName);
            owner.setAge(age);
            ownerService.create(owner);

            System.out.println("Owner has been created successfully with ID: " + owner.getId());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the owner: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: age of the owner should be a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the owner: " + e.getMessage());
        }
    }

    public void createProperty(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the kind of the property:");
            String kindOfProperty = bufferedReader.readLine();
            System.out.println("Enter the rooms quantity of the property:");
            int roomsQuantity = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the square meters of the property");
            int squareMeters = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the id of the property");
            Long id = Long.valueOf(bufferedReader.readLine());

            Property property = new Property();
            property.setId(id);
            property.setKindOfProperty(kindOfProperty);
            property.setRoomsQuantity(roomsQuantity);
            property.setSquareMeters(squareMeters);
            propertyService.create(property);

            System.out.println("Property has been created successfully with ID: " + property.getId());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the property: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while creating the property: " + e.getMessage());
        }
    }

    private void addPropertyToOwner(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the owner:");
            Long ownerId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter the ID of the property:");
            Long propertyId = Long.parseLong(bufferedReader.readLine());

            ownerService.addPropertyToOwner(ownerId, propertyId);
            System.out.println("Property has been added to the owner.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: please enter valid numeric IDs.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while adding property to owner: " + e.getMessage());
        }
    }

    public void updateProperty(BufferedReader bufferedReader) {
        try {
            displayAllPropertiesIds();
            System.out.println("Enter the ID of the property:");
            String propertyId = bufferedReader.readLine();
            if (propertyId == null) {
                throw new RuntimeException("Invalid input: property's id is null");
            }

            Property property = propertyService.findById(Long.valueOf(propertyId));

            if (property != null) {
                System.out.println("Enter the new kind of the property:");
                String newKindOfProperty = bufferedReader.readLine();
                property.setKindOfProperty(newKindOfProperty);
                System.out.println("Enter the new rooms quantity of the property:");
                int newRoomsQuantity = Integer.parseInt(bufferedReader.readLine());
                property.setRoomsQuantity(newRoomsQuantity);
                System.out.println("Enter the new square meters: ");
                int newSquareMeters = Integer.parseInt(bufferedReader.readLine());
                property.setSquareMeters(newSquareMeters);
                propertyService.update(property);

                System.out.println("Property has been updated successfully.");
            } else {
                System.out.println("Property not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the property: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the property: " + e.getMessage());
        }
    }

    public void updateOwner(BufferedReader bufferedReader) {
        try {
            displayAllOwnersIds();
            System.out.println("Enter the ID of the owner:");
            String ownerId = bufferedReader.readLine();
            if (ownerId == null) {
                throw new RuntimeException("Invalid input: owner's id is null");
            }

            Owner owner = ownerService.findById(Long.valueOf(ownerId));

            if (owner != null) {
                System.out.println("Enter the new first name of the owner:");
                String newFirstName = bufferedReader.readLine();
                System.out.println("Enter the new last name of the owner:");
                String newLastName = bufferedReader.readLine();
                System.out.println("Enter the new age of the owner:");
                int newAge = Integer.parseInt(bufferedReader.readLine());

                owner.setFirstName(newFirstName);
                owner.setLastName(newLastName);
                owner.setAge(newAge);
                ownerService.update(owner);

                System.out.println("Owner has been updated successfully.");
            } else {
                System.out.println("Owner not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the owner: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: age of the owner should be a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while updating the owner: " + e.getMessage());
        }
    }

    private void updateOwnerAndProperty(BufferedReader bufferedReader) {
        try {
            displayAllOwnersIds();
            System.out.println("Enter the ID of the owner:");
            String ownerIdStr = bufferedReader.readLine();
            if (ownerIdStr == null) {
                throw new RuntimeException("Invalid input: owner's id is null");
            }

            Long ownerId = Long.valueOf(ownerIdStr);
            Owner owner = ownerService.findById(ownerId);

            if (owner != null) {
                System.out.println("Enter the new first name of the owner:");
                String newFirstName = bufferedReader.readLine();
                System.out.println("Enter the new last name of the owner:");
                String newLastName = bufferedReader.readLine();
                System.out.println("Enter the new age of the owner:");
                int newAge = Integer.parseInt(bufferedReader.readLine());

                owner.setFirstName(newFirstName);
                owner.setLastName(newLastName);
                owner.setAge(newAge);
                ownerService.update(owner);

                System.out.println("Owner has been updated successfully.");
            } else {
                System.out.println("Owner not found.");
                return;
            }
            displayAllPropertiesIds();
            System.out.println("Enter the ID of the property:");
            String propertyIdStr = bufferedReader.readLine();
            if (propertyIdStr == null) {
                throw new RuntimeException("Invalid input: property's id is null");
            }

            Long propertyId = Long.valueOf(propertyIdStr);
            Property property = propertyService.findById(propertyId);

            if (property != null) {
                System.out.println("Enter the new kind of the property:");
                String newKindProperty = bufferedReader.readLine();
                property.setKindOfProperty(newKindProperty);
                System.out.println("Enter the new rooms quantity of the property:");
                int newRoomsQuantity = Integer.parseInt(bufferedReader.readLine());
                property.setRoomsQuantity(newRoomsQuantity);
                System.out.println("Enter the new property square meters: ");
                int newSquareMeters = Integer.parseInt(bufferedReader.readLine());
                property.setSquareMeters(newSquareMeters);
                propertyService.update(property);

                System.out.println("Property has been updated successfully.");
            } else {
                System.out.println("Property not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: property price should be a valid integer.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void deleteProperty(BufferedReader bufferedReader) {
        try {
            displayAllPropertiesIds();
            System.out.println("Enter the ID of the property:");
            String propertyId = bufferedReader.readLine();
            if (propertyId == null) {
                throw new RuntimeException("Invalid input: property's id is null");
            }

            Property property = propertyService.findById(Long.valueOf(propertyId));

            if (property != null) {
                propertyService.delete(Long.valueOf(propertyId));
                System.out.println("Property has been deleted successfully.");
            } else {
                System.out.println("Property not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the property: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the property: " + e.getMessage());
        }
    }

    public void deleteOwner(BufferedReader bufferedReader) {
        try {
            displayAllOwnersIds();
            System.out.println("Enter the ID of the owner:");
            String ownerId = bufferedReader.readLine();
            if (ownerId == null) {
                throw new RuntimeException("Invalid input: owner's id is null");
            }

            Owner owner = ownerService.findById(Long.valueOf(ownerId));

            if (owner != null) {
                ownerService.delete(Long.valueOf(ownerId));
                System.out.println("Owner has been deleted successfully.");
            } else {
                System.out.println("Owner not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the owner: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the owner: " + e.getMessage());
        }
    }

    public void deleteOwnerFromProperty(BufferedReader bufferedReader) {
        try {
            displayAllOwnersIds();
            System.out.println("Enter the ID of the owner:");
            Long ownerId = Long.valueOf(bufferedReader.readLine());

            Owner owner = ownerService.findById(ownerId);

            if (owner != null) {
                Set<Property> properties = owner.getProperties();
                if (!properties.isEmpty()) {
                    for (Property property : properties) {
                        property.getOwners().remove(owner);
                        propertyService.update(property);
                    }
                    System.out.println("Owner has been removed from properties successfully.");
                } else {
                    System.out.println("Owner is not associated with any properties.");
                }
            } else {
                System.out.println("Owner not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the owner from the property: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: please enter a valid number.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the owner from the property: " + e.getMessage());
        }
    }

    public void deleteOwnerAndProperty(BufferedReader bufferedReader) {
        try {
            displayAllOwnersIds();
            System.out.println("Enter the ID of the owner:");
            Long ownerId = Long.valueOf(bufferedReader.readLine());

            Owner owner = ownerService.findById(ownerId);

            if (owner != null) {
                displayAllPropertiesIds();
                System.out.println("Enter the ID of the property:");
                Long propertyId = Long.valueOf(bufferedReader.readLine());

                Property property = propertyService.findById(propertyId);

                if (property != null) {
                    owner.getProperties().remove(property);
                    ownerService.update(owner);
                    System.out.println("Owner's association with the property has been deleted successfully.");
                } else {
                    System.out.println("Property not found.");
                }
            } else {
                System.out.println("Owner not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the owner's association with the property: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: please enter a valid number.");
        } catch (RuntimeException e) {
            System.out.println("An error occurred while deleting the owner's association with the property: " + e.getMessage());
        }
    }

    public void findProperty(BufferedReader bufferedReader) {
        try {
            displayAllPropertiesIds();
            System.out.println("Enter the ID of the property:");
            String propertyId = bufferedReader.readLine();
            if (propertyId == null) {
                throw new RuntimeException("Invalid input: property's id is null");
            }

            Property property = propertyService.findById(Long.valueOf(propertyId));

            if (property != null) {
                System.out.println("Property found:");
                System.out.println("ID: " + property.getId());
                System.out.println("Kind of property: " + property.getKindOfProperty());
                System.out.println("Rooms quantity: " + property.getRoomsQuantity());
                System.out.println("Property square meters: " + property.getSquareMeters());
            } else {
                System.out.println("Property not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding the property: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while finding the property: " + e.getMessage());
        }
    }

    public void findOwner(BufferedReader bufferedReader) {
        try {
            displayAllOwnersIds();
            System.out.println("Enter the ID of the owner:");
            String ownerId = bufferedReader.readLine();
            if (ownerId == null) {
                throw new RuntimeException("Invalid input: owner's id is null");
            }

            Owner owner = ownerService.findById(Long.valueOf(ownerId));

            if (owner != null) {
                System.out.println("Owner found:");
                System.out.println("ID: " + owner.getId());
                System.out.println("First Name: " + owner.getFirstName());
                System.out.println("Last Name: " + owner.getLastName());
                System.out.println("Age: " + owner.getAge());
            } else {
                System.out.println("Owner not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while finding the owner: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("An error occurred while finding the owner: " + e.getMessage());
        }
    }

    private void findAllProperties(BufferedReader bufferedReader) {
        List<Property> properties = propertyService.findAll();
        for (Property property : properties) {
            System.out.println("Property information:");
            System.out.println("ID: " + property.getId());
            System.out.println("Kind of property: " + property.getKindOfProperty());
            System.out.println("Rooms quantity: " + property.getRoomsQuantity());
            System.out.println("Property square meters: " + property.getSquareMeters());
            System.out.println("-------------------------------------");
        }
    }

    private void findAllOwners(BufferedReader bufferedReader) {
        List<Owner> owners = ownerService.findAll();
        for (Owner owner : owners) {
            System.out.println("Owner information:");
            System.out.println("ID: " + owner.getId());
            System.out.println("First Name: " + owner.getFirstName());
            System.out.println("Last Name: " + owner.getLastName());
            System.out.println("Age: " + owner.getAge());
            System.out.println("-------------------------------------");
        }
    }

    public void displayAllPropertiesIds() {
        try {
            Property[] properties = propertyService.findAll().toArray(new Property[0]);
            System.out.println("All Property IDs:");
            for (Property property : properties) {
                System.out.println(property.getId());
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying all property IDs: " + e.getMessage());
        }
    }

    public void displayAllOwnersIds() {
        try {
            Owner[] owners = ownerService.findAll().toArray(new Owner[0]);
            System.out.println("All Owner IDs:");
            for (Owner owner : owners) {
                System.out.println(owner.getId());
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying all owner IDs: " + e.getMessage());
        }
    }
        public void displayAllPropertiesByOwner(BufferedReader bufferedReader) {
            try {
                displayAllOwnersIds();
                System.out.println("Enter the ID of the owner:");
                Long ownerId = Long.valueOf(bufferedReader.readLine());

                List<Property> properties = propertyService.findAllOwnersByProperty(ownerId);

                if (!properties.isEmpty()) {
                    System.out.println("All properties of the owner:");
                    for (Property property : properties) {
                        System.out.println("ID: " + property.getId());
                        System.out.println("Kind of property: " + property.getKindOfProperty());
                        System.out.println("Rooms quantity: " + property.getRoomsQuantity());
                        System.out.println("Property square meters: " + property.getSquareMeters());
                        System.out.println("-------------------------------------");
                    }
                } else {
                    System.out.println("No properties found for the owner.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while finding all properties of the owner: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: owner ID should be a valid number.");
            } catch (RuntimeException e) {
                System.out.println("An error occurred while finding all properties of the owner: " + e.getMessage());
            }
        }

        public void displayAllOwnersSorted(BufferedReader bufferedReader) {
            try {
                System.out.println("Enter the property to sort by (id/first_name/last_name/age):");
                String sortBy = bufferedReader.readLine();
                System.out.println("Enter 'true' for ascending or 'false' for descending:");
                boolean ascending = Boolean.parseBoolean(bufferedReader.readLine());

                List<Owner> owners = ownerService.findAllOwnersSorted(sortBy, ascending);

                if (!owners.isEmpty()) {
                    System.out.println("All owners sorted:");
                    for (Owner owner : owners) {
                        System.out.println("ID: " + owner.getId());
                        System.out.println("First Name: " + owner.getFirstName());
                        System.out.println("Last Name: " + owner.getLastName());
                        System.out.println("Age: " + owner.getAge());
                        System.out.println("-------------------------------------");
                    }
                } else {
                    System.out.println("No owners found.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while finding and sorting owners: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("An error occurred while finding and sorting owners: " + e.getMessage());
            }
        }

        public void displayAllOwnersByProperty(BufferedReader bufferedReader) {
            try {
                displayAllPropertiesIds();
                System.out.println("Enter the ID of the property:");
                Long propertyId = Long.valueOf(bufferedReader.readLine());

                List<Owner> owners = ownerService.findAllPropertiesByOwner(propertyId);

                if (!owners.isEmpty()) {
                    System.out.println("All owners of the property:");
                    for (Owner owner : owners) {
                        System.out.println("ID: " + owner.getId());
                        System.out.println("First Name: " + owner.getFirstName());
                        System.out.println("Last Name: " + owner.getLastName());
                        System.out.println("Age: " + owner.getAge());
                        System.out.println("-------------------------------------");
                    }
                } else {
                    System.out.println("No owners found for the property.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while finding all owners of the property: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: property ID should be a valid number.");
            } catch (RuntimeException e) {
                System.out.println("An error occurred while finding all owners of the property: " + e.getMessage());
            }
        }

        public void displayAllPropertiesSorted(BufferedReader bufferedReader) {
            try {
                System.out.println("Enter the owner to sort by (id/first_name/last_name/age):");
                String sortBy = bufferedReader.readLine();
                System.out.println("Enter 'true' for ascending or 'false' for descending:");
                boolean ascending = Boolean.parseBoolean(bufferedReader.readLine());

                List<Property> properties = propertyService.findAllPropertiesSorted(sortBy, ascending);

                if (!properties.isEmpty()) {
                    System.out.println("All properties sorted:");
                    for (Property property : properties) {
                        System.out.println("ID: " + property.getId());
                        System.out.println("Kind of property: " + property.getKindOfProperty());
                        System.out.println("Rooms quantity: " + property.getRoomsQuantity());
                        System.out.println("Property square meters: " + property.getSquareMeters());
                        System.out.println("-------------------------------------");
                    }
                } else {
                    System.out.println("No properties found.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while finding and sorting properties: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("An error occurred while finding and sorting properties: " + e.getMessage());
            }
        }
    public void displayAllAvailableOwnersAndProperties(BufferedReader bufferedReader){
        try {
            List<Owner> owners = ownerService.findAll();

            System.out.println("Available Owners and Properties:");

            for (Owner owner : owners) {
                System.out.println("Owner information:");
                System.out.println("ID: " + owner.getId());
                System.out.println("First Name: " + owner.getFirstName());
                System.out.println("Last Name: " + owner.getLastName());
                System.out.println("Age: " + owner.getAge());
                System.out.println("Properties owned:");

                List<Property> ownedProperties = propertyService.findAllOwnersByProperty(owner.getId());
                if (!ownedProperties.isEmpty()) {
                    for (Property property : ownedProperties) {
                        System.out.println("Property ID: " + property.getId());
                        System.out.println("Kind of property: " + property.getKindOfProperty());
                        System.out.println("Rooms quantity: " + property.getRoomsQuantity());
                        System.out.println("Property square meters: " + property.getSquareMeters());
                        System.out.println("-----------------------");
                    }
                } else {
                    System.out.println("No properties owned.");
                }

                System.out.println("-------------------------------------");
            }
        } catch (RuntimeException e) {
            System.out.println("An error occurred while displaying available owners and properties: " + e.getMessage());
        }
    }

}



