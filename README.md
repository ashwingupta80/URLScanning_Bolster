# URL Scanning Web Service

## Overview

This project is a Full Stack/Backend exercise aimed at building a robust Web service API for scanning URLs and extracting various artifacts from them. The service is capable of fetching information such as screenshots, IP addresses, source and destination URLs (in case of redirection), ASN information, SSL certificate details (for HTTPS sites), page source, and natural language content extracted from the source. The extracted information is then saved in JSON format in a local PostgreSQL database.

## Technologies Used

- **Backend Framework:** Spring Boot
- **Database:** PostgreSQL
- **Dependency Management:** Maven

## Dependencies

The project utilizes various dependencies to achieve its functionality. Some of the key dependencies include:

- `spring-boot-starter-data-jpa`: Provides support for data access using JPA (Java Persistence API).
- `spring-boot-starter-web`: Enables building web applications using Spring MVC.
- `spring-boot-devtools`: Offers additional development-time features for increased productivity.
- `postgresql`: Provides the PostgreSQL JDBC driver for connecting to the database.
- `com.microsoft.playwright`: Utilized for launching URLs in a browser and extracting information.
- `com.maxmind.geoip2`: Used for retrieving ASN information for IP addresses.

## Setup Instructions

To run the program locally, follow these steps:

1. **Clone the Repository:** 
   ```
   git clone https://github.com/ashwingupta80/URLScanning.git
   ```

2. **Database Configuration:**
   - Install PostgreSQL if not already installed.
   - Create a PostgreSQL database named `urlscanner`.
   - Optionally, update the `spring.datasource.username` and `spring.datasource.password` properties in `application.properties` file if your PostgreSQL setup requires authentication.

3. **Build and Run:**
   ```
   cd URLScanning
   mvn spring-boot:run
   ```

4. **Access the API:**
   Once the application is running, you can access the API endpoints using a tool like Postman or curl.

## API Endpoints

The following API endpoints are available:

1. **Stage 1 Endpoint:**
   - `/scan?url=your_url`
   - Method: `GET`
   - Description: Gets all the data for the 3 stages mentioned and stores them in the database.
   - Example usage `http://localhost:8080/scan?url=https://gmail.com`


## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributors

- Ashwin Guta

---
