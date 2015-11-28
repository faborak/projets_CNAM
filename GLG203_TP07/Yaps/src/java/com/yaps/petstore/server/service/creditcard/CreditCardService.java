package com.yaps.petstore.server.service.creditcard;

import org.dom4j.Document;
import org.dom4j.Node;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.CreditCard;

/**
 * This class verify a Credit Card with BBank.
 */
public class CreditCardService {

	// ======================================
	// = Business methods =
	// ======================================
	public void verifyCreditCard(CreditCard creditCard) throws CheckException {

		Document retour = null;
		try {
			retour = HTTPSender.send(creditCard.toXML());
		} catch (Exception e) {
			throw new CheckException("Invalid Card");
		}
		Node statut = retour.selectSingleNode("/CreditCard/status");
		if (statut.getText() == "Invalid date")
			throw new CheckException("Invalid date");
		if (statut.getText() == "Invalid number")
			throw new CheckException("Invalid number");
	}
}
