[![Java CI with Maven](https://github.com/Zap4ick/appium_demo/actions/workflows/maven.yml/badge.svg)](https://github.com/Zap4ick/appium_demo/actions/workflows/maven.yml)

# Appium test project

This is a test project to test appium.

## Contents

The project consists of positive and negative test suites. 
Every test runs appium server, runs android emulator, creates android driver and connects it to the appium server and emulator instance.
Tests are run consequently, parallel run is not implemented.

## Using

Put file (e.g. SecretEscapes.apk) into resources folder.  
Appium should be installed: https://appium.io/docs/en/2.1/quickstart/install/  
Uiautomator plugin should be installed: https://appium.io/docs/en/2.1/quickstart/uiauto2-driver/  
Tools _npm_, _nodejs_, _adb_ paths should be added to Path variable to be available from the application.
Take a look at **core.properties** file for other parameters.  

## Known issues

The "Oops smth went wrong" alert appears when internet connection is off.

## Built With

* [Selenide](https://selenide.org/) - Web Ui testing framework based on Selenium
* [Selenide-appium](https://github.com/selenide/selenide-appium) - Appium addon for Selenide
