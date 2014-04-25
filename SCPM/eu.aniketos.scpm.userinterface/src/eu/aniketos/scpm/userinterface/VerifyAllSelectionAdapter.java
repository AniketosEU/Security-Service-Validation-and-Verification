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
import java.util.List;
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
import org.eclipse.swt.widgets.TableItem;

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.impl.AgreementTemplate;
import eu.aniketos.scpm.ISelectResult;
import eu.aniketos.scpm.data.impl.CompositionPlan;
import eu.aniketos.scpm.data.impl.ConsumerPolicy;
import eu.aniketos.scpm.impl.client.SCPMClient;
import eu.aniketos.scpm.userinterface.views.ScpmUI;

public class VerifyAllSelectionAdapter extends SelectionAdapter {
	private final Shell shell;
	private String scpmURL;
	boolean errorExist = false;
	List<ISelectResult> suggestedList = new Vector<ISelectResult>();
	List<ICompositionPlan> compositionPlans = new Vector<ICompositionPlan>();
	List<IAgreementTemplate> agreementTemplates = new Vector<IAgreementTemplate>();
	List<IConsumerPolicy> consumerPolicies = new Vector<IConsumerPolicy>();

	
	public VerifyAllSelectionAdapter(Shell shell) {
		this.shell = shell;
		
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		
		Job job = new Job("Verifying services") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			monitor.beginTask("Verifying services in process", 100);
			syncWithUiBefore();
			
			monitor.subTask("Connecting to the CMM");
			monitor.worked(20);
			if (!errorExist)
			{
				monitor.subTask("Waiting for result");
				monitor.worked(60);
				verifyAll();
			}
			else return Status.CANCEL_STATUS;
			if (!errorExist)
			{
				monitor.subTask("Finishing verification");
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

														
				if (ScpmUI.compositionTable.getItemCount()==0)
				{
					errorExist = true;
					MessageDialog.openError(shell, "Error",
							"Please choose composition plans first");
					return;
				}
				else 
					{
					errorExist = false;
					compositionPlans.clear();
					agreementTemplates.clear();
					consumerPolicies.clear();
					suggestedList.clear();
					}
				

				for (int itemNum = 0; itemNum < ScpmUI.compositionTable
						.getItemCount(); itemNum++) {
					String originalString = ScpmUI.fileOpen(ScpmUI.compositionTable
							.getItem(itemNum).getText(ScpmUI.COL_NAME));
					ICompositionPlan planTemp = new CompositionPlan(
							originalString);
					// TODO: possible to use BPMNParser get the real ID
					planTemp.setCompositionPlanID(ScpmUI.compositionTable
							.getItem(itemNum).getText(ScpmUI.COL_ID));
					compositionPlans.add(planTemp);

					IAgreementTemplate templateTemp = new AgreementTemplate("");
					templateTemp = loadAT (planTemp.getCompositionPlanID(), ScpmUI.compositionTable.getItem(itemNum)
							.getText(ScpmUI.COL_AT));
					if (templateTemp == null)
					{
						errorExist = true;
						MessageDialog.openError(shell, "Error",
								"No agreement template file found in the folder for composition: " +planTemp.getCompositionPlanID());
						return;
					}
					agreementTemplates.add(templateTemp);

					IConsumerPolicy policyTemp = new ConsumerPolicy();
					policyTemp = loadCP (ScpmUI.compositionTable.getItem(itemNum).getText(ScpmUI.COL_CP));
					if (policyTemp == null)
					{
						errorExist = true;
						MessageDialog.openError(shell, "Error",
								"No consumer policy file found in the folder for composition: " +planTemp.getCompositionPlanID());
						return;
					}
					consumerPolicies.add(policyTemp);

				}
				
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

	private void verifyAll() {

		try {
			errorExist = false;
			suggestedList = SCPMClient.selectSecureCompositions(
					compositionPlans, consumerPolicies,
					agreementTemplates, scpmURL);
			if (suggestedList == null){
				errorExist = true;
				syncWithUiMessage();
				return;
			}
				
			// System.out.println(suggestedList.get(0).getExplanation());
		} catch (Exception e) {
			errorExist = true;
			e.printStackTrace();
			syncWithUiMessage();
			return;
		}

	}

	private void syncWithUiAfter() {
		Display.getDefault().syncExec(new Runnable() {
		public void run() {
			ScpmUI.compositionTable_Secured.removeAll();
			ScpmUI.compositionTable_UnSecured.removeAll();

			// below is the code when verification returns passed
			// result only

			// List<String> suggestedListID = new Vector<String>();
			// for (int i = 0; i< suggestedList.size(); i++)
			// {
			// suggestedListID.add(suggestedList.get(i).getPlan().getCompositionPlanID());
			// }
			//
			// for (int planIndex = 0; planIndex <
			// compositionPlans.size(); planIndex++)
			// {
			// ICompositionPlan existPlan =
			// compositionPlans.get(planIndex);
			// String planLocation = getLocation
			// (existPlan.getCompositionPlanID());
			// 
			// int ncvmResult = 0;
			// for (int j = 0; j < suggestedList.size(); j++)
			// {
			// if
			// (existPlan.getCompositionPlanID().equals(suggestedList.get(j).getPlan().getCompositionPlanID()))
			// ncvmResult = suggestedList.get(j).getResult();
			// }
			//
			// if
			// (suggestedListID.contains(existPlan.getCompositionPlanID()))
			// {
			// TableItem securedPlan = new
			// TableItem(compositionTable_Secured, SWT.NULL);
			// securedPlan.setText(new String[]
			// {existPlan.getCompositionPlanID(), planLocation,
			// ""+ncvmResult});
			// }
			// else
			// {
			// TableItem unsecuredPlan = new
			// TableItem(compositionTable_UnSecured, SWT.NULL);
			// unsecuredPlan.setText(new String[]
			// {existPlan.getCompositionPlanID(), planLocation,
			// ""+ncvmResult});
			// }
			//
			// }

			for (int planIndex = 0; planIndex < suggestedList
					.size(); planIndex++) {
				ISelectResult selectResult = suggestedList
						.get(planIndex);
				ICompositionPlan plan = selectResult.getPlan();
				String planLocation = ScpmUI.getLocation(plan
						.getCompositionPlanID());
				
				int errorCode = selectResult.getResult();
				String explanation = selectResult.getExplanation();
			
				if (errorCode >= 0) {
					TableItem securedPlan = new TableItem(
							ScpmUI.compositionTable_Secured, SWT.NULL);
					securedPlan.setText(new String[] {
							plan.getCompositionPlanID(),
							planLocation, explanation });
				} else {
					TableItem unsecuredPlan = new TableItem(
							ScpmUI.compositionTable_UnSecured, SWT.NULL);
					unsecuredPlan.setText(new String[] {
							plan.getCompositionPlanID(),
							planLocation, explanation });
				}

			}

			ScpmUI.compositeSelect.setVisible(false);
			ScpmUI.compositeOrder.setVisible(true);
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
}
