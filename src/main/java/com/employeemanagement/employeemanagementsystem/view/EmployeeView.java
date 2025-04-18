package com.employeemanagement.employeemanagementsystem.view;

import com.employeemanagement.employeemanagementsystem.model.Employee;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * JavaFX view component for displaying employees in a table
 */

public class EmployeeView extends BorderPane {

    private TableView<Employee<Integer>> employeeTable;
    private ObservableList<Employee<Integer>> employeeData;
    private Label titleLabel;

    // Constructor
    public EmployeeView() {
        setPadding(new Insets(20));

        // Create a title with improved styling
        titleLabel = new Label("Employee Directory");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c4550; -fx-padding: 0 0 10 0;");

        VBox contentBox = new VBox(10);
        contentBox.getChildren().add(titleLabel);

        setupEmployeeTable();
        contentBox.getChildren().add(employeeTable);

        setCenter(contentBox);
    }

    private void setupEmployeeTable() {
        employeeTable = new TableView<>();
        employeeTable.setStyle("-fx-font-size: 13px; -fx-border-color: #E0E0E0; -fx-border-width: 1px;");
        employeeData = FXCollections.observableArrayList();
        employeeTable.setItems(employeeData);

        // Create table columns with styles
        TableColumn<Employee<Integer>, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getEmployeeId()));
        idColumn.setPrefWidth(70);
        idColumn.setMinWidth(50);
        idColumn.setCellFactory(column -> {
            TableCell<Employee<Integer>, Integer> cell = new TableCell<Employee<Integer>, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.toString());
                        setAlignment(Pos.CENTER); // Center the text in each cell
                    }
                }
            };
            return cell;
        });

        TableColumn<Employee<Integer>, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployeeName()));
        nameColumn.setPrefWidth(180);
        nameColumn.setMinWidth(120);
        nameColumn.setCellFactory(column -> {
            TableCell<Employee<Integer>, String> cell = new TableCell<Employee<Integer>, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER); // Center the text in each cell
                    }
                }
            };
            return cell;
        });


        TableColumn<Employee<Integer>, String> deptColumn = new TableColumn<>("Department");
        deptColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployeeDepartment().name()));
        deptColumn.setPrefWidth(140);
        deptColumn.setMinWidth(100);
        deptColumn.setCellFactory(column -> {
            TableCell<Employee<Integer>, String> cell = new TableCell<Employee<Integer>, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER); // Center the text in each cell
                    }
                }
            };
            return cell;
        });

        TableColumn<Employee<Integer>, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(data -> new SimpleStringProperty(String.format("$%.2f", data.getValue().getEmployeeSalary())));
        salaryColumn.setPrefWidth(100);
        salaryColumn.setMinWidth(90);
        salaryColumn.setCellFactory(column -> {
            TableCell<Employee<Integer>, String> cell = new TableCell<Employee<Integer>, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER); // Center the text in each cell
                    }
                }
            };
            return cell;
        });


        TableColumn<Employee<Integer>, Double> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPerformanceRating()).asObject());
        ratingColumn.setPrefWidth(80);
        ratingColumn.setMinWidth(60);

        // Custom cell factory for rating colors - just the text colored, not the entire cell
        ratingColumn.setCellFactory(column -> new TableCell<Employee<Integer>, Double>() {
            @Override
            protected void updateItem(Double rating, boolean empty) {
                super.updateItem(rating, empty);

                if (empty || rating == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.format("%.1f", rating));
                    // Only color the text, not the background
                    if (rating >= 4.0) {
                        setTextFill(Color.rgb(0, 150, 0)); // Darker green
                        setStyle("-fx-font-weight: bold;");
                    } else if (rating >= 3.0) {
                        setTextFill(Color.rgb(0, 100, 150)); // Blue
                        setStyle("-fx-font-weight: bold;");
                    } else if (rating >= 2.0) {
                        setTextFill(Color.rgb(220, 120, 0)); // Orange
                        setStyle("-fx-font-weight: bold;");
                    } else {
                        setTextFill(Color.rgb(200, 0, 0)); // Red
                        setStyle("-fx-font-weight: bold;");
                    }
                    setAlignment(Pos.CENTER);
                }
            }
        });

        TableColumn<Employee<Integer>, Integer> expColumn = new TableColumn<>("Experience");
        expColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getYearsOfExperience()));
        expColumn.setPrefWidth(100);
        expColumn.setMinWidth(80);

        // Custom cell factory for experience
        expColumn.setCellFactory(column -> new TableCell<Employee<Integer>, Integer>() {
            @Override
            protected void updateItem(Integer years, boolean empty) {
                super.updateItem(years, empty);

                if (empty || years == null) {
                    setText(null);
                } else {
                    setText(years + (years == 1 ? " year" : " years"));
                    setAlignment(Pos.CENTER);
                }
            }
        });

        TableColumn<Employee<Integer>, Boolean> activeColumn = new TableColumn<>("Active");
        activeColumn.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isActive()));
        activeColumn.setPrefWidth(80);
        activeColumn.setMinWidth(70);

        // Custom cell factory for active status - just colored text, not entire cell
        activeColumn.setCellFactory(column -> new TableCell<Employee<Integer>, Boolean>() {
            @Override
            protected void updateItem(Boolean active, boolean empty) {
                super.updateItem(active, empty);

                if (empty || active == null) {
                    setText(null);
                    setStyle("");
                } else {
                    if (active) {
                        setText("Active");
                        setTextFill(Color.rgb(0, 150, 0)); // Green text
                        setStyle("-fx-font-weight: bold;");
                        setAlignment(Pos.CENTER);
                    } else {
                        setText("Inactive");
                        setTextFill(Color.rgb(150, 150, 150)); // Grey text
                        setStyle("-fx-font-style: italic;");
                        setAlignment(Pos.CENTER);
                    }
                }
            }
        });

        // Set column header style - lighter background with dark text for better readability
        String columnHeaderStyle = "-fx-background-color: #f2f2f2; -fx-text-fill: #333333; -fx-font-weight: bold; -fx-padding: 8px; -fx-border-color: #dddddd; -fx-border-width: 0 0 1 0; -fx-alignment: CENTER;";
        idColumn.setStyle(columnHeaderStyle);
        nameColumn.setStyle(columnHeaderStyle);
        deptColumn.setStyle(columnHeaderStyle);
        salaryColumn.setStyle(columnHeaderStyle);
        ratingColumn.setStyle(columnHeaderStyle);
        expColumn.setStyle(columnHeaderStyle);
        activeColumn.setStyle(columnHeaderStyle);

        employeeTable.getColumns().addAll(idColumn, nameColumn, deptColumn, salaryColumn,
                ratingColumn, expColumn, activeColumn);

        // Set row height
        employeeTable.setFixedCellSize(40); // Increased for better readability

        // Style alternating rows - classic striped table effect
        employeeTable.setRowFactory(tv -> {
            javafx.scene.control.TableRow<Employee<Integer>> row = new javafx.scene.control.TableRow<>() {
                @Override
                protected void updateItem(Employee<Integer> employee, boolean empty) {
                    super.updateItem(employee, empty);
                    if (empty) {
                        setStyle("");
                    } else if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: #f2f2f2;"); // Light gray for alternating rows
                    } else {
                        setStyle("-fx-background-color: white;"); // White for other rows
                    }
                }
            };

            // Hover effect - slightly darker gray highlight
            row.setOnMouseEntered(event -> {
                if (!row.isEmpty()) {
                    row.setStyle("-fx-background-color: #e0e0e0;"); // Medium gray for hover
                }
            });

            row.setOnMouseExited(event -> {
                if (!row.isEmpty()) {
                    if (row.getIndex() % 2 == 0) {
                        row.setStyle("-fx-background-color: #f2f2f2;");
                    } else {
                        row.setStyle("-fx-background-color: white;");
                    }
                }
            });

            return row;
        });


        // Set table selection mode
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Selected row style
        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            employeeTable.refresh();
        });

        // Set placeholder text for empty table
        Label placeholder = new Label("No employees found");
        placeholder.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575; -fx-font-style: italic;");
        employeeTable.setPlaceholder(placeholder);

        // Make sure table uses available space
        employeeTable.setPrefHeight(400);

        // Prevent column reordering for consistency
        employeeTable.getColumns().forEach(column -> column.setReorderable(false));
    }

    // Updates the table with new employee data
    public void updateEmployeeTable(List<Employee<Integer>> employees) {
        employeeData.clear();
        employeeData.addAll(employees);

        // Update the title to show count
        titleLabel.setText("Employee Directory (" + employees.size() + " employees)");
    }

    // Get the selected employee
    public Employee<Integer> getSelectedEmployee() {
        return employeeTable.getSelectionModel().getSelectedItem();
    }

    // Get the employee table for the controller to set up listeners
    public TableView<Employee<Integer>> getEmployeeTable() {
        return employeeTable;
    }
}