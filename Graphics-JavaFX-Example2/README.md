# JavaFX Example in IntelliJ

The provided example for a first basic JavaFX application can be run in two possible ways:

* Easiest you use **maven** and simply type in the command line: _mvn clean package javafx:run_ . This command will download all required dependencies, build and run the application.
* Running the application in IntelliJ without maven requires some more preparation. First, the JavaFX SDK must be downloaded from https://gluonhq.com/products/javafx/ .Unzip the downloaded archive to some directory INSTALL_DIR_SDK. Then open in IntelliJ "Run" -> "Edit Configurations" -> "Add VM options" (this might not be directly visible, you might have to click firston a small link "Modify options"). Then enter intothe textbox for "VM options" the string (where INSTALL_DIR_SDKis replaced with the actually used directory for the SDK) _--module-path=INSTALL_DIR_SDK --add-modules=javafx.controls,javafx.swing_ . After these configurations, the IntelliJ IDE should be able to run the example.
