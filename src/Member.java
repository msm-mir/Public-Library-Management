import java.util.Scanner;

public class Member {
    private String name;
    private int age;
    private char gender;
    private int id;
    private static int idManager = 0;
    private boolean exist = false;

    public Member(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = Member.idManager++;
        this.exist = true;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public char getGender() { return this.gender; }
    public int getId() { return this.id; }

    public static void createMember() {
        Scanner scn = new Scanner(System.in);
        int id = Member.idManager;

        if (id >= 0 && id < 100) {
            System.out.println("Member(" + id + "):");

            System.out.print("Name: ");
            Library.members[id].name = scn.nextLine();

            System.out.print("Age: ");
            Library.members[id].age = scn.nextInt();

            System.out.print("Gender: ");
            Library.members[id].gender = scn.next().charAt(0);

            Library.members[id].exist = true;

            Member.idManager++;

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
            for (int i = id; i > Member.idManager - 1; i++) {
                if (i != 99) Library.members[i] = Library.members[i + 1];
                else Library.members[i] = null;
            }
            Member.idManager--;
            Library.members[Member.idManager].exist = false;
            System.out.println("Member deleted successfully!");
        } else {
            System.out.println("Member doesn't exist!");
        }
    }
}
