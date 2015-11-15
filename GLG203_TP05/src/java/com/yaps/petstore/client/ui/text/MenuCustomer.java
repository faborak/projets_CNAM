package com.yaps.petstore.client.ui.text;

import com.yaps.petstore.common.delegate.CustomerDelegate;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.DuplicateKeyException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

/**
 * This text menu is used by the employee to manage Customer information. It can findByPrimaryKey, create,
 * remove and update Customers using the CustomerDelegate.
 *
 * @see CustomerDelegate
 */
public final class MenuCustomer extends AbstractTextMenu {

    // ======================================
    // =             Main Method            =
    // ======================================
    public static void main(final String[] args) {
        show();
    }

    // ======================================
    // =        Presentation Methods        =
    // ======================================
    private static void show() {

        try {

            final BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
            String menuChoice;
            boolean quitNow = false;

            do {

                System.out.println("\n\t------------------  Y A P S  -----------------");
                System.out.println("\t--------------- Pet Store Customer -------------\n\n");
                System.out.println("\t(0) - Quit");
                System.out.println("\t----------------------");
                System.out.println("\t(1) - Create Customer");
                System.out.println("\t(2) - Find Customer");
                System.out.println("\t(3) - Delete Customer");
                System.out.println("\t(4) - Update Customer");
                System.out.println("\t(5) - Find All");
                System.out.println("\t----------------------");

                System.out.print("\n\tEnter your choice : ");

                menuChoice = msgStream.readLine();
                if (menuChoice != null && menuChoice.trim().length() != 0) {

                    switch (Integer.parseInt(menuChoice)) {

                        case 1:
                            createCustomer();
                            break;
                        case 2:
                            findCustomer();
                            break;
                        case 3:
                            deleteCustomer();
                            break;
                        case 4:
                            updateCustomer();
                            break;
                        case 5:
                            findCustomers();
                            break;

                        case 0:
                            quitNow = true;
                            System.out.println("\n\tPress enter to quit...");
                            break;

                        default:
                            System.out.println("\n\tError : Invalid menu choice !!!. Press enter to enter another choice...");
                            break;
                    }
                }

                // Pause
                msgStream.readLine();
                clearScreen();
            } while (!quitNow);
        } catch (Exception e) {
            System.out.println("\n\tMenu Exception : \n\t" + e.getMessage());
        }
    }

    private static void createCustomer() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Create a Customer   ---");
            System.out.println("\tUsage   : Customer Id - Firstname - Lastname - Telephone - Street1      - Street2      - City     - State - Zipcode - Country");
            System.out.println("\tExample : Smith01     - John      - Smith    - 357 1541  - Ritherdon Rd - Hygh Wicombe - New York - NY    - 1354    - USA\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(10);
        }

        try {
            final CustomerDTO customerDTO = new CustomerDTO(line.nextToken().trim(), line.nextToken().trim(), line.nextToken().trim());
            customerDTO.setTelephone(line.nextToken().trim());
            customerDTO.setStreet1(line.nextToken().trim());
            customerDTO.setStreet2(line.nextToken().trim());
            customerDTO.setCity(line.nextToken().trim());
            customerDTO.setState(line.nextToken().trim());
            customerDTO.setZipcode(line.nextToken().trim());
            customerDTO.setCountry(line.nextToken().trim());
            CustomerDelegate.createCustomer(customerDTO);
            System.out.println("\n\tInfo : Customer created. Press enter to continue...");
        } catch (DuplicateKeyException e) {
            System.out.println("\n\tWarning : This Customer already exists !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot create this Customer !!!\n\t" + e.getMessage());
        }


    }

    private static void findCustomer() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Find a Customer   ---");
            System.out.println("\tUsage : Customer Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final CustomerDTO customerDTO = CustomerDelegate.findCustomer(line.nextToken().trim());
            System.out.println("\n" + customerDTO);
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Customer doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Customer !!! \n\t" + e.getMessage());
        }
    }

    private static void findCustomers() {
        System.out.println("\n\n\t---   Find all Customers   ---");

        try {
            // Calls the method that retreives all the data of the object
            final Collection customers = CustomerDelegate.findCustomers();
            for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
                final CustomerDTO customerDTO = (CustomerDTO) iterator.next();
                System.out.println("\n" + customerDTO);
            }
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : No Customer found in the system !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find Customers !!! \n\t" + e.getMessage());
        }
    }

    private static void deleteCustomer() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Delete a Customer   ---");
            System.out.println("\tUsage : Customer Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final CustomerDTO customerDTO = CustomerDelegate.findCustomer(line.nextToken().trim());
            System.out.println("\n" + customerDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to remove this Customer (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                CustomerDelegate.deleteCustomer(customerDTO.getId());
                System.out.println("\n\tInfo : Customer deleted. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Customer not deleted. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Customer doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Customer !!! \n\t" + e.getMessage());
        }

    }

    private static void updateCustomer() {

        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Update a Customer   ---");
            System.out.println("\tUsage : Customer Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final CustomerDTO customerDTO = CustomerDelegate.findCustomer(line.nextToken().trim());
            System.out.println("\n" + customerDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to update this Customer (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                nbArgumentsOK = false;
                while (!nbArgumentsOK) {
                    System.out.println("\tUsage   : Firstname - Lastname - Telephone - Street1      - Street2      - City     - State - Zipcode - Country");
                    System.out.println("\tExample : John      - Smith    - 357 1541  - Ritherdon Rd - Hygh Wicombe - New York - NY    - 1354    - USA\n");
                    line = readsInputLine();
                    nbArgumentsOK = checkNumberOfArguments(9);
                }
                customerDTO.setFirstname(line.nextToken().trim());
                customerDTO.setLastname(line.nextToken().trim());
                customerDTO.setTelephone(line.nextToken().trim());
                customerDTO.setStreet1(line.nextToken().trim());
                customerDTO.setStreet2(line.nextToken().trim());
                customerDTO.setCity(line.nextToken().trim());
                customerDTO.setState(line.nextToken().trim());
                customerDTO.setZipcode(line.nextToken().trim());
                customerDTO.setCountry(line.nextToken().trim());

                CustomerDelegate.updateCustomer(customerDTO);
                System.out.println("\n\tInfo : Customer updated. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Customer not updated. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Customer doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Customer !!! \n\t" + e.getMessage());
        }
    }
}
