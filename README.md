# URL Shortener Project - README.md

## Overview

This project is a simple URL shortener service built with Spring Boot. It allows users to shorten long URLs into shorter, more manageable links. The application uses an H2 in-memory database for storage and Spring Data JPA for data access.

## project Idea URL
https://roadmap.sh/projects/url-shortening-service

## Features

- Shorten long URLs to a compact format (10-12 characters).
- Retrieve the original URL using the shortened link.
- Store URL mappings in an H2 database.
- Simple REST API for interaction.

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For database interactions.
- **H2 Database**: In-memory database for storing URL mappings.
- **Maven**: Dependency management.

## Dependencies

The following dependencies are included in the `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
        <!--if h2 using-->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
        <!--if MYSQL using-->
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
<scope>runtime</scope>
</dependency>
```

## Getting Started

### Prerequisites

- Java 17 or later
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/url-shortener.git
   cd url-shortener
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. Access the application at `http://localhost:8080`.

### API Endpoints

#### Shorten URL


### Explanation of Each Endpoint

1. **Shorten URL (`POST /shorten`)**
    - This endpoint allows users to send a long URL and receive a shortened version in return.

2. **Retrieve Original URL (`GET /shorten/{shortCode}`)**
    - This endpoint retrieves the original long URL associated with a given short code, allowing users to access the original link easily.

3. **Get URL Statistics (`GET /shorten/{shortCode}/stats`)**
    - This endpoint provides statistics about how many times the shortened link has been clicked and when it was created, giving users insights into link performance.

4. **Update Shortened URL (`PUT /shorten/{shortCode}`)**
    - This endpoint allows users to update the long URL associated with a specific short code, enabling flexibility in managing links.

5. **Delete Shortened URL (`DELETE /shorten/{shortCode}`)**
    - This endpoint allows users to delete a specific shortened link and its associated data from the system.

### Conclusion

This section of the README provides clear documentation for each API endpoint, making it easier for users or developers to understand how to interact with your URL shortener service effectively. Adjust any descriptions or response formats as necessary based on your actual implementation details.

## Database Configuration

The application uses an H2 in-memory database. You can configure the database settings in `src/main/resources/application.properties`:

```properties
# H2 Database Configuration
#spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=TRUE
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.username=sa
#spring.datasource.password=password
#spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enable H2 Console
#spring.h2.console.enabled=true
#spring.h2.console.path=/h2-console

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

You can access the H2 console at `http://localhost:8080/h2-console` using the following credentials:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(leave blank)*

## Running Tests

To run tests, use the following command:

```bash
mvn test
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README provides a comprehensive overview of the URL shortener project, including setup instructions, API endpoints, and configuration details. Adjust any sections as necessary to fit your specific project implementation or requirements.

Citations:
[1] https://stackoverflow.com/questions/44768112/difference-between-spring-data-jpa-and-spring-boot-starter-data-jpa
[2] https://www.javatpoint.com/spring-boot-starter-data-jpa
[3] https://howtodoinjava.com/spring-boot/h2-database-example/
[4] https://spring.io/guides/gs/accessing-data-jpa/
[5] https://docs.spring.io/spring-boot/docs/1.3.0.RELEASE/reference/html/boot-features-sql.html
[6] https://spring.io/projects/spring-data-jpa/
[7] https://docs.spring.io/spring-data/jpa/reference/data-commons/dependencies.html
[8] https://stackoverflow.com/questions/37903105/how-to-configure-spring-boot-to-use-file-based-h2-database