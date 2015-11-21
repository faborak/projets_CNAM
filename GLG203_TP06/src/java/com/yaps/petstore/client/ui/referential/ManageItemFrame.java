package com.yaps.petstore.client.ui.referential;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.yaps.petstore.client.ui.AbstractFrame;
import com.yaps.petstore.client.ui.referential.bar.BarEventListener;
import com.yaps.petstore.client.ui.referential.bar.CloseEvent;
import com.yaps.petstore.client.ui.referential.bar.CreateEvent;
import com.yaps.petstore.client.ui.referential.bar.DeleteEvent;
import com.yaps.petstore.client.ui.referential.bar.FindEvent;
import com.yaps.petstore.client.ui.referential.bar.ManageBar;
import com.yaps.petstore.client.ui.referential.bar.ResetEvent;
import com.yaps.petstore.client.ui.referential.bar.UpdateEvent;
import com.yaps.petstore.common.delegate.CatalogDelegate;
import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.DuplicateKeyException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;

/**
 * This class represents the graphical user interface that enables an employee
 * to create, find, delete and update an Item.
 */
public class ManageItemFrame extends AbstractFrame implements BarEventListener{

    // Variables declaration
    private final JLabel labelTitle = new JLabel();
    private final JPanel panelCenter = new JPanel();
    private final JTextField textId = new JTextField();
    private final JTextField textName = new JTextField();
    private final JTextField textUnitCost = new JTextField();
    private final JTextField textProductId = new JTextField();
    private final JTextField textProductName = new JTextField();

    private final ManageBar manageBar = new ManageBar();
	
    /**
     * Creates new form
     */
    public ManageItemFrame() {
        initComponents();
        pack();
        manageBar.setManageListener(this);
    }
    
    // This method is called from within the constructor to display all the graphical components
    private final void initComponents() {
        // Panel North
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", 1, 18));
        labelTitle.setText("Category");

        getContentPane().add(labelTitle, BorderLayout.NORTH);

        // Panel Center
        panelCenter.setLayout(new GridLayout(3, 2));

        panelCenter.add(new JLabel("Identifier"));
        panelCenter.add(textId);

        panelCenter.add(new JLabel("Name"));
        panelCenter.add(textName);

        panelCenter.add(new JLabel("Unit Cost"));
        panelCenter.add(textUnitCost);
        
        panelCenter.add(new JLabel("Product Id"));
        panelCenter.add(textProductId);
        
        panelCenter.add(new JLabel("Product Name"));
        panelCenter.add(textProductName);

        getContentPane().add(panelCenter, BorderLayout.CENTER);

        // Panel South
        getContentPane().add(manageBar, BorderLayout.SOUTH);
    }
    
	@Override
	public void create(CreateEvent evt) {
		final String mname = "create";

        // Sets all the Item data
        final ItemDTO itemDTO = new ItemDTO(textId.getText(), textName.getText(), Double.parseDouble(textUnitCost.getText()));

        try {
            // Creates the item
            CatalogDelegate.createItem(itemDTO);

        } catch (DuplicateKeyException e) {
            JOptionPane.showMessageDialog(this, "This Id already exists", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot create the item", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
		
	}

	@Override
	public void delete(DeleteEvent evt) {
		 final String mname = "delete";

	        try {
	            // Deletes the item
	            CatalogDelegate.deleteItem(textId.getText());
	            reset(new ResetEvent(this));

	        } catch (CheckException e) {
	            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Cannot delete the item", "Error", JOptionPane.ERROR_MESSAGE);
	            Trace.throwing(getCname(), mname, e);
	        }
	}

	@Override
	public void find(FindEvent evt) {
		final String mname = "find";

        try {
            // Finds the item
            final ItemDTO itemDTO = CatalogDelegate.findItem(textId.getText());

            // Displays the data
            textId.setText(itemDTO.getId());
            textName.setText(itemDTO.getName());
            textUnitCost.setText(new Double(itemDTO.getUnitCost()).toString());
            textProductId.setText(itemDTO.getProductId());
            textProductName.setText(itemDTO.getProductName());

        } catch (ObjectNotFoundException e) {
            JOptionPane.showMessageDialog(this, "This item has not been found", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot find the item", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
	}

	@Override
	public void update(UpdateEvent evt) {
		final String mname = "update";

        // Sets all the Item data
        final ItemDTO itemDTO = new ItemDTO(textId.getText(), textName.getText(), Double.parseDouble(textUnitCost.getText()));

        try {
            // Updates the item
            CatalogDelegate.updateItem(itemDTO);

        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot update the item", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
	}

	@Override
	public void reset(ResetEvent evt) {
		textUnitCost.setText("");
        textId.setText("");
        textName.setText("");
	}

	@Override
	public void close(CloseEvent evt) {
		dispose();
	}

}
