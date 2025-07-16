import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Book implements Entity {
    private String name;
    private String author;
    private int price;
    private static int idxManager = 0;
    private boolean exist = false;
    private boolean borrowStatus = false;
    private LocalDate date;
    private int memberID;

    public Book() {}
    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.exist = true;
        idxManager++;
    }

    public String getName() { return this.name; }
    public String getAuthor() { return this.author; }
    public int getPrice() { return this.price; }
    public boolean getBorrowStatus() { return this.borrowStatus; }
    public LocalDate getDate() { return this.date; }
    public int getMemberID() { return this.memberID; }

    public void setBorrowStatus(boolean borrowStatus) { this.borrowStatus = borrowStatus; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setMemberID(int id) { this.memberID = id; }

    public static void createBook() {
        Scanner scn = new Scanner(System.in);
        int idx = idxManager;

        if (idx < 100) {
            System.out.println("Book(" + idx + "):");

            System.out.print("Name: ");
            LibraryImpl.books[idx].name = scn.nextLine();

            System.out.print("Author: ");
            LibraryImpl.books[idx].author = scn.nextLine();

            System.out.print("Price: ");
            LibraryImpl.books[idx].price = scn.nextInt();

            LibraryImpl.books[idx].exist = true;

            idxManager++;

            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library books are full!");
        }
        System.out.println();
    }

    public static void readBook(String name) {
        int idx = search(name);
        if (idx != -1) {
            Book tmp = LibraryImpl.books[idx];
            System.out.println("Book(" + idx + "):");
            System.out.println("name: " + tmp.name + ",");
            System.out.println("author: " + tmp.author + ",");
            System.out.println("price: " + tmp.price + ".");
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
    }

    public static void updateBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        int idx = search(name);
        if (idx != -1) {
            System.out.print("Name: ");
            LibraryImpl.books[idx].name = scn.nextLine();

            System.out.print("Author: ");
            LibraryImpl.books[idx].author = scn.nextLine();

            System.out.print("Price: ");
            LibraryImpl.books[idx].price = scn.nextInt();

            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
    }

    public static void deleteBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        int idx = search(name);
        if (idx != -1) {
            for (int i = idx; i > idxManager - 1; i++) {
                LibraryImpl.books[i] = LibraryImpl.books[i + 1];
            }
            idxManager--;
            LibraryImpl.books[idxManager].exist = false;
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book doesn't exist!");
        }
        System.out.println();
    }

    public static int search(String name) {
        for (int i = 0; i < idxManager; i++) {
            if (Objects.equals(LibraryImpl.books[i].name, name)) return i;
        }
        return -1;
    }

    public static void searchBookByName() {
        System.out.print("Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].name, name)) {
                    readBook(name);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Book with this name doesn't exist!");
            System.out.println();
        }
    }

    public static void searchBookByAuthor() {
        System.out.print("Author: ");

        Scanner scn = new Scanner(System.in);
        String author = scn.nextLine();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].author, author)) {
                    readBook(LibraryImpl.books[i].name);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Book with this author doesn't exist!");
            System.out.println();
        }
    }

    public static void searchBookByPrice() {
        System.out.print("Price: ");

        Scanner scn = new Scanner(System.in);
        int price = scn.nextInt();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.books[i].exist) {
                if (LibraryImpl.books[i].price <= price) {
                    readBook(LibraryImpl.books[i].name);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Book with a lower price doesn't exist!");
            System.out.println();
        }
    }

    public static void searchBookByBorrowStatus() {
        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.books[i].exist) {
                if (!LibraryImpl.books[i].borrowStatus) {
                    readBook(LibraryImpl.books[i].name);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Free book doesn't exist!");
            System.out.println();
        }
    }

    @Override
    public int getIdManager() {
        return idxManager;
    }

    @Override
    public void readFromConsole() throws BadEntityException {

    }

    @Override
    public void showOnConsole() {

    }
}
