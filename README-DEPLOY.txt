Pahana Edu Billing – Setup & Deployment (Tomcat + XAMPP / MySQL)

1) Import into IntelliJ IDEA as Maven project.
2) Start XAMPP MySQL and run the SQL at: src/main/resources/schema.sql
3) Update DB credentials in src/main/java/util/DBConnection.java if needed.
4) Build: mvn clean package
5) Deploy the generated WAR to Tomcat (copy target/Billing-1.0-SNAPSHOT.war into Tomcat webapps).
6) Browse: http://localhost:8080/Billing-1.0-SNAPSHOT/
7) Features:
   - Login (existing)
   - Customers: /customers (CRUD)
   - Billing: /billing (calculate & printable view)
   - Items: existing ItemController + JSPs
   - Help: /help
8) Tests: mvn -q -Dtest=BillingServiceTest test
