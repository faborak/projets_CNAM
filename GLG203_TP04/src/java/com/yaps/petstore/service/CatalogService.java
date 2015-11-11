package com.yaps.petstore.service;

import com.yaps.petstore.domain.category.Category;
import com.yaps.petstore.domain.category.CategoryDAO;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.domain.customer.CustomerDAO;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.item.ItemDAO;
import com.yaps.petstore.domain.product.Product;
import com.yaps.petstore.domain.product.ProductDAO;
import com.yaps.petstore.exception.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is a facade for all catalog services.
 */
public final class CatalogService {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final CategoryDAO _categoryDAO = new CategoryDAO();
    private static final ProductDAO _productDAO = new ProductDAO();
    private static final ItemDAO _itemDAO = new ItemDAO();

    // ======================================
    // =      Category Business methods     =
    // ======================================
    /**
     * Given a Category object, this method creates a Category.
     *
     * @param category cannot be null.
     * @return the created Category
     * @throws DuplicateKeyException is thrown if an object with the same id
     *                               already exists in the system
     * @throws CreateException       is thrown if a DomainException is caught
     *                               or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     */
    public Category createCategory(final Category category) throws CreateException, CheckException {
        if (category == null)
            throw new CreateException("Category object is null");
        category.checkData();
        // Creates the object
        _categoryDAO.insert(category);
        return category;
    }

    /**
     * Given an id this method uses the Category domain object to load all the data of this
     * object.
     *
     * @param categoryId identifier
     * @return Category
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     * @throws CheckException          is thrown if a invalid data is found
     */
    public Category findCategory(final String categoryId) throws FinderException, CheckException {
    	checkId(categoryId);
        // Finds the object
    	return (Category) _categoryDAO.findByPrimaryKey(categoryId);
    }

    /**
     * Given an id, this method finds a Category domain object and then calls its deletion
     * method.
     *
     * @param categoryId identifier
     * @throws RemoveException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
    public void deleteCategory(final String categoryId) throws RemoveException, CheckException {
    	checkId(categoryId);
        // Checks if the object exists
        try {
            _categoryDAO.findByPrimaryKey(categoryId);
        } catch (FinderException e) {
            throw new RemoveException("Category must exist to be deleted");
        }

        // Deletes the object
        try {
        	_categoryDAO.remove(categoryId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Category must exist to be deleted");
        }
    }

    /**
     * Given a Category object, this method updates a Category.
     *
     * @param category cannot be null.
     * @throws UpdateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
    public void updateCategory(final Category category) throws UpdateException, CheckException {
        if (category == null)
            throw new UpdateException("Category object is null");
    	checkId(category.getId());
        category.checkData();
        // Updates the object
        try {
        	_categoryDAO.update(category);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Category must exist to be updated");
        }
    }

    /**
     * This method return all the categories from the system. It uses the Category domain object
     * to get the data.
     *
     * @return a collection of Category
     * @throws ObjectNotFoundException is thrown if the collection is empty
     */
    public Collection findCategories() throws FinderException {

        // Finds all the objects
        final Collection categories = _categoryDAO.selectAll();

        return categories;
    }

    // ======================================
    // =      Product Business methods     =
    // ======================================
    /**
     * Given a Product object, this method creates a Product.
     *
     * @param product cannot be null.
     * @return the created Product
     * @throws DuplicateKeyException is thrown if an object with the same id
     *                               already exists in the system
     * @throws CreateException       is thrown if a DomainException is caught
     *                               or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     */
    public Product createProduct(final Product product) throws CreateException, CheckException {
        if (product == null)
            throw new CreateException("Product object is null");

        if (product.getCategory() == null)
            throw new CheckException("Invalid Category");
        product.checkData();
        // Finds the linked object
        try {
            _categoryDAO.findByPrimaryKey(product.getCategory().getId());
        } catch (FinderException e) {
            throw new CreateException("Category must exist to create a product");
        }

        // Creates the object
        _productDAO.insert(product);
        return product;

    }

    /**
     * Given an id this method uses the Product domain object to load all the data of this
     * object.
     *
     * @param productId identifier
     * @return Product
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     * @throws CheckException          is thrown if a invalid data is found
     */
    public Product findProduct(final String productId) throws FinderException, CheckException {
    	checkId(productId);
    	// Finds the object
    	Product product = (Product)_productDAO.findByPrimaryKey(productId);

        // Retreives the data for the linked object
    	Category category = product.getCategory();
    	category = (Category) _categoryDAO.findByPrimaryKey(category.getId());
    	product.setCategory(category);
        return product;
    }

    /**
     * Given an id, this method finds a Product domain object and then calls its deletion
     * method.
     *
     * @param productId identifier
     * @throws RemoveException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
    public void deleteProduct(final String productId) throws RemoveException, CheckException {
    	checkId(productId);
        // Checks if the object exists
        try {
            _productDAO.findByPrimaryKey(productId);
        } catch (FinderException e) {
            throw new RemoveException("Product must exist to be deleted");
        }

        // Deletes the object
        try {
        	_productDAO.remove(productId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Product must exist to be deleted");
        }
    }

    /**
     * Given a Product object, this method updates a Product.
     *
     * @param product cannot be null.
     * @throws UpdateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
    public void updateProduct(final Product product) throws UpdateException, CheckException {
        if (product == null)
            throw new UpdateException("Product object is null");

        if (product.getCategory() == null)
            throw new CheckException("Invalid Category");
    	checkId(product.getId());
        product.checkData();

        // Finds the linked object
        try {
            _categoryDAO.findByPrimaryKey(product.getCategory().getId());
        } catch (FinderException e) {
            throw new UpdateException("Category must exist to update a product");
        }

        // Updates the object
        try {
        	_productDAO.update(product);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Product must exist to be updated");
        }
    }

    /**
     * This method return all the products from the system. It uses the Product domain object
     * to get the data.
     *
     * @return a collection of Product
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     */
    public Collection findProducts() throws FinderException {
        final Collection result = new ArrayList();

        // Finds all the objects
        final Collection products = _productDAO.selectAll();

        // For each object
        for (Iterator iterator = products.iterator(); iterator.hasNext();) {
            final Product product = (Product) iterator.next();

            try {
                // Retreives the data for the linked object
            	Category category = product.getCategory();
            	category = (Category) _categoryDAO.findByPrimaryKey(category.getId());
            	product.setCategory(category);
            } catch (ObjectNotFoundException e) {
                throw new FinderException("Cannot find the category");
            }

            // Adds the object to the collection
            result.add(product);
        }

        return result;
    }

    // ======================================
    // =        Item Business methods       =
    // ======================================
    /**
     * Given a Item object, this method creates a Item.
     *
     * @param item cannot be null.
     * @return the created Item
     * @throws DuplicateKeyException is thrown if an object with the same id
     *                               already exists in the system
     * @throws CreateException       is thrown if a DomainException is caught
     *                               or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     */
    public Item createItem(final Item item) throws CreateException, CheckException {
        if (item == null)
            throw new CreateException("Item object is null");

        if (item.getProduct() == null)
            throw new CheckException("Invalid Product id");

        // Finds the linked object
        try {
            _productDAO.findByPrimaryKey(item.getProduct().getId());
        } catch (FinderException e) {
            throw new CreateException("Product must exist to create an item");
        }

        // Creates the object
        _itemDAO.insert(item);
        return item;
    }

    /**
     * Given an id this method uses the Item domain object to load all the data of this
     * object.
     *
     * @param itemId identifier
     * @return Item
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     * @throws CheckException          is thrown if a invalid data is found
     */
    public Item findItem(final String itemId) throws FinderException, CheckException {
        checkId(itemId);

        // Finds the object
        Item item = (Item)_itemDAO.findByPrimaryKey(itemId);

        // Retreives the data for the linked object
        Product product = (Product)_productDAO.findByPrimaryKey(item.getProduct().getId());
        item.setProduct(product);
        return item;
    }

    /**
     * Given an id, this method finds a Item domain object and then calls its deletion
     * method.
     *
     * @param itemId identifier
     * @throws RemoveException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
    public void deleteItem(final String itemId) throws RemoveException, CheckException {
    	checkId(itemId);

        // Checks if the object exists
        try {
            _itemDAO.findByPrimaryKey(itemId);
        } catch (FinderException e) {
            throw new RemoveException("Item must exist to be deleted");
        }

        // Deletes the object
        try {
        	_itemDAO.remove(itemId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Customer must exist to be deleted");
        }        
    }

    /**
     * Given a Item object, this method updates an Item.
     *
     * @param item cannot be null.
     * @throws UpdateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
    public void updateItem(final Item item) throws UpdateException, CheckException {
        if (item == null)
            throw new UpdateException("Item object is null");

        if (item.getProduct() == null)
            throw new CheckException("Invalid Product");

    	checkId(item.getId());
        item.checkData();
        // Finds the linked object
        try {
            _productDAO.findByPrimaryKey(item.getProduct().getId());
        } catch (FinderException e) {
            throw new UpdateException("Product must exist to update an item");
        }

        // Updates the object
        try {
        	_itemDAO.update(item);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Customer must exist to be updated");
        }
    }

    /**
     * This method return all the items from the system. It uses the Item domain object
     * to get the data.
     *
     * @return a collection of Item
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     */
    public Collection findItems() throws FinderException {
        final Collection result = new ArrayList();

        // Finds all the objects
        final Collection items = _itemDAO.selectAll();

        // For each object
        for (Iterator iterator = items.iterator(); iterator.hasNext();) {
            final Item item = (Item) iterator.next();

            try {
                // Retreives the data for the linked object
                Product product = (Product)_productDAO.findByPrimaryKey(item.getProduct().getId());
                item.setProduct(product);
            } catch (ObjectNotFoundException e) {
                throw new FinderException("Cannot find the product");
            }

            // Adds the object to the collection
            result.add(item);
        }

        return result;
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @param domainClassName name of a domain class (Category, Product or Item)
     * @return a unique identifer
     */
    public final String getUniqueId(final String domainClassName) {
        return _categoryDAO.getUniqueId(domainClassName);
    }

    private void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }
}
