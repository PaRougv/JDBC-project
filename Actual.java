import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

class Queries_Run {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private Connection conn;

    public Queries_Run() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDatabase(String dbName) {
        try {
            Statement stm = conn.createStatement();
            String query = "CREATE DATABASE " + dbName;
            stm.executeUpdate(query);
            System.out.println("Database " + dbName + " Created Successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String dbName, String tableDetails) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            String query = "CREATE TABLE " + tableDetails;
            stm.executeUpdate(query);
            System.out.println("Table " + tableDetails + " Created Successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertValues(String dbName, String tableName, String values) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            String query = "INSERT INTO " + tableName + " VALUES " + values;
            stm.executeUpdate(query);
            System.out.println("Values inserted into " + tableName + " Successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeDatabase(String dbName) {
        try {
            conn.setCatalog(dbName);
            System.out.println("Changed to database: " + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String dbName, String tableName, String updateQuery) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            stm.executeUpdate("UPDATE " + tableName + " SET " + updateQuery);
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void naturalJoin(String dbName, String table1, String table2, String joinCondition) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM " + table1 + " NATURAL JOIN " + table2 + " ON " + joinCondition;
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()) {
                // Process the results here
            }
            System.out.println("Natural join executed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cartesianProduct(String dbName, String table1, String table2) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM " + table1 + ", " + table2;
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()) {
                // Process the results here
            }
            System.out.println("Cartesian product executed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showRecords(String dbName, String tableName) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = stm.executeQuery(query);
            System.out.println("Records in table " + tableName + ":");
            while (resultSet.next()) {
                // Assuming the table has columns named col1, col2, col3, ..., colN
                int numColumns = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeCustomQuery(String dbName, String customQuery) {
        try {
            conn.setCatalog(dbName);
            Statement stm = conn.createStatement();
            stm.executeUpdate(customQuery);
            System.out.println("Custom query executed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Actual {
    public static void main(String[] args) {
        Queries_Run queries_run = new Queries_Run();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Database");
            System.out.println("2. Create Table");
            System.out.println("3. Insert Values");
            System.out.println("4. Change Database");
            System.out.println("5. Update Record");
            System.out.println("6. Perform Natural Join");
            System.out.println("7. Perform Cartesian Product");
            System.out.println("8. Show Records in Table");
            System.out.println("9. Custom Query");
            System.out.println("10. Exit");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter database name:");
                    String dbName = sc.nextLine();
                    queries_run.createDatabase(dbName);
                    break;
                case 2:
                    System.out.println("Enter database name:");
                    String dbNameForTable = sc.nextLine();
                    System.out.println("Enter table details in SQL format:");
                    String tableDetails = sc.nextLine();
                    queries_run.createTable(dbNameForTable, tableDetails);
                    break;
                case 3:
                    System.out.println("Enter database name:");
                    String dbNameForValues = sc.nextLine();
                    System.out.println("Enter table name:");
                    String tableName = sc.nextLine();
                    System.out.println("Enter values in SQL format:");
                    String values = sc.nextLine();
                    queries_run.insertValues(dbNameForValues, tableName, values);
                    break;
                case 4:
                    System.out.println("Enter database name:");
                    String changeDbName = sc.nextLine();
                    queries_run.changeDatabase(changeDbName);
                    break;
                case 5:
                    System.out.println("Enter database name:");
                    String updateDbName = sc.nextLine();
                    System.out.println("Enter table name:");
                    String updateTableName = sc.nextLine();
                    System.out.println("Enter update query:");
                    String updateQuery = sc.nextLine();
                    queries_run.updateRecord(updateDbName, updateTableName, updateQuery);
                    break;
                case 6:
                    System.out.println("Enter database name:");
                    String joinDbName = sc.nextLine();
                    System.out.println("Enter first table name:");
                    String table1 = sc.nextLine();
                    System.out.println("Enter second table name:");
                    String table2 = sc.nextLine();
                    System.out.println("Enter join condition:");
                    String joinCondition = sc.nextLine();
                    queries_run.naturalJoin(joinDbName, table1, table2, joinCondition);
                    break;
                case 7:
                    System.out.println("Enter database name:");
                    String productDbName = sc.nextLine();
                    System.out.println("Enter first table name:");
                    String productTable1 = sc.nextLine();
                    System.out.println("Enter second table name:");
                    String productTable2 = sc.nextLine();
                    queries_run.cartesianProduct(productDbName, productTable1, productTable2);
                    break;
                case 8:
                    System.out.println("Enter database name:");
                    String showDbName = sc.nextLine();
                    System.out.println("Enter table name:");
                    String showTableName = sc.nextLine();
                    queries_run.showRecords(showDbName, showTableName);
                    break;
                case 9:
                    System.out.println("Enter custom query:");
                    String customQuery = sc.nextLine();
                    System.out.println("Enter database name:");
                    String customDbName = sc.nextLine();
                    queries_run.executeCustomQuery(customDbName, customQuery);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 10);

        queries_run.closeConnection();
    }
}
                

