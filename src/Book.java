import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Book implements Entity {
    private String name;
    private String author;
    private int price;
    private int ID = -1;
    private boolean exist = false;
    private boolean borrowStatus = false;
    private LocalDate date;
    private int memberID;

    public Book() {}

    public String getName() { return this.name; }
    public boolean getBorrowStatus() { return this.borrowStatus; }
    public LocalDate getDate() { return this.date; }
    public int getMemberID() { return this.memberID; }
    @Override
    public int getId() { return this.ID; }

    public void setBorrowStatus(boolean borrowStatus) { this.borrowStatus = borrowStatus; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setMemberID(int id) { this.memberID = id; }

    public static Book createBook() throws BadEntityException {
        int bookId = LibraryImpl.getBookIdx();

        if (bookId < 100) {
            Book book = new Book();
            new Book().showOnConsole("Book(" + bookId + "):\n");

            new Book().showOnConsole("Name: ");
            book.name = (String) new Book().readFromConsole("");

            if (new LibraryImpl().find(new Book(), book.name) == null) {
                new Book().showOnConsole("Author: ");
                book.author = (String) new Book().readFromConsole("");

                new Book().showOnConsole("Price: ");
                book.price = (Integer) new Book().readFromConsole(0);

                book.exist = true;
                book.ID = bookId;

                new Book().showOnConsole("Book added successfully!\n\n");
                return book;
            } else {
                new Book().showOnConsole("Book already exists!\n");
            }
        } else {
            new Book().showOnConsole("Library books are full!\n");
        }
        new Book().showOnConsole("\n");
        return null;
    }

    public static Pair<Book, Integer> updateBook() throws BadEntityException {
        new Book().showOnConsole("Please Enter The Book Name: ");
        String name = (String) new Book().readFromConsole("");

        Book tmpBook = (Book) new LibraryImpl().find(new Book(), name);
        if (tmpBook != null) {
            Book book = new Book();

            new Book().showOnConsole("Name: ");
            book.name = (String) new Book().readFromConsole("");

            new Book().showOnConsole("Author: ");
            book.author = (String) new Book().readFromConsole("");

            new Book().showOnConsole("Price: ");
            book.price = (Integer) new Book().readFromConsole(0);

            new Book().showOnConsole("Book updated successfully!\n\n");
            return new Pair<>(book, tmpBook.ID);
        } else {
            new Book().showOnConsole("Book doesn't exist!\n");
        }
        new Book().showOnConsole("\n");
        return null;
    }

    public static Pair<Book, Integer> deleteBook() throws BadEntityException {
        new Book().showOnConsole("Please Enter The Book Name: ");
        String name = (String) new Book().readFromConsole("");

        Book tmpBook = (Book) new LibraryImpl().find(new Book(), name);
        if (tmpBook != null) {
            for (int i = tmpBook.ID; i < LibraryImpl.getBookIdx() - 1; i++) {
                LibraryImpl.books[i] = LibraryImpl.books[i + 1];
            }

            new Book().showOnConsole("Book deleted successfully!\n\n");
            return new Pair<>(new Book(), tmpBook.ID);
        } else {
            new Book().showOnConsole("Book doesn't exist!\n");
        }
        new Book().showOnConsole("\n");
        return null;
    }

    public static void readBook(int idx) {
        if (idx != -1) {
            Book book = LibraryImpl.books[idx];
            new Book().showOnConsole("Book(" + idx + "):\n");
            new Book().showOnConsole("name: " + book.name + ",\n");
            new Book().showOnConsole("author: " + book.author + ",\n");
            new Book().showOnConsole("price: " + book.price + ".\n");
        } else {
            new Book().showOnConsole("Book doesn't exist!\n");
        }
        new Book().showOnConsole("\n");
    }

    public static void searchBookByName() throws BadEntityException {
        new Book().showOnConsole("Name: ");
        String name = (String) new Book().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].name, name)) {
                    readBook(i);
                    return;
                }
            }
        }

        new Book().showOnConsole("Book with this name doesn't exist!\n\n");
    }

    public static void searchBookByAuthor() throws BadEntityException {
        new Book().showOnConsole("Author: ");
        String author = (String) new Book().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].author, author)) {
                    readBook(i);
                    return;
                }
            }
        }

        new Book().showOnConsole("Book with this author doesn't exist!\n\n");
    }

    public static void searchBookByPrice() throws BadEntityException {
        new Book().showOnConsole("Price: ");
        int price = (Integer) new Book().readFromConsole(0);

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (LibraryImpl.books[i].price <= price) {
                    readBook(i);
                    return;
                }
            }
        }

        new Book().showOnConsole("Book with a lower price doesn't exist!\n\n");
    }

    public static void searchBookByBorrowStatus() {
        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (!LibraryImpl.books[i].borrowStatus) {
                    readBook(i);
                    return;
                }
            }
        }

        new Book().showOnConsole("Free book doesn't exist!\n\n");
    }

    @Override
    public Object readFromConsole(Object object) throws BadEntityException {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        if (object instanceof String) {
            if (input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                throw new BadEntityException("Please enter letters only!");
            }
        } else if (object instanceof Integer) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                throw new BadEntityException("Please enter numbers only!");
            }
        }
        return null;
    }

    @Override
    public void showOnConsole(String output) {
        System.out.print(output);
    }
}
