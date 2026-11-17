# Project Report Outline

## Problem statement
Manual canteen ordering and sales tracking may cause queues, stock confusion and difficulty identifying popular food. This project digitizes menu display, order placement, stock updates and sales summaries.

## Software Engineering
Functional requirements: display menu; accept customer and multiple items; validate stock; calculate/store total; reduce stock; show recent orders and sales dashboard.
Non-functional requirements: responsive UI, input validation, persistent storage, modular backend.
Use cases: View menu, Create order, Validate stock, Update inventory, View recent orders, View sales analytics.
Architecture: Browser (HTML/CSS/JS) → Spring Boot REST API → Service/Repository → MySQL.

## DBMS
Relations:
- menu_items(id PK, name, category, price, stock_qty, available)
- canteen_orders(id PK, customer_name, order_time, total_amount, status)
- order_lines(id PK, order_id FK, menu_item_id FK, quantity, unit_price, line_total)
Relationships: one order has many order lines; one menu item may appear in many order lines.
Normalization: 1NF atomic columns and separate line rows; 2NF item/order attributes are not stored as repeating item columns; 3NF menu attributes, order header attributes and line facts are separated into their respective relations.

Example SQL:
```sql
SELECT DATE(order_time), SUM(total_amount) FROM canteen_orders GROUP BY DATE(order_time);
SELECT m.name, SUM(l.quantity) AS units_sold FROM order_lines l JOIN menu_items m ON m.id=l.menu_item_id GROUP BY m.id,m.name ORDER BY units_sold DESC;
SELECT name,stock_qty FROM menu_items WHERE stock_qty<=5;
```

## Java Programming
MenuItem, CanteenOrder and OrderLine are JPA entity classes. OrderService contains order business logic and transaction boundary. ApiController exposes REST endpoints. Spring Data repositories handle persistence. Validation and exception handling protect input.

## Foundations of Data Science
Dashboard summarizes daily order count, daily revenue, menu count, low-stock count and best-selling items from database records. Extension: export database tables to CSV, clean missing values, derive date/category features, and plot daily revenue/category demand using Python Pandas and Matplotlib.

## Suggested test cases
| ID | Test | Expected result |
|---|---|---|
| TC01 | Open website with backend and DB running | Dashboard loads |
| TC02 | Open menu | Menu and stock appear |
| TC03 | Place valid order | Order saved, total shown, stock reduced |
| TC04 | Submit blank customer name | Validation error |
| TC05 | Order more than available stock | Order rejected |
| TC06 | Refresh dashboard after order | KPIs update |
| TC07 | Open sales analytics after orders | Best sellers appear |
| TC08 | Set item stock to zero | Item cannot be added |

Record actual Pass/Fail results after testing locally.