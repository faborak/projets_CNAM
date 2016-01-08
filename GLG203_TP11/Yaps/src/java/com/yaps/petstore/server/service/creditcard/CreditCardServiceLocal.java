package com.yaps.petstore.server.service.creditcard;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.CreditCard;
import javax.ejb.Remote;

/**
 * This interface gives a remote view of the CreditCardServiceBean. Any distant client that wants to call
 * a method on the CustomerServiceBean has to use this interface.
 */
@Remote
public interface CreditCardServiceLocal {

    // ======================================
    // =           Business methods         =
    // ======================================

    /**
     * Verify a given credit card
     *
     * @param creditCard to verify
     * @throws CheckException  is thrown if a invalid data is found
     */
    void verifyCreditCard(final CreditCard creditCard) throws CheckException;

}
