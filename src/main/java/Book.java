import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

// The Book class
class Book {

    private String title = "";
    private String subtitle = "";
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> translators = new ArrayList<>();
    private String isbn = "";
    private String publisher = "";
    private String date = "";
    private String edition = "";
    private String cover = "";
    private String language = "";
    private String rating = "";
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String> attr = new ArrayList<>();
    

    // Constructor for books attributes
    public Book(String title, String subtitle, ArrayList<String> authors, ArrayList<String> translators,
                String isbn, String publisher, String date, String edition, String cover,
                String language, String rating,ArrayList<String> tags,ArrayList<String> attr) {

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
        this.attr=attr;  
    }

    public Book(){

    }

    //getters
    public ArrayList<String> getAttr() {
        return attr;
    }
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
    public void setAttr(ArrayList<String> attr) {
        this.attr = attr;
    }
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
                             String language, String rating,ArrayList<String> tags, Book book) {
        ArrayList<String> temptag = new ArrayList<String>();
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
        if(tags == null) {
            this.tags=temptag;
        } else {
            this.tags = tags;
        }
        updateAttr(book);
        //updateJson(filename);
    }

    // Method to edit book attributes separately
    public void editDate(String date, Book book) {
        this.date = date;
        updateAttr(book);
    }

    public void editEdition(String edition, Book book) {
        this.edition = edition;
        updateAttr(book);
    }

    public void editIsbn(String isbn, Book book) {
        this.isbn = isbn;
        updateAttr(book);
    }

    public void editLanguage(String language, Book book) {
        this.language = language;
        updateAttr(book);
    }

    public void editPublisher(String publisher, Book book) {
        this.publisher = publisher;
        updateAttr(book);
    }

    public void editRating(String rating, Book book) {
        this.rating = rating;
        updateAttr(book);
    }

    public void editSubtitle(String subtitle, Book book) {
        this.subtitle = subtitle;
        updateAttr(book);
    }

    public void editTitle(String title, Book book) {
        this.title = title;
        updateAttr(book);
    }

    public void editTranslators(ArrayList<String> translators, Book book) {
        this.translators = translators;
        updateAttr(book);
    }
    public void editTags(ArrayList<String> tags, Book book) {
        this.tags = tags;
        updateAttr(book);
    }

    /*private void updateJson(String filename) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public void updateAttr(Book book) {
        ArrayList<String> atr = new ArrayList<String>();
        ArrayList<String> atrlower = new ArrayList<String>();
        if (book.getAttr()!=null) {
            book.getAttr().clear();
        } else {
            book.setAttr(atr);
        }
        
        String[] divide ;
        if (book.getTitle()!=null){
            divide = book.getTitle().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getSubtitle()!=null){
            divide = book.getSubtitle().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }

            }
        }
        if(book.getAuthors()!=null) {
            for(int i = 0;i<book.getAuthors().size();i++){
                divide = book.getAuthors().get(i).split(" ");
                for (int l = 0;l<divide.length;l++) {
                    if (book.getAttr()!=null) {
                        book.getAttr().add(divide[l]);
                    }

                }
            }
        }
        if(book.getTranslators()!=null) {
            for(int i = 0;i<book.getTranslators().size();i++){
                divide = book.getTranslators().get(i).split(" ");
                for (int l = 0;l<divide.length;l++) {
                    if (book.getAttr()!=null) {
                        book.getAttr().add(divide[l]);
                    }

                }
            }
        }
        if (book.getIsbn()!=null){
            divide = book.getIsbn().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getPublisher()!=null) {
            divide = book.getPublisher().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getDate()!=null) {
            divide = book.getDate().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getEdition()!=null) {
            divide = book.getEdition().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getLanguage()!=null) {
            divide = book.getLanguage().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getLanguage()!=null) {
            divide = book.getRating().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if (book.getLanguage()!=null) {
            divide = book.getCover().split(" ");
            for (int l = 0;l<divide.length;l++) {
                if (book.getAttr()!=null) {
                    book.getAttr().add(divide[l]);
                }
            }
        }
        if(book.getTags()!=null) {
            for(int i = 0;i<book.getTags().size();i++){
                divide = book.getTags().get(i).split(" ");
                for (int l = 0;l<divide.length;l++) {
                    if (book.getAttr()!=null) {
                        book.getAttr().add(divide[l]);
                    }
                }
            }
        }
        for(int ijk = 0; ijk<book.getAttr().size();ijk++) {
            atrlower.add(book.getAttr().get(ijk).toLowerCase());
        }
        book.setAttr(atrlower);
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