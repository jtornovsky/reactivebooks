# BOOKS SUGGESTION MICROSERVICE 

## Introduction

The service suggests books to readers based on the book's ratings, reader's favourite genres, book's author and the 
preferences of other readers of the same age.

Your job is to fulfill the requirements described below. There are several unit tests that will help you to kick-start and verify your solution.

The purpose of the exercise is to test your ability of designing and implementing a problem solution using available Java technologies.
 
You are **not** expected to complete all the points described in the *Microservice* section of the exercise. 
It is **not expected** that the whole solution will work or be without bugs, except for parts covered by unit tests (existing or written by you). 

There are no right or wrong answers for the *Microservice* section. The exercise will be scored based on the technical and analytical skills you demonstrate while implementing the requirements.

You are **encouraged** to use any external dependencies by including them in `pom.xml` descriptor.

## Problem Statement

### Basic Implementation

Your job is to correctly implement all the methods in the `BookSuggestionService` class, so that they meet the following requirements:

The method `Set<String> suggestBooks(Reader reader)` should return titles of all the  books that meet all the following criteria:
 1. Have a rating of four or higher
 2. Belong to one of the reader's favourite genres
 3. Belong to favourite books' list of at least one different reader of the same age

 The method `Set<String> suggestBooks(Reader reader, Author author)` should return titles of all the  books that meet all the following criteria:
  1. Have a rating of four or higher
  2. Belong to one of the reader's favourite genres
  3. Belong to favourite books' list of at least one different reader of the same age
  4. Have been written by the author specified in the method argument
  
 The method `Set<String> suggestBooks(Reader reader, int rating)` should return titles of all the  books that meet all the following criteria:
   1. Have exactly the rating specified in the method argument
   2. Belong to one of the reader's favourite genres
   3. Belong to favourite books' list of at least one different reader of the same age
   
### Microservice

Read the requirements carefully before you start. The order of the implementation doesn't matter. 
If you feel you are more comfortable with some requirements than with the others, concentrate on them and implement as much as possible of their aspects.

Think about proper separation of concerns, while adding new parts to the service.

Requirements:

* We need to expose the `BookSuggestionService` to external clients using some of the below methods (peek at least one you are most comfortable with):
    * RESTful API
    * gRPC
    * GraphQL
    
    While implementing the API, think about the proper way to pass the reader's identity to the service and the security of the API.
    
    What additional APIs should be included, except the ones from the *Basic Implementation* section, in order to support the service functionality?
     Add them, even if in a form of stubs. 

* The service needs a persistence layer to store the information required for its functionality (book ratings, readers' favourite genres and authors, etc.).

    Does all of this information belong to the service, or some of it should be brought from other sources?    
    
    Add the persistence layer, using the storage backend you are most comfortable using (file system, RDBMS, NoSQL).
    In case of using any kind of database, adding to project a Docker configuration for local testing is **a bonus**.
    
* What configurations the service should have, so it can be easily transferred between different environments, without changing the source code?
    Implement them using any approach you deem fit.
    
* For the parts you've implemented, what automatic tests would you write?
 Implement the ones you feel will provide the most value, using any framework.
 
 
