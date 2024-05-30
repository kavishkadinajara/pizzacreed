# [Pizza Creed 🍕](https://github.com/kavishkadinajara/pizzacreed)

Pizza Creed is a pizza bakery in Galle Fort. They don't run a pizza restaurant as usual pizza makers. Pizza Creed prepares pizza for orders only.

## Table of Contents
- [Introduction](#introduction)
- [Setup](#setup)
  - [Prerequisites](#prerequisites)
  - [Database Configuration](#database-configuration)
  - [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Postman Collection](#postman-collection)
- [Documentation](#documentation)
- [Admin Panel](##admin-panel)

## Introduction
Pizza Creed is a Spring Boot application providing RESTful APIs for managing pizza orders. It includes functionalities such as listing available pizzas, creating shopping baskets, managing basket items, and checking out orders.

## Setup

### Prerequisites
Ensure you have the following installed on your system:
- Java Development Kit (JDK) - [Download and Install JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Maven Wrapper - Included in the project
- MySQL Database - [Download and Install MySQL](https://www.mysql.com/downloads/)

### Database Configuration
1. Create a MySQL database for the application with the name `pizzacreed`.
2. Navigate to `src/main/resources` and locate the `application.properties.template` file.
3. Duplicate the `application.properties.template` file and remove the `.template` extension, creating a new file named `application.properties`.
4. Open the `application.properties` file in a text editor.
5. Update the file with your database details, including the username and password.

### Running the Application
Open a terminal and navigate to the project directory.

Run the following commands:

```sh
./mvnw clean install
./mvnw spring-boot:run
```
(On Windows, use mvnw instead of ./mvnw)

The application should now be running at `http://localhost:8080`.

## Endpoints
Refer to the [Postman Collection](https://web.postman.co/workspace/NIBM-EAD2-CW4~71e7e8af-b50f-4f42-b90f-4d38fdb2ca96/collection/31839025-a8c89b85-4f81-4879-a140-39ad230de367) for detailed API endpoints and sample requests.

## Postman Collection
Explore and test the API using the [Postman Collection](https://web.postman.co/workspace/NIBM-EAD2-CW4~71e7e8af-b50f-4f42-b90f-4d38fdb2ca96/collection/31839025-a8c89b85-4f81-4879-a140-39ad230de367).

## Documentation
Check out the detailed API documentation on [Postman Documentation](https://web.postman.co/workspace/NIBM-EAD2-CW4~71e7e8af-b50f-4f42-b90f-4d38fdb2ca96/documentation/31839025-a8c89b85-4f81-4879-a140-39ad230de367).

## Admin Panel
The admin panel can be accessed at http://localhost:8080/adminlogin with the following credentials:

 -**Username:** pizza-gallefort-9911.
 -**Password:** 1919gallepizzafort