# ZigWheels
(Script details)

1. The project follows maven standards with a hybrid framework i.e., the mixture of Data-driven and POM.
2. There are 3 source files - "src/main/java", "src/test/java", "src/test/resources".
3. "src/main/java" consists of 5 packages – baseclass, pageClasses, utilities, Object Repository, testcases, pom.
· In baseclasss package, we have 3 classes- baseUI, DriverSetup and PageBaseClass
o	In baseUI- holds up all the reusable methods in it.
o	In DriverSetup- It consists of browser drivers used in automation.
o	In PageBaseClass- It has the instances of the Landing Page during the execution of the website.
· The PageBaseClass consists of instances of other pages of the website.
· The utilities package consists of Excel and Report generation classes.
· The pom file consists of Excel write methods.
4. "src/test/java" consists of only 1 package i.e., testCases
· The testCases package have the test class of the project.
5. "src/test/resources" consists of 3 folders- drivers, Object Repository And ExcelFiles.
· The purpose of the resources source folder is to hold the necessary resources to run the project in various systems. User needs to change the versions of web drivers which are compatible in their machines.
