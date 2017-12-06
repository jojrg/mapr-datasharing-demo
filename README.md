MapR Data Sharing Demo - User Interface
============

CRUD application built on AngularJS and SpringBoot for Definition and Adminstration of Data Sharing Rules.

---

### Getting up and running

1. Install the mapr-client software or choose a cluster node which has mapr-client software installed. Make sure that Java 8 and mvn is installed on this computer.
2. Ensure that the OS user that runs the SpringBoot application has permissions to read/write a MapR table in a mapr cluster that is accessible via the mapr-client software.
3. Clone this repo from `https://github.com/` in a directory on the computer that serves the web application.
4. Edit the property values in `dsDemo/src/main/resources/application.properties` (host and ports of the webapp, table names used by the application, etc.)
5. Login on a cluser node as mapr admin user and run: `mapr dbshell`
6. Create the lookup and data table tablea specified as config.table.name and data.table.name in the properties file mentioned above.
   For instance:
   `create /tables/datasharingConfig`
   `create /tables/datasharingRules`
7. Insert a document into the config table:
   `insert /tables/datasharingConfig --value '{"_id":"1" , "fieldNames":["id","firstName","lastName","address","postcode","age","accountNumber"], "dataFilters":["Masked","Hidden","Substring","MakeRange","SessionFilter"]}'`

8. Go to the root folder of the project and start the application:
    `mvn spring-boot:run` from the root directory to install Node Modules
9. Open http://<hostname>:<port>/datasharing/md in a browser. The browser should be connected to the internet in order to download the angularjs dependencies and stylesheets.

---