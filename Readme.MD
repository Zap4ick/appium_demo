# Appium test project

This is a test project to test appium.

## Contents

The project consists of positive and negative test suites. 
Every test runs appium server, runs android emulator, creates android driver and connects it to the appium server and emulator instance.
Tests are run consequently, parallel run is not implemented.

## Using

Appium should be installed: https://appium.io/docs/en/2.1/quickstart/install/
Uiautomator plugin should be installed: https://appium.io/docs/en/2.1/quickstart/uiauto2-driver/
_npm_, _nodejs_, _adb_ tools paths should be added to Path variable to be available from the application
Take a look at **core.properties** file. 
apk file should be added to _file.path_ param.

## Known issues

The "Oops smth went wrong" alert appears for no reason sometimes. Try to wipe your emulator data.

## Built With

* [Selenide](https://selenide.org/) - Web Ui testing framework based on Selenium
* [Selenide-appium](https://github.com/selenide/selenide-appium) - Appium addon for Selenide
