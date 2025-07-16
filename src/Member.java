import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Scanner;

public class Member implements Entity {
    private String name;
    private int age;
    private Gender gender;
    private int ID;
    private boolean exist = false;
    private Book[] borrowedBooks = new Book[10];
    {
        for (int i = 0; i < 10; i++) {
            borrowedBooks[i] = new Book();
        }
    }

    public Member() {}
    public Member(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ID = LibraryImpl.getMemberIdx();
        this.exist = true;
        LibraryImpl.setMemberIdx(LibraryImpl.getMemberIdx() + 1);
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public Gender getGender() { return this.gender; }
    @Override
    public int getId() { return this.ID; }

    public static Member createMember() {
        Scanner scn = new Scanner(System.in);

        int memberId = LibraryImpl.getMemberIdx();
        if (memberId != 100) {
            Member member = new Member();
            System.out.println("Member(" + memberId + "):");

            System.out.print("Name: ");
            member.name = scn.nextLine();

            if (search(member.name) == -1) {
                System.out.print("Age: ");
                member.age = scn.nextInt();
                scn.nextLine();

                System.out.print("Gender: ");
                member.gender = Gender.valueOf(scn.nextLine());

                member.exist = true;
                member.ID = LibraryImpl.getMemberIdx();

                System.out.println("Member added successfully!");
                System.out.println();
                return member;
            } else {
                System.out.println("Member already exists!");
            }
        } else {
            System.out.println("Library members are full!");
        }
        System.out.println();
        return null;
    }

    public static Pair<Member, Integer> updateMember() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            Member member = new Member();

            System.out.print("Name: ");
            member.name = scn.nextLine();

            System.out.print("Age: ");
            member.age = scn.nextInt();
            scn.nextLine();

            System.out.print("Gender: ");
            member.gender = Gender.valueOf(scn.next());

            System.out.println("Member updated successfully!");
            System.out.println();
            return new Pair<>(member, id);
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
        return null;
    }

    public static Pair<Member, Integer> deleteMember() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            for (int i = id; i < LibraryImpl.getMemberIdx() - 1; i++) {
                LibraryImpl.members[i] = LibraryImpl.members[i + 1];
            }

            System.out.println("Member deleted successfully!");
            System.out.println();
            return new Pair<>(new Member(), id);
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
        return null;
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

    public static int search(String name) {
        for (int i = 0; i < 100; i++) {
            if (Objects.equals(LibraryImpl.members[i].name, name)) return i;
        }
        return -1;
    }

    public static void searchMemberByName() {
        System.out.print("Name: ");

        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();

        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                if (Objects.equals(LibraryImpl.members[i].name, name)) {
                    readMember(i);
                    return;
                }
            }
        }

        System.out.println("Member with this name doesn't exist!");
        System.out.println();
    }

    public static void searchMemberByAge() {
        System.out.print("Age: ");

        Scanner scn = new Scanner(System.in);
        int age = scn.nextInt();

        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].age == age) {
                    readMember(i);
                    return;
                }
            }
        }

        System.out.println("Member with this age doesn't exist!");
        System.out.println();
    }

    public static void searchMemberByGender() {
        System.out.print("Gender: ");

        Scanner scn = new Scanner(System.in);
        Gender gender = Gender.valueOf(scn.next());

        for (int i = 0; i < 100; i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].gender == gender) {
                    readMember(i);
                    return;
                }
            }
        }

        System.out.println("Member with this gender doesn't exist!");
        System.out.println();
    }

    public static void borrowBook() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int memberId = scn.nextInt();

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
            for (int i = 0; i < 10; i++) {
                if (!LibraryImpl.members[memberId].borrowedBooks[i].getBorrowStatus()) {
                    System.out.print("Book name:");
                    String bookName = scn.nextLine();

                    int bookIdx = Book.search(bookName);
                    if (bookIdx != -1) {
                        if (LibraryImpl.books[bookIdx].getBorrowStatus()) {
                            System.out.println("This book is already borrowed by another member!");
                        } else {
                            LibraryImpl.members[memberId].borrowedBooks[i].setDate(LocalDate.now().plus(Period.ofMonths(1)));
                            LibraryImpl.members[memberId].borrowedBooks[i].setBorrowStatus(true);
                            LibraryImpl.members[memberId].borrowedBooks[i].setMemberID(memberId);
                            LibraryImpl.members[memberId].borrowedBooks[i] = LibraryImpl.books[bookIdx];
                            System.out.println("Book borrowed successfully!");
                            return;
                        }
                    } else {
                        System.out.println("Book doesn't exist!");
                    }
                }
            }
            System.out.println("Member can't borrows more than 10 books!");
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
    }

    public static void returnBook() {
        System.out.print("Please Enter The Member Id: ");

        Scanner scn = new Scanner(System.in);
        int memberId = scn.nextInt();

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
                System.out.print("Book name:");
                String bookName = scn.nextLine();

                int bookIdx = Book.search(bookName);
                if (bookIdx != -1) {
                    if (!LibraryImpl.books[bookIdx].getBorrowStatus()) {
                        System.out.println("This book isn't borrowed!");
                    } else {
                        for (int i = 0; i < 10; i++) {
                            if (!LibraryImpl.members[memberId].borrowedBooks[i].getBorrowStatus()) {
                                if (Objects.equals(LibraryImpl.members[memberId].borrowedBooks[i].getName(), bookName)) {
                                    LibraryImpl.members[memberId].borrowedBooks[i].setBorrowStatus(false);
                                    return;
                                }
                            }
                        }
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

    @Override
    public void readFromConsole() throws BadEntityException {

    }

    @Override
    public void showOnConsole() {

    }
}
