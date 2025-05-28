# ✈️ SpringBoot Flight Search Service

This Spring Boot application fetches real-time flight data from a third-party API, stores it into a MySQL database using JPA, and exposes REST endpoints to retrieve, filter, and sort flights.

> Developed as part of a backend assignment – clean, modular, and production-ready.

---

## 🔧 Tech Stack

| Layer       | Technology              |
|-------------|-------------------------|
| Language    | Java 17                 |
| Framework   | Spring Boot 3           |
| ORM         | Spring Data JPA         |
| Database    | MySQL                   |
| HTTP Client | RestTemplate            |
| JSON Mapper | Jackson                 |
| Build Tool  | Maven (wrapper)         |

---

## 📁 Project Structure

springboot-flight-search-service/
└─ src
└─ main
├─ java/org.tripgain/
│ ├─ controller → REST endpoints
│ ├─ service → FlightService logic
│ ├─ repository → JPA repository interface
│ ├─ model → FlightInfo entity
│ └─ utility → FlightJsonParser
└─ resources/
└─ application.properties


---

## 🚀 How to Run Locally

### ✅ Prerequisites

- JDK 17
- MySQL running (port 3306)
- Maven installed (or use the included wrapper)

---

### 🛠️ Steps

```bash
# 1. Clone the repo
git clone https://github.com/swayam228/springboot-flight-search-service.git
cd springboot-flight-search-service

# 2. Configure your MySQL DB
#    - Create a schema named 'flight_db'
#    - Update username & password in: src/main/resources/application.properties

# 3. Run the app
./mvnw spring-boot:run
# or
mvn spring-boot:run

| Method | Endpoint                       | Description                            |
| ------ | ------------------------------ | -------------------------------------- |
| POST   | `/api/flights/fetch-and-store` | Fetch data from external API & save    |
| GET    | `/api/flights`                 | List all flights sorted by price (asc) |
| GET    | `/api/flights/{flightNumber}`  | Find flights by flight number          |
| GET    | `/api/flights/max-price`       | Get the most expensive flight          |
| GET    | `/api/flights/min-price`       | Get the cheapest flight                |

CREATE TABLE flight_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gw_flight_key VARCHAR(255),
    carrier VARCHAR(10),
    flight_number VARCHAR(10),
    fare_type VARCHAR(100),
    total_amount DOUBLE
);
