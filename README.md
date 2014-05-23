Security-Service-Validation-and-Verification
============================================

Security Service Validation and Verification

Details of how to build and run the full SSVV toolchain will be added in the coming days, and the sections below will fill with useful information. Stay tuned.

##Short description

The Security Service Validation and Verification package provides a series of modules that work together to validate the security properties of a Web Service composition. The package is given a selection of service compositions (provided in the form of BPMN processes with Web Services bound to the service tasks), along with the security policy to be fulfilled. The package then performs various checks on the services to establish whether each composition satisfies the policy, returning an ordered list (ordered in terms of security) of the services that do.

##Overview

This software package provides a set of security checks that verify the security properties of a service specification and validate that the security provisions satisfy the policies at runtime.

The verification process involves an analysis of the service implementation to ensure compliance with required security properties, as expressed in a service contract. The verification is conducted both at the level of atomic and composite services. The properties currently implemented by the tool are the following:

1. Confidentiality (based on WS-Security check)
2. Dangerous functions (source code test)
3. Bounding of Duty (extra plugin needed)
4. Separation of Duty (extra plugin needed)
5. Fuzz-testing (runtime check)

Other tests can be added to the package as they're implemented. Other Aniketos packages increase the range of checks further (e.g. trustworthiness/reputation and runtime monitoring checks). The output of the verification process is a list of recommended secure composite services ranked according to the security priorities of the service developers.

The package is split into a number of modules, details of which are provided in the Modules section below.

##Requirements

###Build

The SSVV package requires [JDK 1.6](http://www.oracle.com/technetwork/java/javaee/downloads/java-archive-downloads-eesdk-419427.html#java_ee_sdk-6-oth-JPR). To build all packages [Eclipse Kepler](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2), maven and the [Apache CXF DOSGi RI version 1.3](http://cxf.apache.org/dosgi-releases.html) are required.

###Run

The user-interface elements run as [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2) plugins. The Web Servie processes are build as OSGi bundles and are expected to be run in [Apache Karaf](https://karaf.apache.org/index/community/download/archives.html#Karaf2.3.1). Details of how to do this are provided below.


Registration isn't a requirement for the open source modules here, but is required to make use of some of the remote services. For more information about this, see the [registration info page](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/wiki/Registration).

##Features

The open source SSVV package provides the following features:

* Security verification and validation of Web Service compositions.
* Input as BPMN2 compositions and ConSpec security policies.
* Ordering of services based on their security capabilities.
* Fully automated: can output results without the need for user-intervention.
* Supports multiple security properties:
 * Confidentiality requirements.
 * Dangerous function checks.
 * Runtime fuzz-testing.
 * Separation of Duty (not currently part of the open source release).
 * Binding of Duty (not currently part of the open source release).
* Cloud execution: modules can be executed and used remotely.

##How to get started

The following are very brief instructions; these will be fleshed out in much more detail in due course. Unfortuately, although these steps look simple it's sadly often quite tricky to get everything building and running correctly. More detailed instructions to catch these 'gotchas' will be added in soon.

Build the modules:

1. Check out everything from this repository into a fresh Eclipse workspace.
2. Import [Apache CXF DOSGi RI 1.3](http://search.maven.org/remotecontent?filepath=org/apache/cxf/dosgi/cxf-dosgi-ri-singlebundle-distribution/1.3/cxf-dosgi-ri-singlebundle-distribution-1.3.jar) as a project into the workspace.
3. Call ```maven install``` on each of the packages (except the SCPM and NCVM Eclipse plugin modules).

Execute the modules

1. Install [Apache Karaf 2.3.1](https://karaf.apache.org/index/community/download/archives.html#Karaf2.3.1).
2. Fire up Karaf.
2. From the Karaf command line add the SSVV feature: ```features:addurl file:<eclipse workspace>/eu.aniketos.ncvm/features.xml```
3. From the Karaf command line install the SSVV feature: ```features:install SSVV```

See the [package wiki](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/wiki) for more info.

##Contributing (guide)

All of the modules here are open source (either BSD-new or LGPL); check the licence files inside individual modules to find out which.

We're very keen for others to contribute to the project. Please fork the repository and let us know if you have any suggestions, bugs or suggested changes.

Bugs, suggestions and issues can be submitted through the Github [issue tracker](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/issues).

##Installation

The easiest way to install and execute the modules is using an Apache Karaf container and the feature provided:

1. Install [Apache Karaf 2.3.1](https://karaf.apache.org/index/community/download/archives.html#Karaf2.3.1).
2. Fire up Karaf.
2. From the Karaf command line add the SSVV feature: ```features:addurl file:<eclipse workspace>/eu.aniketos.ncvm/features.xml```
3. From the Karaf command line install the SSVV feature: ```features:install SSVV```

##Modules, APIs

The SSVV package is comprised of the following modules. Links to the full APIs for these modules will be added in due course.

###CSSTM

The Composite Service Security Testing Module performs fuzz-testing of a running Web Service. This open source toolchain includes the interface to access an online version of the fuzz-testing tool.

###CSVM

The Composite Security Verification Module formally verifies the security properties of a service at the level of the composition. Separation of Duty and Binding of Duty have both been implemented, but this open source version currently provides only a dummy interface.

###ConSpec Parser

Security policies are defined using the ConSpec contract specification language. The ConSpec_Parser module provides support to the other modules for interpretting ConSpec files.

###Marketplace

The Marketplace provides a directory of Web Services that conform to the Aniketos requirements (e.g. by providing an agreement template that defines the security properties the service claims to satisfy). The marketplace is an online service; this package provides an interface for access to it.

###NCVM

The Nested Composition Verification Module decomposes security properties/policies at the composition level into requirements to be checked against sub-services (e.g. the underlying atomic services). It acts as an intermediary orchestrating the other services in the package.

###SCPM

The Secure Composition Planner Module is comprised of an Eclipse plugin userinterface element and a backend Web Service. Together they manage the process of calling the other modules for the supplied BPMN plan and security policy, generating a list of service compositions that satisfy the security policy ordered in terms of their security strength.

###SPDM

The Security Property Determination Module manages the lifecycle of properties that have been verified against Web Services and service compositions. For example it caches verification results and requests re-verification once a property has expired.

###Trustworthiness

The Trustworthiness Module manages trust and reputation values for Web Services. The Trustworthiness Module is accessed remotely; this package provides an interface for accessing it.

###Common Datatypes

There are various classes and datatypes used for interoperability between services within the package. The common-datatypes modules provides access to a uniform set of interfaces for use by all of the modules in the package.

##Usage manual

Full instructions will be provided on the [repository wiki](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/wiki) in due course. Please refer there for more info.

##Example usage

The general steps for using these services are:

1. Create a Web service composition using the Service Composition Framework (part of the [Secure Service Specification and Deployment package](https://github.com/AniketosEU/Secure-Service-Specification-and-Deployment).
2. Create a ConSpec security policy using the ConSpec_Editor (also part of the [SSSD package](https://github.com/AniketosEU/Secure-Service-Specification-and-Deployment).
3. Use the SCPM interface integrated into the Service Composition Framework to call the SSVV package toolchain in order to verify the service security properties.
4. Choose the service you want to execute based on the security results.
5. Deploy the composite service to the Service Runtime Environment (part of the [SSSD package](https://github.com/AniketosEU/Secure-Service-Specification-and-Deployment).
6. Profit!

More details, example files and a usage walkthrough will be provided on the [package wiki](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/wiki) in due course.

##Credits

The various modules that make up the SSVV package have been developed by a number of organisations:

1. CSSTM developed by [SEARCH-LAB](http://www.search-lab.hu/).
2. CSVM developed by [SAP](http://www.sap.com/).
3. ConSpec_Parer developed by [CNR](http://www.iit.cnr.it/).
4. Marketplace developed by [ATC](http://www.atc.gr/).
5. NCVM developed by [LJMU](http://www.ljmu.ac.uk/cmp/).
6. SCPM developed by [LJMU](http://www.ljmu.ac.uk/cmp/).
7. SPDM developed by [TSSG](http://www.tssg.org/).
8. Trustworthiness Module developed by [TSSG](http://www.tssg.org/).
9. common-datatypes developed by multiple partners, coordinated by [ATC](http://www.atc.gr/).

##Official site, external resources

Please see the [Aniketos project website](http://aniketos.eu/) for more details.

##About the developers

The initial code for this project was developed as part of the [Aniketos project]((http://aniketos.eu/)), partly funded by the European Community's Seventh Framework Programme under grant agreement no. 257930.

##Updates and list of known issues

For changes please see the [commit history](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/commits/master).

For known issues please see the [issue tracker](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/issues) (and let us know if you find any more please!).

