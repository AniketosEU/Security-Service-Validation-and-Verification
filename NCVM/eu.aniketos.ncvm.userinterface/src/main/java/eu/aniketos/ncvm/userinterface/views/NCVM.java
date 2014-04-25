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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.impl.ConsumerPolicy;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.ncvm.userinterface.Activator;

/**
 * Plugin for testing the NCVM. It provides a simple workbench
 * interface that allows the NCVM to be invoked.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public class NCVM extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "eu.aniketos.wp3.ncvm.userinterface.views.NCVM";

	private Action actionConfigure;
	private Action actionInfo;
	private NCVMRemote ncvm;
	private Text service;
	private Text property;
	private Button callButton;
	private Button resetService;
	private Button resetProperty;
	private Button resetOutput;
	private Button configureButton;
	private Button testButton;
	private Text output;
	private int outputLogLines;
	private final int outputLogMaxLines = 200;
	private String outputLog;
	public Display display;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return new String[] { "One", "Two", "Three" };
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public NCVM() {
		ncvm = new NCVMRemote();
	    ncvm.initialiseNCVMRemote();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
	    FormToolkit toolkit;
	    GridLayout gridLayout;
	    Label label;
	    GridData gridData;
	    
	    Activator.getDefault().getNCVMFeedback().setNCVM(this);    
	    outputLog = "";
	    outputLogLines = 0;

	    display = parent.getDisplay();
	    toolkit = new FormToolkit(display);
	    final ScrolledForm form = toolkit.createScrolledForm(parent);
	    form.setText("NCVM Testing Interface");
	    form.getBody().setLayout(new GridLayout(1, true));
	    form.reflow(true);

		Section serviceComposite = new Section(form.getBody(), Section.TITLE_BAR | Section.TWISTIE);
		serviceComposite.setText("NCVM input: Service or BPMN2");
		serviceComposite.setTitleBarBackground(new Color(form.getDisplay(), 204, 204, 255));
		serviceComposite.setBackground(new Color(form.getDisplay(), 225, 230, 246));
		serviceComposite.marginWidth = 0;
		label = toolkit.createLabel(serviceComposite, "NCVM input: Service BPMN2", SWT.WRAP);
		serviceComposite.setClient(label);
		serviceComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
	    
	    serviceComposite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
	    
	    Composite serviceSub = toolkit.createComposite(serviceComposite);
	    serviceComposite.setClient(serviceSub);
	    gridLayout = new GridLayout();
	    gridLayout.numColumns = 1;
	    gridLayout.marginLeft = 0;
	    gridLayout.marginRight = 0;
	    serviceSub.setBackground(new Color(form.getDisplay(), 225, 230, 246));
	    serviceSub.setLayout(gridLayout);
	    
	    service = new Text(serviceSub, SWT.MULTI | SWT.LEFT | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP | SWT.BORDER);
	    service.setText(Activator.getDefault().getPrefString("bpmn2"));
		toolkit.adapt(service, true, true);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gridData.widthHint = 0;
		gridData.heightHint = 200;
		service.setLayoutData(gridData);

	    resetService = new Button (serviceSub, SWT.NONE);
	    resetService.setText("Reset Service");
		toolkit.adapt(resetService, true, true);
	    
		// Add a listener to check whether the user has changed the identifier.
		resetService.addSelectionListener(new SelectionListener () {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				service.setText(Activator.getDefault().resetPref("bpmn2"));
			}
		});
	    
		Section policyComposite = new Section(form.getBody(), Section.TITLE_BAR | Section.TWISTIE);
		policyComposite.setText("NCVM input: Policy ConSpec XML");
		policyComposite.setTitleBarBackground(new Color(form.getDisplay(), 204, 204, 255));
		policyComposite.setBackground(new Color(form.getDisplay(), 225, 230, 246));
		policyComposite.marginWidth = 0;
		label = toolkit.createLabel(policyComposite, "NCVM input: Policy ConSpec XML", SWT.WRAP);
		policyComposite.setClient(label);
		policyComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

	    policyComposite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
	    
	    Composite propertySub = toolkit.createComposite(policyComposite);
	    policyComposite.setClient(propertySub);
	    gridLayout = new GridLayout();
	    gridLayout.numColumns = 1;
	    gridLayout.marginLeft = 0;
	    gridLayout.marginRight = 0;
	    propertySub.setBackground(new Color(form.getDisplay(), 225, 230, 246));
	    propertySub.setLayout(gridLayout);
	    
	    property = new Text(propertySub, SWT.MULTI | SWT.LEFT | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
	    property.setText(Activator.getDefault().getPrefString("conspec"));
		toolkit.adapt(property, true, true);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gridData.widthHint = 0;
		gridData.heightHint = 200;
		property.setLayoutData(gridData);	

		resetProperty = new Button (propertySub, SWT.NONE);
		resetProperty.setText("Reset Policy");
		toolkit.adapt(resetProperty, true, true);
	    
		// Add a listener to check whether the user has changed the identifier.
		resetProperty.addSelectionListener(new SelectionListener () {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				property.setText(Activator.getDefault().resetPref("conspec"));
			}
		});
	    
		Section outputComposite = new Section(form.getBody(), Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		outputComposite.setTitleBarBackground(new Color(form.getDisplay(), 204, 204, 255));
		outputComposite.setBackground(new Color(form.getDisplay(), 225, 230, 246));
		outputComposite.marginWidth = 0;
		outputComposite.setText("NCVM output: process info");
		label = toolkit.createLabel(outputComposite, "NCVM output process info", SWT.WRAP);
		outputComposite.setClient(label);
		outputComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		outputComposite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
	    
	    Composite outputSub = toolkit.createComposite(outputComposite, SWT.NONE);
	    outputComposite.setClient(outputSub);
	    gridLayout = new GridLayout();
	    gridLayout.numColumns = 1;
	    gridLayout.marginLeft = 0;
	    gridLayout.marginRight = 0;
	    outputSub.setBackground(new Color(form.getDisplay(), 225, 230, 246));
	    outputSub.setLayout(gridLayout);
	    
	    output = new Text(outputSub, SWT.MULTI | SWT.LEFT | SWT.V_SCROLL | SWT.WRAP);
	    output.setText(outputLog);
		toolkit.adapt(output, true, true);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gridData.widthHint = 0;
		gridData.heightHint = 200;
		output.setLayoutData(gridData);	
		output.setEditable(false);

	    resetOutput = new Button (outputSub, SWT.NONE);
	    resetOutput.setText("Clear Output");
		toolkit.adapt(resetOutput, true, true);
	    
		// Add a listener to check whether the user has changed the identifier.
		resetOutput.addSelectionListener(new SelectionListener () {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				logClear();
			}
		});
		
		// Add a row of buttons
	    Composite buttonsComposite = toolkit.createComposite(form.getBody(), SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = true;
	    buttonsComposite.setLayout(rowLayout);

	    callButton = new Button(buttonsComposite, SWT.NONE);
		callButton.setText("Call NCVM");
		toolkit.adapt(callButton, true, true);
		
		// Add a listener to check whether the user has changed the identifier.
		callButton.addSelectionListener(new SelectionListener () {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				String bpmn2File = service.getText();
				Activator.getDefault().setPrefString("bpmn2", bpmn2File);

				String conspecFile = property.getText();
				Activator.getDefault().setPrefString("conspec", conspecFile);
				//conspecFile = new String (Base64.encodeBase64(conspecFile.getBytes()));
				IConsumerPolicy policySend = new ConsumerPolicy();
				policySend.setXML(conspecFile);
				String[] conspecFiles = new String[1];
				conspecFiles[0] = conspecFile;
				policySend.setXmlContents(conspecFiles);

				// Check whether it's a BPMN2 file or a service ID
				if (bpmn2File.charAt(0) == '<') {
					// BPMN2 file
					ICompositionPlan serviceSend= new CompositionPlan(bpmn2File);
					callNCVM(serviceSend, policySend);
				}
				else {
					// Service ID
					callNCVMDeployed(bpmn2File, policySend);
				}
			}
			
		});
		
		configureButton = new Button(buttonsComposite, SWT.NONE);
		configureButton.setText("Configure");
		toolkit.adapt(configureButton, true, true);

		// Add a listener to check whether the user has changed the identifier.
		configureButton.addSelectionListener(new SelectionListener () {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				actionConfigure.run();
			}
		});
		
		testButton = new Button(buttonsComposite, SWT.NONE);
		testButton.setText("Perform Tests");
		toolkit.adapt(testButton, true, true);
		
		// Add a listener to check whether the user has changed the identifier.
		testButton.addSelectionListener(new SelectionListener () {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Thread callThread = new Thread(new Runnable() {

					@Override
					public void run() {
						testNCVM();
					}
				}, "NCVM test");
				
				callThread.start();
			}
		});
		
		form.reflow(true);

		// Create the help context id for the viewer's control
		makeActions();
		contributeToActionBars();
		IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
		if (window instanceof WorkbenchWindow) {
			MenuManager menuManager = ((WorkbenchWindow)window).getMenuManager();
			contributeToMenu(menuManager);
		}
	}

	private void callNCVM (final ICompositionPlan serviceSend, final IConsumerPolicy policySend) {
		Thread callThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					logOutputLine("");
					logOutputLine("Calling NCVM");
					logOutputLine("Parameter lengths: " + serviceSend.getBPMNXML().length() + ", " + policySend.getXML().length());

					IVerificationResult result = ncvm.verifyProperty(serviceSend, policySend);
					logOutputLine("Result: " + result.getResult());
				} catch (Exception e) {
					e.printStackTrace();
					logOutputLine("Error calling remote service");
				}	
			}
		}, "NCVM call");
		
		callThread.start();
	}
	
	private void callNCVMDeployed (final String serviceID, final IConsumerPolicy policySend) {
		Thread callThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					logOutputLine("");
					logOutputLine("Calling NCVM for deployed service");
					logOutputLine("Parameter lengths: " + serviceID.length() + ", " + policySend.getXML().length());

					IVerificationResult result = ncvm.verifyPropertyDeployed(serviceID, policySend);
					logOutputLine("Result: " + result.getResult());
				} catch (Exception e) {
					e.printStackTrace();
					logOutputLine("Error calling remote service");
				}	
			}
		}, "NCVM deployed call");
		
		callThread.start();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void contributeToMenu(IMenuManager manager) {
		// Create a menu to add to the Eclipse menus.
		IMenuManager menu = new MenuManager("&NCVM");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
		menu.add(actionInfo);
		manager.add(new Separator());
		menu.add(actionConfigure);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actionInfo);
		manager.add(new Separator());
		manager.add(actionConfigure);
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionConfigure);
		manager.add(actionInfo);
	}

	private void makeActions() {
		actionConfigure = new Action() {
			public void run() {
				// Create the dialogue box to edit the rule.
				ConfigureDialogue configureShell = new ConfigureDialogue(getSite(), Activator.getDefault().getSettings());
				
				// Display the dialogue box and capture the returned result.
				// A result of 0 means the user clicked 'OK'.
				int result = configureShell.open();
				if (result == 0) {
					configureNCVM(configureShell.getTemporarySettings());
				}
			}
		};
		actionConfigure.setText("&Configure NCVM");
		actionConfigure.setToolTipText("Configure the Aniketos Nested Composition Verification Module");
		actionConfigure.setImageDescriptor(Activator.getDefault().getImageRegistry().getDescriptor(Activator.IMG_NCVM_CONFIGURE));
	
		actionInfo = new Action() {
			public void run() {
				showMessage("Aniketos Nested Composition Verification Module");
			}
		};
		actionInfo.setText("&About");
		actionInfo.setToolTipText("About the Aniketos Nested Composition Verification Module");
		actionInfo.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(getSite().getShell(), "NCVM", message);
	}

	public void logOutputLine(String message) {
		if (Thread.currentThread() != display.getThread()) {
			final String logText = message;
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					logOutputLineSync(logText);
				}
			});
		}
		else {
			logOutputLineSync(message);
		}
	}
	
	private void logOutputLineSync(String message) {
		outputLog += message;
		outputLog += "\n";

	    outputLogLines++;
	    while (outputLogLines > outputLogMaxLines) {
	    	outputLog = outputLog.substring(outputLog.indexOf('\n') + 1);
		    outputLogLines--;
	    }
	    output.setText(outputLog);
	    output.setSelection(output.getText().length());
	    output.setTopIndex(output.getLineCount() - 1);
	}
	
	public void logClear() {
		outputLog = "";
		outputLogLines = 0;
		output.setText(outputLog);
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//composite.setFocus();
	}
	
	public void configureNCVM(Settings settings) {
		boolean reRegister = false;
		// Check whether the service address has changed and needs to be re-registered
		if (!((settings.getRegisterAddress()).equals(Activator.getDefault().getSettings().getRegisterAddress()))) {
			reRegister = true;
		}
		Activator.getDefault().assignSettings(settings);

		ncvm.initialiseNCVMRemote();

	    try {
			ncvm.configureCSVM(settings.getCsvmAddress(), settings.isCsvmTracker());
			ncvm.configurePVM(settings.getPvmAddress(), settings.isPvmTracker());
			ncvm.configureSPDM(settings.getSpdmAddress(), settings.isSpdmTracker());
			ncvm.configureMarketplace(settings.getMarketplaceAddress(), settings.isMarketplaceTracker());
		}
	    catch (Exception e) {
			System.out.println("Failed to configure remote service: " + e.getMessage());
		}
	    
	    if (reRegister) {
	    	Activator.getDefault().reRegister();
	    }
	}
	
	public void testNCVM () {
		ncvm.initialiseNCVMRemote();
		
		try {
			ncvm.performTests();
		} catch (Exception e) {
			System.out.println("Failed to configure remote service: " + e.getMessage());
		}
	}
}
