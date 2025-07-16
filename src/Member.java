import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Scanner;

public class Member implements Entity {
    private String name;
    private int age;
    private Gender gender;
    private int ID;
    private static int idManager = 0;
    private boolean exist = false;
    private Book[] borrowedBooks;

    public Member() {}
    public Member(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ID = idManager++;
        this.exist = true;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public Gender getGender() { return this.gender; }
    public int getId() { return this.ID; }
    public static void setIdManager() {
        for (int i = 0; i < 100; i++) {
            if (!LibraryImpl.members[i].exist) {
                idManager = i;
                return;
            }
        }
        idManager = 100;
    }

    public static void createMember() {
        Scanner scn = new Scanner(System.in);

        if (idManager != 100) {
            int id = idManager;
            System.out.println("Member(" + id + "):");

            System.out.print("Name: ");
            LibraryImpl.members[id].name = scn.nextLine();

            System.out.print("Age: ");
            LibraryImpl.members[id].age = scn.nextInt();
            scn.nextLine();

            System.out.print("Gender: ");
            LibraryImpl.members[id].gender = Gender.valueOf(scn.nextLine());

            LibraryImpl.members[id].exist = true;
            setIdManager();

            System.out.println("Member added successfully!");
        } else {
            System.out.println("Library members are full!");
        }
        System.out.println();
    }

    public static void readMember(int id) {
        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            Member tmp = LibraryImpl.members[id];
            System.out.println("Member(" + id + "):");
            System.out.println("name: " + tmp.name + ",");
            System.out.println("age: " + tmp.age + ",");
            System.out.println("gender: " + tmp.gender + ".");
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
    }

    public static void updateMember() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            System.out.print("Name: ");
            LibraryImpl.members[id].name = scn.nextLine();

            System.out.print("Age: ");
            LibraryImpl.members[id].age = scn.nextInt();

            System.out.print("Gender: ");
            LibraryImpl.members[id].gender = Gender.valueOf(scn.next());

            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
    }

    public static void deleteMember() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            LibraryImpl.members[id].exist = false;
            setIdManager();
            System.out.println("Member deleted successfully!");
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
    }

    public static void borrowBook() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            if (!LibraryImpl.members[id].borrowedBooks[9].getBorrowStatus()) {
                System.out.print("Book name:");
                String name = scn.nextLine();

                for (int i = 0; i < 100; i++) {
                    if (Objects.equals(LibraryImpl.books[i].getName(), name)) {
                        if (LibraryImpl.books[i].getBorrowStatus()) {
                            System.out.println("This book is already borrowed!");
                        } else {
                            for (int j = 0; j < 10; j++) {
                                if (!LibraryImpl.members[id].borrowedBooks[j].getBorrowStatus()) {
                                    LibraryImpl.members[id].borrowedBooks[j].setDate(LocalDate.now().plus(Period.ofMonths(1)));
                                    LibraryImpl.members[id].borrowedBooks[j].setBorrowStatus(true);
                                    LibraryImpl.members[id].borrowedBooks[j].setMemberID(id);
                                    LibraryImpl.members[id].borrowedBooks[j] = LibraryImpl.books[i];
                                    System.out.println("Book borrowed successfully!");
                                    return;
                                }
                            }
                        }
                        return;
                    }
                }
                System.out.println("Book doesn't exist!");
            } else {
                System.out.println("Member can't borrows more than 10 books!");
            }
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
    }

    public static void returnBook() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
                System.out.print("Book name:");
                String name = scn.nextLine();

                for (int i = 0; i < 100; i++) {
                    if (Objects.equals(LibraryImpl.books[i].getName(), name)) {
                        if (!LibraryImpl.books[i].getBorrowStatus()) {
                            System.out.println("This book isn't borrowed!");
                        } else {
                            for (int j = 0; j < 10; j++) {
                                if (!LibraryImpl.members[id].borrowedBooks[j].getBorrowStatus()) {
                                    if (Objects.equals(LibraryImpl.members[id].borrowedBooks[j].getName(), name)) {
                                        LibraryImpl.members[i].borrowedBooks[j].setBorrowStatus(false);
                                        return;
                                    }
                                }
                            }
                        }
                        return;
                    }
                }
                System.out.println("Book doesn't exist!");
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
    }

    public static void showOverBorrowedMembers() {
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                for (int j = 0; j < 10; j++) {
                    if (LibraryImpl.members[i].borrowedBooks[j].getBorrowStatus()) {
                        if (LocalDate.now().isBefore(LibraryImpl.members[i].borrowedBooks[j].getDate())) {
                            readMember(i);
                        }
                    }
                }
            }
        }
    }

    public static void searchMemberByName() {
        System.out.print("Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                if (Objects.equals(LibraryImpl.members[i].name, name)) {
                    readMember(i);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Member with this name doesn't exist!");
            System.out.println();
        }
    }

    public static void searchMemberByAge() {
        System.out.print("Age: ");

        Scanner scn = new Scanner(System.in);
        int age = scn.nextInt();

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].age == age) {
                    readMember(i);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Member with this age doesn't exist!");
            System.out.println();
        }
    }

    public static void searchMemberByGender() {
        System.out.print("Gender: ");

        Scanner scn = new Scanner(System.in);
        Gender gender = Gender.valueOf(scn.next());

        boolean find = false;
        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].gender == gender) {
                    readMember(i);
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            System.out.println("Member with this gender doesn't exist!");
            System.out.println();
        }
    }

    public int getID() {
        return idManager;
    }

    public void readFromConsole() throws BadEntityException {

    }

    public void showOnConsole() {

    }
}
