# College Canteen Ordering and Sales Analysis System
A full-stack college project: responsive website + Java Spring Boot REST backend + MySQL.

## Requirements
- JDK 21
- MySQL Server 8+
- Maven 3.9+
- VS Code (Extension Pack for Java recommended)

## Run
1. Open MySQL Workbench and run `database/schema.sql`.
2. Open `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your MySQL password. Change username if needed.
3. Open this folder in VS Code.
4. Open Terminal > New Terminal and run:
   ```powershell
   mvn spring-boot:run
   ```
   If Maven is not recognized, use your full Maven path:
   ```powershell
   & "C:\Users\YourUser\Downloads\apache-maven-3.10.1-bin\apache-maven-3.10.1\bin\mvn.cmd" spring-boot:run
   ```
5. Keep the terminal running and open `http://localhost:8080` in Chrome.

The website is served by Spring Boot. No Live Server or separate frontend server is required.

## Features
- Dashboard with today's order count, revenue, menu count and low-stock count
- Browse menu and add multiple items to cart
- Place customer orders with stock validation and automatic stock deduction
- Recent orders and best-selling item analytics
- REST API for menu, orders and dashboard

## API
- `GET /api/menu`
- `POST /api/orders`
- `GET /api/orders`
- `GET /api/dashboard`
- `POST /api/menu` (add item)
- `PUT /api/menu/{id}/stock` (update stock)

## Four-subject mapping
- Software Engineering: requirements, use cases, architecture and test plan in `docs/project-report-outline.md`
- DBMS: relational tables, PK/FK, relationships, SQL and normalization notes in `database/schema.sql` and report outline
- Java Programming: Java entity, service, repository and REST controller layers
- Foundations of Data Science: MySQL-derived sales KPIs and best-selling-item analysis; CSV/Python extension described in report

## Important
Customize college name, food menu and screenshots. Run the test cases and record actual Pass/Fail outcomes in your report. This is a student project starter, not a production-secured system; authentication and stronger concurrent-stock locking should be added for deployment.