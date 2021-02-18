# H1 Clean-me-up
The clean-me-up project is rather ugly and the task is to clean it up to make the code more maintainable and follow good coding
practice.

The application exposes a REST end-point for sending email. 
The contract has not yet been published so you don't need to be backwards compatible.

When you are done you should be rather happy with code. 
If you left things behind - please note that down in a read me file.

Estimated time - You have 7 days.  

You should clean/refactor as much OR as little as you like.  The objective of this assignment is to see your coding skills and how you would go about in designing good code.

Show us your skills!

Step 1: I have changed the project name from clean-me-up to mail-launcher. Actually I have changed the 90% files. 
So that's why I have changed the name. If you have any suggestion I can change the file according to your choice.

Step 2: In this project we have two modules, but I thought we can go with a single module to make it simple and So we can easily maintain it.

Step 3: Spring boot provides auto-configuration features for mail api, log4j and devtools. In this project I have used these three features here.

Step 4: I have created a new gmail account for testing purposes in GMail. I have tested gmail SMTP server and 
It provides an option to send an email from other devices and custom applications also.

Step 5: I have created the packages and the reason is we can maintain code readability.

Step 6: I have added @configuration annotation for loading my required bean when the application starts.

Step 7: Log4j related properties changed from logback.xml to application.properties. So no need to maintain two properties separately.

Step 8: We need to provide spring email properties in application.properties. Then spring boot automatically loads the properties and 
provides the java mail api objects. So we can easily configure it.

Step 9: I have maintained all email validation related properties in application.properties. 
So if any changes come from the Product Owner, we can change very easily.

Step 10: I have added the validation.java file for validating the mail properties. It will provide good quality applications.

Step 11: I have used Spring boot exception handling features. These below annotations provide the help to handle the exceptions @ControllerAdvice and @ExceptionHandler.




