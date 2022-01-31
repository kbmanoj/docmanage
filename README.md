# docmanage
Document add retrieval Rest resource

Steps for local build

1.Checkout the project to local using - git clone https://github.com/kbmanoj/docmanage.git

2.Copy the sql commands provided in create.sql to create the table in local mysql server and schema (check application.properties to use schema names/modify)

3.Build the project by running cmd - mvn clean install

4.Start the spring boot aplication 

5.Import the postman collection postman_collection.json provided in the repository to local and execute the endpoints 

6.Validate connecting to sqlworkbench and query the tables
