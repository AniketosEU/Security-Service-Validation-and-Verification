/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
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

package eu.aniketos.scpm.userinterface.views;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;




//import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.scpm.data.impl.AgreementTemplate;
import eu.aniketos.scpm.data.impl.CompositionPlan;
import eu.aniketos.scpm.data.impl.ConsumerPolicy;
import eu.aniketos.scpm.impl.client.SCPMClient;
import eu.aniketos.scpm.userinterface.BPMNParser;
import eu.aniketos.scpm.userinterface.OrderSelectionAdapter;
import eu.aniketos.scpm.userinterface.VerifyAllSelectionAdapter;
//import eu.aniketos.scpm.userinterface.VerifyAllSelectionAdapter;


/**
 * Client side of the Secure Composition Planner Module. This plugin has an user
 * interface and demonstrates how to invoke and use Secure Composition Planner
 * Module.
 * 
 * @author Bo Zhou, David Lamb- Liverpool John Moores University May 2012
 * 
 */

public class ScpmUI extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "eu.aniketos.scpm.userinterface.views.ScpmUI";
	public static final String templateFile = "/resources/Confidentiality_Agreement.xml";
	public static final String policyFile = "/resources/Confidentiality_Policy.xml";
	public static final String BPMNplan = "/resources/compositionPlan1.bpmn20.xml";

	/**
	 * The shared instance.
	 */
	private static ScpmUI plugin;
	/**
	 * Label shows "Available Composition Plans:".
	 */
	private Label labelAvailable;
	/**
	 * Label shows "Secured Composition Plans:".
	 */
	private Label labelSecured;
	/**
	 * Label shows "Unsecured Composition Plans:".
	 */
	private Label labelUnSecured;

	/**
	 * Button used to browse local composition plan. When clicked, a file
	 * browser dialog will appear.
	 */
	private Button buttonBrowse;

	/**
	 * Test button testing marketplace.
	 */
	private Button buttonRecomposition;

	private Button buttonReconfiguration;

	/**
	 * Button to trigger the verification process.
	 */
	private Button buttonSelect;
	/**
	 * Button to clear all composition plans.
	 */
	private Button buttonClear;
	/**
	 * Label shows "Order By:".
	 */
	private Label labelOrderBy;

	/**
	 * Button to make order of all composition plans.
	 */
	private Button buttonOrder;
	/**
	 * Button to go back to previous page.
	 */
	private Button buttonBack;
	/**
	 * Composite holds all elements that relate to original composition plans.
	 */
	public static Composite compositeSelect;
	/**
	 * Composite holds all elements that relate to verification and ordering of
	 * composition plans.
	 */
	public static Composite compositeOrder;
	/**
	 * Composite holds all elements that relate to individual services.
	 */
	private Composite compositeServices;
	/**
	 * "Aniketos Services in selected composition plan:".
	 */
	private Label labelServices;
	/**
	 * Table holds original list of composition plans".
	 */
	public static Table compositionTable;
	/**
	 * ID column for compositionTable. It shows the ID of composition plan.
	 */
	private TableColumn colID;
	/**
	 * Name column for compositionTable. It shows the name of composition plan.
	 */
	private TableColumn colName;
	/**
	 * AgreementTemplate column for compositionTable. It shows the content of
	 * agreement template.
	 */
	private TableColumn colAgreementTemplate;
	/**
	 * ConsumerPolicy column for compositionTable. It shows the content of
	 * consumer policy.
	 */
	private TableColumn colConsumerPolicy;
	/**
	 * Table holds list of composition plans that verified as secured".
	 */
	public static Table compositionTable_Secured;
	/**
	 * ID column for secured table. It shows the ID of composition plan.
	 */
	private TableColumn colID_Secured;
	/**
	 * Name column for secured table. It shows the name of composition plan.
	 */
	private TableColumn colName_Secured;
	private TableColumn colResult_Secured;
	public static TableColumn colValue_Secured;

	/**
	 * Table holds list of composition plans that verified as unsecured".
	 */
	public static Table compositionTable_UnSecured;
	/**
	 * ID column for unsecured table. It shows the ID of composition plan.
	 */
	private TableColumn colID_UnSecured;
	/**
	 * Name column for unsecured table. It shows the name of composition plan.
	 */
	private TableColumn colName_UnSecured;
	private TableColumn colReason_UnSecured;
	/**
	 * Table holds list of services in selected composition plan".
	 */
	private Table serviceTable;
	/**
	 * Name column for service table. It shows the ID of service.
	 */
	private TableColumn colID_Services;
	/**
	 * A service that selected in serviceTable.
	 */
	private TableItem targetService;
	
	/**
	 * Constant value represents column ID for all Table.
	 */
	public static final int COL_ID = 0;
	/**
	 * Constant value represents column Name for all Table.
	 */
	public static final int COL_NAME = 1;
	/**
	 * Constant value represents column Agreement Template for compositionTable.
	 */
	public static final int COL_AT = 2;
	/**
	 * Constant value represents column Order for Secured Table.
	 */
	public static final int COL_RESULT = 2;
	public static final int COL_VALUE = 3;
	public static final int COL_REASON = 2;
	/**
	 * Constant value represents column ConsumerPolicy for compositionTable.
	 */
	public static final int COL_CP = 3;
	static final double iniValue = 0.5;
	public static Slider sliderCredibility;
	private Text valueCredibility;
	private Slider sliderAvailability;
	private Text valueAvailability;
	public static Slider sliderValidness;
	private Text valueValidness;
	public static Slider sliderTrustworthiness;
	private Text valueTrustworthiness;
	
	public  static Text textURL;
	private Label labelURL;
	private Button buttonURL;
	
	/**
	 * Generator of Random valye.
	 */
	Random randomGenerator = new Random();

	/**
	 * The constructor.
	 */
	public ScpmUI() {
		plugin = this;
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 * 
	 * @param parent
	 *            The parent composite that holds the interface elements.
	 */
	public void createPartControl(final Composite parent) {
		// set labels and buttons to open BPMN20.XML files

		parent.setLayout(null);

		compositeSelect = new Composite(parent, SWT.BORDER);
		compositeSelect.setLayout(null);
		compositeSelect.setBounds(1, 1, 250, 350);

		labelAvailable = new Label(compositeSelect, 0);
		labelAvailable.setText("Available Composition Plans:");
		labelAvailable.setBounds(1, 1, 248, 20);

		compositionTable = new Table(compositeSelect, SWT.CHECK | SWT.SINGLE
				| SWT.FULL_SELECTION);
		compositionTable.setHeaderVisible(true);
		compositionTable.setLinesVisible(true);
		compositionTable.setBounds(2, 23, 240, 200);

		colID = new TableColumn(compositionTable, SWT.CENTER, COL_ID);
		colID.setText("ID");
		colID.pack();

		colName = new TableColumn(compositionTable, SWT.CENTER, COL_NAME);
		colName.setText("Location");
		colName.pack();

		colAgreementTemplate = new TableColumn(compositionTable, SWT.CENTER,
				COL_AT);
		colAgreementTemplate.setText("AgreeTemps");
		colAgreementTemplate.pack();

		colConsumerPolicy = new TableColumn(compositionTable, SWT.CENTER,
				COL_CP);
		colConsumerPolicy.setText("Policy");
		colConsumerPolicy.pack();

		buttonSelect = new Button(compositeSelect, SWT.PUSH);
		buttonSelect.setText("Verify All");
		buttonSelect.setBounds(10, 260, 80, 25);
		// buttonSelect.setVisible(false);

		buttonClear = new Button(compositeSelect, SWT.PUSH);
		buttonClear.setText("Delete All");
		buttonClear.setBounds(150, 228, 80, 25);

		buttonBrowse = new Button(compositeSelect, SWT.CENTER);
		buttonBrowse.setText("Add Local Plan");
		buttonBrowse.setBounds(1, 228, 100, 25);

		buttonRecomposition = new Button(compositeSelect, SWT.CENTER);
		buttonRecomposition.setText("Recompose");
		buttonRecomposition.setBounds(95, 260, 70, 25);

		buttonReconfiguration = new Button(compositeSelect, SWT.CENTER);
		buttonReconfiguration.setText("Reconfigure");
		buttonReconfiguration.setBounds(170, 260, 70, 25);
		

		labelURL = new Label (compositeSelect, 0);
		labelURL.setText("SCPM service address:");
		labelURL.setBounds(1, 290, 160, 15);
		
		buttonURL = new Button(compositeSelect, SWT.CHECK);
		buttonURL.setSelection(false);
		buttonURL.setText("Edit");
		buttonURL.setLocation(170,290);
		buttonURL.pack();
		
		textURL = new Text (compositeSelect, SWT.BORDER | SWT.MULTI);
		textURL.setText("http://ec2-54-235-118-152.compute-1.amazonaws.com:9092/scpm?wsdl");
		textURL.setBounds(1, 310, 240, 20);
		textURL.setEnabled(false);
		
		compositeOrder = new Composite(parent, SWT.BORDER);
		compositeOrder.setLayout(null);
		compositeOrder.setVisible(false);
		compositeOrder.setBounds(1, 1, 250, 350);
		
		

		labelSecured = new Label(compositeOrder, 0);
		labelSecured.setText("Verified Composition Plans:");
		labelSecured.setBounds(1, 1, 248, 20);

		compositionTable_Secured = new Table(compositeOrder, SWT.SINGLE
				| SWT.FULL_SELECTION);
		compositionTable_Secured.setHeaderVisible(true);
		compositionTable_Secured.setLinesVisible(true);
		compositionTable_Secured.setBounds(2, 23, 240, 200);

		colID_Secured = new TableColumn(compositionTable_Secured, SWT.CENTER,
				COL_ID);
		colID_Secured.setText("ID");
		colID_Secured.pack();

		colName_Secured = new TableColumn(compositionTable_Secured, SWT.CENTER,
				COL_NAME);
		colName_Secured.setText("Plans");
		colName_Secured.setWidth(88);;

		colResult_Secured = new TableColumn(compositionTable_Secured,
				SWT.CENTER, COL_RESULT);
		colResult_Secured.setText("Result");
		colResult_Secured.setWidth(80);
		
		colValue_Secured = new TableColumn(compositionTable_Secured,
				SWT.CENTER, COL_VALUE);
		colValue_Secured.setText("Value");
		colValue_Secured.pack();

		labelOrderBy = new Label(compositeOrder, 0);
		labelOrderBy.setText("Order Criteria:");
		labelOrderBy.setBounds(1, 228, 80, 20);
		
		buttonOrder = new Button(compositeOrder, SWT.PUSH);
		buttonOrder.setText("Order/Rank");
		buttonOrder.setBounds(90, 225, 70, 25);
			

		buttonBack = new Button(compositeOrder, SWT.PUSH);
		buttonBack.setText("Back");
		buttonBack.setBounds(170, 225, 70, 25);

		// criteriaList = new Combo(compositeOrder, SWT.DROP_DOWN | SWT.BORDER |
		// SWT.READ_ONLY);
		// criteriaList.setBounds(1, 250, 100, 25);
		// loadOrderCriteria();

		sliderTrustworthiness = new Slider(compositeOrder, SWT.HORIZONTAL);
		sliderTrustworthiness.setBounds(1, 255, 110, 20);
		sliderTrustworthiness.setMaximum(104);
		sliderTrustworthiness.setMinimum(0);
		sliderTrustworthiness.setIncrement(1);
		sliderTrustworthiness.setPageIncrement(5);
		sliderTrustworthiness.setThumb(4);
		valueTrustworthiness = new Text(compositeOrder, SWT.BORDER | SWT.MULTI);
		valueTrustworthiness.setEditable(false);
		valueTrustworthiness.setBounds(120, 255, 125, 20);

		sliderTrustworthiness.setSelection(50);
		valueTrustworthiness.setText("Trustworthiness: "
				+ roundTwoDecimals(iniValue));

		sliderCredibility = new Slider(compositeOrder, SWT.HORIZONTAL);
		sliderCredibility.setBounds(1, 280, 110, 20);
		sliderCredibility.setMaximum(104);
		sliderCredibility.setMinimum(0);
		sliderCredibility.setIncrement(1);
		sliderCredibility.setPageIncrement(5);
		sliderCredibility.setThumb(4);
		valueCredibility = new Text(compositeOrder, SWT.BORDER | SWT.MULTI);
		valueCredibility.setEditable(false);
		valueCredibility.setBounds(120, 280, 125, 20);

		sliderCredibility.setSelection(50);
		valueCredibility.setText("Credibility: " + roundTwoDecimals(iniValue));

		sliderValidness = new Slider(compositeOrder, SWT.HORIZONTAL);
		sliderValidness.setBounds(1, 305, 110, 20);
		sliderValidness.setMaximum(104);
		sliderValidness.setMinimum(0);
		sliderValidness.setIncrement(1);
		sliderValidness.setPageIncrement(5);
		sliderValidness.setThumb(4);
		valueValidness = new Text(compositeOrder, SWT.BORDER | SWT.MULTI);
		valueValidness.setEditable(false);
		valueValidness.setBounds(120, 305, 125, 20);

		sliderValidness.setSelection(50);
		valueValidness.setText("Validness: " + roundTwoDecimals(iniValue));

		sliderAvailability = new Slider(compositeOrder, SWT.HORIZONTAL);
		sliderAvailability.setBounds(1, 390, 160, 40);
		sliderAvailability.setMaximum(104);
		sliderAvailability.setMinimum(0);
		sliderAvailability.setIncrement(1);
		sliderAvailability.setPageIncrement(5);
		sliderAvailability.setThumb(4);
		valueAvailability = new Text(compositeOrder, SWT.BORDER | SWT.MULTI);
		valueAvailability.setEditable(false);
		valueAvailability.setBounds(165, 390, 100, 40);

		sliderAvailability.setSelection(50);
		valueAvailability
				.setText("VerifyValue:\n" + roundTwoDecimals(iniValue));
		sliderAvailability.setVisible(false);
		valueAvailability.setVisible(false);

		// criteriaList.add("Trustworthiness");
		// criteriaList.add("Breach Reporting");


		labelUnSecured = new Label(compositeOrder, 0);
		labelUnSecured.setText("UnSecured Composition Plans:");
		labelUnSecured.setBounds(250, 1, 180, 20);

		compositionTable_UnSecured = new Table(compositeOrder, SWT.SINGLE
				| SWT.FULL_SELECTION);
		compositionTable_UnSecured.setHeaderVisible(true);
		compositionTable_UnSecured.setLinesVisible(true);
		compositionTable_UnSecured.setBounds(250, 23, 240, 200);
		compositionTable_UnSecured.setVisible(false);

		colID_UnSecured = new TableColumn(compositionTable_UnSecured,
				SWT.CENTER, COL_ID);
		colID_UnSecured.setText("ID");
		colID_UnSecured.pack();

		colName_UnSecured = new TableColumn(compositionTable_UnSecured,
				SWT.CENTER, COL_NAME);
		colName_UnSecured.setText("Composition Plans");
		colName_UnSecured.pack();

		colReason_UnSecured = new TableColumn(compositionTable_UnSecured,
				SWT.CENTER, COL_REASON);
		colReason_UnSecured.setText("Explanation");
		colReason_UnSecured.pack();


		compositeServices = new Composite(parent, SWT.BORDER);
		compositeServices.setLayout(new GridLayout(1, true));
		compositeServices.setVisible(false);
		labelServices = new Label(compositeServices, 0);
		labelServices
				.setText("Aniketos Services in selected composition plan:");
		serviceTable = new Table(compositeServices, SWT.CHECK | SWT.SINGLE
				| SWT.FULL_SELECTION);
		serviceTable.setHeaderVisible(true);
		serviceTable.setLinesVisible(true);
		colID_Services = new TableColumn(serviceTable, SWT.CENTER, COL_ID);
		colID_Services.setText("Service ID");
		colID_Services.pack();
		

		/**
		 * listener for browse button. Find a composition plan in the local
		 * system.
		 */
		buttonBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// the duplication judgement here is based on the file location,
				// it could be and should be changed to be based on composition
				// ID later.
				String fileBPMN = getFileName();
								
				if (fileBPMN == null)
					return;
				
				String fileContent = fileOpen (fileBPMN);
				String planID = BPMNParser.getProcessId(BPMNParser.getDocument(fileContent));
				
				for (int itemNum = 0; itemNum < compositionTable.getItemCount(); itemNum++) {
					String name = compositionTable.getItem(itemNum).getText(
							COL_ID);
					if (planID.equals(name)) {
						
						MessageDialog.openError(parent.getShell(), "Error",
								"NComposition plan with the same ID already exists! ");
					
						return;
					}
				}
				
				String planLocation = moveToWorkspace(fileBPMN);
				int end = planLocation.lastIndexOf('\\');
				if (end ==-1)
					end = planLocation.lastIndexOf('/');
				String planPath = planLocation.substring(0, end-1);

				String atLocation = getATLocation(planPath);
				
				String cpLocation = getCPLocation (planPath);
				
				TableItem compositionPlan = new TableItem(compositionTable,
						SWT.NULL);
				compositionPlan.setText(new String[] {
						planID + "", planLocation,
						atLocation, cpLocation });				

			}
		});
		// temporary code to test SPDM
//		buttonSPDM.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent event) {
//				ICompositionPlan p = new CompositionPlan ("23");
//				p.setCompositionPlanID("3");
//				saveCompositionPlan(p, "D:/eclipse/runtime-New_configuration/LocalProject/diagrams/Local/CompositionPlans");
//				//planner = Activator.getDefault().getSCPM();
//				//ICompositionPlan testS = planner.inputSPDMData();
//
//			}
//		});
		// temporary code to test CSVM
//		buttonCSVM.addSelectionListener(new SelectionAdapter() {
//
//			public void widgetSelected(SelectionEvent event) {
//
//				planner = Activator.getDefault().getSCPM();
//
//				String exampleBPMN = "";
//				String exampleActiviti = "";
//
//				try {
//					exampleBPMN = readFileAsString(this.getClass()
//							.getClassLoader().getResource(csvmBPMN)
//							.openStream());
//					exampleActiviti = readFileAsString(this.getClass()
//							.getClassLoader().getResource(csvmActiviti)
//							.openStream());
//				} catch (IOException e) {
//
//					e.printStackTrace();
//				}
//
//				planner.testCSVM(exampleBPMN, exampleActiviti);
//
//			}
//		});

		buttonRecomposition.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String exampleBPMN = "";
				String exampleCP = "";
				String exampleAT = "";

				try {
					exampleBPMN = readFileAsString(this.getClass()
							.getClassLoader().getResource(BPMNplan)
							.openStream());
					exampleCP = readFileAsString(this.getClass()
							.getClassLoader().getResource(policyFile)
							.openStream());
					exampleAT = readFileAsString(this.getClass()
							.getClassLoader().getResource(templateFile)
							.openStream());
				} catch (IOException e) {

					e.printStackTrace();
				}
				ICompositionPlan backupBPMNdiagram = new CompositionPlan(
						exampleBPMN);
				IAgreementTemplate agreementTemplate = new AgreementTemplate(
						"testingID");
				IConsumerPolicy consumerPolicy = new ConsumerPolicy();
				// TODO: at the moment use both ways to initialise agreement
				// template and consumer policy
				agreementTemplate.setXML(exampleAT);
				String[] xmlContents = new String[1];
				xmlContents[0] = exampleAT;
				agreementTemplate.setXmlContents(xmlContents);
				
				String[] xmlContents1 = new String[1];
				consumerPolicy.setXML(exampleCP);
				xmlContents1[0] = exampleCP;
				consumerPolicy.setXmlContents(xmlContents1);

				
				ICompositionPlan testS = SCPMClient.Recomposition(
						backupBPMNdiagram, "infotast", agreementTemplate,
						consumerPolicy, textURL.getText());
				if (testS != null)
					System.out.println(testS.getBPMNXML());
				else
					System.out.println("No new composition created");

			}
		});

		buttonReconfiguration.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String exampleBPMN = "";
				String exampleAT = "";
				String exampleCP = "";

				try {
					exampleBPMN = readFileAsString(this.getClass()
							.getClassLoader().getResource(BPMNplan)
							.openStream());
					exampleCP = readFileAsString(this.getClass()
							.getClassLoader().getResource(policyFile)
							.openStream());
					exampleAT = readFileAsString(this.getClass()
							.getClassLoader().getResource(templateFile)
							.openStream());
				} catch (IOException e) {

					e.printStackTrace();
				}
				ICompositionPlan backupBPMNdiagram = new CompositionPlan(
						exampleBPMN);
				IAgreementTemplate agreementTemplate = new AgreementTemplate(
						"testingID");
				IConsumerPolicy consumerPolicy = new ConsumerPolicy();
				// TODO: at the moment use both ways to initialise agreement
				// template and consumer policy
				agreementTemplate.setXML(exampleAT);
				String[] xmlContents = new String[1];
				xmlContents[0] = exampleAT;
				agreementTemplate.setXmlContents(xmlContents);

				String[] xmlContents1 = new String[1];
				consumerPolicy.setXML(exampleCP);
				xmlContents1[0] = exampleCP;
				consumerPolicy.setXmlContents(xmlContents1);

				
				ICompositionPlan testS = SCPMClient.Reconfiguration(
						backupBPMNdiagram, agreementTemplate, consumerPolicy, textURL.getText());
				System.out.println(testS.getBPMNXML());

			}
		});

		/**
		 * listener for 'clear all' button. Delete all composition plans in the
		 * table.
		 */
		buttonClear.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				
				boolean b = MessageDialog.openQuestion(parent.getShell(), "Warning", "Do you really want to delete all the composition plans in the table?");
			
				if (b)
					compositionTable.removeAll();
				return;

			}
		});

		/**
		 * listener for verification button. Verify composition plans one by
		 * one.
		 */
		
		buttonSelect.addSelectionListener(new VerifyAllSelectionAdapter(parent.getShell())); 
		buttonOrder.addSelectionListener(new OrderSelectionAdapter(parent.getShell())); 
		
		buttonURL.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event) {
				if (buttonURL.getSelection())
					textURL.setEnabled(true);
				else textURL.setEnabled(false);
			}
		}	
		);
	

		/**
		 * go back to the available table
		 */

		buttonBack.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				compositeSelect.setVisible(true);
				compositeOrder.setVisible(false);
			}
		});
		
		

		/**
		 * listener for table. when a composition plan is selected, the service
		 * table shows the FlowElement (IDs) in the composition plan It uses the
		 * planner's parser and does not support Call Activity at this moment.
		 */
		compositionTable.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				if (compositionTable.getSelectionCount() == 0)
					return;

				String bpmnString = "";
				TableItem[] selection = compositionTable.getSelection();
				for (int i = 0; i < selection.length; i++)
					// bpmnString = fileOpen(selection[i].getText(COL_NAME));
					bpmnString = selection[i].getText(COL_NAME);
				System.out.println("Selection={" + bpmnString + "}");

				ICompositionPlan planTemp = new CompositionPlan(bpmnString);

				List<String> serviceIDs = new Vector<String>();
				serviceIDs = decompositeComposition(planTemp);

				serviceTable.removeAll();
				for (String id : serviceIDs) {
					TableItem service = new TableItem(serviceTable, SWT.NULL);
					service.setText(new String[] { id });

				}
				colID_Services.pack();
				serviceTable.pack();
				compositeServices.pack();

				String fileName = "";
				TableItem[] selection1 = compositionTable.getSelection();
				for (int i = 0; i < selection1.length; i++)
					fileName = selection1[i].getText(COL_NAME);
				loadBPMN(fileName);

			}
		});

		/**
		 * listener for table. when a composition plan is selected, the service
		 * table shows the FlowElement (IDs) in the composition plan It uses the
		 * planner's parser and does not support Call Activity at this moment.
		 */
		compositionTable_Secured.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				if (compositionTable_Secured.getSelectionCount() == 0)
					return;

				String bpmnString = "";
				TableItem[] selection = compositionTable_Secured.getSelection();
				for (int i = 0; i < selection.length; i++)
					// bpmnString = fileOpen(selection[i].getText(COL_NAME));
					bpmnString = selection[i].getText(COL_NAME);
				System.out.println("Selection={" + bpmnString + "}");

				ICompositionPlan planTemp = new CompositionPlan(bpmnString);

				List<String> serviceIDs = new Vector<String>();
				serviceIDs = decompositeComposition(planTemp);

				serviceTable.removeAll();
				for (String id : serviceIDs) {
					TableItem service = new TableItem(serviceTable, SWT.NULL);
					service.setText(new String[] { id });

				}
				colID_Services.pack();
				serviceTable.pack();
				compositeServices.pack();

				String fileName = "";
				TableItem[] selection1 = compositionTable_Secured
						.getSelection();
				for (int i = 0; i < selection1.length; i++)
					fileName = selection1[i].getText(COL_NAME);
				loadBPMN(fileName);

			}
		});

		/**
		 * listener for table. when a composition plan is selected, the service
		 * table shows the FlowElement (IDs) in the composition plan It uses the
		 * planner's parser and does not support Call Activity at this moment.
		 */
		compositionTable_UnSecured.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				if (compositionTable_UnSecured.getSelectionCount() == 0)
					return;

				String bpmnString = "";
				TableItem[] selection = compositionTable_UnSecured
						.getSelection();
				for (int i = 0; i < selection.length; i++)
					// bpmnString = fileOpen(selection[i].getText(COL_NAME));
					bpmnString = selection[i].getText(COL_NAME);
				System.out.println("Selection={" + bpmnString + "}");

				ICompositionPlan planTemp = new CompositionPlan(bpmnString);

				List<String> serviceIDs = new Vector<String>();
				serviceIDs = decompositeComposition(planTemp);

				serviceTable.removeAll();
				for (String id : serviceIDs) {
					TableItem service = new TableItem(serviceTable, SWT.NULL);
					service.setText(new String[] { id });

				}
				colID_Services.pack();
				serviceTable.pack();
				compositeServices.pack();

				String fileName = "";
				TableItem[] selection1 = compositionTable_UnSecured
						.getSelection();
				for (int i = 0; i < selection1.length; i++)
					fileName = selection1[i].getText(COL_NAME);
				loadBPMN(fileName);

			}
		});
		// /**
		// * listener for table. when right click a service in the table, a
		// popup menu shows.
		// */
		// compositionTable_UnSecured.addMouseListener(new MouseAdapter() {
		// public void mouseDown(final MouseEvent e)
		// {
		// if (e.button != 3) return;
		// targetService = compositionTable_UnSecured.getItem(new
		// Point(e.x,e.y));
		// }
		// });
		//
		// final Menu menuForUnsecuredTable = new
		// Menu(compositionTable_UnSecured);
		// compositionTable_UnSecured.setMenu(menuForUnsecuredTable);
		//
		// final MenuItem reasonItem = new MenuItem(menuForUnsecuredTable,
		// SWT.NONE);
		// reasonItem.setText("Show unsecured reason");
		// /**
		// * listener for popup menu "show unsecured reason".
		// */
		// reasonItem.addSelectionListener(new SelectionAdapter() {
		// public void widgetSelected(final SelectionEvent e)
		// {
		// if (targetService==null) return;
		//
		// }
		// });

		/**
		 * listener for atomic service table. when right click a service in the
		 * table, a popup menu shows.
		 */
		serviceTable.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if (e.button != 3)
					return;
				targetService = serviceTable.getItem(new Point(e.x, e.y));
			}
		});
		final Menu menu = new Menu(serviceTable);
		serviceTable.setMenu(menu);

		final MenuItem replaceItem = new MenuItem(menu, SWT.NONE);
		replaceItem.setText("Replace service");
		final MenuItem propertyItem = new MenuItem(menu, SWT.NONE);
		propertyItem.setText("Show Properties");

		replaceItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (targetService == null)
					return;

			}
		});

		sliderCredibility.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int perspectiveValue = sliderCredibility.getSelection();
				valueCredibility.setText("Credibility: "
						+ roundTwoDecimals(perspectiveValue / 100.0));
			}
		});
		sliderAvailability.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int perspectiveValue = sliderAvailability.getSelection();
				valueAvailability.setText("VerifyValue: "
						+ roundTwoDecimals(perspectiveValue / 100.0));
			}
		});
		sliderValidness.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int perspectiveValue = sliderValidness.getSelection();
				valueValidness.setText("Validness: "
						+ roundTwoDecimals(perspectiveValue / 100.0));
			}
		});
		sliderTrustworthiness.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int perspectiveValue = sliderTrustworthiness.getSelection();
				valueTrustworthiness.setText("Trustworthiness: "
						+ roundTwoDecimals(perspectiveValue / 100.0));
			}
		});

	}

	public static double roundTwoDecimals(double d) {
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setDecimalSeparator('.');
		DecimalFormat twoDForm = new DecimalFormat("#.##", symbols);
		return Double.valueOf(twoDForm.format(d));
	}



	/**
	 * Prompts the user for, and returns a file name on the local system. The
	 * file open dialogue box will be restricted to showing .xml and .bpmn20.xml
	 * files only
	 * 
	 * @return Name of the file that has been selected by the user.
	 */
	private String getFileName() {
		FileDialog dialog = new FileDialog(compositionTable.getShell(),
				SWT.OPEN);
		dialog.setText("Open");
		String[] filterExt = { "*.bpmn20.xml; *.xml", };
		dialog.setFilterExtensions(filterExt);
		String fileName = dialog.open();
		return fileName;
	}

	/**
	 * @see getFileName().
	 * @see loadBPMN() Move the user selected composition plan into workspace.
	 *      The selected composition plan will be browsable from the package
	 *      explore. It is used when ask the system to open a file automatically
	 *      (as in loadBPMN()). The files are saved at /AniketosFiles/BPMNdiagrams
	 * 
	 * @param fileName
	 *            of the file that has been selected by the user.
	 */
	private String moveToWorkspace(String fileName) {
		
		String planContent = fileOpen(fileName);
		String planID = BPMNParser.getProcessId(BPMNParser.getDocument(planContent));
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		String savedLocation = null;
		ICompositionPlan plan = new CompositionPlan (planContent);
		plan.setCompositionPlanID(planID);
		
		savedLocation = root.getLocation()+"\\LocalProject\\diagrams\\Local\\CompositionPlans";
		savedLocation = saveToWorkspace(plan, savedLocation);
		
		return savedLocation;
	}

	/**
	 * Save the received composition plan (from Service Composition Framework)
	 * into workspace. The received composition plan will be browsable from the
	 * package explore. It is used when ask the system to open a file
	 * automatically (as in loadBPMN()). The files are saved at
	 * /AniketosFiles/BPMNdiagrams. *
	 * 
	 * @param compositionPlan
	 *            Composition Plan that received from SCF.
	 * @return Location of the composition plan file in local system.
	 * 
	 * @see saveCompositionPlan{}
	 * @see moveToWorkspace()
	 * @see loadBPMN
	 */
	private String saveToWorkspace(ICompositionPlan compositionPlan, String path) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IFile file = null;
		
		String tempFile = path.replace("\\", "/");
		String filePath[] = tempFile.split("/");
		int pathLength = filePath.length;

		String savedLocation = null;
		try {

			IProject bpmnProject = root.getProject(filePath[pathLength-4]);
			if (!bpmnProject.exists()) {
				System.out.print("xxx project not exists xxx");
				bpmnProject.create(null);
			}

			if (!bpmnProject.isOpen())
				bpmnProject.open(null);

			IFolder folder = bpmnProject.getFolder(filePath[pathLength-3]);
			if (!folder.exists()) {
				System.out.print("xxx Folder not exists xxx");
				folder.create(true, true, null);
			}
			folder = folder.getFolder(filePath[pathLength-2]);
			if (!folder.exists()) {
				System.out.print("xxx Folder not exists xxx");
				folder.create(true, true, null);
			}
			folder = folder.getFolder(filePath[pathLength-1]);
			if (!folder.exists()) {
				System.out.print("xxx Folder not exists xxx");
				folder.create(true, true, null);
			}

			String fileName = compositionPlan.getCompositionPlanID()
					+ ".bpmn20.xml";
			file = folder.getFile(fileName);
			if (file.exists())
				file.delete(true, null);
			InputStream source = new ByteArrayInputStream(compositionPlan
					.getBPMNXML().getBytes());
			file.create(source, false, null);

			// System.out.println("Diagram full path:"+file.getFullPath().toOSString());
			// System.out.println(root.getLocation());
			savedLocation = root.getLocation()
					+ file.getFullPath().toOSString();
			savedLocation = savedLocation.replace('/', '\\');

		} catch (Exception e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}

		return savedLocation;
	}
	
private String getCPLocation(String path) {
		
		int last = path.lastIndexOf("\\");
		if (last ==-1)
			last = path.lastIndexOf("/");
		
		String filePath = path.substring(0, last+1);
		filePath += "ConsumerPolicies";
		filePath = filePath.replace('/', '\\');
		return filePath;
	}
	
	private String getATLocation(String path) {
		
		int last = path.lastIndexOf("\\");
		if (last ==-1)
			last = path.lastIndexOf("/");
		
		String filePath = path.substring(0, last+1);
		filePath += "AgreementTemplates";
		filePath = filePath.replace('/', '\\');
		
		return filePath;
	}

	

	/**
	 * Load the composition plan as Activiti diagram. It is used when user
	 * manually selects composition plans from the table. The method uses system
	 * API to load the *.bpmn20.xml file first. That will create a *.activiti
	 * file automatically. It then close the *.bpmn20.xml file and load the
	 * *.activiti file instead. During the process it also abstracts any
	 * security extensions that embedded in the composition plan by SCF. If
	 * there is any extensions found, it saves them in the activiti file as the
	 * system will not deal with security extensions by default.
	 * 
	 * The files are loaded from /AniketosFiles/BPMNdiagrams in the workspace.
	 * 
	 * @param fileName
	 *            File name with full path to the bpmn file.
	 * 
	 * @see saveToWorkspace()
	 * @see moveToWorkspace()
	 * @see getExtensions(); setExtensions()
	 */
	private void loadBPMN(String fileName) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		String fileActiviti = "";
			
		String tempFile = fileName.replace("\\", "/");
		String filePath[] = tempFile.split("/");
		int idx = tempFile.lastIndexOf("/");

		if (idx >= 0)
			fileActiviti = tempFile.substring(idx + 1);
		else return;
		
		fileActiviti.toLowerCase();
		idx = fileActiviti.lastIndexOf(".bpmn20.xml");
		if (idx >= 0)
			fileActiviti = fileActiviti.substring(0, idx);
		fileActiviti = fileActiviti.concat(".activiti");

		String bpmnEditorId = "org.activiti.designer.editor.bpmn";
		String diagramEditorId = "org.activiti.designer.editor.multiPageEditor";
		IFile fileBPMN = null;
		IFile fileDiagram = null;
		IFolder folder = null;
		int pathLength = filePath.length;
		try {

			IProject bpmnProject = root.getProject(filePath[pathLength-5]);
			if (!bpmnProject.exists()) {
				System.out.print("xxx project not exists xxx");
				bpmnProject.create(null);
			}

			if (!bpmnProject.isOpen())
				bpmnProject.open(null);

			folder = bpmnProject.getFolder(filePath[pathLength-4]);
			if (!folder.exists()) {
				System.out.print("xxx Folder not exists xxx");
				folder.create(true, true, null);
			}
			folder = folder.getFolder(filePath[pathLength-3]);
			if (!folder.exists()) {
				System.out.print("xxx Folder not exists xxx");
				folder.create(true, true, null);
			}
			folder = folder.getFolder(filePath[pathLength-2]);
			if (!folder.exists()) {
				System.out.print("xxx Folder not exists xxx");
				folder.create(true, true, null);
			}
			
			fileBPMN = folder.getFile(filePath[pathLength-1]);

		} catch (Exception e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}

		// Get the view
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();

		IEditorInput bpmnInput = new FileEditorInput(fileBPMN);
		try {
			IEditorPart bpmnEditor = page.openEditor(bpmnInput, bpmnEditorId);
			page.closeEditor(bpmnEditor, true);
			fileDiagram = folder.getFile(fileActiviti);

			File bpmnFile = fileBPMN.getRawLocation().makeAbsolute().toFile();
			DocumentBuilderFactory bpmnFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder bpmnBuilder = bpmnFactory.newDocumentBuilder();
			Document bpmnDoc = bpmnBuilder.parse(bpmnFile);
			bpmnDoc.getDocumentElement().normalize();

			File activitiFile = fileDiagram.getRawLocation().makeAbsolute()
					.toFile();
			DocumentBuilderFactory activitiFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder activitiBuilder = activitiFactory
					.newDocumentBuilder();
			Document activitiDoc = activitiBuilder.parse(activitiFile);
			activitiDoc.getDocumentElement().normalize();

			NodeList allList = activitiDoc.getDocumentElement().getChildNodes();
			List<org.w3c.dom.Element> bpmn2Element = new Vector<org.w3c.dom.Element>();
			for (int index = 0; index < allList.getLength(); index++) {

				Node bpmn2Node = allList.item(index);
				if (bpmn2Node.getNodeType() == Node.ELEMENT_NODE) {

					org.w3c.dom.Element eElement = (org.w3c.dom.Element) bpmn2Node;
					if (eElement.getTagName().startsWith("bpmn2:")) {
						bpmn2Element.add(eElement);
						// System.out.println("bpmn2 ID : " + getAttValue("id",
						// eElement));
					}
				}
			}

			NodeList serviceList = bpmnDoc.getElementsByTagName("serviceTask");

			for (int index = 0; index < serviceList.getLength(); index++) {
				org.w3c.dom.Element serviceElement = (org.w3c.dom.Element) serviceList
						.item(index);
				String serviceID = getAttValue("id", serviceElement);
				List<String> serviceAttributes = new Vector<String>();
				serviceAttributes = getExtensions(serviceElement);
				setExtensions(serviceID, serviceAttributes, bpmn2Element);
			}

			for (int index = 0; index < bpmn2Element.size(); index++)
				System.out.println(getAttValue("expression",
						bpmn2Element.get(index)));

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(activitiDoc);
			StreamResult result = new StreamResult(fileDiagram.getRawLocation()
					.makeAbsolute().toFile());
			transformer.transform(source, result);

			IEditorInput diagramInput = new FileEditorInput(fileDiagram);
			page.openEditor(diagramInput, diagramEditorId);

		} catch (PartInitException e) {
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return;

	}
	


	/**
	 * Opens and reads the file specified, returning the contents as a string
	 * 
	 * @param name
	 *            The name of the file
	 * @return the contents of the file in String format.
	 */
	public static String fileOpen(String name) {
		StringBuffer buf = new StringBuffer();

		try {
			InputStream streamTemp = new FileInputStream(name);

			int ch;

			try {
				while ((ch = streamTemp.read()) != -1)
					buf.append((char) ch);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException fe) {

		}
		// System.out.println(buf.toString());
		return buf.toString();
	}

	/**
	 * Obtains the plan name/location given its ID
	 * 
	 * @param bpmnID
	 *            A valid BPMN ID
	 * @return the BPMN name and location
	 */
	public static String getLocation(String bpmnID) {
		String tempString = "";
		for (int itemNum = 0; itemNum < compositionTable.getItemCount(); itemNum++) {
			if (compositionTable.getItem(itemNum).getText(COL_ID)
					.equals(bpmnID))
				tempString = compositionTable.getItem(itemNum)
						.getText(COL_NAME);
		}

		return tempString;
	}

	/**
	 * Save composition plan as local. The method is called by Service
	 * Composition Framework. The saved composition plan will appear in the
	 * composition plan table.
	 * 
	 * @param compositionPlan
	 *            A composition plan in XML format.
	 * @param agreementTemplates
	 *            Agreement templates for all the atomic service in the
	 *            composition plan.
	 * @param consumerPolicy
	 *            The consumer policy.
	 * 
	 * @see saveToWorkspace{}
	 */
	public void saveCompositionPlan(ICompositionPlan compositionPlan, String location) {

		String cpID = compositionPlan.getCompositionPlanID();
		for (int itemNum = 0; itemNum < compositionTable.getItemCount(); itemNum++) {
			String id = compositionTable.getItem(itemNum).getText(COL_ID);
			if (cpID.equals(id)) {

				return;
			}
		}

		TableItem compositionPlanTemp = new TableItem(compositionTable,
				SWT.NULL);

		String savedLocation = saveToWorkspace(compositionPlan, location);
		String savedCPLocation = getCPLocation (location);
		String savedATLocation = getATLocation (location);
		compositionPlanTemp.setText(new String[] {
				cpID, savedLocation, savedATLocation, savedCPLocation });
				
	}

	/**
	 * Clear the composition plan table. The method is called by Service
	 * Composition Framework.
	 * 
	 */
	public void clearCompositionPlans() {
		compositionTable.removeAll();
	}

	/**
	 * update the consumer policy in SCF to include the order criteira
	 * 
	 */
	public IConsumerPolicy updateConsumerPolicy(IConsumerPolicy consumerPolicy) {
		String orderCriteria = "\n<!--OrderCriteria";
		double trustworthiness = roundTwoDecimals(sliderTrustworthiness
				.getSelection() / 100.0);
		orderCriteria = orderCriteria + " Trustworthiness " + trustworthiness;
		double credibility = roundTwoDecimals(sliderCredibility.getSelection() / 100.0);
		orderCriteria = orderCriteria + " Credibility " + credibility;
		double validness = roundTwoDecimals(sliderValidness.getSelection() / 100.0);
		orderCriteria = orderCriteria + " Validness " + validness;
		orderCriteria += " -->";
		// TODO: may consider use the getXML and setXML for order value only if
		// it will no longer be used for real consumer content.
		String policyXML = consumerPolicy.getXML();
		policyXML += orderCriteria;
		IConsumerPolicy updatePolicy = new ConsumerPolicy();
		updatePolicy = consumerPolicy;
		updatePolicy.setXML(policyXML);
		return updatePolicy;
	}

	/**
	 * It is compulsory to implement this method when implementing
	 * WorkbenchPart. We'll use it to ask for our compare button to get the
	 * focus.
	 */
	public void setFocus() {
		this.buttonSelect.setFocus();

	}

	/**
	 * Return the shared instance.
	 * 
	 * @return The shared instance of the plugin.
	 */
	public static ScpmUI getUI() {
		return plugin;
	}

	// /**
	// * Read input file and transforms it into Base64 String format.
	// * @param is Input stream that contains the file.
	// * @return String in base64 format.
	// */
	// private static String readFileAsBase64String(InputStream is) throws
	// java.io.IOException{
	//
	// ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	// int nRead;
	// byte[] data = new byte[16384];
	// while ((nRead = is.read(data, 0, data.length)) != -1) {
	// buffer.write(data, 0, nRead);
	// }
	// buffer.flush();
	// byte[] bytes = buffer.toByteArray();
	// is.close();
	// return Base64.encodeBase64String(bytes);
	// }

	/**
	 * Read input file and transforms it into String.
	 * 
	 * @param is
	 *            Input stream that contains the file.
	 * @return Output String.
	 */
	private static String readFileAsString(InputStream is)
			throws java.io.IOException {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();

		is.close();
		return buffer.toString();
	}

	// /**
	// * Transform a String into Base64 String format.
	// * @param s Input String.
	// * @return Output String in base64 format.
	// */
	// private static String stringToBase64String(String s) {
	//
	// byte[] bytes;
	//
	// try
	// {
	// bytes = s.getBytes("US-ASCII");
	// }
	// catch (UnsupportedEncodingException ue)
	// {
	// bytes = s.getBytes();
	// }
	// return Base64.encodeBase64String(bytes);
	//
	// }
	/*
	 * private Map<String, ISecurityProperty> getXMLValues(InputStream
	 * inputStream){ //String[] ret = new String[2]; Map<String,
	 * ISecurityProperty> ret = new HashMap<String, ISecurityProperty>(); try {
	 * DocumentBuilderFactory docBuilderFactory =
	 * DocumentBuilderFactory.newInstance(); DocumentBuilder docBuilder =
	 * docBuilderFactory.newDocumentBuilder(); Document doc =
	 * docBuilder.parse(inputStream);
	 * 
	 * // normalize text representation doc.getDocumentElement().normalize();
	 * 
	 * NodeList list = doc.getChildNodes(); list = list.item(0).getChildNodes();
	 * for(int i = 0; i<list.getLength(); i++){ Node node = list.item(i);
	 * if(node.getNodeType() == Node.ELEMENT_NODE){ ret.put(node.getNodeName(),
	 * new SecurityProperty(node.getNodeName(), node.getTextContent())); } }
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } return ret; }
	 */
	/**
	 * Decomposite the selected composition plan It uses the planner's parser.
	 */

	private List<String> decompositeComposition(ICompositionPlan compositionPlan) {
		List<String> serviceIDs = new Vector<String>();
		String bpmnString = compositionPlan.getBPMNXML();

		serviceIDs = getServiceIDs(bpmnString);

		return serviceIDs;
	}

	/**
	 * Decomposite the composition plan
	 * 
	 * @param inputPlan
	 *            the BPMN2 files location
	 * @return the serviceIDs in the composition plan. at this moment it only
	 *         looks for serviceTask which will be the type of Aniketos Task
	 */
	private List<String> getServiceIDs(String inputPlan) {

		List<String> serviceIDs = new Vector<String>();

		try {
			List<NodeList> translated = new Vector<NodeList>();

			File fXmlFile = new File(inputPlan);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("serviceTask");
			System.out.println("-----------------------");
			translated.add(nList);

			List<org.w3c.dom.Element> tasks = new Vector<org.w3c.dom.Element>();
			for (NodeList check : translated) {

				for (int temp = 0; temp < check.getLength(); temp++) {

					Node nNode = check.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
						tasks.add(eElement);

						System.out.println("Service ID : "
								+ getAttValue("id", eElement));
						System.out.println("Service Name : "
								+ getAttValue("name", eElement));

					}
				}
			}

			for (org.w3c.dom.Element service : tasks) {
				serviceIDs.add(getAttValue("id", service));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return serviceIDs;

	}

	/**
	 * Get the attribute value of an element by using XML parser. The input
	 * element must be a type of org.w3c.dom.Element
	 * 
	 * @param sAtt
	 *            The attribute name.
	 * @param eElement
	 *            The element to be parsed.
	 * @return The value of the attribute if found.
	 */
	private static String getAttValue(String sAtt, org.w3c.dom.Element eElement) {

		return eElement.getAttribute(sAtt);
	}

	/**
	 * @see loadBPMN When generates a *.activiti file from *.bpmn20.xml file,
	 *      The Activiti designer only copes with the original tags/elements.
	 *      Any security extensions that embedded in the composition plan by SCF
	 *      have to be abstracted manually. This method looks for security
	 *      extensions in the XML file.
	 * 
	 * @param serviceElement
	 *            The element to be parsed.
	 * @return Values of the extended attributes.
	 */
	private static List<String> getExtensions(org.w3c.dom.Element serviceElement) {

		List<String> attValues = new Vector<String>();
		NodeList attList = serviceElement
				.getElementsByTagName("activiti:field");

		for (int index = 0; index < attList.getLength(); index++) {

			Node attNode = attList.item(index);
			if (attNode.getNodeType() == Node.ELEMENT_NODE) {

				org.w3c.dom.Element attElement = (org.w3c.dom.Element) attNode;
				// have to guess the type of the value.
				NodeList valueList = attElement
						.getElementsByTagName("activiti:string");
				if (valueList.getLength() == 0)
					valueList = attElement
							.getElementsByTagName("activiti:expression");
				// assume there is only one child that contains the value
				org.w3c.dom.Element valueElement = (org.w3c.dom.Element) valueList
						.item(0);
				attValues.add(valueElement.getTextContent());

			}
		}
		return attValues;

	}

	/**
	 * @see loadBPMN
	 * @see getExtensions() When generates a *.activiti file from *.bpmn20.xml
	 *      file, The Activiti designer only copes with the original
	 *      tags/elements. Any security extensions that embedded in the
	 *      composition plan by SCF have to be abstracted manually. This method
	 *      saves security extensions into the activiti file.
	 * 
	 * @param id
	 *            The ID of element to be saved.
	 * @param attributes
	 *            Value of the extended attributes.
	 * @param bpmn2Element
	 *            The element to be parsed.
	 */

	private static void setExtensions(String id, List<String> attributes,
			List<org.w3c.dom.Element> bpmn2Element) {

		for (int index = 0; index < bpmn2Element.size(); index++) {
			// System.out.println(getAttValue("id", bpmn2Element.get(index)));
			if (getAttValue("id", bpmn2Element.get(index)).equals(id)) {
				String fieldPosition = getAttValue("fieldExtensions",
						bpmn2Element.get(index));
				StringTokenizer str = new StringTokenizer(fieldPosition);

				int attNumber = 0;

				while (str.hasMoreElements()) {
					String pString = (String) str.nextElement();
					int pInt = Integer.parseInt(pString.substring(1));
					// System.out.println(pInt);
					bpmn2Element.get(pInt - 1).setAttribute("expression",
							attributes.get(attNumber));
					attNumber++;
				}
			}
		}
	}

}