# Please Note this application is Work In Progress
#E-banking Web Application
An e-banking application for the fictional bank *KonChris Bank*, built with **Angular 7** and **Spring** 

## Deployment Workflow
### Build The Production artifact
In order to build the run the application as a jar file do the following:  
```bash
$ mvn clean package
$ export ENVIRONMENT=prod
$ java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
```
### Staging Environment
When on development branch commit the feature and create a pull request for 
staging branch. After the pull request is merged the deployment is automatically started.  

Staging URL: https://staging-konchris-ebanking.herokuapp.com/

### Production Environment
Production URL: https://konchris-ebanking.herokuapp.com/