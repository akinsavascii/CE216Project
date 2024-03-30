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

        //checks if all given tags by user are matches with books data. otherwise adds nothing
    public static void listedBooksBySelectedTags(ArrayList<String> selectedTags)
        {
            ArrayList<Book> selectedBooks = new ArrayList<>();
            
            
            for(Book book: library)
            {
                boolean match = true;
    
                for(String tag : selectedTags)
                {
                    if(!book.getTags().contains(tag));
                    {
                        match = false;
                        break;
                    }
    
                }
                if(match)
                {
                    selectedBooks.add(book);
                }
    
            }
    
            if(selectedBooks.isEmpty())
            {
                System.out.println("There isn't any specific book that contains your tag.");
    
            }
            else
            {
                System.out.println("Books that contains your search criterion.");
                for(Book book:selectedBooks)
                {
                    System.out.println(book.getTitle());
                }
            }
        }

    
}