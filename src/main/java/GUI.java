import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GUI extends Application {

    private ArrayList<String> labelStrings = new ArrayList<>();
    //private ArrayList<String> searchStrings = new ArrayList<>();
    ListView<String> bookListView = new ListView<>();
    private Library library = new Library();
    private ArrayList<Book> chosenBooks = new ArrayList<>(); // selected books for export
    JsonHandler jsn = new JsonHandler();
    Stage primaryStage = new Stage();
    BorderPane inspectorPane = new BorderPane();

    @Override
    public void start(Stage primaryStage) {


        jsn.readFile(library , "library.json"); //reads file first to make them appear when the program opens.
        ArrayList<Book> savedBooks = library.getLibraries();
        ObservableList<String> SavedBookTitles = FXCollections.observableArrayList(
                savedBooks.stream().map(Book::getTitle).collect(Collectors.toList())
        );
        bookListView.setItems(SavedBookTitles);


        Button addBookButton = new Button("AddBook");
        Button importButton = new Button("Import");
        Button exportButton = new Button("Export");
        Button helpButton = new Button("Help");
        ToolBar toolBar = new ToolBar(addBookButton,importButton, exportButton, helpButton);
        VBox searchPaneTop = new VBox();
        VBox searchPaneBottom = new VBox();
        BorderPane catalogPane = new BorderPane();

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
        Button resetButton = new Button("Reset");

        filterButton.setOnAction(e -> {
            labelBox.getChildren().clear();
            labelStrings.clear();
        });

        resetButton.setOnAction(e -> {
            labelBox.getChildren().clear();
            labelStrings.clear();
            updateBookListView();
        });
        HBox searchPaneButtons = new HBox();
        searchPaneButtons.getChildren().addAll(filterButton , resetButton );
        searchPaneBottom.getChildren().addAll(addPane, labelScrollPane, searchPaneButtons);
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
            if(textToSearch.isEmpty())
            {
                labelBox.getChildren().clear();
                labelStrings.clear();
                updateBookListView();
            }
        });
        SplitPane splitPane = new SplitPane(searchSplitPane, catalogPane, inspectorPane);
        splitPane.setDividerPositions(0.265, 0.6);
        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(splitPane);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library App");
        primaryStage.show();
        addBookButton.setOnAction(event -> {
            addBookDialog(primaryStage);
        });
        importButton.setOnAction(event -> {importAction(primaryStage);});
        exportButton.setOnAction(event -> {exportAction(primaryStage);});
        helpButton.setOnAction(event -> {helpAction(primaryStage);});

        bookListView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                String selectedTitle = bookListView.getSelectionModel().getSelectedItem();
                //
                if (selectedTitle != null) {
                    Book selectedBook = library.getBookByTitle(selectedTitle);
                    displayBookDetails(selectedBook, inspectorPane);
                }
            }
        });
    }
    private void exportAction(Stage primaryStage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Export");
        //Label text = new Label("Please enter the path of the file you want books to get exported:");
        Label fileName = new Label("Enter a name for output file, then choose a directory via clicking 'export'.");
        TextField exportPath = new TextField();
        DirectoryChooser dChooser = new DirectoryChooser();
        Button chooseFileButton = new Button("Export");
        chooseFileButton.setOnAction(e -> {
            File selectedFile = dChooser.showDialog(primaryStage);
            String path = selectedFile.toString() + "\\" + exportPath.getText() + ".json";
            File file = new File(path);
            System.out.println(path);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            jsn.writeToJson( chosenBooks , path , false);
            chosenBooks.clear();
            inspectorPane.getChildren().clear();
            dialog.close();
        });

        VBox exportVbox = new VBox(20);
        exportVbox.setPadding(new Insets(20, 20, 20, 20));
        exportVbox.getChildren().addAll(fileName, exportPath ,chooseFileButton);
        Scene dialogScene = new Scene(exportVbox, 800, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    private void importAction(Stage primaryStage){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Import");
        //Label text = new Label("Please enter the path of the file you want books to get exported:");
        Label info = new Label("Choose the file you want to import books from");
        FileChooser fChooser = new FileChooser();
        Button chooseFileButton = new Button("Choose file");
        chooseFileButton.setOnAction(e -> {
            FileChooser.ExtensionFilter fileType = new FileChooser.ExtensionFilter("JSON files", "*.json");
            fChooser.getExtensionFilters().add(fileType);
            File selectedFile = fChooser.showOpenDialog(primaryStage);
            String path = selectedFile.toString();
            jsn.readFile(library , path);
            System.out.println(path);
            updateBookListView();
            dialog.close();
        });

        VBox importVbox = new VBox(20);
        importVbox.setPadding(new Insets(20, 20, 20, 20));
        importVbox.getChildren().addAll(info ,chooseFileButton);
        Scene dialogScene = new Scene(importVbox, 800, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    private void helpAction(Stage primaryStage){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Manuel");

        Label manuelTxt = new Label("-Help Manual-\n\n1.Search: Write the attributes of the book you are searching for in the search bar. Then proceed to click the search button on the right.\n2.Books: Left click twice on the book that is wanted to be inspected. The book and its attributes will shown at the right.\n3.Tags: To narrow the seach with tags, there is a menu on the left. Type and add tags. After adding the tags click filter to see the books. \n4.Delete/Edit: There is an addbook menu on the top right. Seperate your input with commas.Be careful about spaces\n5.You can import books from the .json file by pressing Import at the top left.\n6.You can extract the books by pressing Extract.\n7.While inspecting a book under the description there are 2 buttons: Delete and Edit.\nClicking Delete will delete the book,clicking edit will pop up an edit page for you to edit. \nIf you dont want to change a thing leave the blank empty.");
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.getChildren().addAll(manuelTxt); 
        Scene dialogScene = new Scene(dialogVbox, 800, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void addBookDialog(Stage primaryStage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Add Book");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField subtitleField = new TextField();
        subtitleField.setPromptText("Subtitle");
        TextField authorField = new TextField();
        authorField.setPromptText("Author(s)");
        TextField translatorField = new TextField();
        translatorField.setPromptText("Translator(s)");
        TextField isbnField = new TextField();
        isbnField.setPromptText("Isbn");
        TextField publisherField = new TextField();
        publisherField.setPromptText("Publisher");
        TextField dateField = new TextField();
        dateField.setPromptText("Date");
        TextField editionField = new TextField();
        editionField.setPromptText("Edition");
        TextField coverField = new TextField();
        coverField.setPromptText("Cover");
        TextField languageField = new TextField();
        languageField.setPromptText("Language");
        TextField ratingField = new TextField();
        ratingField.setPromptText("Rating");
        TextField tagsField = new TextField();
        tagsField.setPromptText("Tag(s)");


        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String title = titleField.getText();
            String subtitle = subtitleField.getText();
            String authortxt = authorField.getText();
            String translatortxt = translatorField.getText();
            String isbn = isbnField.getText();
            String publisher = publisherField.getText();
            String date = dateField.getText();
            String edition = editionField.getText();
            String cover = coverField.getText();
            String language = languageField.getText();
            String rating = ratingField.getText();
            String tagtxt = tagsField.getText();
            

            String[] divider;
            ArrayList<String> authors = new ArrayList<>();
            divider = authortxt.split(", ");
            for (int k =0;k<divider.length;k++) {
                authors.add(divider[k]);
            }
            ArrayList<String> translators = new ArrayList<>();
            divider = translatortxt.split(", ");
            for (int k =0;k<divider.length;k++) {
                translators.add(divider[k]);
            }
            ArrayList<String> tags = new ArrayList<>(); 
            divider = tagtxt.split(", ");
            for (int k =0;k<divider.length;k++) {
                tags.add(divider[k]);
            }


            Library.createBook(library, title, subtitle, authors, translators, isbn, publisher, date, edition, cover, language, rating, tags); 


            dialog.close(); 

            updateBookListView(); 
        });

        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.getChildren().addAll(titleField, subtitleField,authorField,translatorField, isbnField,publisherField,dateField,editionField,coverField,languageField,ratingField,tagsField, saveButton); 
        Scene dialogScene = new Scene(dialogVbox, 300, 600);
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
            publisherLabel = new Label("Publisher: " + book.getPublisher());
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

    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");
    Button deselectButton = new Button("Delete from export list");
    Button selectButton = new Button("Select for export list");

    VBox detailsBoxSecond = new VBox(titleLabel, subTitleLabel, authorLabel, translatorLabel,
                isbnLabel, publisherLabel, dateLabel, editionLabel,
                coverLabel, languageLabel, ratingLabel, tagLabel,
                editButton, deleteButton);


    if (chosenBooks.contains(book)) {
        detailsBoxSecond.getChildren().add(deselectButton);
    } else {
        detailsBoxSecond.getChildren().add(selectButton);
    }
    inspectorPane.setCenter(detailsBoxSecond);

    deselectButton.setOnAction(e -> {
        chosenBooks.remove(book);
        detailsBoxSecond.getChildren().remove(deselectButton);
        detailsBoxSecond.getChildren().add(selectButton);
    });

    selectButton.setOnAction(e -> {
        chosenBooks.add(book);
        detailsBoxSecond.getChildren().remove(selectButton);
        detailsBoxSecond.getChildren().add(deselectButton);
    });

    deleteButton.setOnAction(e -> {
        library.getLibraries().remove(book);
        jsn.writeToJson(library.getLibraries() , "library.json" , false);
        updateBookListView();
        inspectorPane.setCenter(null);
    });

    editButton.setOnAction(e -> {
        showEditDialog(primaryStage, book);
    });
    }
    private void showEditDialog(Stage primaryStage, Book book) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Edit Book");
    
        // Input fields (pre-filled with existing book data)
        TextField titleField = new TextField(book.getTitle());
        titleField.setPromptText("Title");
        TextField subtitleField = new TextField(book.getSubtitle());
        subtitleField.setPromptText("Subtitle");
        TextField authorField = new TextField(String.join(", ", book.getAuthors()));
        authorField.setPromptText("Author(s)");
        TextField translatorField = new TextField(String.join(", ", book.getTranslators()));
        translatorField.setPromptText("Translator(s)");
        TextField isbnField = new TextField(book.getIsbn());
        isbnField.setPromptText("Isbn");
        TextField publisherField = new TextField(book.getPublisher());
        publisherField.setPromptText("Publisher");
        TextField dateField = new TextField(book.getDate());
        dateField.setPromptText("Date");
        TextField editionField = new TextField(book.getEdition());
        editionField.setPromptText("Edition");
        TextField coverField = new TextField(book.getCover());
        coverField.setPromptText("Cover");
        TextField languageField = new TextField(book.getLanguage());
        languageField.setPromptText("Language");
        TextField ratingField = new TextField(book.getRating());
        ratingField.setPromptText("Rating");
        TextField tagsField = new TextField(String.join(", ", book.getTags()));
        tagsField.setPromptText("Tag(s)");
    
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Get updated values from fields
            String title = titleField.getText();
            String subtitle = subtitleField.getText();
            String authortxt = authorField.getText();
            String translatortxt = translatorField.getText();
            String isbn = isbnField.getText();
            String publisher = publisherField.getText();
            String date = dateField.getText();
            String edition = editionField.getText();
            String cover = coverField.getText();
            String language = languageField.getText();
            String rating = ratingField.getText();
            String tagtxt = tagsField.getText();
    
            // Parse authors and translators
            String[] divider;
            ArrayList<String> authors = new ArrayList<>();
            divider = authortxt.split(",");
            for (int k = 0; k < divider.length; k++) {
                authors.add(divider[k].trim()); // Trim to remove extra spaces
            }
            ArrayList<String> translators = new ArrayList<>();
            divider = translatortxt.split(",");
            for (int k = 0; k < divider.length; k++) {
                translators.add(divider[k].trim()); // Trim to remove extra spaces
            }
    
            // Parse tags
            ArrayList<String> tags = new ArrayList<>();
            divider = tagtxt.split(",");
            for (int k = 0; k < divider.length; k++) {
                tags.add(divider[k].trim()); // Trim to remove extra spaces
            }
    
            // Update the book object
            book.setTitle(title);
            book.setSubtitle(subtitle);
            book.setAuthors(authors);
            book.setTranslators(translators);
            book.setIsbn(isbn);
            book.setPublisher(publisher);
            book.setDate(date);
            book.setEdition(edition);
            book.setCover(cover);
            book.setLanguage(language);
            book.setRating(rating);
            book.setTags(tags);
            book.updateAttr(book); 
    
            dialog.close();
            updateBookListView();
            displayBookDetails(book, inspectorPane); 
        });
    
        // Dialog setup
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.getChildren().addAll(titleField, subtitleField, authorField, translatorField, 
                                         isbnField, publisherField, dateField, editionField, 
                                         coverField, languageField, ratingField, tagsField, saveButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 600);
        dialog.setScene(dialogScene);
        dialog.show();
    }


    private void updateBookListView() {
        ArrayList<Book> books = library.getLibraries();
        ObservableList<String> bookTitles = FXCollections.observableArrayList(
                books.stream().map(Book::getTitle).collect(Collectors.toList())
        );
        bookListView.setItems(bookTitles);
    }

    @Override
    public void stop() {
        jsn.writeToJson(library.getLibraries() , "library.json" , false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}