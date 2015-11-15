package com.yaps.petstore.server.service.catalog;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.CustomerDTO;
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
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.domain.product.Product;
import com.yaps.petstore.server.domain.product.ProductDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

public class CatalogService extends AbstractRemoteService implements
		CatalogServiceRemote {

	// ======================================
	// = Attributes =
	// ======================================
	private static final CategoryDAO _cdao = new CategoryDAO();
	private static final ProductDAO _pdao = new ProductDAO();
	private static final ItemDAO _idao = new ItemDAO();

	// ======================================
	// = Constructors =
	// ======================================
	public CatalogService() throws RemoteException {
	}

	// ======================================
	// = Business methods =
	// ======================================
	public CategoryDTO createCategory(CategoryDTO categoryDTO)
			throws CreateException, CheckException, RemoteException {
		final String mname = "createCategory";
		Trace.entering(_cname, mname, categoryDTO);

		if (categoryDTO == null)
			throw new CreateException("Category object is null");

		// Transforms DTO into domain object
		final Category category = new Category(categoryDTO.getId(),
				categoryDTO.getName(), categoryDTO.getDescription());
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());

		category.checkData();

		// Creates the object
		_cdao.insert(category);

		// Transforms domain object into DTO
		final CategoryDTO result = transformCategory2DTO(category);

		Trace.exiting(_cname, mname, result);

		return result;
	}

	public CategoryDTO findCategory(String categoryId) throws FinderException,
			CheckException, RemoteException {
		final String mname = "findCategory";
		Trace.entering(_cname, mname, categoryId);

		checkId(categoryId);
		// Finds the object
		final Category category = (Category) _cdao.findByPrimaryKey(categoryId);

		// Transforms domain object into DTO
		final CategoryDTO categoryDTO = transformCategory2DTO(category);

		Trace.exiting(_cname, mname, categoryDTO);
		return categoryDTO;
	}

	public void deleteCategory(String categoryId) throws RemoveException,
			CheckException, RemoteException {
		final String mname = "deleteCategory";
		Trace.entering(_cname, mname, categoryId);

		checkId(categoryId);

		// Checks if the object exists
		try {
			_cdao.findByPrimaryKey(categoryId);
		} catch (FinderException e) {
			throw new RemoveException("Category must exist to be deleted");
		}

		// Deletes the object
		try {
			_cdao.remove(categoryId);
		} catch (ObjectNotFoundException e) {
			throw new RemoveException("Category must exist to be deleted");
		}
	}

	public void updateCategory(CategoryDTO categoryDTO) throws UpdateException,
			CheckException, RemoteException {
		final String mname = "updateCategory";
		Trace.entering(_cname, mname, categoryDTO);

		if (categoryDTO == null)
			throw new UpdateException("Category object is null");

		checkId(categoryDTO.getId());

		final Category category;

		// Checks if the object exists
		try {
			category = (Category) _cdao.findByPrimaryKey(categoryDTO.getId());
		} catch (FinderException e) {
			throw new UpdateException("Category must exist to be updated");
		}

		// Transforms DTO into domain object
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());

		category.checkData();

		// Updates the object
		try {
			_cdao.update(category);
		} catch (ObjectNotFoundException e) {
			throw new UpdateException("Category must exist to be updated");
		}
	}

	public Collection findCategories() throws FinderException, RemoteException {
		final String mname = "findCategories";
		Trace.entering(_cname, mname);

		// Finds all the objects
		final Collection categories = _cdao.selectAll();

		// Transforms domain objects into DTOs
		final Collection categoriesDTO = transformCategories2DTOs(categories);

		Trace.exiting(_cname, mname, new Integer(categoriesDTO.size()));
		return categoriesDTO;
	}

	// ======================================
	// = Product
	// ======================================

	public ProductDTO createProduct(ProductDTO productDTO)
			throws CreateException, CheckException, RemoteException {
		final String mname = "createProduct";
		Trace.entering(_cname, mname, productDTO);

		if (productDTO == null)
			throw new CreateException("Product object is null");

		// Transforms DTO into domain object
		final Product product = new Product(productDTO.getId(),
				productDTO.getName(), productDTO.getDescription(),
				new Category(productDTO.getCategoryId()));

		product.checkData();

		// Creates the object
		_pdao.insert(product);

		// Transforms domain object into DTO
		final ProductDTO result = transformProduct2DTO(product);

		Trace.exiting(_cname, mname, result);
		return result;
	}

	public ProductDTO findProduct(String productId) throws FinderException,
			CheckException, RemoteException {
		final String mname = "findProduct";
		Trace.entering(_cname, mname, productId);

		checkId(productId);
		// Finds the object
		final Product product = (Product) _pdao.findByPrimaryKey(productId);

		// Transforms domain object into DTO
		final ProductDTO productDTO = transformProduct2DTO(product);

		Trace.exiting(_cname, mname, productDTO);
		return productDTO;
	}

	public void deleteProduct(String productId) throws RemoveException,
			CheckException, RemoteException {
		final String mname = "deleteProduct";
		Trace.entering(_cname, mname, productId);

		checkId(productId);

		// Checks if the object exists
		try {
			_pdao.findByPrimaryKey(productId);
		} catch (FinderException e) {
			throw new RemoveException("Product must exist to be deleted");
		}

		// Deletes the object
		try {
			_pdao.remove(productId);
		} catch (ObjectNotFoundException e) {
			throw new RemoveException("Product must exist to be deleted");
		}
	}

	public void updateProduct(ProductDTO productDTO) throws UpdateException,
			CheckException, RemoteException {
		final String mname = "updateProduct";
		Trace.entering(_cname, mname, productDTO);

		if (productDTO == null)
			throw new UpdateException("Product object is null");

		checkId(productDTO.getId());

		final Product product;

		// Checks if the object exists
		try {
			product = (Product) _pdao.findByPrimaryKey(productDTO.getId());
		} catch (FinderException e) {
			throw new UpdateException("Product must exist to be updated");
		}

		// Transforms DTO into domain object
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setCategory(new Category(productDTO.getCategoryId()));

		product.checkData();

		// Updates the object
		try {
			_pdao.update(product);
		} catch (ObjectNotFoundException e) {
			throw new UpdateException("Product must exist to be updated");
		}
	}

	public Collection findProducts() throws FinderException, RemoteException {
		final String mname = "findCategorys";
		Trace.entering(_cname, mname);

		// Finds all the objects
		final Collection categorys = _cdao.selectAll();

		// Transforms domain objects into DTOs
		final Collection categorysDTO = transformCategories2DTOs(categorys);

		Trace.exiting(_cname, mname, new Integer(categorysDTO.size()));
		return categorysDTO;
	}

	public ItemDTO createItem(ItemDTO itemDTO) throws CreateException,
			CheckException, RemoteException {
		final String mname = "createItem";
		Trace.entering(_cname, mname, itemDTO);

		if (itemDTO == null)
			throw new CreateException("Item object is null");

		// Transforms DTO into domain object
		final Item item = new Item(itemDTO.getId(), itemDTO.getName(),
				itemDTO.getUnitCost(), new Product(itemDTO.getProductId(),
				itemDTO.getProductName(), null, null));

		item.checkData();

		// Creates the object
		_idao.insert(item);

		// Transforms domain object into DTO
		final ItemDTO result = transformItem2DTO(item);

		Trace.exiting(_cname, mname, result);
		return result;
	}

	public ItemDTO findItem(String itemId) throws FinderException,
			CheckException, RemoteException {
        final String mname = "findItem";
        Trace.entering(_cname, mname, itemId);

    	checkId(itemId);
        // Finds the object
        final Item item = (Item)_idao.findByPrimaryKey(itemId);

        // Transforms domain object into DTO
        final ItemDTO itemDTO = transformItem2DTO(item);

        Trace.exiting(_cname, mname, itemDTO);
        return itemDTO;
	}

	public void deleteItem(String itemId) throws RemoveException,
			CheckException, RemoteException {
        final String mname = "deleteItem";
        Trace.entering(_cname, mname, itemId);

    	checkId(itemId);

        // Checks if the object exists
        try {
        	_idao.findByPrimaryKey(itemId);
        } catch (FinderException e) {
            throw new RemoveException("Item must exist to be deleted");
        }

        // Deletes the object
        try {
        	_idao.remove(itemId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Item must exist to be deleted");
        }
	}

	public void updateItem(ItemDTO itemDTO) throws UpdateException,
			CheckException, RemoteException {
	      final String mname = "updateItem";
	        Trace.entering(_cname, mname, itemDTO);

	        if (itemDTO == null)
	            throw new UpdateException("Item object is null");

	    	checkId(itemDTO.getId());

	    	final Item item;

	        // Checks if the object exists
	        try {
	        	item = (Item)_idao.findByPrimaryKey(itemDTO.getId());
	        } catch (FinderException e) {
	            throw new UpdateException("Item must exist to be updated");
	        }

	        // Transforms DTO into domain object
	        item.setName(itemDTO.getName());
	        item.setUnitCost(itemDTO.getUnitCost());
	        item.setProduct(new Product(itemDTO.getProductId(), itemDTO.getProductName(), null, null));

	        item.checkData();

	        // Updates the object
	        try {
	        	_idao.update(item);
	        } catch (ObjectNotFoundException e) {
	            throw new UpdateException("Item must exist to be updated");
	        }
	}

	public Collection findItems() throws FinderException, RemoteException {
        final String mname = "findItems";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Collection items = _idao.selectAll();

        // Transforms domain objects into DTOs
        final Collection itemsDTO = transformItems2DTOs(items);

        Trace.exiting(_cname, mname, new Integer(itemsDTO.size()));
        return itemsDTO;
	}

	// ======================================
	// = Private Methods =
	// ======================================
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

	private ProductDTO transformProduct2DTO(final Product product) {
		final ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setCategoryName(product.getCategory().getName());
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
	
	private ItemDTO transformItem2DTO(final Item item) {
		final ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(item.getId());
		itemDTO.setName(item.getName());
		itemDTO.setUnitCost(item.getUnitCost());
		itemDTO.setProductId(item.getProduct().getId());
		itemDTO.setProductName(item.getProduct().getName());
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
	 * @return a unique identifer
	 */
	public final String getUniqueId(String string) {
		return _cdao.getUniqueId();
	}

}
