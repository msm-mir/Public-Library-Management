import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Book implements Entity {
    private String name;
    private String author;
    private int price;
    private int ID;
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
            System.out.println("Book(" + bookId + "):");

            System.out.print("Name: ");
            book.name = (String) new Member().readFromConsole("");

            if (new LibraryImpl().find(new Book(), book.name).getId() == -1) {
                System.out.print("Author: ");
                book.author = (String) new Member().readFromConsole("");

                System.out.print("Price: ");
                book.price = (Integer) new Member().readFromConsole(0);

                book.exist = true;
                book.ID = LibraryImpl.getBookIdx();

                System.out.println("Book added successfully!");
                System.out.println();
                return book;
            } else {
                System.out.println("Book already exists!");
            }
        } else {
            System.out.println("Library books are full!");
        }
        System.out.println();
        return null;
    }

    public static Pair<Book, Integer> updateBook() throws BadEntityException {
        System.out.print("Please Enter The Book Name: ");
        String name = (String) new Member().readFromConsole("");

        int idx = new LibraryImpl().find(new Book(), name).getId();
        if (idx != -1) {
            Book book = new Book();

            System.out.print("Name: ");
            book.name = (String) new Member().readFromConsole("");

            System.out.print("Author: ");
            book.author = (String) new Member().readFromConsole("");

            System.out.print("Price: ");
            book.price = (Integer) new Member().readFromConsole(0);

            System.out.println("Book updated successfully!");
            System.out.println();
            return new Pair<>(book, idx);
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
        return null;
    }

    public static Pair<Book, Integer> deleteBook() throws BadEntityException {
        System.out.print("Please Enter The Book Name: ");
        String name = (String) new Member().readFromConsole("");

        int idx = new LibraryImpl().find(new Book(), name).getId();
        if (idx != -1) {
            for (int i = idx; i < LibraryImpl.getBookIdx() - 1; i++) {
                LibraryImpl.books[i] = LibraryImpl.books[i + 1];
            }
            System.out.println("Book deleted successfully!");
            System.out.println();
            return new Pair<>(new Book(), idx);
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
        return null;
    }

    public static void readBook(int idx) {
        if (idx != -1) {
            Book book = LibraryImpl.books[idx];
            System.out.println("Book(" + idx + "):");
            System.out.println("name: " + book.name + ",");
            System.out.println("author: " + book.author + ",");
            System.out.println("price: " + book.price + ".");
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
    }

    public static void searchBookByName() throws BadEntityException {
        System.out.print("Name: ");
        String name = (String) new Member().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].name, name)) {
                    readBook(new LibraryImpl().find(new Book(), name).getId());
                    return;
                }
            }
        }

        System.out.println("Book with this name doesn't exist!");
        System.out.println();
    }

    public static void searchBookByAuthor() throws BadEntityException {
        System.out.print("Author: ");
        String author = (String) new Member().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].author, author)) {
                    readBook(new LibraryImpl().find(new Book(), LibraryImpl.books[i].name).getId());
                    return;
                }
            }
        }

        System.out.println("Book with this author doesn't exist!");
        System.out.println();
    }

    public static void searchBookByPrice() throws BadEntityException {
        System.out.print("Price: ");
        int price = (Integer) new Member().readFromConsole(0);

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (LibraryImpl.books[i].price <= price) {
                    readBook(new LibraryImpl().find(new Book(), LibraryImpl.books[i].name).getId());
                    return;
                }
            }
        }

        System.out.println("Book with a lower price doesn't exist!");
        System.out.println();
    }

    public static void searchBookByBorrowStatus() {
        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (!LibraryImpl.books[i].borrowStatus) {
                    readBook(new LibraryImpl().find(new Book(), LibraryImpl.books[i].name).getId());
                    return;
                }
            }
        }

        System.out.println("Free book doesn't exist!");
        System.out.println();
    }

    @Override
    public Object readFromConsole(Object object) throws BadEntityException {
        Scanner scn = new Scanner(System.in);

        if (object instanceof String) {
            return scn.nextLine();
        } else if (object instanceof Integer) {
            Integer i = scn.nextInt();
            scn.nextLine();
            return i;
        } else {
            throw new BadEntityException();
        }
    }

    @Override
    public void showOnConsole() {

    }
}
