# demo-one
A demo project built with several technologies, Spring boot, liquibase, dozer, JPA, Spring data, Junit, Mockito, Spring Hateoas, mockito, Spring test, it can be taken as a template or a base for new projects

The project is ready to run, it exposes a rest service(UserController) using Spring Hateoas, the controller exposes the options to save, find, update and delete users with the UserControllers. It has a classic architecture, (Controller, Facades, Services, Repositories and Entities), it can be used as a template to start a project, it uses gradle to manage the build process and dependencies, I'll explain the technologies used and the idea of each one of them.

##Gradle

Manage the build process and the dependencies of the project

##Liquibase:

Manage the log changes of the database, the file that manage this is db.changelog-master.xml

##Spring Data

It uses repositories interfaces in the package com.demo.concept.persistence.repository.

##JPA and Hibernate

Entities in the package com.demo.concept.persistence.entity, database.properties

##Persistence Configuration

The datasource and TransactionManager is configured in the com.demo.concept.config.PersistenceConfig

##Dozer

Dozer is used to mapping Entities and Resources classes, each class of these have the annotation mapping to identify the field to map, the process of mapping is executed in the assembler class of the Spring Hateoas(UserResourceAssembler)

##Spring Hateoas

There are a definition of resource class, assembler class to create the links to each resource, the controller manage the events and the assembler converts the the class from entity to resource using dozer.

##Spring boot.

It uses spring boot to start the configuration and start the execution, the folder config hold the class for these idea.

##Junit.

There are several unit test for Facades, service and Controller

##Database

I'm using MySql as database to store the information

##Spring test

Integration test with dbunit for manage the insertion of the information and delete after execute the test

##DBUnit

Execute the integration test with configuration of xml files for insert and delete the data needed for the test.

##Request Examples

For these Examples I'm using postman pointing to http://localhost:8080/users

* Save a User: (http://localhost:8080/users/save) POST Method for request
{
	"name":"Peter",
	"lastName":"Norbil",
	"phone":"679878878",
	"email":"email@server.com"
}

* Update a User: (http://localhost:8080/users/update) POST Method for request
{
	"userId":"1"
	"name":"Peter",
	"lastName":"Norbil",
	"phone":"679878878",
	"email":"email@server.com"
}

* Delete a User: (http://localhost:8080/users/delete/1) GET Method for request 

* Get All Users (http://localhost:8080/users/all) GET Method for request