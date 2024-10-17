import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("Welcome To Our Library!");

        while (true) {
            printMenu();
            System.out.print("Please Enter Your Order: ");

            Scanner scn = new Scanner(System.in);
            int cmd = scn.nextInt();

            switch (cmd) {
                case 1:
                    Member.createMember();
                    break;
                case 2:
                    Member.updateMember();
                    break;
                case 3:
                    Member.deleteMember();
                    break;
                case 4:
                    readMember();
                    break;
                case 5:
                    Book.createBook();
                    break;
                case 6:
                    Book.updateBook();
                    break;
                case 7:
                    Book.deleteBook();
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
                        default:
                            System.out.println("Wrong Order!");
                            break;
                    }
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
                        default:
                            System.out.println("Wrong Order!");
                            break;
                    }
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
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("1.Add Member");
        System.out.println("2.Edit Member");
        System.out.println("3.Delete Member");
        System.out.println("4.Show Member");
        System.out.println("5.Add Book");
        System.out.println("6.Edit Book");
        System.out.println("7.Delete Book");
        System.out.println("8.Show Book");
        System.out.println("9.Search Member");
        System.out.println("10.Search Book");
        System.out.println("11.Borrow Book");
        System.out.println("12.Return Book");
        System.out.println("13.Show Members Didn't Return Book");
        System.out.println("14.Exit");
    }
    static void printMemberSearchMenu() {
        System.out.println("1.Search Member By Name");
        System.out.println("2.Search Member By Age");
        System.out.println("3.Search Member By Gender");
    }
    static void printBookSearchMenu() {
        System.out.println("1.Search Book By Name");
        System.out.println("2.Search Book By Author");
        System.out.println("3.Search Book By Price");
        System.out.println("4.Search Book By Borrow Status");
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

        Book.readBook(name);
    }
}