import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GUI extends Application {
    private ArrayList<String> labelStrings = new ArrayList<>();
    private Library library = new Library(); // Assuming you have the Library class

    @Override
    public void start(Stage primaryStage) {
        Button importButton = new Button("Import");
        Button exportButton = new Button("Export");
        Button helpButton = new Button("Help");
        ToolBar toolBar = new ToolBar(importButton, exportButton, helpButton);

        VBox searchPaneTop = new VBox();
        VBox searchPaneBottom = new VBox();
        BorderPane catalogPane = new BorderPane();
        BorderPane inspectorPane = new BorderPane();

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");
        Button searchButton = new Button("Search");
        HBox searchBox = new HBox(searchBar, searchButton);
        searchBox.setSpacing(5);
        searchPaneTop.getChildren().addAll(searchBox);

        TextField addTextField = new TextField();
        addTextField.setPromptText("Filter");
        Button addButton = new Button("Add");
        HBox addBox = new HBox(addTextField, addButton);
        addBox.setSpacing(5);
        VBox labelBox = new VBox();
        addButton.setOnAction(e -> {
            String textToAdd = addTextField.getText();
            if (!textToAdd.isEmpty()) {
                Label newLabel = new Label(textToAdd);
                labelBox.getChildren().add(newLabel);
                labelStrings.add(textToAdd);
                addTextField.clear();
            }
        });
        VBox addPane = new VBox();
        addPane.getChildren().addAll(addBox);

        ScrollPane labelScrollPane = new ScrollPane();
        labelScrollPane.setContent(labelBox);
        labelScrollPane.setPrefHeight(250);

        // Button to clear labels and search
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            labelBox.getChildren().clear();
            labelStrings.clear();
            // Add any other clearing actions here (e.g., clear search results)
        });

        searchPaneBottom.getChildren().addAll(addPane, labelScrollPane, clearButton);

        SplitPane searchSplitPane = new SplitPane(searchPaneTop, searchPaneBottom);
        searchSplitPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        searchSplitPane.setDividerPositions(0.3);

        // ListView to display search results
        ListView<String> bookListView = new ListView<>();
        catalogPane.setCenter(bookListView);

        clearButton.setOnAction(e -> {
            ArrayList<String> selectedTags = labelStrings;
            ArrayList<Book> selectedBooks = Library.listedBooksBySelectedTags(selectedTags);
            ObservableList<String> bookTitles = FXCollections.observableArrayList(
                    selectedBooks.stream().map(Book::getTitle).collect(Collectors.toList())
            );
            bookListView.setItems(bookTitles);
        });

        SplitPane splitPane = new SplitPane(searchSplitPane, catalogPane, inspectorPane);
        splitPane.setDividerPositions(0.265, 0.6);

        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(splitPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Blank Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}