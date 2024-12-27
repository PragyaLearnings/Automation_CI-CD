
@tag
Feature: Error Validations
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline:Negative Scenario of Login in Ecommerce Website
    Given I landed on Ecommerce Page
    When logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

   Examples: 
      | username           |     password     | productName |
      | prag@gmail.com |     Johr@10     | IPHONE 13 PRO |