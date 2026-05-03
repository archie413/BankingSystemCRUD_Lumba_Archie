# Simple Banking System with CRUD Operations  

## 📖 System Description
The Simple Banking Application is a Java Swing-based desktop application designed to simulate basic banking operations. It is integrated with a MySQL database to efficiently manage customer data, account balances, and transaction histories. 

The system features three primary modules:
1. **Customer & Account Management:** Allows bank staff to Create, Read, Update, and Delete (CRUD) customer profiles and their associated savings or current accounts.
2. **Transaction Management:** Facilitates secure deposit and withdrawal operations, ensuring users cannot withdraw more than their current balance. 
3. **Transaction History:** Provides a searchable log of all transactions linked to specific accounts.

## 🗄️ Entity-Relationship Diagram (ERD) Explanation
The database follows a relational design containing three main tables, structured to maintain data integrity and track banking activities accurately:

1. **Customer Table:** Stores the client's personal information.
   * `customer_id` (Primary Key)
   * `first_name`, `last_name`, `email`, `phone_number`
2. **Account Table:** Stores specific account details linked to a customer.
   * `account_id` (Primary Key)
   * `customer_id` (Foreign Key referencing `Customer`)
   * `account_type` (e.g., Savings, Current)
   * `balance`
3. **Transaction Table:** Logs every deposit or withdrawal made on an account.
   * `transaction_id` (Primary Key)
   * `account_id` (Foreign Key referencing `Account`)
   * `transaction_type` (Deposit / Withdraw)
   * `amount`, `transaction_date`

**Relationships:**
* **One-to-Many (Customer to Account):** One Customer can own multiple Accounts (e.g., a Savings and a Current account).
* **One-to-Many (Account to Transaction):** One Account can have multiple Transactions over time.

## ⚙️ How to Run the Program

**Prerequisites:**
* Java Development Kit (JDK 8 or higher)
* MySQL Server & MySQL Workbench (or XAMPP)
* An IDE like Apache NetBeans or Eclipse
* MySQL JDBC Driver (`mysql-connector-java.jar`)

**Steps to Execute:**
1. **Database Setup:**
   * Open your MySQL environment.
   * Create a new database named `simple_banking_systems`.
   * Import the provided `.sql` file to generate the tables.
2. **Configure Database Credentials:**
   * Open the project in your Java IDE.
   * Navigate to `DBConnection.java`.
   * Update the username (default is `"root"`) and password (currently set to `"SkennMYpets0025"`) to match your local MySQL server credentials.
3. **Add Dependencies:**
   * Ensure the MySQL JDBC Driver is added to your project's Libraries/Classpath.
4. **Run the Application:**
   * Right-click on `CustomerAccountWindow.java` (or your main class) and select **Run File**.

https://drive.google.com/file/d/11oa9BIwiDBVSkWQHlAMYDIakHkN01YC7/view?usp=sharing

<img width="1919" height="1199" alt="image" src="https://github.com/user-attachments/assets/6427028a-2f44-4e62-ba1f-0c8d0d7d6522" />
<img width="1219" height="1109" alt="image" src="https://github.com/user-attachments/assets/5c7a06e8-0539-44b1-8da7-f2cfa6ff5f1a" />
<img width="1231" height="1119" alt="image" src="https://github.com/user-attachments/assets/948b9135-fc36-4a60-81cb-31f6d365096d" />
<img width="1227" height="1120" alt="image" src="https://github.com/user-attachments/assets/3eddb6d3-028f-4fdf-9c13-e2a5ae5b90a7" />
<img width="1220" height="761" alt="image" src="https://github.com/user-attachments/assets/5cf2789a-4691-48fa-af9f-87d1823c7d39" />
<img width="1209" height="761" alt="image" src="https://github.com/user-attachments/assets/31992043-751c-453c-974c-f8e8fee8fc6c" />
<img width="1167" height="880" alt="image" src="https://github.com/user-attachments/assets/a4b29f6d-9630-4ab9-9bab-41ccb557eb46" />
<img width="1161" height="876" alt="image" src="https://github.com/user-attachments/assets/edabc893-45c1-4396-99a0-5a01d5dad81c" />


