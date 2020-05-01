# Senior-Project---Lily
### Requirements to Run Locally

**Build the project if it ask you if you would like to build it**

Download the [Chrome Driver](https://chromedriver.chromium.org/downloads) executable here if the downloaded version does 
not work, you may need a lower version or a higher version, but chromedriver is already in the files.

**There is a TODO in tho code** Click **TODO**, select **Selenium.java** and complete the TODO

Module needed is **Tomcat**. This module is inside of the node_modules folder.

Tomcat is used to run Lily on the server. If you are using Intellij, the following steps will help with configuration:
1. Add/Edit Configurations
2. Click **+** to add new configuration
3. Scroll to **Tomcat Server**, hover over it and click **Local**
4. Beside **Application Server** click **Configure**
5. Configure the path to the tomcat module in the node_models folder **please chose the tomcat module for your machine**
6. Add **'Senior-Project---Lily:Web exploded artifact'** to Build

***Note:*** If project doesn't run you may need to check if you have Java 10 or higher.
If you do not have Java 10 or higher download it [here](https://jdk.java.net/10/).