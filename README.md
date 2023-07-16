# Return On Investment Calculator
The ROI Calculator Application is a tool that allows users to track financial data and expense for real estate properties. It provides a user-friendly interface for entering property details and tracking expenses per property. 

## Contents

- [Technologies Used](#Technolgies-Used)
- [Prerequisites ](#Prerequisites)
- [Getting Started](#Getting-Started)
- [Presentation Slides](doc/Real%20Estate%20Investment%20Tracker.pdf)
- [Daily Progress](#daily-progress)
- [User Stories](#user-stories)
  - [Investor](#investor)
- [Technical Architecture]()
- [Technical Challenges](#technical-challenges)
- [Lessons Learned](#lessons-learned)
- [Future Improvements](#future-improvements)

## Teachnologies Used

- Java: Programming language used for backend development.
- Spring Boot: Framework used for building the application.
- Thymeleaf: Server-side Java template engine for generating dynamic HTML pages.
- HTML/CSS: Markup and styling languages for creating the user interface.
- JavaScript: To add actions to buttons such as show password and Light and Dark Theme.  
- Bootstrap: CSS framework for responsive web design.
- MySQL: Relational database for storing user and property data.
- Maven: Dependency management tool for Java projects.

## Prerequisites
- Before running the application, make sure you have the following installed:

  - Java Development Kit (JDK) 8 or higher
  - MySQL database
  - Maven

## Getting Started

1. Clone the repository:

    ```
    git clone 
   https://github.com/paulrod3/Paul_Rodriguez-ReturnOnInvestmentCalculator_Capstone_Application.git

    ```

2. Configure the database:
  - Create a new MySQL database.
  - Update the database configuration in `src/main/resources/application.properties` with your database credentials.
3. Build and run the application:

    ```
    cd roi-calculator-application
    mvn spring-boot:run
    
    ```

4. Access the application:

   Open a web browser and go to `http://localhost:8080` to access the ROI Calculator Application.

## Daily Progress
- June 12th (50% done)
  - Created the Initial Controllers for the models.
- June 15th (55% done)
  - Made Changes to Controllers to get CRUD functionality for Property Model. Full CRUD achieved. 
- June 20th (57% done)
  - Made changes to financial detail controller to start working on CRUD for Financial Detail Model. 
- June 23 (60% done)
  - Full CRUD functional on financial detail controller. 
  - Added some bootstrap and CSS Styling to the HTML pages. 
- June 30 (70% done)
  - Full CRUD functional on Renovation Expense Controller. 
  - Added dependencies for Spring Security.
  - Added Classes related to Security.
- July 11th (75% done)
  - All HTML templates have full CSS styling. 
  - Going to start working on Spring Security disabled it to work on styling un encumbered.
- July 12th (80% done)
  - Having issues with Spring Security. Going to Git revert and restart security from the dependencies. 
- July 14th (90% done)
  - Security Complete. 
  - Some CSS styling isn't coming through to HTML templates. I added inline styling. 
- July 14th (95% done)
  - Improved on flow through the pages and corrected some styling.
  - Added all Service Tests and paramatized test
  - Working on Readme file and presentation. 
- July 15th (100% done)
  - Finished presentation.
  - Fixed a few minor flaws in styling. 

## User Stories

### Investor

1. As a property investor, I want to be able to add a new property to the system by providing its address, type, square footage, number of bedrooms, and number of bathrooms.

2. As a property investor, I want to view a list of all properties in the system, including their addresses and basic details.

3. As a property investor, I want to update the details of a property, such as its type, square footage, number of bedrooms, or number of bathrooms.

4. As a property investor, I want to delete a property from the system if it is no longer part of my portfolio.

5. As a property investor, I want to add a renovation expense to a specific property, including a description, amount, and date of purchase.

6. As a property investor, I want to view a list of renovation expenses for a particular property, along with their descriptions, amounts, and dates of purchase.

7. As a property investor, I want to remove a renovation expense from a property if it is no longer relevant.

8. As a property investor, I want to view the financial details of a property, including its purchase price, current value, rental income, and expenses.

9. As a property investor, I want to update the financial details of a property, such as its purchase price, rental income, or expenses.

## Technical Challenges
- Thymeleaf provided some functionality challenges related to certain interactions with controllers. Annotations have to be precise to work. 
- Bidirectional Relationships proved complicated in the models. The data especially with in the Renovation Expense Class was not persisting in the database.The solution was to change the relationship to unidirectional towards property and this worked.

## Lessons Learned
- Getting To MVP was the biggest lesson learned. I had very big goals in the beginning and once I trimmed that down to something manageable I was able to make real progress
- I learned that it is better to get things to work quickly and then go back afterwards to tweak and style. I wasted a lot of time initially trying to make things perfect the first go around.
- Commit and push as often as you can. This saved me over and over again from having to rewrite a lot of code due to getting to far ahead and breaking my application.

## Future Improvements

- In its current state the application tracks all properties and their related expenses. The plan is to include totals in the renovation expenses and financial details and to have the actual real time ROI returned. 
