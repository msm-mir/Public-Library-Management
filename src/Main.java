import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryImpl library = new LibraryImpl();

        System.out.println("Welcome To Our Library!");

        while (true) {
            printMenu();
            System.out.print("Please Enter Your Choice: ");

            Scanner scn = new Scanner(System.in);
            int cmd = scn.nextInt();

            switch (cmd) {
                case 1:
                    try {
                        Member m = Member.createMember();
                        if (m == null) throw new NullPointerException();
                        library.save(m);
                    } catch (BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 2:
                    try {
                        Pair<Member, Integer> p = Member.updateMember();
                        if (p == null) throw new NullPointerException();
                        library.update(p.left, p.right);
                    } catch (EntityNotFoundException | BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 3:
                    try {
                        Pair<Member, Integer> p = Member.deleteMember();
                        if (p == null) throw new NullPointerException();
                        library.delete(p.left, p.right);
                    } catch (EntityNotFoundException | BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 4:
                    try {
                        readMember();
                    } catch (Throwable _) {}
                    break;

                case 5:
                    try {
                        Book b = Book.createBook();
                        if (b == null) throw new NullPointerException();
                        library.save(b);
                    } catch (BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 6:
                    try {
                        Pair<Book, Integer> p = Book.updateBook();
                        if (p == null) throw new NullPointerException();
                        library.update(p.left, p.right);
                    } catch (EntityNotFoundException | BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 7:
                    try {
                        Pair<Book, Integer> p = Book.deleteBook();
                        if (p == null) throw new NullPointerException();
                        library.delete(p.left, p.right);
                    } catch (EntityNotFoundException | BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 8:
                    try {
                        readBook();
                    } catch (Throwable _) {}
                    break;

                case 9:
                    printMemberSearchMenu();
                    System.out.print("Please Enter Your Choice: ");
                    cmd = scn.nextInt();

                    switch (cmd) {
                        case 1:
                            try {
                                Member.searchMemberByName();
                            } catch (BadEntityException exception) {
                                System.out.println(exception.getMessage());
                            }  catch (Throwable _) {}
                            break;

                        case 2:
                            try {
                                Member.searchMemberByAge();
                            } catch (BadEntityException exception) {
                                System.out.println(exception.getMessage());
                            } catch (Throwable _) {}
                            break;

                        case 3:
                            try {
                                Member.searchMemberByGender();
                            } catch (BadEntityException exception) {
                                System.out.println(exception.getMessage());
                            } catch (Throwable _) {}
                            break;

                        case 4:
                            break;

                        default:
                            System.out.println("Wrong Choice!");
                            System.out.println();
                            break;
                    }
                    break;

                case 10:
                    printBookSearchMenu();
                    System.out.print("Please Enter Your Choice: ");
                    cmd = scn.nextInt();

                    switch (cmd) {
                        case 1:
                            try {
                                Book.searchBookByName();
                            } catch (BadEntityException exception) {
                                System.out.println(exception.getMessage());
                            } catch (Throwable _) {}
                            break;

                        case 2:
                            try {
                                Book.searchBookByAuthor();
                            } catch (BadEntityException exception) {
                                System.out.println(exception.getMessage());
                            } catch (Throwable _) {}
                            break;

                        case 3:
                            try {
                                Book.searchBookByPrice();
                            } catch (BadEntityException exception) {
                                System.out.println(exception.getMessage());
                            } catch (Throwable _) {}
                            break;

                        case 4:
                            try {
                                Book.searchBookByBorrowStatus();
                            } catch (Throwable _) {}
                            break;

                        case 5:
                            break;

                        default:
                            System.out.println("Wrong Choice!");
                            System.out.println();
                            break;
                    }
                    break;

                case 11:
                    try {
                        Member.borrowBook();
                    } catch (BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 12:
                    try {
                        Member.returnBook();
                    } catch (BadEntityException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Throwable _) {}
                    break;

                case 13:
                    try {
                        Member.showOverBorrowedMembers();
                    } catch (Throwable _) {}
                    break;

                case 14:
                    return;

                default:
                    System.out.println("Wrong Choice!");
                    System.out.println();
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println(" -----------------------------------------------------------------------------------------");
        System.out.print("| 1.Add Member                                  ");
        System.out.print("2.Edit Member             ");
        System.out.println("3.Delete Member |");
        System.out.print("| 4.Show Members                                ");
        System.out.print("5.Add Book                ");
        System.out.println("6.Edit Book     |");
        System.out.print("| 7.Delete Book                                 ");
        System.out.print("8.Show Books              ");
        System.out.println("9.Search Member |");
        System.out.print("| 10.Search Book                                ");
        System.out.print("11.Borrow Book            ");
        System.out.println("12.Return Book  |");
        System.out.print("| 13.Show Members Who Didn't Return Books       ");
        System.out.println("14.Exit                                   |");
        System.out.println(" -----------------------------------------------------------------------------------------");
    }

    static void printMemberSearchMenu() {
        System.out.println(" ---------------------------------------------------------------------------------------------");
        System.out.print("| 1.Search Members By Name    ");
        System.out.print("2.Search Members By Age    ");
        System.out.print("3.Search Members By Gender    ");
        System.out.println("4.Back |");
        System.out.println(" ---------------------------------------------------------------------------------------------");
    }

    static void printBookSearchMenu() {
        System.out.println(" ----------------------------------------------------------------------");
        System.out.print("| 1.Search Books By Name    ");
        System.out.println("2.Search Books By Author                   |");
        System.out.print("| 3.Search Books By Price    ");
        System.out.print("4.Search Books By Borrow Status    ");
        System.out.println("5.Back |");
        System.out.println(" ----------------------------------------------------------------------");
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

        Book.readBook(new LibraryImpl().find(new Book(), name).getId());
    }
}