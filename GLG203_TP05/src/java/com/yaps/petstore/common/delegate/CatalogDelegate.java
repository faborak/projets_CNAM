package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.List;

import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.rmi.RMIConstant;
import com.yaps.petstore.server.domain.category.Category;

public class CatalogDelegate implements RMIConstant {

	public static CategoryDTO findCategory(String id)
			throws ObjectNotFoundException, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void createCategory(Object object) throws CheckException,
			CreateException, RemoteException {
		// TODO Auto-generated method stub

	}

	public static void updateCategory(Object object) throws CheckException,
			UpdateException {
		// TODO Auto-generated method stub

	}

	public static ProductDTO findProduct(String id)
			throws ObjectNotFoundException, CheckException, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void createProduct(Object object) throws CheckException,
			CreateException {
		// TODO Auto-generated method stub

	}

	public static void updateProduct(Object object) throws CheckException,
			UpdateException {
		// TODO Auto-generated method stub

	}

	public static ItemDTO findItem(String id) throws ObjectNotFoundException,
			RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void createItem(Object object) throws CheckException,
			CreateException {
		// TODO Auto-generated method stub

	}

	public static void updateItem(Object object) throws CheckException,
			UpdateException {
		// TODO Auto-generated method stub

	}

	public static List<Category> findCategories()
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void deleteCategory(String string) {
		// TODO Auto-generated method stub

	}

	public static List<Category> findProducts() throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void deleteProduct(String string) {
		// TODO Auto-generated method stub

	}

	public static List<Category> findItems() throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void deleteItem(String string) {
		// TODO Auto-generated method stub
		
	}

}
