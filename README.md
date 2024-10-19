# Spring Boot To-Do List App

A simple To-Do List application built with Spring Boot, Thymeleaf, MySQL and Spring Security. This application allows users to create, read, update, and delete tasks in their to-do list. It also includes user account creation and login functionalities.

## Features

- **CRUD Operations**: Users can create, read, update, and delete tasks.
- **Account Creation**: New users can register and create their accounts.
- **User Authentication**: Secure login functionality using Spring Security.
- **Password Hashing**: User passwords are securely hashed for storage.
- **Thymeleaf Templating**: Frontend views are rendered using Thymeleaf templates.

## Technologies Used

- **Spring Boot**: Framework for creating standalone, production-grade Spring-based applications.
- **Thymeleaf**: Modern server-side Java template engine for web and standalone environments.
- **Spring Security**: Powerful and customizable authentication and access control framework.
- **Spring Data JPA**: Simplifies data access and persistence using the Java Persistence API.
- **Hibernate**: Object-relational mapping tool for the Java programming language.
- **MySQL/PostgreSQL/H2 Database**: Choose your preferred relational database for storage.

## Getting Started

### Prerequisites

- Java JDK (8 or higher)
- Maven
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)
- MySQL/PostgreSQL/H2 Database (Make sure you have it installed and running)

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/rahul-siddhu/todo-list.git
   ```

2. Navigate to the project directory i.e. todo-list


3. Open the project in your prefered IDE:

4. Update `application.properties` with your database configuration:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_todolist
   spring.datasource.username={Your username}
   spring.datasource.password={Yours password}
   ```
   You need to create database with name 'db_todolist' in MySQL and also the tables needed(Model folder inside src folder haas the information regarding what tables to be created)

5. Run the application by running the file TodoListApplication.java in the src folder

6. Open your browser and visit `http://localhost:8080` to access the application.

## Usage

1. **Create an Account**: Click on the "Sign Up" link to register and create your account.
2. **Login**: After creating an account, log in with your credentials.
3. **Manage Tasks**: Add, edit, delete tasks from your to-do list.

