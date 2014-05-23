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

The package is split into a number of modules, details of which follow.

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

##Requirements

###Build

The SSVV package requires [JDK 1.6](http://www.oracle.com/technetwork/java/javaee/downloads/java-archive-downloads-eesdk-419427.html#java_ee_sdk-6-oth-JPR). To build all packages [Eclipse Kepler](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2), maven and the [Apache CXF DOSGi RI version 1.3](http://cxf.apache.org/dosgi-releases.html) are required.

###Run

The user-interface elements run as [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2) plugins. The Web Servie processes are build as OSGi bundles and are expected to be run in [Apache Karaf](https://karaf.apache.org/index/community/download/archives.html#Karaf2.3.1). Details of how to do this are provided below.

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
1. Install [Apache Karaf 2.3.1]().
2. Add the SSVV feature.
3. Install the SSVV feature.

##Contributing (guide)

All of the modules here are open source (either BSD-new or LGPL); check the licence files inside individual modules to find out which.

We're very keen for others to contribute to the project. Please fork the repository and let us know if you have any suggestions, bugs or suggested changes.

Bugs, suggestions and issues can be submitted through the Github [issue tracker](https://github.com/AniketosEU/Security-Service-Validation-and-Verification/issues).

##Installation

The easiest way to install and execute the modules is using an Apache Karaf container and the feature provided:
1. Install [Apache Karaf 2.3.1]().
2. Add the SSVV feature.
3. Install the SSVV feature.

##Modules, APIs

##Usage manual

##Example usage

##Credits

##Official site, external resources

##About the developers

##Updates and list of known issues
