NCVM Maven instructions
=======================



Add repository for use with Karaf
---------------------------------

In the etc/org.ops4j.pax.url.mvn.cfg file, find the org.ops4j.pax.url.mvn.repositories property and add the following URL to it:

http://username:password@ec2-54-235-118-152.compute-1.amazonaws.com:8080@snapshots@noreleases@id=aniketos-snapshots



Add repository for use with maven
---------------------------------

Add the following to your ~/.m2/settings.xml file

  <servers>
    <server>
      <id>aniketos-snapshots</id>
      <username>aniketos-internal</username>
      <password>***********</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <id>Aniketos Proxy</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <id>aniketos-snapshots</id>
          <name>Aniketos Managed Snapshot Repository</name>
          <url>http://ec2-54-235-118-152.compute-1.amazonaws.com:8080/archiva/r$
          <releases>
            <enabled>false</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>



Building
--------

To build, download the following projects from SVN:

eu.aniketos.ncvm
eu.aniketos.ncvm.client
eu.aniketos.ncvm.proxy
eu.aniketos.ncvm.userinterface
eu.aniketos.ncvm.userinterface.client
eu.aniketos.ncvm.userinterface.proxy
eu.aniketos.ncvm.pvm.client
eu.aniketos.ncvm.pvm.proxy
eu.aniketos.ncvm.csvm.client
eu.aniketos.ncvm.csvm.proxy
eu.aniketos.ncvm.spdm.client
eu.aniketos.ncvm.spdm.proxy
eu.aniketos.ncvm.marketplace.client
eu.aniketos.ncvm.marketplace.proxy
common-datatypes
ConSpec_Parser

And download the following from elsewhere

org.eclipse.bpmn2
org.eclipse.bpmn2.edit
org.eclipse.bpmn2.editor
org.eclipse.bpmn2.feature
org.eclipse.bpmn2.tests
org.eclipse.bpmn2.tools.ecoremerger
org.eclipse.bpmn2.tools.xsltFromEcore



Running the service backend
---------------------------

  1. Configure Karaf

Download and unpack Karaf 2.3.1

Change the framework from felix to equinox in etc/config.properties

Create etc/org.apache.cxf.dosgi.discovery.zookeeper.cfg containing
zookeeper.port=2181
zookeeper.host=localhost

Create etc/org.apache.cxf.dosgi.discovery.zookeeper.server.cfg containing
zookeeper.host:127.0.0.1
clientPort:2181
zookeeper.port:2181

  2. Fire up Karaf

cd apache-karaf-2.3.1
./bin/karaf

  3. Install CXF-DOSGi

features:chooseurl cxf-dosgi 1.4.0
features:install cxf-dosgi-discovery-distributed
features:install cxf-dosgi-zookeeper-server

  4. Install the NCVM

features:addurl file:<eclipse workspace>/eu.aniketos.ncvm/features.xml
features:install NCVM

If you want to install everytying, use the following:

features:install SSVV

In the above <eclipse workspace> should be replaced with the path to your workspace. In my case, this results in the following two lines:
mvn install:install-file -Dfile=/home/flypig/Documents/Development/Aniketos/eu.aniketos.ncvm/lib/org.eclipse.bpmn2_0.7.0.201305200222.jar -DgroupId=org.eclipse.bpmn2 -DartifactId=org.eclipse.bpmn2 -Dversion=0.7.0-SNAPSHOT -Dpackaging=jar
features:addurl file:/home/flypig/Documents/Development/Aniketos/eu.aniketos.ncvm/features.xml



Running the test frontend
-------------------------

Run as an Eclipse Application (Run > Run Configurations...) product: org.eclipse.platform.ide

On the Plug-ins tab, set the following plug-ins to run:

common-datatypes
ConSpec_Parser
cxf-dosgi-ri-singlebundle-distribution-1.5
eu.aniketos.proxy (Start Level=3, Auto-Start=true)
eu.aniketos.ncvm.userinterface (Auto-Start=true)
eu.aniketos.ncvm.userinterface.proxy (Auto-Start=true)
org.eclipse.bpmn2

Add everything under "Target Platform".
Select "Add Required Plug-ins" to ensure everything else needed is selctected.

