public class LibraryImpl implements Library {
    public static Member[] members = new Member[100];
    {
        for (int i = 0; i < 100; i++) {
            members[i] = new Member();
        }
    }

    public static Book[] books = new Book[100];

    public LibraryImpl() {}

    @Override
    public void save(Entity entity) {

    }

    @Override
    public void update(Entity entity) throws EntityNotFoundException {

    }

    @Override
    public void delete(Entity entity) throws EntityNotFoundException {

    }

    @Override
    public Entity[] find(Entity example) {
        return new Entity[0];
    }

    @Override
    public void borrow(Member member, Book book) {

    }

    @Override
    public void giveBack(Member member, Book book) {

    }
}
