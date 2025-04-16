# Integration-Assignment

## 1. Overview

This project is a full-stack **bidirectional data ingestion tool** that enables seamless interaction between **ClickHouse** and **Flat Files (CSV)**.

## 2. Tech Stack

- **Frontend:** React.js  
- **Backend:** Spring Boot (Java)  
- **Database:** ClickHouse  
- **Authentication:** JWT (JSON Web Tokens)

## 3. Folder Structure

```
Integration-Assignment/
├── ingestion-backend/       # Spring Boot backend
└── ingestion-frontend/      # React frontend
```

## 4. Backend Setup (Spring Boot)

### 4.1 Navigate to Backend

```bash
cd ingestion-backend
```

### 4.2 Configuration

Edit the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:clickhouse://<HOST>:<PORT>/<DATABASE>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>

jwt.secret=<YOUR_SECRET_KEY>
```

### 4.3 Run the Backend

```bash
./mvnw spring-boot:run
```
Backend runs at: `http://localhost:8080`

## 5. Frontend Setup (React)

### 5.1 Navigate to Frontend

```bash
cd ../ingestion-frontend
```

### 5.2 Install Dependencies

```bash
npm install
```

### 5.3 Run the Frontend

```bash
npm start
```
Frontend runs at: `http://localhost:3000`
