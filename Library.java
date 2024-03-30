import java.util.ArrayList;

public class Library{

    public Library(){}

    static public ArrayList<Book> library = new ArrayList<Book>();

    

    public static void addToLib(Book book){ 
        // kütüphaneye eklenir
        library.add(book);

    }

    public static ArrayList<Book> getLibrarys() {
        return library;
    }
    public static void setLibrarys(ArrayList<Book> library) {
        Library.library = library;
    }

    
}