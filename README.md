# Senior-Project---Lily
### Requirements to Run Locally

Download [Selenium](https://www.selenium.dev/downloads/) here. **We are using version 3.141.59**

Download the [Chrome Driver](https://chromedriver.chromium.org/downloads) executable here. **We are using version 
79.0.3945.36**

Modules needed are **PDF Reader** and **Tomcat**. Those modules are inside of the node_modules folder.

Tomcat is used to run Lily on the server. If you are using Intellij, the following steps will help with configuration:
1. Add/Edit Configurations
2. Click **+** to add new configuration
3. Scroll to **Tomcat Server**, hover over it and click **Local**
4. Beside **Application Server** click **Configure**
5. Configure the path to the tomcat module in the node_models folder
6. Add **'Senior-Project---Lily:Web exploded artifact'** to Build

This is a maven project so you may do not have to add Selenium, but just in case you may need to add Selenium do the 
following:
1. In Intellij click on **File**
2. Click **Project Structure**
3. Click **Modules**
4. Click **Senior-Project---Lily**
5. Click **Dependencies**
6. Click **+** to add dependencies
7. Click **JARs or Directories**
8. Go to the Selenium jar file (wherever you put it) and add it. If the jar file is already in your dependencies ensure 
that it is checked.
