public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        while (true) {

        }
    }

    public void printMenu() {
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
    public void printMemberSearchMenu() {
        System.out.println("1.Search Member By Name");
        System.out.println("2.Search Member By Age");
        System.out.println("3.Search Member By Gender");
    }
    public void printBookSearchMenu() {
        System.out.println("1.Search Book By Name");
        System.out.println("2.Search Book By Author");
        System.out.println("3.Search Book By Price");
        System.out.println("4.Search Book By Borrow Status");
    }
}