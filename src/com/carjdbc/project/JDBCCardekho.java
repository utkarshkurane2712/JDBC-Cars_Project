package com.carjdbc.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCCardekho {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static String query;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("1. Display All Cars");
            System.out.println();
            System.out.println("2. Add New Car");
            System.out.println();
            System.out.println("3. Search Cars ");
            System.out.println();
            System.out.println("4. Remove The Car");
            System.out.println();
            System.out.println("5. Edit Car");
            System.out.println();
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------------------------------------------");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        openConnection();
                        query = "SELECT * FROM car";
                        preparedStatement = connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            System.out.println(resultSet.getInt(1));
                            System.out.println(resultSet.getString(2));
                            System.out.println(resultSet.getString(3));
                            System.out.println(resultSet.getString(4));
                            System.out.println(resultSet.getDouble(5));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            closeConnection();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:

                    try {
                        openConnection();
                        query = "INSERT INTO car VALUES(?,?,?,?,?)";
                        preparedStatement = connection.prepareStatement(query);

                        System.out.println("How many cars you want added?");
                        int count = scanner.nextInt();
                        for (int i = 1; i <= count; i++) {
                            System.out.println("Enter car id");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter car name");
                            String name = scanner.nextLine();
                            System.out.println("Enter car model");
                            String model = scanner.nextLine();
                            System.out.println("Enter car color");
                            String color = scanner.nextLine();
                            System.out.println("Enter car Price");
                            Double price = scanner.nextDouble();
                            preparedStatement.setInt(1, id);
                            preparedStatement.setString(2, name);
                            preparedStatement.setString(3, model);
                            preparedStatement.setString(4, color);
                            preparedStatement.setDouble(5, price);
                            preparedStatement.addBatch();
                        }

                        int[] res = preparedStatement.executeBatch();

                        System.out.println(res.length + " car(s) added");
                        System.out.println();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    finally {
                        try {
                            closeConnection();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    break;

                case 3:
                    boolean continueSearching = true; // Flag to control loop exit
                    while (continueSearching) { // Loop until user wants to return to main menu
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.println("1. Search car by id");
                        System.out.println();
                        System.out.println("2. Search car by name");
                        System.out.println();
                        System.out.println("3. Search Cars by model");
                        System.out.println();
                        System.out.println("4. Search car by color");
                        System.out.println();
                        System.out.println("5. Search car by price");
                        System.out.println();
                        System.out.println("6. Return to main menu"); // Option to return to main menu
                        System.out.println("---------------------------------------------------------------------------------");

                        System.out.print("Enter your choice: ");
                        int choice1 = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        switch (choice1) {
                            case 1:
                                System.out.println("Enter car id");
                                int id = scanner.nextInt();
                                try {
                                    openConnection();
                                    query = "SELECT * FROM car WHERE id=?";
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setInt(1, id);
                                    resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        System.out.println(resultSet.getInt(1));
                                        System.out.println(resultSet.getString(2));
                                        System.out.println(resultSet.getString(3));
                                        System.out.println(resultSet.getString(4));
                                        System.out.println(resultSet.getDouble(5));
                                    } else {
                                        System.out.println("Car not found");
                                        System.out.println();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        closeConnection();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;

                            case 2:
                                System.out.println("Enter car name");
                                String name = scanner.nextLine();
                                try {
                                    openConnection();
                                    query = "SELECT * FROM car WHERE name=?";
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setString(1, name);
                                    resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        System.out.println(resultSet.getInt(1));
                                        System.out.println(resultSet.getString(2));
                                        System.out.println(resultSet.getString(3));
                                        System.out.println(resultSet.getString(4));
                                        System.out.println(resultSet.getDouble(5));

                                    } else {
                                        System.out.println("Car not found");
                                        System.out.println();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        closeConnection();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;
                                
                            case 3:
                                System.out.println("Enter car model");
                                String model = scanner.nextLine();
                                try {
                                    openConnection();
                                    query = "SELECT * FROM car WHERE model=?";
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setString(1, model);
                                    resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        System.out.println(resultSet.getInt(1));
                                        System.out.println(resultSet.getString(2));
                                        System.out.println(resultSet.getString(3));
                                        System.out.println(resultSet.getString(4));
                                        System.out.println(resultSet.getDouble(5));

                                    } else {
                                        System.out.println("Car not found");
                                        System.out.println();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        closeConnection();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;
                                
                            case 4:
                                System.out.println("Enter car color");
                                String color = scanner.nextLine();
                                try {
                                    openConnection();
                                    query = "SELECT * FROM car WHERE color=?";
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setString(1, color);
                                    resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        System.out.println(resultSet.getInt(1));
                                        System.out.println(resultSet.getString(2));
                                        System.out.println(resultSet.getString(3));
                                        System.out.println(resultSet.getString(4));
                                        System.out.println(resultSet.getDouble(5));

                                    } else {
                                        System.out.println("Car not found");
                                        System.out.println();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        closeConnection();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;
                                
                            case 5:
                                System.out.println("Enter car price");
                                double price = scanner.nextDouble();
                                try {
                                    openConnection();
                                    query = "SELECT * FROM car WHERE price=?";
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setDouble(1, price);
                                    resultSet = preparedStatement.executeQuery();

                                    if (resultSet.next()) {
                                        System.out.println(resultSet.getInt(1));
                                        System.out.println(resultSet.getString(2));
                                        System.out.println(resultSet.getString(3));
                                        System.out.println(resultSet.getString(4));
                                        System.out.println(resultSet.getDouble(5));

                                    } else {
                                        System.out.println("Car not found");
                                        System.out.println();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        closeConnection();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;

                            case 6: // If user chooses to return to main menu
                                continueSearching = false; // Set flag to false to exit loop
                                break;

                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter car id");
                    int id1 = scanner.nextInt();
                    try {
                        openConnection();
                        query = "DELETE FROM car WHERE id=?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, id1);

                        int res = preparedStatement.executeUpdate();

                        if (res == 1) {
                            System.out.println("car is removed");
                            System.out.println();
                        } else {
                            System.out.println("car not found");
                            System.out.println();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            closeConnection();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    break;

                case 5:
                    System.out.println("Enter car id");
                    int id3 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new Name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new Model: ");
                    String newModel = scanner.next();
                    System.out.print("Enter new color: ");
                    String newColor = scanner.next();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();

                    try {
                        openConnection();
                        query = "UPDATE car SET name=?,model=?, color=?, price=? WHERE id=?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, newName);
                        preparedStatement.setString(2, newModel);
                        preparedStatement.setString(3, newColor);
                        preparedStatement.setDouble(4, newPrice);
                        preparedStatement.setInt(5, id3);

                        preparedStatement.executeUpdate();

                        System.out.println("car is edited");
                        System.out.println();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            closeConnection();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    break;

                case 6:
                    System.out.println("Exiting Car Dekho App. Goodbye!");
                    System.out.println();
                    System.exit(0);

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }

        }

    }

    private static void openConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4", "root", "root");

    }

    private static void closeConnection() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }

    }

}


