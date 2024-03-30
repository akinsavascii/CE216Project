import java.util.ArrayList;
import java.util.List;

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
}