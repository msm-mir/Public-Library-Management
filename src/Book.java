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
    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.ID = LibraryImpl.getBookIdx();
        this.exist = true;
        LibraryImpl.setBookIdx(LibraryImpl.getBookIdx() + 1);
    }

    public String getName() { return this.name; }
    public String getAuthor() { return this.author; }
    public int getPrice() { return this.price; }
    public boolean getBorrowStatus() { return this.borrowStatus; }
    public LocalDate getDate() { return this.date; }
    public int getMemberID() { return this.memberID; }
    @Override
    public int getId() { return this.ID; }

    public void setBorrowStatus(boolean borrowStatus) { this.borrowStatus = borrowStatus; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setMemberID(int id) { this.memberID = id; }

    public static Book createBook() {
        Scanner scn = new Scanner(System.in);

        int bookId = LibraryImpl.getBookIdx();
        if (bookId < 100) {
            Book book = new Book();
            System.out.println("Book(" + bookId + "):");

            System.out.print("Name: ");
            book.name = scn.nextLine();

            LibraryImpl library = new LibraryImpl();
            if (((Book) library.find(new Book(), book.name)).getId() == -1) {
                System.out.print("Author: ");
                book.author = scn.nextLine();

                System.out.print("Price: ");
                book.price = scn.nextInt();

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

    public static Pair<Book, Integer> updateBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        LibraryImpl library = new LibraryImpl();
        int idx = ((Book) library.find(new Book(), name)).getId();
        if (idx != -1) {
            Book book = new Book();

            System.out.print("Name: ");
            book.name = scn.nextLine();

            System.out.print("Author: ");
            book.author = scn.nextLine();

            System.out.print("Price: ");
            book.price = scn.nextInt();

            System.out.println("Book updated successfully!");
            System.out.println();
            return new Pair<>(book, idx);
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
        return null;
    }

    public static Pair<Book, Integer> deleteBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        LibraryImpl library = new LibraryImpl();
        int idx = ((Book) library.find(new Book(), name)).getId();
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

    public static void searchBookByName() {
        System.out.print("Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].name, name)) {
                    LibraryImpl library = new LibraryImpl();
                    readBook(((Book) library.find(new Book(), name)).getId());
                    return;
                }
            }
        }

        System.out.println("Book with this name doesn't exist!");
        System.out.println();
    }

    public static void searchBookByAuthor() {
        System.out.print("Author: ");

        Scanner scn = new Scanner(System.in);
        String author = scn.nextLine();

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].author, author)) {
                    LibraryImpl library = new LibraryImpl();
                    readBook(((Book) library.find(new Book(), LibraryImpl.books[i].name)).getId());
                    return;
                }
            }
        }

        System.out.println("Book with this author doesn't exist!");
        System.out.println();
    }

    public static void searchBookByPrice() {
        System.out.print("Price: ");

        Scanner scn = new Scanner(System.in);
        int price = scn.nextInt();

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (LibraryImpl.books[i].price <= price) {
                    LibraryImpl library = new LibraryImpl();
                    readBook(((Book) library.find(new Book(), LibraryImpl.books[i].name)).getId());
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
                    LibraryImpl library = new LibraryImpl();
                    readBook(((Book) library.find(new Book(), LibraryImpl.books[i].name)).getId());
                    return;
                }
            }
        }

        System.out.println("Free book doesn't exist!");
        System.out.println();
    }

    @Override
    public void readFromConsole() throws BadEntityException {

    }

    @Override
    public void showOnConsole() {

    }
}
