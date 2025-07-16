import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryImpl library = new LibraryImpl();

        System.out.println("Welcome To Our Library!");

        while (true) {
            printMenu();
            System.out.print("Please Enter Your Order: ");

            Scanner scn = new Scanner(System.in);
            int cmd = scn.nextInt();

            switch (cmd) {
                case 1:
                    try {
                        library.save(Member.createMember());
                    } catch (EntityNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 2:
                    try {
                        Pair<Member, Integer> p = Member.updateMember();
                        if (p == null) throw new NullPointerException();
                        library.update(p.left, p.right);
                    } catch (EntityNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 3:
                    try {
                        Pair<Member, Integer> p = Member.deleteMember();
                        if (p == null) throw new NullPointerException();
                        library.delete(p.left, p.right);
                    } catch (EntityNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 4:
                    readMember();
                    break;
                case 5:
                    try {
                        library.save(Book.createBook());
                    } catch (EntityNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 6:
                    try {
                        Pair<Book, Integer> p = Book.updateBook();
                        if (p == null) throw new NullPointerException();
                        library.update(p.left, p.right);
                    } catch (EntityNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 7:
                    try {
                        Pair<Book, Integer> p = Book.deleteBook();
                        if (p == null) throw new NullPointerException();
                        library.delete(p.left, p.right);
                    } catch (EntityNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 8:
                    readBook();
                    break;
                case 9:
                    printMemberSearchMenu();
                    System.out.print("Please Enter Your Order: ");
                    cmd = scn.nextInt();

                    switch (cmd) {
                        case 1:
                            Member.searchMemberByName();
                            break;
                        case 2:
                            Member.searchMemberByAge();
                            break;
                        case 3:
                            Member.searchMemberByGender();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Wrong Order!");
                            System.out.println();
                            break;
                    }
                    break;
                case 10:
                    printBookSearchMenu();
                    System.out.print("Please Enter Your Order: ");
                    cmd = scn.nextInt();

                    switch (cmd) {
                        case 1:
                            Book.searchBookByName();
                            break;
                        case 2:
                            Book.searchBookByAuthor();
                            break;
                        case 3:
                            Book.searchBookByPrice();
                            break;
                        case 4:
                            Book.searchBookByBorrowStatus();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Wrong Order!");
                            System.out.println();
                            break;
                    }
                    break;
                case 11:
                    Member.borrowBook();
                    break;
                case 12:
                    Member.returnBook();
                    break;
                case 13:
                    Member.showOverBorrowedMembers();
                    break;
                case 14:
                    return;
                default:
                    System.out.println("Wrong Order!");
                    System.out.println();
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.print("1.Add Member    ");
        System.out.print("2.Edit Member    ");
        System.out.println("3.Delete Member");
        System.out.print("4.Show Member    ");
        System.out.print("5.Add Book    ");
        System.out.println("6.Edit Book");
        System.out.print("7.Delete Book    ");
        System.out.print("8.Show Book    ");
        System.out.println("9.Search Member");
        System.out.print("10.Search Book    ");
        System.out.print("11.Borrow Book    ");
        System.out.println("12.Return Book");
        System.out.print("13.Show Members Didn't Return Book    ");
        System.out.println("14.Exit");
    }

    static void printMemberSearchMenu() {
        System.out.print("1.Search Member By Name    ");
        System.out.print("2.Search Member By Age    ");
        System.out.print("3.Search Member By Gender    ");
        System.out.println("4.back");
    }

    static void printBookSearchMenu() {
        System.out.print("1.Search Book By Name    ");
        System.out.println("2.Search Book By Author");
        System.out.print("3.Search Book By Price    ");
        System.out.print("4.Search Book By Borrow Status    ");
        System.out.println("5.back");
    }

    static void readMember() {
        System.out.print("Please Enter Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        Member.readMember(id);
    }

    static void readBook() {
        System.out.print("Please Enter The Book Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        LibraryImpl library = new LibraryImpl();
        Book.readBook(((Book) library.find(new Book(), name)).getId());
    }
}