# web3-eclipselink-jsf-example

How to make this work?
- Used configuration:
- - WildFly 26.1.3 Preview Jakarta 9.1 ( [download .zip](https://github.com/wildfly/wildfly/releases/download/26.1.3.Final/wildfly-preview-26.1.3.Final.zip) )
  - - !! **It's important**, because WildFly 27+ supports **only** Jakarta EE 10.0.0, where `.xml` configuration for managed beans were *unspecified (and removed)*.
- - Jakarta EE Web Profile 9.1.0 and EclipseLink ORM 3.0.2 (see [pom.xml](./pom.xml))
 
1) Download WildFly on helios.
2) Setup standalone configuration:
   - Use port setup from lab2
   - Run `[WILDFLY_ROOT]/bin/jboss-cli.sh`. Please, don't worry that you'll be disconnected from the server.
   - Execute this command:   
      `module add --name=org.postgresql --resources=/usr/local/share/java/classes/postgresql.jar --dependencies=javax.api,javax.transaction.api`
   - Now, open your `standalone.xml` in `[WILDFLY_ROOT]/standalone/configuration`
   - Find and change the datasources element on this:
   - ```
             <datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
                        <connection-url>jdbc:postgresql://pg:5432/studs</connection-url>
                    <driver>postgresql</driver>
                    <security>
                        <user-name>username_from_pgpass</user-name>
                        <password>password_from_pgpass</password>
                    </security>
                </datasource>
                <drivers>
                        <driver name="postgresql" module="org.postgresql">
                                <driver-class>org.postgresql.Driver</driver-class>
                                <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
                        </driver>
                        <driver name="postgres" module="org.postgresql">
                                <driver-class>org.postgresql.Driver</driver-class>
                        </driver>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
            </datasources>
    - *Keep in mind, that you need to change username and password*
    - In some cases you'll need to increase metaspace size:
    - - .bash_profile:
    - - ```    
        export _JAVA_OPTIONS="-Xmx512M -XX:MaxMetaspaceSize=3200m"
        export JAVA_VERSION="17.0+"
        export JAVA_HOME="/usr/local/openjdk17"
        export PATH=/usr/local/openjdk17/bin/:$PATH
    - *Don't forget to relogin into helios for applying `.bash_profile` changes*
3) Provide correct login and password in `resources/db.cfg`
4) Build Maven `.war` archive and deploy it!
