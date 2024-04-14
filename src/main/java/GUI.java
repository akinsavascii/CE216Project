import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GUI extends Application {

    private ArrayList<String> labelStrings = new ArrayList<>();
    private ArrayList<String> searchStrings = new ArrayList<>();
    ListView<String> bookListView = new ListView<>();
    private Library library = new Library(); 

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
        Button filterButton = new Button("Filter");
        filterButton.setOnAction(e -> {
            labelBox.getChildren().clear();
            labelStrings.clear();
        });
        searchPaneBottom.getChildren().addAll(addPane, labelScrollPane, filterButton);
        SplitPane searchSplitPane = new SplitPane(searchPaneTop, searchPaneBottom);
        searchSplitPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        searchSplitPane.setDividerPositions(0.3);
        
        catalogPane.setCenter(bookListView);
        filterButton.setOnAction(e -> {
            ArrayList<String> selectedTags = labelStrings;
            ArrayList<Book> selectedBooks = Library.listByTags(selectedTags);
            ObservableList<String> bookTitles = FXCollections.observableArrayList(
                    selectedBooks.stream().map(Book::getTitle).collect(Collectors.toList())
            );
            bookListView.setItems(bookTitles);
            labelBox.getChildren().clear();
            labelStrings.clear();
        });
        searchButton.setOnAction(e -> {
            String textToSearch = searchBar.getText();
            if(!textToSearch.isEmpty())
            {
                searchBar.clear();
            }
            ArrayList<Book> searchedBooks = Library.search(textToSearch);
            ObservableList<String> bookTitles = FXCollections.observableArrayList(
                    searchedBooks.stream().map(Book::getTitle).collect(Collectors.toList())
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

        importButton.setOnAction(event -> {
            showImportDialog(primaryStage);
        });

        bookListView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                String selectedTitle = bookListView.getSelectionModel().getSelectedItem();
                if (selectedTitle != null) {
                    Book selectedBook = library.getBookByTitle(selectedTitle);
                    displayBookDetails(selectedBook, inspectorPane);
                }
            }
        });
    }

    private void showImportDialog(Stage primaryStage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Import Book");

        TextField titleField = new TextField();
        TextField subtitleField = new TextField();
        TextField authorField = new TextField();
        TextField translatorField = new TextField();
        TextField isbnField = new TextField();
        TextField publisherField = new TextField();
        TextField dateField = new TextField();
        TextField editionField = new TextField();


        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String title = titleField.getText();
            String subtitle = subtitleField.getText();
            


            ArrayList<String> authors = new ArrayList<>();
            ArrayList<String> translators = new ArrayList<>();
            ArrayList<String> tags = new ArrayList<>(); 


            Library.createBook(library, title, subtitle, authors, translators, "", "", "", "", "", "", "", tags); 


            dialog.close(); 

            updateBookListView(); 
        });

        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.getChildren().addAll(titleField, subtitleField, saveButton); 
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void displayBookDetails(Book book, BorderPane inspectorPane) {
        Label titleLabel = new Label(" ");
        Label subTitleLabel= new Label(" ");
        Label authorLabel = new Label(" ");
        Label translatorLabel = new Label(" ");
        Label isbnLabel = new Label(" ");
        Label publisherLabel = new Label(" ");
        Label dateLabel = new Label(" ");
        Label editionLabel = new Label(" ");
        Label coverLabel = new Label(" ");
        Label languageLabel = new Label(" ");
        Label ratingLabel = new Label(" ");
        Label tagLabel = new Label(" ");
        if (book.getTitle()!=null) {
            titleLabel = new Label("Title: " + book.getTitle());
        }
        if (book.getSubtitle()!=null) {
            subTitleLabel = new Label("Subtitle: " + book.getSubtitle());
        }
        if (book.getAuthors()!=null) {
            authorLabel = new Label("Author(s): " + String.join(", ", book.getAuthors()));
        }
        if (book.getTranslators()!=null) {
            translatorLabel = new Label("Translator(s): " + String.join(", ", book.getTranslators()));
        }
        if (book.getIsbn()!=null) {
            isbnLabel = new Label("Isbn: " + book.getIsbn());
        }
        if (book.getPublisher()!=null) {
            publisherLabel = new Label("Publisher: " + book.getSubtitle());
        }
        if (book.getDate()!=null) {
            dateLabel = new Label("Date: " + book.getDate());
        }
        if (book.getEdition()!=null) {
            editionLabel = new Label("Edition: " + book.getEdition());
        }
        if (book.getCover()!=null) {
            coverLabel = new Label("Cover: " + book.getCover());
        }
        if (book.getLanguage()!=null) {
            languageLabel = new Label("Language: " + book.getLanguage());
        }
        if (book.getRating()!=null) {
            ratingLabel = new Label("Rating: " + book.getRating());
        }
        if (book.getTags()!=null) {
            tagLabel = new Label("Tag(s): " + String.join(", ", book.getTags()));
        }
 

 
        VBox detailsBox = new VBox(titleLabel,subTitleLabel,authorLabel,translatorLabel,isbnLabel,publisherLabel,dateLabel,editionLabel,coverLabel,languageLabel,ratingLabel,tagLabel); 
        inspectorPane.setCenter(detailsBox);
    }

    private void updateBookListView() {
        ArrayList<Book> books = library.getLibraries();
        ObservableList<String> bookTitles = FXCollections.observableArrayList(
                books.stream().map(Book::getTitle).collect(Collectors.toList())
        );
        bookListView.setItems(bookTitles);
    }

    public static void main(String[] args) {
        launch(args);
    }
}