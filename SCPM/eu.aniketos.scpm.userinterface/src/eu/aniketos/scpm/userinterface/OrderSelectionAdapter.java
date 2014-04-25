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

package eu.aniketos.scpm.userinterface;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.impl.AgreementTemplate;
import eu.aniketos.scpm.data.OrderCriteria;
import eu.aniketos.scpm.data.impl.CompositionPlan;
import eu.aniketos.scpm.data.impl.ConsumerPolicy;
import eu.aniketos.scpm.impl.client.SCPMClient;
import eu.aniketos.scpm.userinterface.views.ScpmUI;

public class OrderSelectionAdapter extends SelectionAdapter {
	private final Shell shell;
	private String scpmURL;
	List<ICompositionPlan> orderedList = new Vector<ICompositionPlan>();
	List<ICompositionPlan> compositionPlans = new Vector<ICompositionPlan>();
	List<IAgreementTemplate> agreementTemplates = new Vector<IAgreementTemplate>();
	List<IConsumerPolicy> consumerPolicies = new Vector<IConsumerPolicy>();
	OrderCriteria criteria = new OrderCriteria();
	boolean errorExist = false;
	
	public OrderSelectionAdapter(Shell shell) {
		this.shell = shell;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		
		Job job = new Job("Ordering services") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Ordering services in process", 100);
				syncWithUiBefore();
				monitor.subTask("Calculating Trustworthiness");
				monitor.worked(20);
				if (!errorExist)
				{
					monitor.subTask("Connecting to the SPDM");
					monitor.worked(60);
					orderAll();
				}
				else return Status.CANCEL_STATUS;
				if (!errorExist)
				{
					monitor.subTask("Finishing ordering");
					monitor.worked(20);
					syncWithUiAfter();
				}
				else return Status.CANCEL_STATUS;
				
				return Status.OK_STATUS;
			}
			

		};
		job.setUser(true);
		job.schedule();
	}

	private void syncWithUiBefore() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				
		if (ScpmUI.compositionTable_Secured.getItemCount() < 1) {
			errorExist = true;
			MessageDialog.openError(shell, "Error",
					"There is no secured composition plan. Request recomposition or click back button to select other local composition plan in previous step.");
					
			return;
		}
		else 
		{
			errorExist = false;
			compositionPlans.clear();
			agreementTemplates.clear();
			consumerPolicies.clear();
			orderedList.clear();
		}

		
		for (int itemNum = 0; itemNum < ScpmUI.compositionTable_Secured
				.getItemCount(); itemNum++) {
			String originalString = ScpmUI.fileOpen(ScpmUI.compositionTable_Secured
					.getItem(itemNum).getText(ScpmUI.COL_NAME));
		
			ICompositionPlan planTemp = new CompositionPlan(originalString);
			String compositionId = ScpmUI.compositionTable_Secured.getItem(
					itemNum).getText(ScpmUI.COL_ID);
			planTemp.setCompositionPlanID(compositionId);
			compositionPlans.add(planTemp);
		
			// find the composition plan position in the original table
			// and get agreement templates and consumer policies
			//TODO: possible to use BPMNParser get real ID and the index
			int index = 0;
			while (!ScpmUI.compositionTable.getItem(index).getText(ScpmUI.COL_ID)
					.equals(compositionId)) {
				index++;
			}
			
			String cpID = planTemp.getCompositionPlanID();
			IAgreementTemplate templateTemp = new AgreementTemplate("");			
			templateTemp = loadAT (cpID, ScpmUI.compositionTable.getItem(index).getText(ScpmUI.COL_AT));
			if (templateTemp == null)
			{
				errorExist = true;
				MessageDialog.openError(shell, "Error",
						"No agreement template file found in the folder for composition: " +planTemp.getCompositionPlanID());
				return;
			}
			agreementTemplates.add(templateTemp);

			IConsumerPolicy policyTemp = new ConsumerPolicy();
			policyTemp = loadCP (ScpmUI.compositionTable.getItem(index).getText(
					ScpmUI.COL_CP));
			if (policyTemp == null)
			{
				errorExist = true;
				MessageDialog.openError(shell, "Error",
						"No consumer policy file found in the folder for composition: " +planTemp.getCompositionPlanID());
				return;
			}
			consumerPolicies.add(policyTemp);
		}

		
		double trustworthiness = ScpmUI.roundTwoDecimals(ScpmUI.sliderTrustworthiness
				.getSelection() / 100.0);
		criteria.addCriterion("Trustworthiness",
				Double.toString(trustworthiness));
		double credibility = ScpmUI.roundTwoDecimals(ScpmUI.sliderCredibility
				.getSelection() / 100.0);
		criteria.addCriterion("Credibility",
				Double.toString(credibility));
		double validness = ScpmUI.roundTwoDecimals(ScpmUI.sliderValidness
				.getSelection() / 100.0);
		criteria.addCriterion("Validness", Double.toString(validness));
		
		scpmURL = ScpmUI.textURL.getText();
		
		try {
			System.out.println("Connecting to SCPM");
			URL scpmurl = new URL(scpmURL);
		} catch (MalformedURLException e) {
			errorExist = true;
			MessageDialog.openError(shell, "Error",
					"Cannot find SCPM service!");
			return;
		}
			}
			
			
		});
	}

	private void orderAll(){
		errorExist = false;
		try {
			 orderedList = SCPMClient				
				.orderSecureCompositions(compositionPlans,
						consumerPolicies, agreementTemplates, criteria, scpmURL);
		}
		catch (Exception e){
			//errorExist = true;
			//syncWithUiMessage();
			e.printStackTrace();
//			//TODO://temporary solution for Tenerife demo
//			 orderedList = compositionPlans;
//			 for (int planIndex = 0; planIndex < orderedList.size(); planIndex++) {
//				 orderedList.get(planIndex).setBPMNXML("0");
//				 
//			 }
			 return;
		}
	}

	private void syncWithUiAfter() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
		
		int itemCount = ScpmUI.compositionTable_Secured.getItemCount();
		
		Map<String, String> mapItems = new HashMap<String, String>();
		for (int i = 0; i <itemCount; i++)
		{
			mapItems.put(ScpmUI.compositionTable_Secured.getItem(i).getText(ScpmUI.COL_ID), 
					ScpmUI.compositionTable_Secured.getItem(i).getText(ScpmUI.COL_REASON));
		}
		
		
		ScpmUI.compositionTable_Secured.removeAll();
		
		for (int planIndex = 0; planIndex < orderedList.size(); planIndex++) {
			ICompositionPlan existPlan = orderedList.get(planIndex);
			String planLocation = ScpmUI.getLocation(existPlan.getCompositionPlanID());
			
			
			String verifyResult = getVerifyResult (existPlan.getCompositionPlanID(), mapItems);
			String criterionValue = existPlan.getBPMNXML();// this field
															// subtlely
															// used to
															// save the
															// order
															// value
			
			TableItem securedPlan = new TableItem(
					ScpmUI.compositionTable_Secured, SWT.NULL);
			securedPlan.setText(new String[] {
					existPlan.getCompositionPlanID(), planLocation, verifyResult, criterionValue });
			// securedPlan.setText(new String[]
			// {existPlan.getCompositionPlanID(), planLocation});
		}

		ScpmUI.compositionTable_Secured.setSortColumn(ScpmUI.colValue_Secured);
		ScpmUI.compositionTable_Secured.setSortDirection(SWT.DOWN);
		


		//sortTable(ScpmUI.compositionTable_Secured, 2);
			}
		});

	}
	

	private void syncWithUiMessage() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openError(shell, "Error",
						"Error while trying to connect to the SCPM service.");
			}
	
		});

	}
	
	private void sortTable(Table table, int columnIndex) {

		TableItem[] items = table.getItems();
		Collator collator = Collator.getInstance(Locale.getDefault());

		for (int i = 0; i < items.length; i++) {
			String value1 = items[i].getText(columnIndex);
			for (int j = 0; j < i; j++) {
				String value2 = items[j].getText(columnIndex);
				if (collator.compare(value1, value2) > 0) {
					String[] values = { items[i].getText(ScpmUI.COL_ID),
							items[i].getText(ScpmUI.COL_NAME),
							items[i].getText(ScpmUI.COL_VALUE) }; // to be generalised
					items[i].dispose();
					TableItem item = new TableItem(table, SWT.NONE, j);
					item.setText(values);
					items = table.getItems();
					break;
				}
			}
		}

	}
	
	private String getVerifyResult(String planID, Map<String, String> mapItems){
		for (Map.Entry<String, String> entry : mapItems.entrySet()) {
			if (entry.getKey().equals(planID))
				return entry.getValue();
		}
		return null;
		
	}

	private IConsumerPolicy loadCP (String fileLocation){
		IConsumerPolicy cp = new ConsumerPolicy ();
		
		String[] consumerPolicies = new String[0];
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		
		String tempFile = fileLocation.replace("\\", "/");
		String filePath[] = tempFile.split("/");
		int pathLength = filePath.length;

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
		
			IResource[] files;
                
            files = folder.members();
            if (files.length == 0)
            {
            	return null;
            }
            else{
            	consumerPolicies = new String[files.length];
            	for(int i=0; i<files.length; i++){
                    String consumerPolicy = ScpmUI.fileOpen(files[i].getLocation().toString());
                    consumerPolicies[i] = consumerPolicy;
                        //log.debug("ConsumerPolicy found: "+consumerPolicy);
            	}
            
            }
        } catch (CoreException e1) {
                        //log.debug(e1);
        }

        cp.setXmlContents(consumerPolicies);
	
		return cp;

	}
private IAgreementTemplate loadAT (String atID, String fileLocation){
		
		IAgreementTemplate at = new AgreementTemplate (atID);
		String[] agreementTemplates = new String[0];
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		
		String tempFile = fileLocation.replace("\\", "/");
		String filePath[] = tempFile.split("/");
		int pathLength = filePath.length;

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
		
			IResource[] files;
                
            files = folder.members();
            if (files.length == 0)
            {
            	return null;
            }
            else{
            	agreementTemplates = new String[files.length];
            	for(int i=0; i<files.length; i++){
                    String agreementTemplate = ScpmUI.fileOpen(files[i].getLocation().toString());
                    agreementTemplates[i] = agreementTemplate;
                        //log.debug("ConsumerPolicy found: "+consumerPolicy);
            	}
            
            }
        } catch (CoreException e1) {
                        //log.debug(e1);
        }

        at.setXmlContents(agreementTemplates);
	
		return at;
	}

}
