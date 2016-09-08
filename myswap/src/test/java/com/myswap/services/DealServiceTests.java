package com.myswap.services;

import com.myswap.exceptions.DealInsertException;
import com.myswap.exceptions.DealNotFoundException;
import com.myswap.models.Deal;

import junit.framework.TestCase;

public final class DealServiceTests extends TestCase {

	public DealServiceTests(final String s) {
		super(s);
	}

	DealService dealService = new DealService();

	public void testFinddealByIdWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			dealService.findDeal(-1);
			fail("Object with unknonw id should not be found");
		} catch (DealNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			dealService.findDeal(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (DealNotFoundException e) {
		}

		// Finds an object with a null identifier
		try {
			dealService.findDeal(null);
			fail("Object with null id should not be found");
		} catch (DealNotFoundException e) {
		}
	}

	public void testFindDealByUserWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			dealService.findDealByUser(-1);
			fail("Object with unknonw id should not be found");
		} catch (DealNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			dealService.findDealByUser(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (DealNotFoundException e) {
		}

	}

	public void testInsertDeleteDeal() throws Exception {

		Set<String> swapObjectsId = new HashSet<String>();
		swapObjectsId.add(1);
		
		// Creates an object
		Deal deal = dealService.insertDeal(1, 2, swapObjectsId);

		// Ensures that the object exists
		long dealId = deal.getId();
		
		deal = null;
		
		try {
			deal = dealService.findDeal(dealId);
		} catch (DealNotFoundException e) {
			fail("Object has been created it should be found");
		}

		// Cleans the test environment
		dealService.deleteDeal(dealId);

		try {
			dealService.findDeal(dealId);
			fail("Object has been deleted it shouldn't be found");
		} catch (DealNotFoundException e) {
		}
	}
	
	public void testCreateDealWithInvalidValues() throws Exception {

		Set<String> swapObjectsId = new HashSet<String>();
		swapObjectsId.add(1);
		
        // Creates an object with invalid Users
        try {
        	dealService.insertDeal(0, 0, swapObjectsId);
            fail("Object with null parameter should not be created");
        } catch (DealInsertException e) {
        }

        // Creates an object withinvalid User
        try {
        	dealService.insertDeal(1, 0, swapObjectsId);
            fail("Object with empty values should not be created");
        } catch (DealInsertException e) {
        }
		
		// Creates an object with invalid User
        try {
        	dealService.insertDeal(0, 2, swapObjectsId);
            fail("Object with empty values should not be created");
        } catch (DealInsertException e) {
        }
		
		// Creates an object with invalid SwapsId
        try {
        	dealService.insertDeal(1, 2, null);
            fail("Object with empty values should not be created");
        } catch (DealInsertException e) {
        }

    }
	
	public void testUpdateDeal() throws Exception {
	
		Set<String> swapObjectsId = new HashSet<String>();
		swapObjectsId.add(1);
		
		// Creates an object
		Deal deal = dealService.insertDeal(1, 2, swapObjectsId);

		// Ensures that the object exists
		long dealId = deal.getId();
		
		deal = null;
		
		try {
			deal = dealService.findDeal(dealId);
		} catch (DealNotFoundException e) {
			fail("Object has been created it should be found");
		}
		
		Set<String> dealsId = new HashSet<String>();
		dealsId.add(1);
		
		try {
			dealService.updateDeal(dealId, "statut faux",dealsId);
			fail("Status doesn't exist");
		} catch (DealUpdateException e) {		
		}

		try {
			dealService.updateDeal(0, "statut faux",dealsId);
			fail("deal doesn'y exist");
		} catch (DealUpdateException e) {		
		}
		
		try {
			dealService.updateDeal(0, "En cours de création",dealsId);
		} catch (DealUpdateException e) {	
			fail("Update shouldn't have failed");
		}
		
		try {
			deal = dealService.findDeal(dealId);
		} catch (DealNotFoundException e) {
			fail("Object has been updated it should be found");
		}
		
		dealService.deleteDeal(dealId);

		try {
			dealService.findDeal(dealId);
			fail("Object has been deleted it shouldn't be found");
		} catch (DealNotFoundException e) {
		}
	
	}
	
		public void testModifyStatus() throws Exception {
	
		Set<String> swapObjectsId = new HashSet<String>();
		swapObjectsId.add(1);
		
		// Creates an object
		Deal deal = dealService.insertDeal(1, 2, swapObjectsId);

		// Ensures that the object exists
		long dealId = deal.getId();
		
		deal = null;
		
		try {
			deal = dealService.findDeal(dealId);
		} catch (DealNotFoundException e) {
			fail("Object has been created it should be found");
		}
		
		Set<String> dealsId = new HashSet<String>();
		dealsId.add(1);
		
		try {
			dealService.modifyStatus(dealId, "wrong status");
			fail("Update should have failed");
		} catch (DealUpdateException e) {	
		}
		
		try {
			dealService.modifyStatus(dealId, "Transaction mise à jour par le proposed");
		} catch (DealUpdateException e) {
			fail("Update shouldn't have failed");
		}
		
		try {
			deal = dealService.findDeal(dealId);
		} catch (DealNotFoundException e) {
			fail("Object has been updated it should be found");
		}
		
		dealService.deleteDeal(dealId);

		try {
			dealService.findDeal(dealId);
			fail("Object has been deleted it shouldn't be found");
		} catch (DealNotFoundException e) {
		}
	
}