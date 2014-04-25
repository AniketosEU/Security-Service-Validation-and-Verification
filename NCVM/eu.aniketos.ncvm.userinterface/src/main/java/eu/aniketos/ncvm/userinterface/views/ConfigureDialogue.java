/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package eu.aniketos.ncvm.userinterface.views;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;

import eu.aniketos.ncvm.userinterface.Activator;

/**
 * Create a dialogue box for editing ConSpec reactions.
 * @author Aniketos Project; David Llewellyn-Jones, Liverpool John Moores University
 *
 */
public class ConfigureDialogue extends TitleAreaDialog {
	private Settings temporarySettings;
	private WebServiceEntry ncvm = new WebServiceEntry();
	private WebServiceEntry pvm = new WebServiceEntry();
	private WebServiceEntry csvm = new WebServiceEntry();
	private WebServiceEntry spdm = new WebServiceEntry();
	private WebServiceEntry marketplace = new WebServiceEntry();
	private String registerAddress = "";
	private String registerAddressUser = "";
	private boolean registerAuto = true;
	
	private IWorkbenchPartSite site;
	
	/**
	 * Constructor for the reaction edit dialogue box.
	 * @param site The site that the dialogue box relates to.
	 * @param index The index (row) of the reaction in the reaction table of the Rules dialogue box.
	 * @param reaction The details of the reaction being edited.
	 */
	public ConfigureDialogue(IWorkbenchPartSite site, Settings settings) {
		// Call the TitleAreaDialog constructor.
		super(site.getShell());
		// Store the information passed in for later use.
		this.site = site;
		temporarySettings = new Settings(settings);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		// Sets up the dialogue box.
		Control contents = super.createContents(parent);
		
		// Set the title of the dialogue box.
		setTitle("NCVM Configuration");
		
		// Set some helpful text about the dialogue for the benefit of the user.
		setMessage("Specify the properties of the Nested Composition Verification Module, including locations of dependencies.");

		// Give the dialogue box an attractive Aniketos header.
	    ImageDescriptor image = Activator.getImageDescriptor("icons/header.png");
		setTitleImage(image.createImage());

		return contents;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	protected Point getInitialSize() {
		// Set the initial size of the dialogue box.
		return new Point(412, 400);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#setShellStyle(int)
	 */
	protected void setShellStyle(int newShellStyle) {
		// Set the style for the dialogue box.
		// Allow resizing and maximising.
		super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.MAX);
	}
	
	class WebServiceEntry {
		String address;
		boolean useTracker;
		Text addressEntry;
	}
	
	private void CreateWebServiceEntry(String label, Composite composite, final WebServiceEntry status) {
		// Label for the text box for entering the guard expression.
		Label labelWSEntry = new Label(composite, SWT.LEFT);
		labelWSEntry.setText(label);
		labelWSEntry.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		// Create a guard text box to allow the user to edit the guard expression.
		status.addressEntry = new Text(composite, SWT.BORDER);

		// Set up the guard text box with the expression string.
		status.addressEntry.setText(status.address);
		status.addressEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		status.addressEntry.setEnabled(!status.useTracker);

		// Add a listener to keep track of whether the user has edited the guard expression.
		status.addressEntry.addListener(SWT.Modify, new Listener () {
			@Override
			public void handleEvent(Event arg0) {
				// The guard expression changed.
				// Convert the expression string back into an object hierarchy.
				status.address = status.addressEntry.getText();
			}
		});

		final org.eclipse.swt.widgets.Button tracker = new org.eclipse.swt.widgets.Button(composite, SWT.LEFT | SWT.CHECK);
		tracker.setText("Use Tracker");
		tracker.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		tracker.setSelection(status.useTracker);

		// Add a listener to keep track of whether the user has edited the guard expression.
		tracker.addSelectionListener(new SelectionListener () {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				status.useTracker = tracker.getSelection();
				status.addressEntry.setEnabled(!status.useTracker);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				status.useTracker = tracker.getSelection();
				status.addressEntry.setEnabled(!status.useTracker);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		// Add the controls into the dialogue box.
		// Set up the SWT layout for rendering the form.
		Composite container = (Composite)super.createDialogArea(parent);
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 3;

		// Create the feedback address entry
		registerAddress = temporarySettings.getRegisterAddress();
		registerAddressUser = registerAddress;
		registerAuto = temporarySettings.isRegisterAuto();

		// Label for the text box for entering the guard expression.
		Label labelRegisterEntry = new Label(composite, SWT.LEFT);
		labelRegisterEntry.setText("Reply address");
		labelRegisterEntry.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		// Create text box to allow the user to edit the registration address.
		final Text registerAddressEntry = new Text(composite, SWT.BORDER);

		// Set up the guard text box with the expression string.
		registerAddressEntry.setText(registerAddress);
		registerAddressEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		registerAddressEntry.setEnabled(!registerAuto);

		// Add a listener to keep track of whether the user has edited the guard expression.
		registerAddressEntry.addListener(SWT.Modify, new Listener () {
			@Override
			public void handleEvent(Event arg0) {
				// The guard expression changed.
				// Convert the expression string back into an object hierarchy.
				registerAddress = registerAddressEntry.getText();
			}
		});

		final org.eclipse.swt.widgets.Button autoRegisterButton = new org.eclipse.swt.widgets.Button(composite, SWT.LEFT | SWT.CHECK);
		autoRegisterButton.setText("Auto");
		autoRegisterButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		autoRegisterButton.setSelection(registerAuto);

		// Add a listener to keep track of whether the user has edited the guard expression.
		autoRegisterButton.addSelectionListener(new SelectionListener () {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				registerAuto = autoRegisterButton.getSelection();
				registerAddressEntry.setEnabled(!registerAuto);
				if (registerAuto) {
					registerAddressUser = registerAddress;
					registerAddressEntry.setText(Register.getAutoAddress());
				}
				else {
					registerAddressEntry.setText(registerAddressUser);
				}
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				registerAuto = autoRegisterButton.getSelection();
				registerAddressEntry.setEnabled(!registerAuto);
				if (registerAuto) {
					registerAddressUser = registerAddress;
					registerAddressEntry.setText(Register.getAutoAddress());
				}
				else {
					registerAddressEntry.setText(registerAddressUser);
				}
			}
		});		
		
	    // Horizontal separator
	    Label separator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	    separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, layout.numColumns, 1));
		
		
		// Create the service dependency entries
		ncvm.address = temporarySettings.getNcvmAddress();
		ncvm.useTracker = temporarySettings.isNcvmTracker();
		CreateWebServiceEntry("NCVM address:", composite, ncvm);	

		pvm.address = temporarySettings.getPvmAddress();
		pvm.useTracker = temporarySettings.isPvmTracker();
		CreateWebServiceEntry("PVM address:", composite, pvm);	
		
		csvm.address = temporarySettings.getCsvmAddress();
		csvm.useTracker = temporarySettings.isCsvmTracker();
		CreateWebServiceEntry("CSVM address:", composite, csvm);	

		spdm.address = temporarySettings.getSpdmAddress();
		spdm.useTracker = temporarySettings.isSpdmTracker();
		CreateWebServiceEntry("SPDM address:", composite, spdm);	
		
		marketplace.address = temporarySettings.getMarketplaceAddress();
		marketplace.useTracker = temporarySettings.isMarketplaceTracker();
		CreateWebServiceEntry("Marketplace address:", composite, marketplace);	
		
		// Hook up the actions and context menus for the dialogue box.
		makeActions ();
		hookContextMenu ();
		
		// Return the contents of the dialogue box.
		return composite;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Add buttons for OK and Cancel to the dialogue box.
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Callback for adding appropriate entries to the context menu for deleting update entries.
	 * @param manager The menu to add the menu entries to.
	 */
	private void fillContextMenuUpdate(IMenuManager manager) {
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	/**
	 * Set up the context menu for the dialogue box.
	 */
	private void hookContextMenu() {
		// The menu to be added.
		Menu menu;

		// Param context menu.
		MenuManager menuMgrDeclaration = new MenuManager("#PopupMenu");
		menuMgrDeclaration.setRemoveAllWhenShown(true);
		// Set up a listener so we can add the relevant entries to the menu.
		menuMgrDeclaration.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ConfigureDialogue.this.fillContextMenuUpdate(manager);
			}
		});
		// Attach the menu to the appropriate places for Eclipse to handle it.
	}

	/**
	 * Set up callbacks for the various actions the user may perform
	 */
	private void makeActions() {
	}
	
	public Settings getTemporarySettings() {
		temporarySettings.setNcvmAddress(ncvm.address);
		temporarySettings.setNcvmTracker(ncvm.useTracker);
		temporarySettings.setPvmAddress(pvm.address);
		temporarySettings.setPvmTracker(pvm.useTracker);
		temporarySettings.setCsvmAddress(csvm.address);
		temporarySettings.setCsvmTracker(csvm.useTracker);
		temporarySettings.setSpdmAddress(spdm.address);
		temporarySettings.setSpdmTracker(spdm.useTracker);
		temporarySettings.setMarketplaceAddress(marketplace.address);
		temporarySettings.setMarketplaceTracker(marketplace.useTracker);
		temporarySettings.setRegisterAddress(registerAddress);
		temporarySettings.setRegisterAuto(registerAuto);
		
		return temporarySettings;
	}
}
