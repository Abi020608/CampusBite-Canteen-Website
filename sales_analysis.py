
import mysql.connector
import pandas as pd
import matplotlib.pyplot as plt

try:
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="abinaya@0213",
        database="canteen_db"
    )

    print("Database Connected Successfully!")

    # Chart 1: Daily Sales Revenue
    daily_query = """
        SELECT DATE(order_time) AS sales_date,
               SUM(total_amount) AS revenue
        FROM canteen_orders
        GROUP BY DATE(order_time)
        ORDER BY sales_date
    """

    daily = pd.read_sql(daily_query, connection)

    if not daily.empty:
        daily["revenue"] = pd.to_numeric(daily["revenue"])

        plt.figure(figsize=(9, 5))
        plt.plot(daily["sales_date"], daily["revenue"], marker="o")
        plt.title("CampusBite - Daily Sales Revenue")
        plt.xlabel("Date")
        plt.ylabel("Revenue (Rs.)")
        plt.xticks(rotation=45)
        plt.grid(True)
        plt.tight_layout()
        plt.show()
    else:
        print("No sales data available!")

    # Chart 2: Top 5 Best Selling Food Items
    items_query = """
        SELECT m.name AS item_name,
               SUM(ol.quantity) AS total_sold
        FROM order_lines ol
        JOIN menu_items m ON ol.menu_item_id = m.id
        GROUP BY m.id, m.name
        ORDER BY total_sold DESC
        LIMIT 5
    """

    items = pd.read_sql(items_query, connection)

    if not items.empty:
        plt.figure(figsize=(9, 5))
        plt.bar(items["item_name"], items["total_sold"])
        plt.title("CampusBite - Top 5 Best Selling Food Items")
        plt.xlabel("Food Items")
        plt.ylabel("Quantity Sold")
        plt.xticks(rotation=30)
        plt.tight_layout()
        plt.show()
    else:
        print("No order items available!")

    connection.close()
    print("Sales Analysis Completed!")

except Exception as e:
    print("Error:", e)


import mysql.connector
import pandas as pd
import matplotlib.pyplot as plt

try:
    connection = mysql.connector.connect(
        host="localhost",
        user="root",
        password="YOUR_MYSQL_PASSWORD",
        database="canteen_db"
    )

    print("Database Connected Successfully!")

    # CHART 1: Daily Sales Revenue
    query1 = """
        SELECT DATE(order_time) AS sales_date,
               SUM(total_amount) AS revenue
        FROM canteen_orders
        GROUP BY DATE(order_time)
        ORDER BY sales_date
    """

    daily = pd.read_sql(query1, connection)

    if not daily.empty:
        daily["revenue"] = pd.to_numeric(daily["revenue"])

        plt.figure(figsize=(9, 5))
        plt.plot(daily["sales_date"], daily["revenue"], marker="o")
        plt.title("CampusBite - Daily Sales Revenue")
        plt.xlabel("Date")
        plt.ylabel("Revenue (Rs.)")
        plt.xticks(rotation=45)
        plt.grid(True)
        plt.tight_layout()
        plt.show()
    else:
        print("No sales data available!")

    # CHART 2: Top 5 Best Selling Food Items
    query2 = """
        SELECT m.name AS item_name,
               SUM(ol.quantity) AS total_sold
        FROM order_lines ol
        JOIN menu_items m ON ol.menu_item_id = m.id
        GROUP BY m.id, m.name
        ORDER BY total_sold DESC
        LIMIT 5
    """

    best_items = pd.read_sql(query2, connection)

    if not best_items.empty:
        plt.figure(figsize=(9, 5))
        plt.bar(best_items["item_name"], best_items["total_sold"])
        plt.title("CampusBite - Top 5 Best Selling Food Items")
        plt.xlabel("Food Items")
        plt.ylabel("Quantity Sold")
        plt.xticks(rotation=30)
        plt.tight_layout()
        plt.show()
    else:
        print("No order items available!")

    connection.close()
    print("Sales Analysis Completed Successfully!")

except Exception as e:
    print("Error:", e)
