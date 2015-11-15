package com.yaps.petstore.client.ui.text;

import com.yaps.petstore.common.delegate.CatalogDelegate;
import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.DuplicateKeyException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

/**
 * This text menu is used by the employee to manage Catalog information. It can find, create,
 * remove and update Categories, Products and Items using the CatalogDelegate.
 *
 * @see CatalogDelegate
 */
public final class MenuCatalog extends AbstractTextMenu {

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
                System.out.println("\t--------------- Pet Store Catalog --------------\n\n");
                System.out.println("\t(0) - Quit");
                System.out.println("\t----------------------");
                System.out.println("\tCategory : (10)-Create\t(11)-Find\t(12)-Delete\t(13)-Update\t(14)-Find All");
                System.out.println("\tProduct  : (20)-Create\t(21)-Find\t(22)-Delete\t(23)-Update\t(24)-Find All");
                System.out.println("\tItem     : (30)-Create\t(31)-Find\t(32)-Delete\t(33)-Update\t(34)-Find All");
                System.out.println("\t----------------------");

                System.out.print("\n\tEnter your choice : ");

                menuChoice = msgStream.readLine();
                if (menuChoice != null && menuChoice.trim().length() != 0) {

                    switch (Integer.parseInt(menuChoice)) {

                        case 10:
                            createCategory();
                            break;
                        case 11:
                            findCategory();
                            break;
                        case 12:
                            deleteCategory();
                            break;
                        case 13:
                            updateCategory();
                            break;
                        case 14:
                            findCategories();
                            break;

                        case 20:
                            createProduct();
                            break;
                        case 21:
                            findProduct();
                            break;
                        case 22:
                            deleteProduct();
                            break;
                        case 23:
                            updateProduct();
                            break;
                        case 24:
                            findProducts();
                            break;

                        case 30:
                            createItem();
                            break;
                        case 31:
                            findItem();
                            break;
                        case 32:
                            deleteItem();
                            break;
                        case 33:
                            updateItem();
                            break;
                        case 34:
                            findItems();
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

    private static void createCategory() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Create a Category   ---");
            System.out.println("\tUsage   : Category Id - Name - Descritpion ");
            System.out.println("\tExample : CATFI       - Fish - Swiming animals\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(3);
        }

        try {
            final CategoryDTO categoryDTO = new CategoryDTO(line.nextToken().trim(), line.nextToken().trim(), line.nextToken().trim());
            CatalogDelegate.createCategory(categoryDTO);
            System.out.println("\n\tInfo : Category created. Press enter to continue...");
        } catch (DuplicateKeyException e) {
            System.out.println("\n\tWarning : This Category already exists !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot create this Category !!!\n\t" + e.getMessage());
        }
    }

    private static void createProduct() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Create a Product   ---");
            System.out.println("\tUsage   : Category Id - Product Id - Name        - Descritpion");
            System.out.println("\tExample : CATFI       - PROD1      - Golden Fish - Red fish for your aquariums\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(4);
        }

        try {
            final String categoryId = line.nextToken().trim();
            final ProductDTO productDTO = new ProductDTO(line.nextToken().trim(), line.nextToken().trim(), line.nextToken().trim());
            productDTO.setCategoryId(categoryId);
            CatalogDelegate.createProduct(productDTO);
            System.out.println("\n\tInfo : Product created. Press enter to continue...");
        } catch (DuplicateKeyException e) {
            System.out.println("\n\tWarning : This Product already exists !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot create this Product !!!\n\t" + e.getMessage());
        }
    }

    private static void createItem() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Create a Item   ---");
            System.out.println("\tUsage   : Product Id - Item Id - Name             - Price");
            System.out.println("\tExample : PROD1      - ITEM1   - Male Golden Fish - 10.5\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(4);
        }

        try {
            final String productId = line.nextToken().trim();
            final ItemDTO itemDTO = new ItemDTO(line.nextToken().trim(), line.nextToken().trim(), Double.parseDouble(line.nextToken().trim()));
            itemDTO.setProductId(productId);
            CatalogDelegate.createItem(itemDTO);
            System.out.println("\n\tInfo : Item created. Press enter to continue...");
        } catch (DuplicateKeyException e) {
            System.out.println("\n\tWarning : This Item already exists !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot create this Item !!!\n\t" + e.getMessage());
        }
    }

    private static void findCategory() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Find a Category   ---");
            System.out.println("\tUsage : Category Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final CategoryDTO categoryDTO = CatalogDelegate.findCategory(line.nextToken().trim());
            System.out.println("\n" + categoryDTO);
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Category doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Category !!! \n\t" + e.getMessage());
        }
    }

    private static void findProduct() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Find a Product   ---");
            System.out.println("\tUsage : Product Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final ProductDTO productDTO = CatalogDelegate.findProduct(line.nextToken().trim());

            System.out.println("\n" + productDTO);
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Product doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Product !!! \n\t" + e.getMessage());
        }
    }

    private static void findItem() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Find an Item   ---");
            System.out.println("\tUsage : Item Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final ItemDTO itemDTO = CatalogDelegate.findItem(line.nextToken().trim());

            System.out.println("\n" + itemDTO);
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Item doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Item !!! \n\t" + e.getMessage());
        }
    }

    private static void findCategories() {
        System.out.println("\n\n\t---   Find all Categories   ---");

        try {
            // Calls the method that retreives all the data of the object
            final Collection categories;
            categories = CatalogDelegate.findCategories();
            for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
                final CategoryDTO categoryDTO = (CategoryDTO) iterator.next();
                System.out.println("\n" + categoryDTO);
            }
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : No Category found in the system !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find Categories !!! \n\t" + e.getMessage());
        }
    }

    private static void findProducts() {
        System.out.println("\n\n\t---   Find all Products   ---");

        try {
            // Calls the method that retreives all the data of the object
            final Collection products;
            products = CatalogDelegate.findProducts();
            for (Iterator iterator = products.iterator(); iterator.hasNext();) {
                final ProductDTO productDTO = (ProductDTO) iterator.next();
                System.out.println("\n" + productDTO);
            }
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : No Product found in the system !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find Products !!! \n\t" + e.getMessage());
        }

    }

    private static void findItems() {
        System.out.println("\n\n\t---   Find all Items   ---");

        try {
            // Calls the method that retreives all the data of the object
            final Collection items;
            items = CatalogDelegate.findItems();
            for (Iterator iterator = items.iterator(); iterator.hasNext();) {
                final ItemDTO itemDTO = (ItemDTO) iterator.next();
                System.out.println("\n" + itemDTO);
            }
            System.out.println("\n\tPress enter to continue...");
        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : No Item found in the system !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find Items !!! \n\t" + e.getMessage());
        }

    }

    private static void deleteCategory() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Delete a Category   ---");
            System.out.println("\tUsage : Category Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final CategoryDTO categoryDTO = CatalogDelegate.findCategory(line.nextToken().trim());
            System.out.println("\n" + categoryDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to remove this Category (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                CatalogDelegate.deleteCategory(categoryDTO.getId());
                System.out.println("\n\tInfo : Category deleted. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Category not deleted. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Category doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Category !!! \n\t" + e.getMessage());
        }

    }

    private static void deleteProduct() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Delete a Product   ---");
            System.out.println("\tUsage : Product Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final ProductDTO productDTO = CatalogDelegate.findProduct(line.nextToken().trim());
            System.out.println("\n" + productDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to remove this Product (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                CatalogDelegate.deleteProduct(productDTO.getId());
                System.out.println("\n\tInfo : Product deleted. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Product not deleted. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Product doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Product !!! \n\t" + e.getMessage());
        }

    }

    private static void deleteItem() {
        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Delete a Item   ---");
            System.out.println("\tUsage : Item Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final ItemDTO itemDTO = CatalogDelegate.findItem(line.nextToken().trim());
            System.out.println("\n" + itemDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to remove this Item (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                CatalogDelegate.deleteItem(itemDTO.getId());
                System.out.println("\n\tInfo : Item deleted. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Item not deleted. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Item doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Item !!! \n\t" + e.getMessage());
        }

    }

    private static void updateCategory() {

        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Update a Category   ---");
            System.out.println("\tUsage : Cateogy Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final CategoryDTO categoryDTO = CatalogDelegate.findCategory(line.nextToken().trim());
            System.out.println("\n" + categoryDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to update this Category (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                nbArgumentsOK = false;
                while (!nbArgumentsOK) {
                    System.out.println("\tUsage   : Name - Descritpion ");
                    System.out.println("\tExample : Fish - Swiming animals\n");
                    line = readsInputLine();
                    nbArgumentsOK = checkNumberOfArguments(2);
                }
                categoryDTO.setName(line.nextToken().trim());
                categoryDTO.setDescription(line.nextToken().trim());

                CatalogDelegate.updateCategory(categoryDTO);
                System.out.println("\n\tInfo : Category updated. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Category not updated. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Category doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Category !!! \n\t" + e.getMessage());
        }
    }

    private static void updateProduct() {

        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Update a Product   ---");
            System.out.println("\tUsage : Product Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final ProductDTO productDTO = CatalogDelegate.findProduct(line.nextToken().trim());
            System.out.println("\n" + productDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to update this Product (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                nbArgumentsOK = false;
                while (!nbArgumentsOK) {
                    System.out.println("\tUsage   : Category Id - Name - Descritpion");
                    System.out.println("\tExample : CATFI       - Fish - Swiming animals\n");
                    line = readsInputLine();
                    nbArgumentsOK = checkNumberOfArguments(3);
                }
                productDTO.setCategoryId(line.nextToken().trim());
                productDTO.setName(line.nextToken().trim());
                productDTO.setDescription(line.nextToken().trim());

                CatalogDelegate.updateProduct(productDTO);
                System.out.println("\n\tInfo : Product updated. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Product not updated. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Product doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Product !!! \n\t" + e.getMessage());
        }
    }

    private static void updateItem() {

        boolean nbArgumentsOK = false;
        while (!nbArgumentsOK) {
            System.out.println("\n\n\t---   Update a Item   ---");
            System.out.println("\tUsage : Item Id\n");

            // Reads the line entered by the user
            line = readsInputLine();
            nbArgumentsOK = checkNumberOfArguments(1);
        }

        try {
            // Calls the method that retreives all the data of the object
            final ItemDTO itemDTO = CatalogDelegate.findItem(line.nextToken().trim());
            System.out.println("\n" + itemDTO + '\n');

            // Deletes the displayed object
            nbArgumentsOK = false;
            while (!nbArgumentsOK) {
                System.out.print("\tDo you want to update this Item (y/n) : ");
                line = readsInputLine();
                nbArgumentsOK = checkNumberOfArguments(1);
            }

            if ("y".equalsIgnoreCase(line.nextToken().trim())) {
                nbArgumentsOK = false;
                while (!nbArgumentsOK) {
                    System.out.println("\tUsage   : Product Id - Name             - Price");
                    System.out.println("\tExample : PROD1      - Male Golden Fish - 10.5\n");
                    line = readsInputLine();
                    nbArgumentsOK = checkNumberOfArguments(3);
                }
                itemDTO.setProductId(line.nextToken().trim());
                itemDTO.setName(line.nextToken().trim());
                itemDTO.setUnitCost(Double.parseDouble(line.nextToken().trim()));

                CatalogDelegate.updateItem(itemDTO);
                System.out.println("\n\tInfo : Item updated. Press enter to continue...");
            } else {
                System.out.println("\n\tInfo : Item not updated. Press enter to continue...");
            }

        } catch (ObjectNotFoundException e) {
            System.out.println("\n\tWarning : This Item doesn't exist !");
        } catch (Exception e) {
            System.out.println("\n\tError : Cannot find this Item !!! \n\t" + e.getMessage());
        }
    }
}
