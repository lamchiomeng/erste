# erste

This is the repository for the files used in the demonstration of test automation as part of the application to the position of QA Engineer at Erste.

Files with prefix "TC" denotes test cases

Files with prefix "Page" denotes the pages that will be traversed.

The test scope for this demonstration covers 2 functionality of an the wallet tracking mobile app known as Wallet by BudgetBakers. The app is obtainable from https://play.google.com/store/apps/details?id=com.droid4you.application.wallet&referrer=utm_source%3Dhome_page

Functionality 1 :
Test of Login function. In this test, 2 credentials will be pumped into the login page of the app. One of the credentials is valid, whle the other is invalid.

Expected result - Valid credentials shold be able to access to the app's main page, while the invalid credentials should cause the appropriate error message to be displayed, and should not advance to apps' main page

Functionality 2 : 
Test adjustment of balance. This test is to check that the cash balance can be updated accordingly with amount keyed in by user.

Expected result - Cash balance should display the correct value after update. If user key in an amount with more than 2 decimal points, the value should be rounded up to 2 decimal points.
