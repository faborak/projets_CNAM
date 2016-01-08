package com.yaps.petstore.server.service.catalog;

import com.yaps.petstore.common.dto.CatalogDTO;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.catalog.Catalog;
import com.yaps.petstore.server.domain.catalog.CatalogDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;
/* Do not check credit cart data here anymore
import com.yaps.petstore.common.locator.ejb.ServiceLocator;
import com.yaps.petstore.server.service.creditcard.CreditCardServiceLocal;
import com.yaps.petstore.server.service.creditcard.CreditCardServiceLocalHome;
*/
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateless;

/**
 * This class is a session facade for all catalog services.
 */
// @Stateless (name="CatalogSB", mappedName=CatalogServiceHome.JNDI_NAME)
@Stateless (name="CatalogSB")
public class CatalogServiceBean extends AbstractRemoteService implements CatalogService {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final CatalogDAO _dao = new CatalogDAO();

    // ======================================
    // =            Constructors            =
    // ======================================
    public CatalogServiceBean() {
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public CatalogDTO authenticate(final String catalogId, final String password) throws FinderException, CheckException {
        final String mname = "authenticate";
        Trace.entering(getCname(), mname, new Object[]{catalogId, password});

        checkId(catalogId);
        if (password == null || "".equals(password))
            throw new CheckException("Invalid password");
        
        // Finds the object
        final Catalog catalog = (Catalog)_dao.findByPrimaryKey(catalogId);

        // Check if it's the right password, if not, a CheckException is thrown
        catalog.matchPassword(password);

        // Transforms domain object into DTO
        final CatalogDTO catalogDTO = transformCatalog2DTO(catalog);

        Trace.exiting(getCname(), mname, catalogDTO);
        return catalogDTO;
    }

    public CatalogDTO createCatalog(final CatalogDTO catalogDTO) throws CreateException, CheckException {
        final String mname = "createCatalog";
        Trace.entering(getCname(), mname, catalogDTO);

        if (catalogDTO == null)
            throw new CheckException("Catalog object is null");

        // Transforms DTO into domain object
        final Catalog catalog = new Catalog(catalogDTO.getId(), catalogDTO.getFirstname(), catalogDTO.getLastname());
        catalog.setPassword(catalogDTO.getPassword());
        catalog.setCity(catalogDTO.getCity());
        catalog.setCountry(catalogDTO.getCountry());
        catalog.setState(catalogDTO.getState());
        catalog.setStreet1(catalogDTO.getStreet1());
        catalog.setStreet2(catalogDTO.getStreet2());
        catalog.setTelephone(catalogDTO.getTelephone());
        catalog.setZipcode(catalogDTO.getZipcode());
        catalog.setEmail(catalogDTO.getEmail());
        catalog.setCreditCardExpiryDate(catalogDTO.getCreditCardExpiryDate());
        catalog.setCreditCardNumber(catalogDTO.getCreditCardNumber());
        catalog.setCreditCardType(catalogDTO.getCreditCardType());

        catalog.checkData();

        /* Do not check here if the credit card is valid
        	getCreditCardService().verifyCreditCard(catalog.getCreditCard());
         */
        // Creates the object
        _dao.insert(catalog);

        // Transforms domain object into DTO
        final CatalogDTO result = transformCatalog2DTO(catalog);

        Trace.exiting(getCname(), mname, result);
        return result;
    }

    public CatalogDTO findCatalog(final String catalogId) throws FinderException, CheckException {
        final String mname = "findCatalog";
        Trace.entering(getCname(), mname, catalogId);

    	checkId(catalogId);
        // Finds the object
        final Catalog catalog = (Catalog)_dao.findByPrimaryKey(catalogId);

        // Transforms domain object into DTO
        final CatalogDTO catalogDTO = transformCatalog2DTO(catalog);

        Trace.exiting(getCname(), mname, catalogDTO);
        return catalogDTO;
    }

    public void deleteCatalog(final String catalogId) throws RemoveException, CheckException {
        final String mname = "deleteCatalog";
        Trace.entering(getCname(), mname, catalogId);

    	checkId(catalogId);

        // Checks if the object exists
        try {
        	_dao.findByPrimaryKey(catalogId);
        } catch (FinderException e) {
            throw new CheckException("Catalog must exist to be deleted");
        }

        // Deletes the object
        try {
        	_dao.remove(catalogId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Catalog must exist to be deleted");
        }
    }

    public void updateCatalog(final CatalogDTO catalogDTO) throws UpdateException, CheckException {
        final String mname = "updateCatalog";
        Trace.entering(getCname(), mname, catalogDTO);

        if (catalogDTO == null)
            throw new CheckException("Catalog object is null");

    	checkId(catalogDTO.getId());

    	final Catalog catalog;

        // Checks if the object exists
        try {
        	catalog = (Catalog)_dao.findByPrimaryKey(catalogDTO.getId());
        } catch (FinderException e) {
            throw new CheckException("Catalog must exist to be updated");
        }

        // Transforms DTO into domain object
        catalog.setFirstname(catalogDTO.getFirstname());
        catalog.setLastname(catalogDTO.getLastname());
        catalog.setPassword(catalogDTO.getPassword());
        catalog.setCity(catalogDTO.getCity());
        catalog.setCountry(catalogDTO.getCountry());
        catalog.setState(catalogDTO.getState());
        catalog.setStreet1(catalogDTO.getStreet1());
        catalog.setStreet2(catalogDTO.getStreet2());
        catalog.setTelephone(catalogDTO.getTelephone());
        catalog.setZipcode(catalogDTO.getZipcode());
        catalog.setEmail(catalogDTO.getEmail());
        catalog.setCreditCardExpiryDate(catalogDTO.getCreditCardExpiryDate());
        catalog.setCreditCardNumber(catalogDTO.getCreditCardNumber());
        catalog.setCreditCardType(catalogDTO.getCreditCardType());

        catalog.checkData();
        
        /* Do not check if the credit card is valid
        	getCreditCardService().verifyCreditCard(catalog.getCreditCard());
		*/
        // Updates the object
        try {
        	_dao.update(catalog);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Catalog must exist to be updated");
        }
    }

    public Collection findCatalogs() throws FinderException {
        final String mname = "findCatalogs";
        Trace.entering(getCname(), mname);

        // Finds all the objects
        final Collection catalogs = _dao.selectAll();

        // Transforms domain objects into DTOs
        final Collection catalogsDTO = transformCatalogs2DTOs(catalogs);

        Trace.exiting(getCname(), mname, new Integer(catalogsDTO.size()));
        return catalogsDTO;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private CatalogDTO transformCatalog2DTO(final Catalog catalog) {
        final CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setId(catalog.getId());
        catalogDTO.setPassword(catalog.getPassword());
        catalogDTO.setCity(catalog.getCity());
        catalogDTO.setCountry(catalog.getCountry());
        catalogDTO.setFirstname(catalog.getFirstname());
        catalogDTO.setEmail(catalog.getEmail());
        catalogDTO.setLastname(catalog.getLastname());
        catalogDTO.setState(catalog.getState());
        catalogDTO.setStreet1(catalog.getStreet1());
        catalogDTO.setStreet2(catalog.getStreet2());
        catalogDTO.setTelephone(catalog.getTelephone());
        catalogDTO.setZipcode(catalog.getZipcode());
        catalogDTO.setCreditCardNumber(catalog.getCreditCardNumber());
        catalogDTO.setCreditCardType(catalog.getCreditCardType());
        catalogDTO.setCreditCardExpiryDate(catalog.getCreditCardExpiryDate());
        return catalogDTO;
    }

    private Collection transformCatalogs2DTOs(final Collection catalogs) {
        final Collection catalogsDTO = new ArrayList();
        for (Iterator iterator = catalogs.iterator(); iterator.hasNext();) {
            final Catalog catalog = (Catalog) iterator.next();
            catalogsDTO.add(transformCatalog2DTO(catalog));
        }
        return catalogsDTO;
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @return a unique identifer
     */
    public final String getUniqueId() {
        return _dao.getUniqueId();
    }
}
