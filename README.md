OTP APIs for APA
The otp-apis microservice supports:
1. Generate Otp (6 digits)
2. Regenerate Otp (6 digits)
3. Validate Otp
4. The service integrates with the notifications service to send the smses


#Help
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

##Liquibase
_NB:_ All entities should be under `entity`  

To get the schema of the db run:  
`$ mvn liquibase:generateChangeLog`

To generate and persist changes made to an entity:
1. Create the ChangeSets for changes made to entities by running:  
`$ make makeMigration MIGRATION_LABEL="to-be-changed"`  
The files containing ChangeSets are stored in the `resources/db/changelog/changes` directory within the project.
2. To enable persisting of the changes to the db add the reference of the created file with the ChangeSets to the master changelog `db.changelog-master.xml` i.e  
`<include file="changes/20200812154349-to-be-changed.xml" relativeToChangelogFile="true"/>`

Here's a guide that illustrate how to use liquibase features:
- [Managing database migrations with liquibase](https://docs.liquibase.com/tools-integrations/springboot/home.html)