# Book Store 

## Overview
Welcome to the Book Store project. This application employs robust technologies to deliver an efficient user experience.

### Tech Stack
**Backend:** `Spring` framework, the backend of this project is built to be robust, scalable, and easily maintainable. The use of Spring facilitates the implementation of best practices, enhancing the overall performance and reliability of the system.

**Data Storage:** `MySQL` for its data storage needs. This database system ensures secure and efficient storage of book-related information, contributing to the project's reliability and data integrity.


**User Interface:** `javax.swing`, the interface provides an intuitive and user-friendly design, ensuring an enjoyable experience for both customers and administrators.

**Operations:** The system incorporates essential CRUD operations. This allows users to manage their book inventory, ensuring accurate information.


## Project Structure

```
src
└── main
    ├── java
    │   └── utn
    │       └── book_store
    │           ├── BookStoreApplication.java
    │           ├── models
    │           │   └── Book.java
    │           ├── repository
    │           │   └── BookRepository.java
    │           ├── service
    │           │   ├── BookService.java
    │           │   └── IBookService.java
    │           └── views
    │               ├── BookForm.form
    │               └── BookForm.java
    └── resources
        ├── application.properties
        └── logback-spring.xml
```