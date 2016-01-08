package com.yaps.petstore.server.service.catalog;

import java.util.Collection;

import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.server.service.catalog.Remote;

@Remote
public interface CatalogService {

    /**
     * Given an Category, this method creates the category.
     *
     * @param categoryDTO
     * @return CategoryDTO category data
     * @throws CreateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data or password is found
     */
	CategoryDTO createCategory(CategoryDTO categoryDTO) throws CreateException, CheckException;
	
	CategoryDTO findCategory(String categoryId) throws FinderException, CheckException;

	void deleteCategory(String categoryId) throws RemoveException, CheckException;

	void updateCategory(CategoryDTO categoryDTO) throws UpdateException, CheckException;

	Collection findCategories() throws FinderException;

	ProductDTO createProduct(ProductDTO productDTO) throws CreateException, CheckException;

	ProductDTO findProduct(String productId) throws FinderException, CheckException;

	void deleteProduct(String productId) throws RemoveException, CheckException;

	void updateProduct(ProductDTO productDTO) throws UpdateException, CheckException;

	Collection findProducts() throws FinderException;

	Collection findProducts(String categoryId) throws FinderException;
	 
	ItemDTO createItem(ItemDTO item) throws CreateException, CheckException;

	ItemDTO findItem(String itemId) throws FinderException, CheckException;

	void deleteItem(String itemId) throws RemoveException, CheckException;
	
	void updateItem(ItemDTO item) throws UpdateException, CheckException;

	public Collection findItems() throws FinderException;

	public Collection findItems(String productId) throws FinderException;

	public Collection searchItems(String keyword) throws FinderException;

}
