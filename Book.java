import java.util.ArrayList;
import java.util.List;

// The Book class
class Book {

    private String title;
    private String subtitle;
    private List<String> authors;
    private List<String> translators;
    private String isbn;
    private String publisher;
    private String date;
    private int edition;
    private String cover;
    private String language;
    private double rating;
    private List<String> tags;

    // Constructor for books attributes
    public Book(String title, String subtitle, List<String> authors, List<String> translators,
                String isbn, String publisher, String date, int edition, String cover,
                String language, double rating) {

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

        tags.add(title);
        tags.add(subtitle);
        tags.add(isbn);
        tags.add(publisher);
        tags.add(date);
        tags.add(toString(edition));
        tags.add(toString(rating));
        tags.add(cover);
        tags.add(language);
        for(int i = 0;i<authors.size();i++){
            tags.add(authors(i));
        }
        for(int i = 0;i<translators.size();i++){
            tags.add(translators(i));
        }
    }







    //getters
    public List<String> getAuthors() {
        return authors;
    }
    public String getCover() {
        return cover;
    }
    public String getDate() {
        return date;
    }
    public int getEdition() {
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
    public double getRating() {
        return rating;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public List<String> getTags() {
        return tags;
    }
    public String getTitle() {
        return title;
    }
    public List<String> getTranslators() {
        return translators;
    }

    //setters
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEdition(int edition) {
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
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public void setTags(List<String> tags) {

        this.tags = tags;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTranslators(List<String> translators) {
        this.translators = translators;
    }
}