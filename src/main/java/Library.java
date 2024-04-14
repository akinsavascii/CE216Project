import java.util.ArrayList;

public class Library{

    public Library(){}

    static public ArrayList<Book> library = new ArrayList<Book>();

    public void addToLib(Book book){
        library.add(book);

    }
    public ArrayList<Book> getLibraries() {
        return library;
    }
    public int getSize() { 
        return library.size();
    }
    public static void setLibraries(ArrayList<Book> library) {
        Library.library = library;
    }

    public static void createBook(Library lib,String title, String subtitle, ArrayList<String> authors, ArrayList<String> translators,String isbn, String publisher, 
                                String date, String edition, String cover,String language, String rating,ArrayList<String> tags){
        Book cBook = new Book();
        cBook.setTitle(title);
        cBook.setSubtitle(subtitle);
        cBook.setAuthors(authors);
        cBook.setTranslators(translators);
        cBook.setIsbn(isbn);
        cBook.setPublisher(publisher);
        cBook.setDate(date);
        cBook.setEdition(edition);
        cBook.setCover(cover);
        cBook.setLanguage(language);
        cBook.setRating(rating);
        cBook.setTags(tags);
        lib.addToLib(cBook);
        cBook.updateAttr(cBook);
    }

    public static ArrayList<Book> search(String search) {
        ArrayList<Book> DisplaySearch = new ArrayList<Book>();
        String[] parts = search.split(" ");
            for (int j =0;j<parts.length;j++){
                String search2 = parts[j];
                for(int i =0;i<Library.library.size();i++) {
                    if (Library.library.get(i).getAttr()!=null) {
                        if(Library.library.get(i).getAttr().contains(search2)){
                            if(!DisplaySearch.contains(Library.library.get(i))){
                            DisplaySearch.add(Library.library.get(i));}
                        }
                    }
                }
            }
            return DisplaySearch;
    }

    public static void booktagprint(Book book){
        if (book.getTags() != null) {
            String taglar = "";
            for(int i = 0; i<book.getTags().size();i++) {
                if (book.getTags().get(i) != null ) {
                    taglar=taglar+book.getTags().get(i) + " ";
                }
            }
        System.out.println(taglar);
        }
    }


        //checks if all given tags by user are matches with books data. otherwise adds nothing
        public static ArrayList<Book> listByTags(ArrayList<String> selectedTags)
        {
            ArrayList<Book> selectedBooks = new ArrayList<>();

            for(Book book: library)
            {
                int counter =0;

                boolean match = false;

                if (book.getTags() != null) {                
                    for(String tag : selectedTags)
                    {
                        match = false;
                        if(book.getTags().contains(tag))
                        {
                            match = true;
                        }
                
                        if(match)
                        {
                            counter++;
                        }
                
                    }
                    if(counter == selectedTags.size()) {
                        selectedBooks.add(book);
                    }
                }
            }
            if(selectedBooks.isEmpty())
            {
                //System.out.println("There isn't any specific book that contains your tag.");
            }
            else
            {
                /*System.out.println("Books that contains your search criterion.");
                for(Book book:selectedBooks)
                {
                    System.out.println(book.getTitle());
                }*/
            }
            return selectedBooks;
        }

            public Book getBookByTitle(String title) {
            for (Book book : library) {
                if (book.getTitle().equals(title)) {
                    return book;
                }
            }
            return null;
        }

    
}
