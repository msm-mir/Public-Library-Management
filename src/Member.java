import java.time.LocalDate;
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

    public void setBorrowedBooks (Book book, int idx) { this.borrowedBooks[idx] = book; }
    public String getName() { return this.name; }
    public Book getBorrowBooks(int idx) { return this.borrowedBooks[idx]; }
    @Override
    public int getId() { return this.ID; }

    public static Member createMember() throws BadEntityException {
        int memberId = LibraryImpl.getMemberIdx();
        if (memberId < 100) {
            Member member = new Member();
            System.out.println("Member(" + memberId + "):");

            System.out.print("Name: ");
            member.name = (String) new Member().readFromConsole("");

            if (new LibraryImpl().find(new Book(), member.name).getId() == -1) {
                System.out.print("Age: ");
                member.age = (Integer) new Member().readFromConsole(0);

                System.out.print("Gender: ");
                member.gender = Gender.valueOf((String) new Member().readFromConsole(""));

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

    public static Pair<Member, Integer> updateMember() throws BadEntityException {
        System.out.print("Please Enter The Member Id: ");
        int id = (Integer) new Member().readFromConsole(0);

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            Member member = new Member();

            System.out.print("Name: ");
            member.name = (String) new Member().readFromConsole("");

            System.out.print("Age: ");
            member.age = (Integer) new Member().readFromConsole(0);

            System.out.print("Gender: ");
            member.gender = Gender.valueOf((String) new Member().readFromConsole(""));

            System.out.println("Member updated successfully!");
            System.out.println();
            return new Pair<>(member, id);
        } else {
            System.out.println("Member doesn't exist!");
        }
        System.out.println();
        return null;
    }

    public static Pair<Member, Integer> deleteMember() throws BadEntityException {
        System.out.print("Please Enter The Member Id: ");
        int id = (Integer) new Member().readFromConsole(0);

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

    public static void searchMemberByName() throws BadEntityException {
        System.out.print("Name: ");
        String name = (String) new Member().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
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

    public static void searchMemberByAge() throws BadEntityException {
        System.out.print("Age: ");
        int age = (Integer) new Member().readFromConsole(0);

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
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

    public static void searchMemberByGender() throws BadEntityException {
        System.out.print("Gender: ");
        Gender gender = Gender.valueOf((String) new Member().readFromConsole(""));

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
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

    public static void borrowBook() throws BadEntityException {
        System.out.print("Please Enter The Member Id: ");
        int memberId = (Integer) new Member().readFromConsole(0);

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
            for (int i = 0; i < 10; i++) {
                if (!LibraryImpl.members[memberId].borrowedBooks[i].getBorrowStatus()) {
                    System.out.print("Book name:");
                    String bookName = (String) new Member().readFromConsole("");

                    int bookIdx = new LibraryImpl().find(new Book(), bookName).getId();
                    if (bookIdx != -1) {
                        if (LibraryImpl.books[bookIdx].getBorrowStatus()) {
                            System.out.println("This book is already borrowed by another member!");
                        } else {
                            new LibraryImpl().borrow(LibraryImpl.members[memberId], LibraryImpl.books[bookIdx], i);
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

    public static void returnBook() throws BadEntityException {
        System.out.print("Please Enter The Member Id: ");
        int memberId = (Integer) new Member().readFromConsole(0);

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
                System.out.print("Book name:");
                String bookName = (String) new Member().readFromConsole("");

                int bookIdx = new LibraryImpl().find(new Book(), bookName).getId();
                if (bookIdx != -1) {
                    if (!LibraryImpl.books[bookIdx].getBorrowStatus()) {
                        System.out.println("This book isn't borrowed!");
                    } else {
                        if (new LibraryImpl().giveBack(LibraryImpl.members[memberId], LibraryImpl.books[bookIdx]))
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
        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].getBorrowStatus()) {
                if (LocalDate.now().isBefore(LibraryImpl.books[i].getDate())) {
                    readMember(LibraryImpl.books[i].getMemberID());
                }
            }
        }
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
