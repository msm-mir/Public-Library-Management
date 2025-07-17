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
                member.gender = (Gender) new Member().readFromConsole(Gender.Male);

                member.exist = true;
                member.ID = memberId;

                new Member().showOnConsole("Member Added Successfully!\n\n");
                return member;
            } else {
                new Member().showOnConsole("Member Already Exists!\n");
            }
        } else {
            new Member().showOnConsole("Library Has No Space For New Members!\n");
        }
        new Member().showOnConsole("\n");
        return null;
    }

    public static Pair<Member, Integer> updateMember() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member's ID: ");
        int id = (Integer) new Member().readFromConsole(0);

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            new Member().showOnConsole("Please Enter The New Information!\n");
            Member member = LibraryImpl.members[id];

            new Member().showOnConsole("Name: ");
            member.name = (String) new Member().readFromConsole("");

            new Member().showOnConsole("Age: ");
            member.age = (Integer) new Member().readFromConsole(0);

            new Member().showOnConsole("Gender: ");
            member.gender = (Gender) new Member().readFromConsole(Gender.Male);

            new Member().showOnConsole("Member Updated Successfully!\n\n");
            return new Pair<>(member, id);
        } else {
            new Member().showOnConsole("Member Doesn't Exist!\n");
        }
        new Member().showOnConsole("\n");
        return null;
    }

    public static Pair<Member, Integer> deleteMember() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member's ID: ");
        int id = (Integer) new Member().readFromConsole(0);

        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            for (int i = id; i < LibraryImpl.getMemberIdx() - 1; i++) {
                LibraryImpl.members[i] = LibraryImpl.members[i + 1];
            }

            new Member().showOnConsole("Member Deleted Successfully!\n\n");
            return new Pair<>(new Member(), id);
        } else {
            new Member().showOnConsole("Member Doesn't Exist!\n");
        }
        new Member().showOnConsole("\n");
        return null;
    }

    public static void readMember(int id) {
        if ((id >= 0 && id < 100) && (LibraryImpl.members[id].exist)) {
            Member member = LibraryImpl.members[id];
            new Member().showOnConsole("Member(" + id + "): ");
            new Member().showOnConsole("Name: " + member.name + ", ");
            new Member().showOnConsole("Age: " + member.age + ", ");
            new Member().showOnConsole("Gender: " + member.gender + ".\n");
        } else {
            new Member().showOnConsole("Member Doesn't Exist!\n");
        }
    }

    public static void searchMemberByName() throws BadEntityException {
        new Member().showOnConsole("Name: ");
        String name = (String) new Member().readFromConsole("");

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
            if (LibraryImpl.members[i].exist) {
                if (Objects.equals(LibraryImpl.members[i].name, name)) {
                    readMember(i);
                    new Member().showOnConsole("\n");
                    return;
                }
            }
        }

        new Member().showOnConsole("Member With This Name Doesn't Exist!\n\n");
    }

    public static void searchMemberByAge() throws BadEntityException {
        new Member().showOnConsole("Age: ");
        int age = (Integer) new Member().readFromConsole(0);
        boolean hasFound = false;

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].age == age) {
                    readMember(i);
                    hasFound = true;
                }
            }
        }

        if (!hasFound)
            new Member().showOnConsole("Member With This Age Doesn't Exist!\n");

        new Member().showOnConsole("\n");
    }

    public static void searchMemberByGender() throws BadEntityException {
        new Member().showOnConsole("Gender: ");
        Gender gender = (Gender) new Member().readFromConsole(Gender.Male);
        boolean hasFound = false;

        for (int i = 0; i < LibraryImpl.getMemberIdx(); i++) {
            if (LibraryImpl.members[i].exist) {
                if (LibraryImpl.members[i].gender == gender) {
                    readMember(i);
                    hasFound = true;
                }
            }
        }

        if (!hasFound)
            new Member().showOnConsole("Member With This Gender Doesn't Exist!\n");

        new Member().showOnConsole("\n");
    }

    public static void borrowBook() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member's ID: ");
        int memberId = (Integer) new Member().readFromConsole(0);

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
            for (int i = 0; i < 10; i++) {
                if (!LibraryImpl.members[memberId].borrowedBooks[i].getBorrowStatus()) {
                    new Member().showOnConsole("Please Enter The Book's Name: ");
                    String bookName = (String) new Member().readFromConsole("");

                    Book tmpBook = (Book) new LibraryImpl().find(new Book(), bookName);
                    if (tmpBook != null) {
                        if (LibraryImpl.books[tmpBook.getId()].getBorrowStatus()) {
                            new Member().showOnConsole("Book Isn't Available!\n\n");
                        } else {
                            new LibraryImpl().borrow(LibraryImpl.members[memberId], LibraryImpl.books[tmpBook.getId()], i);
                            new Member().showOnConsole("Book Borrowed Successfully!\n\n");
                        }
                    } else {
                        new Member().showOnConsole("Book Doesn't Exist!\n\n");
                    }
                    return;
                }
            }
            new Member().showOnConsole("Member Can't Borrow More Than 10 Books!\n");
        } else {
            new Member().showOnConsole("Member Doesn't Exist!\n");
        }
        new Member().showOnConsole("\n");
    }

    public static void returnBook() throws BadEntityException {
        new Member().showOnConsole("Please Enter The Member's ID: ");
        int memberId = (Integer) new Member().readFromConsole(0);

        if ((memberId >= 0 && memberId < 100) && (LibraryImpl.members[memberId].exist)) {
            new Member().showOnConsole("Please Enter The Book's Name: ");
                String bookName = (String) new Member().readFromConsole("");

                Book tmpBook = (Book) new LibraryImpl().find(new Book(), bookName);
                if (tmpBook != null) {
                    if (LibraryImpl.books[tmpBook.getId()].getBorrowStatus()) {
                        if (new LibraryImpl().giveBack(LibraryImpl.members[memberId], LibraryImpl.books[tmpBook.getId()])) {
                            new Member().showOnConsole("Book Returned Successfully!\n\n");
                            return;
                        } else {
                            new Member().showOnConsole("Member Hasn't Borrowed This Book!\n");
                        }
                    } else {
                        new Member().showOnConsole("This Book Hasn't Been Borrowed Yet!\n");
                    }
                } else {
                    new Member().showOnConsole("Book Doesn't Exist!\n");
                }
        } else {
            new Member().showOnConsole("Member Doesn't Exist!\n");
        }
        new Member().showOnConsole("\n");
    }

    public static void showOverBorrowedMembers() {
        boolean hasBorrowed = false;
        boolean isDue = false;
        for (int i = 0; i < LibraryImpl.getBookIdx(); i++) {
            if (LibraryImpl.books[i].getBorrowStatus()) {
                hasBorrowed = true;
                if (LibraryImpl.books[i].getDate().isBefore(LocalDate.now())) {
                    readMember(LibraryImpl.books[i].getMemberID());
                    isDue = true;
                }
            }
        }
        if (!hasBorrowed)
            new Member().showOnConsole("There Are No Borrowed Books Yet!\n");
        else if (!isDue)
            new Member().showOnConsole("No Book Is Due For Return Yet!\n");

        new Member().showOnConsole("\n");
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
                if (i >= 0)
                    return i;
                else
                    throw new BadEntityException("Please Enter Positive Numbers Only!\n");
            } catch (NumberFormatException exception) {
                throw new BadEntityException("Please Enter Numbers Only!\n");
            }
        } else if (object instanceof Gender) {
            if (Objects.equals(input, "Male")) return Gender.Male;
            else if (Objects.equals(input, "Female")) return Gender.Female;
            else throw new BadEntityException("Please Enter Gender Only (Male or Female)!\n");
        }
        return null;
    }

    @Override
    public void showOnConsole(String output) {
        System.out.print(output);
    }
}
