Security-Service-Validation-and-Verification
============================================

Security Service Validation and Verification

Details of how to build and run the full SSVV toolchain will be added in the coming days, and the sections below will fill with useful information. Stay tuned.

##Short description

The Security Service Validation and Verification package provides a series of modules that work together to validate the security properties of a Web Service composition. The package is given a selection of service compositions (provided in the form of BPMN processes with service tasks bound to Web Services), along with the security policy that must be fulfilled. The package then performs various checks on the services to establish whether each composition satisfies the policy, and returns an ordered list (in terms of security) of the services that do.

##Overview

This software package provides a set of security checks that verify the security properties of a service specification and validate that the security provisions satisfy the policies at runtime.

The verification process involves an analysis of the service implementation to ensure compliance with required security properties, as expressed in a service contract. Examples of properties covered include the absence of specific vulnerabilities and the enforcement of access control mechanisms. The verification is conducted both at the level of atomic and composite services.

The output of the verification process is a list of recommended secure composite services ranked according to the security priorities of the service developers.

##Requirements

##Features

##How to get started

##Contributing (guide)

##Installation

##Modules, APIs

##Usage manual

##Example usage

##Credits

##Official site, external resources

##About the developers

##Updates and list of known issues
