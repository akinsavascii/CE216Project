import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

// The Book class
class Book {

    private String title = null;
    private String subtitle = null;
    private ArrayList<String> authors = null;
    private ArrayList<String> translators = null;
    private String isbn = null;
    private String publisher = null;
    private String date = null;
    private String edition = null;
    private String cover = null;
    private String language = null;
    private String rating = null;
    private ArrayList<String> tags = null;
    

    // Constructor for books attributes
    public Book(String title, String subtitle, ArrayList<String> authors, ArrayList<String> translators,
                String isbn, String publisher, String date, String edition, String cover,
                String language, String rating,ArrayList<String> tags) {

        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.translators = translators;
        this.isbn = isbn;
        this.publisher = publisher;
        this.date = date;
        this.edition = edition;
        this.cover = cover;
        this.language = language;
        this.rating = rating;
        this.tags=tags;    
    }

    public Book(){

    }






    //getters
    public ArrayList<String> getTags() {
        return tags;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public String getCover() {
        return cover;
    }
    public String getDate() {
        return date;
    }
    public String getEdition() {
        return edition;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getLanguage() {
        return language;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getRating() {
        return rating;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public String getTitle() {
        return title;
    }
    public List<String> getTranslators() {
        return translators;
    }

    //setters
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTranslators(ArrayList<String> translators) {
        this.translators = translators;
    }

    // Method to edit book information
    public void editAllBookInfo(String title, String subtitle, ArrayList<String> authors, ArrayList<String> translators,
                             String isbn, String publisher, String date, String edition, String cover,
                             String language, String rating,ArrayList<String> tags, String filename) {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.translators = translators;
        this.isbn = isbn;
        this.publisher = publisher;
        this.date = date;
        this.edition = edition;
        this.cover = cover;
        this.language = language;
        this.rating = rating;
        this.tags = tags;
        updateJson(filename);
    }

    // Method to edit book attributes separately
    public void editDate(String date, String filename) {
        this.date = date;
        updateJson(filename);
    }

    public void editEdition(String edition, String filename) {
        this.edition = edition;
        updateJson(filename);
    }

    public void editIsbn(String isbn, String filename) {
        this.isbn = isbn;
        updateJson(filename);
    }

    public void editLanguage(String language, String filename) {
        this.language = language;
        updateJson(filename);
    }

    public void editPublisher(String publisher, String filename) {
        this.publisher = publisher;
        updateJson(filename);
    }

    public void editRating(String rating, String filename) {
        this.rating = rating;
        updateJson(filename);
    }

    public void editSubtitle(String subtitle, String filename) {
        this.subtitle = subtitle;
        updateJson(filename);
    }

    public void editTitle(String title, String filename) {
        this.title = title;
        updateJson(filename);
    }

    public void editTranslators(ArrayList<String> translators, String filename) {
        this.translators = translators;
        updateJson(filename);
    }

    private void updateJson(String filename) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Method to delete all book information
    public void deleteBookInfo() {
        this.title = null;
        this.subtitle = null;
        this.authors = null;
        this.translators = null;
        this.isbn = null;
        this.publisher = null;
        this.date = null;
        this.edition = null;
        this.cover = null;
        this.language = null;
        this.rating = null;
        this.tags = null;
    }

    //Method to delete attributes separately

    public void delDate(String date) {
        this.date = null;
    }
    public void delEdition(String edition) {
        this.edition = null;
    }
    public void delIsbn(String isbn) {
        this.isbn = null;
    }
    public void delLanguage(String language) {
        this.language = null;
    }
    public void delPublisher(String publisher) {
        this.publisher = null;
    }
    public void delRating(String rating) {
        this.rating = null;
    }
    public void delSubtitle(String subtitle) {
        this.subtitle = null;
    }
    public void delTitle(String title) {
        this.title = null;
    }
    public void delTranslators(ArrayList<String> translators) {
        this.translators = null;
    }

}