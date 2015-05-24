## How to Compile  
First, use maven to make the sources "Eclipse-ready":
```
mvn clean verify -DskipTests=true -q eclipse:clean 
mvn -P eclipse eclipse:eclipse
```
After this, all projects can be imported into a fresh Eclipse
workspace using `File -> Import -> Existing Projects into Workspace`.

While many Wala projects may contain compilation errors, the projects
`eu.aniketos.pvm.checks.implementation`,  `eu.aniketos.pvm.checks.wsdl`,
`eu.aniketos.pvm.client`, and `eu.aniketos.pvm.commons` should compile
without errors. 
