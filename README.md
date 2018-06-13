# rzd

Project created as test task for DIGINETICA.
Using selenium for actions in different browsers with "Russian railways" Passengers and Scheduler pages.

1. Use test.properties for configure test:
- specify start page
- select chrome/firefox webdriver
- specify waiting timeout

2. implemented following common browser steps:
- waitUntilElementIsVisibility
- waitUntilElementIsInvisibility
- getNumberOfVisibilityElementsBy

following steps on Passengers page (http://pass.rzd.ru):
- enterRoute
- enterRouteDate
- pressRouteSubmit
- and others

following steps on Scheduler page (https://pass.rzd.ru/tickets/public/ru/...):
- selectTrain
- countVacantSeatsOfSelectedTrain
- countVacantSeatsInAllCarsOfSelectedTrain
- and others

3. Test logged results to log.txt

4. Test designed to run with TestNG and using page object pattern.

5. Test configure in test steps, by replacing input parameters.
E.g.: page.enterRouteDate("Июль", "26")
      .enterRoute("Москва", "Тула")
Use only cyrrilic for months, cities and train numbers!
      
6. Test from repository executed following:
// Generate new page object for Passengers page
PassengersPage page = new PassengersPage(driver);

// Select route date for 26 Июль in calendar.
page.enterRouteDate("Июль", "26")

// Select route from Москва to Тула.
.enterRoute("Москва", "Тула")

// Send selected information to open available ticket and seats.
.pressRouteSubmit()

// Select Купе cars in train number 174А
.selectTrain("741А", "Купе")

// Count and logged results for all type of available seats in selected train
.countVacantSeatsOfSelectedTrain()

// Count and logged results for available for buying seat in each car in selected train, include places for disabled people
.countVacantSeatsInAllCarsOfSelectedTrain();
