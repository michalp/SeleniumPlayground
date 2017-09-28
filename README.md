# SeleniumPlayground

This repository is a micro project with Selenium-based tests for following webpage: 

http://timvroom.com/selenium/playground 


This project uses Selenide (http://selenide.org/) - Selenium WebDriver-based wrapper/framework, with a lot of convenient helper methods.


To install/setup the project you will need Maven (https://maven.apache.org/; suggested version: 3.0.0 or higher).

Once you have Maven setup, run: <code>mvn clean install</code>


In order to run the tests, user must download certain drivers, each for different browser (e.g. Firefox 48+ -> geckodriver).

Once the drivers are downloaded, please specify the path(s) in selenide.properties file.


There are two test classes in the repository:
 1) containing each task in a separate test
 2) containing all the tasks in a single test
 
 
Additionally to the task tests, there is verification method added which check if the resuls are correct or not.


Enjoy,

Michal
