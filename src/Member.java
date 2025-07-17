import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Member implements Entity {
    private String name;
    private int age;
    private Gender gender;
    private int ID = -1;
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
            new Member().showOnConsole("Member(" + memberId + "):\n");

            new Member().showOnConsole("Name: ");
            member.name = (String) new Member().readFromConsole("");

            if (new LibraryImpl().find(new Member(), member.name) == null) {
                new Member().showOnConsole("Age: ");
                member.age = (Integer) new Member().readFromConsole(0);

                new Member().showOnConsole("Gender: ");
                member.gender = (Gender) new Member().readFromConsole(Gender.class);

                member.exist = true;
                member.ID = memberId;

                new Member().showOnConsole("Member added successfully!\n\n");
                return member;
            } else {
                new Member().showOnConsole("Member already exists!\n");
            }
        } else {
            new Member().showOnConsole("Library members are full!\n");
        }
        new Member().showOnConsole("\n");
        return null;
    }

    public static Pair<Member, Integer> updateMember() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member Id: ");
        int id = (Integer) new Member().readFromConsole(0);

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            Member member = new Member();

            new Member().showOnConsole("Name: ");
            member.name = (String) new Member().readFromConsole("");

            new Member().showOnConsole("Age: ");
            member.age = (Integer) new Member().readFromConsole(0);

            new Member().showOnConsole("Gender: ");
            member.gender = (Gender) new Member().readFromConsole(Gender.class);

            new Member().showOnConsole("Member updated successfully!\n\n");
            return new Pair<>(member, id);
        } else {
            new Member().showOnConsole("Member doesn't exist!\n");
        }
        new Member().showOnConsole("\n");
        return null;
    }

    public static Pair<Member, Integer> deleteMember() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member Id: ");
        int id = (Integer) new Member().readFromConsole(0);

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            for (int i = id; i < LibraryImpl.getMemberIdx() - 1; i++) {
                LibraryImpl.members[i] = LibraryImpl.members[i + 1];
            }

            new Member().showOnConsole("Member deleted successfully!\n\n");
            return new Pair<>(new Member(), id);
        } else {
            new Member().showOnConsole("Member doesn't exist!\n");
        }
        new Member().showOnConsole("\n");
        return null;
    }

    public static void readMember(int id) {
        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            Member member = LibraryImpl.members[id];
            new Member().showOnConsole("Member(" + id + "):\n");
            new Member().showOnConsole("name: " + member.name + ",\n");
            new Member().showOnConsole("age: " + member.age + ",\n");
            new Member().showOnConsole("gender: " + member.gender + ".\n");
        } else {
            new Member().showOnConsole("Member doesn't exist!\n");
        }
        new Member().showOnConsole("\n");
    }

    public static void searchMemberByName() throws BadEntityException {
        new Member().showOnConsole("Name: ");
        String name = (String) new Member().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
            if (LibraryImpl.members[i].exist) {
                if (Objects.equals(LibraryImpl.members[i].name, name)) {
                    readMember(i);
                    return;
                }
            }
        }

        new Member().showOnConsole("Member with this name doesn't exist!\n\n");
    }

    public static void searchMemberByAge() throws BadEntityException {
        new Member().showOnConsole("Age: ");
        int age = (Integer) new Member().readFromConsole(0);

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].age == age) {
                    readMember(i);
                    return;
                }
            }
        }

        new Member().showOnConsole("Member with this age doesn't exist!\n\n");
    }

    public static void searchMemberByGender() throws BadEntityException {
        new Member().showOnConsole("Gender: ");
        Gender gender = (Gender) new Member().readFromConsole(Gender.class);

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].gender == gender) {
                    readMember(i);
                    return;
                }
            }
        }

        new Member().showOnConsole("Member with this gender doesn't exist!\n\n");
    }

    public static void borrowBook() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member Id: ");
        int memberId = (Integer) new Member().readFromConsole(0);

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
            boolean cannotBorrow = true;
            for (int i = 0; i < 10; i++) {
                if (!LibraryImpl.members[memberId].borrowedBooks[i].getBorrowStatus()) {
                    new Member().showOnConsole("Book name:");
                    String bookName = (String) new Member().readFromConsole("");

                    Book tmpBook = (Book) new LibraryImpl().find(new Book(), bookName);
                    if (tmpBook != null) {
                        if (LibraryImpl.books[tmpBook.getId()].getBorrowStatus()) {
                            new Member().showOnConsole("This book is already borrowed by another member!\n");
                            cannotBorrow = false;
                        } else {
                            new LibraryImpl().borrow(LibraryImpl.members[memberId], LibraryImpl.books[tmpBook.getId()], i);
                            new Member().showOnConsole("Book borrowed successfully!\n");
                            return;
                        }
                    } else {
                        new Member().showOnConsole("Book doesn't exist!\n");
                    }
                }
            }
            if (cannotBorrow) new Member().showOnConsole("Member can't borrows more than 10 books!\n");
        } else {
            new Member().showOnConsole("Member doesn't exist!\n");
        }
        new Member().showOnConsole("\n");
    }

    public static void returnBook() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member Id: ");
        int memberId = (Integer) new Member().readFromConsole(0);

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
            new Member().showOnConsole("Book name:");
                String bookName = (String) new Member().readFromConsole("");

                Book tmpBook = (Book) new LibraryImpl().find(new Book(), bookName);
                if (tmpBook != null) {
                    if (LibraryImpl.books[tmpBook.getId()].getBorrowStatus()) {
                        if (new LibraryImpl().giveBack(LibraryImpl.members[memberId], LibraryImpl.books[tmpBook.getId()])) {
                            return;
                        } else {
                            new Member().showOnConsole("Member hasn't borrowed this book!\n");
                        }
                    } else {
                        new Member().showOnConsole("This book isn't borrowed!\n");
                    }
                } else {
                    new Member().showOnConsole("Book doesn't exist!\n");
                }
        } else {
            new Member().showOnConsole("Member doesn't exist!\n");
        }
        new Member().showOnConsole("\n");
    }

    public static void showOverBorrowedMembers() {
        boolean hasBorrowed = false;
        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].getBorrowStatus()) {
                if (LocalDate.now().isBefore(LibraryImpl.books[i].getDate())) {
                    readMember(LibraryImpl.books[i].getMemberID());
                    hasBorrowed = true;
                }
            }
        }
        if (!hasBorrowed)
            new Member().showOnConsole("Members haven't borrowed yet!\n\n");
    }

    @Override
    public Object readFromConsole(Object object) throws BadEntityException {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        if (object instanceof String) {
            if (input.matches("[a-zA-Z]+")) {
                return input;
            } else {
                throw new BadEntityException("Please enter letters only!\n");
            }
        } else if (object instanceof Integer) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                throw new BadEntityException("Please enter numbers only!\n");
            }
        } else if (object instanceof Gender) {
            if (Objects.equals(input, "Male")) return Gender.Male;
            else if (Objects.equals(input, "Female")) return Gender.Female;
            else throw new BadEntityException("Please enter Gender only (Male or Female)!\n");
        }
        return null;
    }

    @Override
    public void showOnConsole(String output) {
        System.out.print(output);
    }
}
