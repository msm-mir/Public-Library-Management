
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Book {
    private String name;
    private String author;
    private int price;
    private static int idxManager = 0;
    private boolean exist = false;
    private boolean borrowStatus = false;
    private LocalDate date;
    private int memberId;

    Book(String name, String author, int price) {
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
    public void setBorrowStatus(boolean status) { this.borrowStatus = status; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setMemberId(int id) {this.memberId = id; }

    public static void createBook() {
        Scanner scn = new Scanner(System.in);
        int idx = idxManager;

        if (idx != 100) {
            System.out.println("Book(" + idx + "):");

            System.out.print("Name: ");
            Library.books[idx].name = scn.nextLine();

            System.out.print("Author: ");
            Library.books[idx].author = scn.nextLine();

            System.out.print("Price: ");
            Library.books[idx].price = scn.nextInt();

            Library.books[idx].exist = true;

            idxManager++;

            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library books are full!");
        }
    }
    public static void readBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        int idx = search(name);
        if (idx != -1) {
            Book tmp = Library.books[idx];
            System.out.println("Book(" + idx + "):");
            System.out.println("name: " + tmp.name + ",");
            System.out.println("author: " + tmp.author + ",");
            System.out.println("price: " + tmp.price + ".");
        } else {
            System.out.println("Book doesn't exist!");
        }
    }
    public static void updateBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        int idx = search(name);
        if (idx != -1) {
            System.out.print("Name: ");
            Library.books[idx].name = scn.nextLine();

            System.out.print("Author: ");
            Library.books[idx].author = scn.nextLine();

            System.out.print("Price: ");
            Library.books[idx].price = scn.nextInt();

            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book doesn't exist!");
        }
    }
    public static void deleteBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        int idx = search(name);
        if (idx != -1) {
            for (int i = idx; i > idxManager - 1; i++) {
                Library.books[i] = Library.books[i + 1];
            }
            idxManager--;
            Library.books[idxManager].exist = false;
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book doesn't exist!");
        }
    }

    public static int search(String name) {
        for (int i = 0; i < idxManager; i++) {
            if (name == Library.books[i].name) return i;
        }
        return -1;
    }
    public static void searchBookByName() {
        System.out.print("Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (Library.books[i].exist) {
                if (Library.books[i].name == name) {
                    readBook(name);
                    find = true;
                }
            }
        }
        if (!find) {
            System.out.println("Book with this name doesn't exist!");
        }
    }
    public static void searchBookByAuthor() {
        System.out.print("Author: ");

        Scanner scn = new Scanner(System.in);
        String author = scn.nextLine();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (Library.books[i].exist) {
                if (Library.books[i].author == author) {
                    readBook(Library.books[i].name);
                    find = true;
                }
            }
        }
        if (!find) {
            System.out.println("Book with this author doesn't exist!");
        }
    }
    public static void searchBookByPrice() {
        System.out.print("Price: ");

        Scanner scn = new Scanner(System.in);
        int price = scn.nextInt();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (Library.books[i].exist) {
                if (Library.books[i].price <= price) {
                    readBook(Library.books[i].name);
                    find = true;
                }
            }
        }
        if (!find) {
            System.out.println("Book with a lower price doesn't exist!");
        }
    }
    public static void searchBookByBorrowStatus() {
        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (Library.books[i].exist) {
                if (!Library.books[i].borrowStatus) {
                    readBook(Library.books[i].name);
                    find = true;
                }
            }
        }
        if (!find) {
            System.out.println("Free book doesn't exist!");
        }
    }
}
