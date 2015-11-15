package com.yaps.petstore.common.dto;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Category. This class only
 * transfers data from a distant service to a client.
 */
public class CategoryDTO implements DataTransfertObject {

	// ======================================
	// = Attributes =
	// ======================================
	private String _id;
	private String _name;
	private String _description;

    // ======================================
    // =            Constructors            =
    // ======================================
    public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoryDTO(String id, String name, String description) {
		this._id = id;
		this._name = name;
		this._description = description;
	}

	// ======================================
    // =         Getters and Setters        =
    // ======================================
	public void setName(String name) {
		this._name = name;
	}

	public void setDescription(String description) {
		this._description = description;
	}

	public String getName() {
		return _name;
	}

	public String getDescription() {
		return _description;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getId() {
		return _id;
	}

}
