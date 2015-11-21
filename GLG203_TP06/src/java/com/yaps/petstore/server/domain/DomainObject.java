package com.yaps.petstore.server.domain;

/**
 * Every domain object should extend this abstract class.
 */
public abstract class DomainObject {

    // ======================================
    // =             Attributes             =
    // ======================================

    // Every domain object has a unique identifier.
    private String _id;

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

}
