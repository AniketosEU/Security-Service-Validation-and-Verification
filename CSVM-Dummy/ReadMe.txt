CSVM-Dummy Maven instructions
=============================

Adding the appropriate repository to maven
------------------------------------------

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

Running the service
-------------------

Ensure you have CXF DOSGi running; for example, see https://cxf.apache.org/dosgi-apache-karaf-feature.html

Then at the Karaf command line, run the following:

install -s mvn:eu.aniketos.csvm/csvm-dummy/0.0.1-SNAPSHOT

