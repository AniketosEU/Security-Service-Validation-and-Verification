# CSVM Modelling and Validation Environment 
The *CSVM Modeling and Validation Environment* is an environment for
modelling and validating business processes as well as service
compositions using
[SecureBPMN](https://www.brucker.ch/projects/securebpmn/index.en.html).
[SecureBPMN](https://www.brucker.ch/projects/securebpmn/index.en.html)
is a domain-specific modeling language that allows to model security
aspects (e.g., access control, separation of duty,
confidentiality). SecurePBPMN is defined as a meta-model that can
easily be integrated into BPMN and, thus, can be used for modeling
secure and business processes as well as secure service compositions.

## Installation
### Prerequisites
* Eclipse Helios

### Preparing the Eclipse environment
To initialize the Eclipse project structure, please do 
```
cd src/eu.aniketos.securebpmn.xacml.parent
mvn clean eclipse:clean 
mvn eclipse:eclipse
mvn install 
cd ../org.activiti.designer.parent
mvn clean eclipse:clean 
mvn eclipse:eclipse
```
  
After this, all projects can be imported into a fresh Eclipse
workspace using `File -> Import -> Existing Projects into Workspace`.

### Generate Model Classes
1. Open the folder `model` in the project `org.activiti.designer.model`
2. Open `BPMN20.genmodel`
3. Select the top level node (`bpmn2`)
4. Select `Generator -> Reload...` from the top-level menu, select
   `Ecore model` and complete the wizard. While doing this, ensure
   that all packages are select in the `Package Selection` screen.
5. Select the top level node (`bpmn2`)
6. Select `Generator -> Generate all` from the top-level menu

### Start Eclipse Application
Select the project `org.activiti.designer.eclipse` and select `Run as
-> Eclipse application` in the context menu (right click).

## Team 
Main developer: [Achim D. Brucker](http://www.brucker.ch/)

### Contributors
* Jan Alexander
* Matthias Klink
* Helmut Petritsch
* Raj Ruparel
