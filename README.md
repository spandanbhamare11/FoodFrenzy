# FoodFrenzy — Food Ordering API

A small backend API for managing a food menu and creating simple orders, built with Java 21, Spring Boot, Spring Data JPA, and MySQL.

## Features
- CRUD endpoints for menu items
- Create and retrieve customer orders
- JPA relationship from each order to a menu item
- Automatically calculate order total from item price and quantity
- Basic request validation and error responses

## Run locally
1. Install JDK 21, Maven, and MySQL 8+.
2. Configure `DB_USERNAME` and `DB_PASSWORD` environment variables or update `application.properties` for your local MySQL setup.
3. Run `mvn spring-boot:run`.
4. Base URL: `http://localhost:8080`.

## Endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/menu` | List menu items |
| GET | `/api/menu/{id}` | Get a menu item |
| POST | `/api/menu` | Create a menu item |
| PUT | `/api/menu/{id}` | Update a menu item |
| DELETE | `/api/menu/{id}` | Delete a menu item |
| GET | `/api/orders` | List orders |
| GET | `/api/orders/{id}` | Get an order |
| POST | `/api/orders` | Create an order |

### Example menu item
```json
{"name":"Paneer Wrap","category":"Main Course","price":180.00,"available":true}
```
### Example order
```json
{"customerName":"Aarav Patil","menuItem":{"id":1},"quantity":2}
```
The order total is calculated by the API. Do not commit real database credentials.
