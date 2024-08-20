# Courier Tracking API

This project is a simple RESTful API for managing couriers and tracking their locations. The API provides basic CRUD operations for the `Courier` entity, as well as the ability to track and retrieve courier locations over time.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## Getting Started

### Prerequisites

- **Java 17** or later installed
- **PostgreSQL** database running
- **Maven** installed

### Installation

1. Clone the repository:

    ```bash
    git clone github.com/erensunar/courier-tracking.git
    cd courier-tracking
    ```

2. Configure the database in the `src/main/resources/application.properties` file:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database-name
    spring.datasource.username=your-username
    spring.datasource.password=your-password

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. Build and run the application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### API Endpoints

#### Courier Endpoints

- **GET** `/api/couriers`: Retrieve all couriers
- **GET** `/api/couriers/{id}`: Retrieve a specific courier by ID
- **POST** `/api/couriers`: Create a new courier
- **PUT** `/api/couriers/{id}`: Update an existing courier by ID
- **DELETE** `/api/couriers/{id}`: Delete a courier by ID

#### Location Tracking Endpoints

- **POST** `/api/locations`: Create a new location entry for a courier
- **GET** `/api/locations/courier/{courierId}`: Retrieve all locations for a specific courier, ordered by timestamp.

### Example Usage

You can use `curl`, `Postman`, or any HTTP client to interact with the API.

#### Create a Courier

```bash
curl -X POST http://localhost:8080/api/couriers -H "Content-Type: application/json" -d '{"name": "John Doe"}'
