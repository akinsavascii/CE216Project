class Library {
    
    private List<Book> books;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
    }

    // Method to add a book
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to delete a book
    public void deleteBook(Book book) {
        books.remove(book);
    }

    // Method to search books by tag
    public List<Book> searchByTag(String tag) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTags().contains(tag)) {
                result.add(book);
            }
        }
        return result;
    }
}