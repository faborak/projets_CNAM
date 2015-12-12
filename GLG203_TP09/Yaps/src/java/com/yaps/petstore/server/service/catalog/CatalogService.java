package com.yaps.petstore.server.service.catalog;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.yaps.petstore.common.delegate.CatalogServiceRemote;
import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.category.Category;
import com.yaps.petstore.server.domain.category.CategoryDAO;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.domain.product.Product;
import com.yaps.petstore.server.domain.product.ProductDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

/**
 * This class is a facade for all catalog services.
 */
public final class CatalogService extends AbstractRemoteService implements CatalogServiceRemote {

	    // ======================================
	    // =             Attributes             =
	    // ======================================
	    private static final CategoryDAO _categoryDAO = new CategoryDAO();
	    private static final ProductDAO _productDAO = new ProductDAO();
	    private static final ItemDAO _itemDAO = new ItemDAO();

	    // ======================================
	    // =            Constructors            =
	    // ======================================
	    public CatalogService() throws RemoteException {
	    }

	    // ======================================
	    // =      Category Business methods     =
	    // ======================================
	    public CategoryDTO createCategory(final CategoryDTO categoryDTO) throws CreateException, CheckException {
	        final String mname = "createCategory";
	        Trace.entering(_cname, mname, categoryDTO);

	        if (categoryDTO == null)
	            throw new CreateException("Category object is null");

	        // Transforms DTO into domain object
	        final Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());

	        category.checkData();
	        // Creates the object
	        _categoryDAO.insert(category);

	        // Transforms domain object into DTO
	        final CategoryDTO result = transformCategory2DTO(category);

	        Trace.exiting(_cname, mname, result);
	        return result;
	    }

	    public CategoryDTO findCategory(final String categoryId) throws FinderException, CheckException {
	        final String mname = "findCategory";
	        Trace.entering(_cname, mname, categoryId);

	        final Category category = (Category) _categoryDAO.findByPrimaryKey(categoryId);

	        // Transforms domain object into DTO
	        final CategoryDTO categoryDTO = transformCategory2DTO(category);

	        Trace.exiting(_cname, mname, categoryDTO);
	        return categoryDTO;
	    }

	    public void deleteCategory(final String categoryId) throws RemoveException, CheckException {
	        final String mname = "deleteCategory";
	        Trace.entering(_cname, mname, categoryId);

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

	    public void updateCategory(final CategoryDTO categoryDTO) throws UpdateException, CheckException {
	        final String mname = "updateCategory";
	        Trace.entering(_cname, mname, categoryDTO);

	        if (categoryDTO == null)
	            throw new UpdateException("Category object is null");

	    	checkId(categoryDTO.getId());
	        Category category = new Category();

	        // Checks if the object exists
	        try {
	            category = (Category) _categoryDAO.findByPrimaryKey(categoryDTO.getId());
	        } catch (FinderException e) {
	            throw new UpdateException("Category must exist to be updated");
	        }

	        // Transforms DTO into domain object
	        category.setName(categoryDTO.getName());
	        category.setDescription(categoryDTO.getDescription());

	        // Updates the object
	        try {
	        	_categoryDAO.update(category);
	        } catch (ObjectNotFoundException e) {
	            throw new UpdateException("Category must exist to be updated");
	        }
	    }

	    public Collection findCategories() throws FinderException {
	        final String mname = "findCategories";
	        Trace.entering(_cname, mname);

	        // Finds all the objects
	        final Collection categories = _categoryDAO.findAll();

	        // Transforms domain objects into DTOs
	        final Collection categoriesDTO = transformCategories2DTOs(categories);

	        Trace.exiting(_cname, mname, new Integer(categoriesDTO.size()));
	        return categoriesDTO;
	    }

	    // ======================================
	    // =      Product Business methods     =
	    // ======================================
	    public ProductDTO createProduct(final ProductDTO productDTO) throws CreateException, CheckException {
	        final String mname = "createProduct";
	        Trace.entering(_cname, mname, productDTO);

	        if (productDTO == null)
	            throw new CreateException("Product object is null");

	        // Finds the linked object
	        Category category = null;
	        try {
	            category = (Category) _categoryDAO.findByPrimaryKey(productDTO.getCategoryId());
	        } catch (FinderException e) {
	            throw new CreateException("Category must exist to create a product");
	        }

	        // Transforms DTO into domain object
	        final Product product = new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), category);

	        // Creates the object
	        _productDAO.insert(product);

	        // Transforms domain object into DTO
	        final ProductDTO result = transformProduct2DTO(product);

	        Trace.exiting(_cname, mname, result);
	        return result;
	    }

	    public ProductDTO findProduct(final String productId) throws FinderException, CheckException {
	        final String mname = "findProduct";
	        Trace.entering(_cname, mname, productId);

	    	checkId(productId);

	        // Finds the object
	    	Product product = null;
//	    	try {
	         product = (Product) _productDAO.findByPrimaryKey(productId);
//	    	} catch (ObjectNotFoundException e){
//	    		throw new FinderException();
//	    	}

	        // Transforms domain object into DTO
	        final ProductDTO productDTO = transformProduct2DTO(product);

	        Trace.exiting(_cname, mname, productDTO);
	        return productDTO;
	    }

	    public void deleteProduct(final String productId) throws RemoveException, CheckException {
	        final String mname = "deleteProduct";
	        Trace.entering(_cname, mname, productId);

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

	    public void updateProduct(final ProductDTO productDTO) throws UpdateException, CheckException {
	        final String mname = "updateProduct";
	        Trace.entering(_cname, mname, productDTO);

	        if (productDTO == null)
	            throw new UpdateException("Product object is null");

	        if (productDTO.getCategoryId() == null)
	            throw new CheckException("Invalid Category");
	    	checkId(productDTO.getId());
	    	
	        Product product = null;

	        // Checks if the object exists
	        try {
	            product = (Product) _productDAO.findByPrimaryKey(productDTO.getId());
	        } catch (FinderException e) {
	            throw new UpdateException("Product must exist to be updated");
	        }

	        // Finds the linked object
	        Category category = null;
	        try {
	            category = (Category)_categoryDAO.findByPrimaryKey(productDTO.getCategoryId());
	        } catch (FinderException e) {
	            throw new UpdateException("Category must exist to update a product");
	        }

	        // Transforms DTO into domain object
	        product.setName(productDTO.getName());
	        product.setDescription(productDTO.getDescription());
	        product.setCategory(category);

	        // Updates the object
	        try {
	        	_productDAO.update(product);
	        } catch (ObjectNotFoundException e) {
	            throw new UpdateException("Product must exist to be updated");
	        }
	    }

	    public Collection findProducts() throws FinderException, RemoteException {
	        final String mname = "findProducts";
	        Trace.entering(_cname, mname);

	        // Finds all the objects
	        final Collection products = _productDAO.findAll();

	        // Transforms domain objects into DTOs
	        Collection productsDTO = transformProducts2DTOs(products);

	        Trace.exiting(_cname, mname, new Integer(productsDTO.size()));
	        return productsDTO;
	    }
	    
		public Collection findProducts(String categoryId) throws FinderException, RemoteException {
			final String mname = "findProducts";
	        Trace.entering(_cname, mname);

	        // Finds all the objects
	        final Collection products = _productDAO.findAll();
	        
	        // remove objects different from categoryId
	        final Collection finalProducts = new ArrayList();
	        for (Object object : products){
	        	Product product = (Product) object;
	        	if (categoryId.equals(product.getCategory().getId())){
	        		finalProducts.add(object);
	        	}
	        }
	        
	        // Transforms domain objects into DTOs
	        Collection productsDTO = transformProducts2DTOs(finalProducts);

	        Trace.exiting(_cname, mname, new Integer(productsDTO.size()));
	        return productsDTO;
		}
	    
	    // ======================================
	    // =        Item Business methods       =
	    // ======================================
	    public ItemDTO createItem(final ItemDTO itemDTO) throws CreateException, CheckException {
	        final String mname = "createItem";
	        Trace.entering(_cname, mname, itemDTO);

	        if (itemDTO == null)
	            throw new CreateException("Item object is null");

	        if (itemDTO.getProductId() == null)
	            throw new CheckException("Invalid Product id");

	        // Finds the linked object
	        Product product = null;
	        try {
	            product = (Product)_productDAO.findByPrimaryKey(itemDTO.getProductId());
	        } catch (FinderException e) {
	            throw new CreateException("Product must exist to create an item");
	        }

	        // Transforms DTO into domain object
	        final Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getUnitCost(), product);
	        item.setImagePath(itemDTO.getImagePath());
	        
	        item.checkData();

	        // Creates the object
	        _itemDAO.insert(item);

	        // Transforms domain object into DTO
	        final ItemDTO result = transformItem2DTO(item);

	        Trace.exiting(_cname, mname, result);
	        return result;
	    }

	    public ItemDTO findItem(final String itemId) throws FinderException, CheckException {
	        final String mname = "findItem";
	        Trace.entering(_cname, mname, itemId);

	        // Finds the object
	        final Item item = (Item)_itemDAO.findByPrimaryKey(itemId);

	        // Retreives the data for the linked object
	        Product product = (Product)_productDAO.findByPrimaryKey(item.getProduct().getId());
	        item.setProduct(product);

	        // Transforms domain object into DTO
	        final ItemDTO itemDTO = transformItem2DTO(item);

	        Trace.exiting(_cname, mname, itemDTO);
	        return itemDTO;
	    }
	    
	    public void deleteItem(final String itemId) throws RemoveException, CheckException {
	        final String mname = "deleteItem";
	        Trace.entering(_cname, mname, itemId);

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

	    public void updateItem(final ItemDTO itemDTO) throws UpdateException, CheckException {
	        final String mname = "updateItem";
	        Trace.entering(_cname, mname, itemDTO);

	        if (itemDTO == null)
	            throw new UpdateException("Item object is null");

	        if (itemDTO.getProductId() == null)
	            throw new CheckException("Invalid Productin in Item");

	        Item item = null;

	        // Checks if the object exists
	        try {
	            item = (Item)_itemDAO.findByPrimaryKey(itemDTO.getId());
	        } catch (FinderException e) {
	            throw new UpdateException("Item must exist to be updated");
	        }

	        // Finds the linked object
	        Product product = null;
	        try {
	            product = (Product)_productDAO.findByPrimaryKey(itemDTO.getProductId());
	        } catch (FinderException e) {
	            throw new UpdateException("Product must exist to update an item");
	        }

	        // Transforms DTO into domain object
	        item.setName(itemDTO.getName());
	        item.setUnitCost(itemDTO.getUnitCost());
	        item.setProduct(product);
	        item.setImagePath(itemDTO.getImagePath());

	        item.checkData();

	        // Updates the object
	        try {
	        	_itemDAO.update(item);
	        } catch (ObjectNotFoundException e) {
	            throw new UpdateException("Item must exist to be updated");
	        }
	    }

	    public Collection findItems() throws FinderException, RemoteException {
	        final String mname = "findItems";
	        Trace.entering(_cname, mname);

	        // Finds all the objects
	        final Collection items = _itemDAO.findAll();

	        // Transforms domain objects into DTOs
	        final Collection itemsDTO = transformItems2DTOs(items);

	        Trace.exiting(_cname, mname, new Integer(itemsDTO.size()));
	        return itemsDTO;
	    }

		public Collection findItems(String productId) throws FinderException, RemoteException {
			final String mname = "findItems";
	        Trace.entering(_cname, mname);

	        // Finds all the objects
	        final Collection items = _itemDAO.findAll();
	        
	     // remove objects different from categoryId
	        final Collection finalItems = new ArrayList();
	        for (Object object : items){
	        	Item item = (Item) object;
	        	if (productId.equals(item.getProduct().getId())){
	        		finalItems.add(object);
	        	}
	        }

	        // Transforms domain objects into DTOs
	        final Collection itemsDTO = transformItems2DTOs(finalItems);

	        Trace.exiting(_cname, mname, new Integer(itemsDTO.size()));
	        return itemsDTO;
		}
		
		public Collection searchItems(String keyword) throws FinderException, RemoteException {
			final String mname = "searchItems";
			Trace.entering(_cname, mname);
			
			// Finds all the objects
	        final Collection items = _itemDAO.search(keyword);
	        
	        // Transforms domain objects into DTOs
	        final Collection itemsDTO = transformItems2DTOs(items);

	        Trace.exiting(_cname, mname, new Integer(itemsDTO.size()));
			
			return itemsDTO;
		}
		
	    // ======================================
	    // =          Private Methods           =
	    // ======================================
	    // Category
	    private CategoryDTO transformCategory2DTO(final Category category) {
	        final CategoryDTO categoryDTO = new CategoryDTO();
	        categoryDTO.setId(category.getId());
	        categoryDTO.setName(category.getName());
	        categoryDTO.setDescription(category.getDescription());
	        return categoryDTO;
	    }

	    private Collection transformCategories2DTOs(final Collection categories) {
	        final Collection categoriesDTO = new ArrayList();
	        for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
	            final Category category = (Category) iterator.next();
	            categoriesDTO.add(transformCategory2DTO(category));
	        }
	        return categoriesDTO;
	    }

	    // Product
	    private ProductDTO transformProduct2DTO(final Product product) {
	        final ProductDTO productDTO = new ProductDTO();
	        productDTO.setId(product.getId());
	        productDTO.setName(product.getName());
	        productDTO.setDescription(product.getDescription());
	        // Retreives the data for the linked object
	        // Finds the linked object
	        Category category = null;
	        try {
	            category = (Category)_categoryDAO.findByPrimaryKey(product.getCategory().getId());
	        } catch (FinderException e) {
	        	// No exception can occur
	        }
	        productDTO.setCategoryId(category.getId());
	        productDTO.setCategoryName(category.getName());
	        return productDTO;
	    }

	    private Collection transformProducts2DTOs(final Collection products) {
	        final Collection productsDTO = new ArrayList();
	        for (Iterator iterator = products.iterator(); iterator.hasNext();) {
	            final Product product = (Product) iterator.next();
	            productsDTO.add(transformProduct2DTO(product));
	        }
	        return productsDTO;
	    }

	    // Item
	    private ItemDTO transformItem2DTO(final Item item) {
	        final ItemDTO itemDTO = new ItemDTO();
	        itemDTO.setId(item.getId());
	        itemDTO.setName(item.getName());
	        itemDTO.setImagePath(item.getImagePath());
	        itemDTO.setUnitCost(item.getUnitCost());
	        // Retreives the data for the linked object
	        Product product = null;
	        try {
	            product = (Product)_productDAO.findByPrimaryKey(item.getProduct().getId());
	        } catch (FinderException e) {
	            // No exception can occur
	        }
	        itemDTO.setProductId(product.getId());
	        itemDTO.setProductName(product.getName());
	        itemDTO.setProductDescription(product.getDescription());
	        return itemDTO;
	    }

	    private Collection transformItems2DTOs(final Collection items) {
	        final Collection itemsDTO = new ArrayList();
	        for (Iterator iterator = items.iterator(); iterator.hasNext();) {
	            final Item item = (Item) iterator.next();
	            itemsDTO.add(transformItem2DTO(item));
	        }
	        return itemsDTO;
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

}
