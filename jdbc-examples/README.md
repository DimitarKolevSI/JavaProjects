
# Setup IDE

Import the Project pom.xml into Intellij 
Use `File -> New -> Project From Existing Sources … `<br>
Make sure to specify the pom.xml file 

# Project structure 

All code should be under package com.vmware.talentboost.jdbc

* `/examples` Examples you can use to run. Make sure to run each one of them. Also use debugger to walk throug the code. <br>
* `/exercises` Solve the exercise (see below for instructions first), You need to implement the empty methods (marked with // TODO: implement) <br>
* `/solution` Solutions of above (in /exercises)

# Exercises Instructions

Set in `Constants.JDBC_URL` the correct URL of your database (see Setup your own Postgres if you do not have one)
Set USER_PASSWORD and USER_NAME as well to your own

You need to implement the empty methods (marked with // TODO: implement) 

Starting point is src/main/java/com/vmware/talentboost/jdbc/exercises/Main.java

# Setup  your own Postgres (optional)
If you do not have postgres server running you can start one with: 
```
docker network create --driver bridge postgres-network
docker run --name postgres1 --network postgres-network -p 54320:5432 -d postgres

# You can now access on localhost:54320 with user "postgres", no password and database "postgres" . 
```
 

