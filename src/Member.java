import java.util.Scanner;

public class Member {
    private String name;
    private int age;
    private char gender;
    private int id;
    private static int idManager = 0;
    private boolean exist = false;
    private Book borrowedBooks[] = new Book[10];

    public Member(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = idManager++;
        this.exist = true;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public char getGender() { return this.gender; }
    public int getId() { return this.id; }
    public static void setIdManager() {
        for (int i = 0; i < 100; i++) {
            if (!Library.members[i].exist) idManager = i;
        }
        idManager = 100;
    }

    public static void createMember() {
        Scanner scn = new Scanner(System.in);

        if (idManager != 100) {
            int id = idManager;
            System.out.println("Member(" + id + "):");

            System.out.print("Name: ");
            Library.members[id].name = scn.nextLine();

            System.out.print("Age: ");
            Library.members[id].age = scn.nextInt();

            System.out.print("Gender: ");
            Library.members[id].gender = scn.next().charAt(0);

            Library.members[id].exist = true;
            setIdManager();

            System.out.println("Member added successfully!");
        } else {
            System.out.println("Library members are full!");
        }
    }
    public static void readMember(int id) {
        if ((id >= 0 && id < 100) && (Library.members[id].exist)) {
            Member tmp = Library.members[id];
            System.out.println("Member(" + id + "):");
            System.out.println("name: " + tmp.name + ",");
            System.out.println("age: " + tmp.age + ",");
            System.out.println("gender: " + tmp.gender + ".");
        } else {
            System.out.println("Member doesn't exist!");
        }
    }
    public static void updateMember(int id) {
        if ((id >= 0 && id < 100) && (Library.members[id].exist)) {
            Scanner scn = new Scanner(System.in);

            System.out.print("Name: ");
            Library.members[id].name = scn.nextLine();

            System.out.print("Age: ");
            Library.members[id].age = scn.nextInt();

            System.out.print("Gender: ");
            Library.members[id].gender = scn.next().charAt(0);

            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member doesn't exist!");
        }
    }
    public static void deleteMember(int id) {
        if ((id >= 0 && id < 100) && (Library.members[id].exist)) {
            Library.members[id].exist = false;
            setIdManager();
            System.out.println("Member deleted successfully!");
        } else {
            System.out.println("Member doesn't exist!");
        }
    }
    public static void borrowBook(int id) {
        if ((id >= 0 && id < 100) && (Library.members[id].exist)) {
            if (!Library.members[id].borrowedBooks[9].getBorrowStatus()) {
                System.out.println("Book name:");

                Scanner scn = new Scanner(System.in);
                String name = scn.nextLine();

                for (int i = 0; i < 100; i++) {
                    if (Library.books[i].getName() == name) {
                        if (Library.books[i].getBorrowStatus()) {
                            System.out.println("This book is already borrowed!");
                        } else {
                            for (int j = 0; j < 9; j++) {
                                if (!Library.members[id].borrowedBooks[j].getBorrowStatus()) {
                                    Library.books[i].borrow(id);
                                    Library.members[id].borrowedBooks[j] = Library.books[i];
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
    }
    public static void returnBook(int id) {
        if ((id >= 0 && id < 100) && (Library.members[id].exist)) {
                System.out.println("Book name:");

                Scanner scn = new Scanner(System.in);
                String name = scn.nextLine();

                for (int i = 0; i < 100; i++) {
                    if (Library.books[i].getName() == name) {
                        if (!Library.books[i].getBorrowStatus()) {
                            System.out.println("This book isn't borrowed!");
                        } else {
                            for (int j = 0; j < 9; j++) {
                                if (!Library.members[id].borrowedBooks[j].getBorrowStatus()) {
                                    if (Library.members[id].borrowedBooks[j].getName() == name) {
                                        Library.books[i].returnB();
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
    }
}
