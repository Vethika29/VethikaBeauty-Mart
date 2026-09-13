# VETHIKA MART
Mini Full-Stack Cosmetics E-Commerce Project

## Stack
Frontend: HTML + CSS + JavaScript
Backend: Java 17 + Spring Boot + REST API
Database: MySQL
ORM: Spring Data JPA / Hibernate
Password hashing: BCrypt

## Included
- Buyer registration/login
- Admin login
- Seller login
- 50 seeded cosmetics products
- Search
- Category filters
- Product price/MRP/discount/rating/color/stock
- Add to cart
- Quantity update/remove
- Checkout
- My Orders
- Admin statistics
- Product management REST API
- VS Code extension recommendations

## Demo logins
Buyer: buyer@beautymart.com / buyer123
Seller: seller@beautymart.com / seller123
Admin: admin@beautymart.com / admin123

## Run
1. Install JDK 17+ and MySQL.
2. Run database/setup.sql.
3. Edit src/main/resources/application.properties and set your MySQL password.
4. Open this folder in VS Code.
5. Run BeautyMartApplication.java.
6. Open http://localhost:8080

## Maven alternative
Windows:
mvnw.cmd spring-boot:run
If Maven Wrapper is not present, use:
mvn spring-boot:run

## Important
This is an academic/demo mini project. The login session is kept in browser localStorage and the management endpoints are
intentionally simple for a student project. For production use, add Spring Security, JWT/session authentication,
authorization checks, CSRF protection, payment gateway integration and stronger validation.
