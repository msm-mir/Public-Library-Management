public class Library {
    public static Member members[] = new Member[100];
    {
        for (int i = 0; i < 100; i++) {
            members[i] = new Member();
        }
    }
    public static Book books[] = new Book[100];
    {
        for (int i = 0; i < 100; i++) {
            books[i] = new Book();
        }
    }
}
