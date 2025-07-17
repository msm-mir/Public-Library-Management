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

                new Book().showOnConsole("Book Added Successfully!\n\n");
                return book;
            } else {
                new Book().showOnConsole("Book Already Exists!\n");
            }
        } else {
            new Book().showOnConsole("Library Has No Space For New Books!\n");
        }
        new Book().showOnConsole("\n");
        return null;
    }

    public static Pair<Book, Integer> updateBook() throws BadEntityException {
        new Book().showOnConsole("Please Enter The Book Name: ");
        String name = (String) new Book().readFromConsole("");

        Book tmpBook = (Book) new LibraryImpl().find(new Book(), name);
        if (tmpBook != null) {
            new Book().showOnConsole("Please Enter The New Information!\n");
            Book book = LibraryImpl.books[tmpBook.getId()];

            new Book().showOnConsole("Name: ");
            book.name = (String) new Book().readFromConsole("");

            new Book().showOnConsole("Author: ");
            book.author = (String) new Book().readFromConsole("");

            new Book().showOnConsole("Price: ");
            book.price = (Integer) new Book().readFromConsole(0);

            new Book().showOnConsole("Book Updated Successfully!\n\n");
            return new Pair<>(book, tmpBook.ID);
        } else {
            new Book().showOnConsole("Book Doesn't Exist!\n");
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

            new Book().showOnConsole("Book Deleted Successfully!\n\n");
            return new Pair<>(new Book(), tmpBook.ID);
        } else {
            new Book().showOnConsole("Book Doesn't Exist!\n");
        }
        new Book().showOnConsole("\n");
        return null;
    }

    public static void readBook(int idx) {
        if (idx != -1) {
            Book book = LibraryImpl.books[idx];
            new Book().showOnConsole("Book(" + idx + "): ");
            new Book().showOnConsole("Name: " + book.name + ", ");
            new Book().showOnConsole("Author: " + book.author + ", ");
            new Book().showOnConsole("Price: " + book.price + ", ");
            new Book().showOnConsole("Borrow Status: " + (book.borrowStatus ? "Borrowed" : "Available" + ".\n"));
        } else {
            new Book().showOnConsole("Book Doesn't Exist!\n");
        }
    }

    public static void searchBookByName() throws BadEntityException {
        new Book().showOnConsole("Name: ");
        String name = (String) new Book().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].name, name)) {
                    readBook(i);
                    new Book().showOnConsole("\n");
                    return;
                }
            }
        }

        new Book().showOnConsole("Book With This Name Doesn't Exist!\n\n");
    }

    public static void searchBookByAuthor() throws BadEntityException {
        new Book().showOnConsole("Author: ");
        String author = (String) new Book().readFromConsole("");
        boolean hasFound = false;

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (Objects.equals(LibraryImpl.books[i].author, author)) {
                    readBook(i);
                    hasFound = true;
                }
            }
        }

        if (!hasFound)
            new Book().showOnConsole("Book With This Author Doesn't Exist!\n");

        new Book().showOnConsole("\n");
    }

    public static void searchBookByPrice() throws BadEntityException {
        new Book().showOnConsole("Price: ");
        int price = (Integer) new Book().readFromConsole(0);
        boolean hasFound = false;

        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (LibraryImpl.books[i].price <= price) {
                    readBook(i);
                    hasFound = true;
                }
            }
        }

        if (!hasFound)
            new Book().showOnConsole("Book With A Lower Price Doesn't Exist!\n");

        new Book().showOnConsole("\n");
    }

    public static void searchBookByBorrowStatus() {
        boolean hasFound = false;
        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].exist) {
                if (!LibraryImpl.books[i].borrowStatus) {
                    readBook(i);
                    hasFound = true;
                }
            }
        }

        if (!hasFound)
            new Book().showOnConsole("No Available Book Exists!\n");

        new Book().showOnConsole("\n");
    }

    @Override
    public Object readFromConsole(Object object) throws BadEntityException {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        if (object instanceof String) {
            if (input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                throw new BadEntityException("Please Enter Letters Only!\n");
            }
        } else if (object instanceof Integer) {
            try {
                int i = Integer.parseInt(input);
                if (i > 0)
                    return i;
                else
                    throw new BadEntityException("Please Enter Positive Numbers Only!\n");
            } catch (NumberFormatException exception) {
                throw new BadEntityException("Please Enter Numbers Only!\n");
            }
        }
        return null;
    }

    @Override
    public void showOnConsole(String output) {
        System.out.print(output);
    }
}
