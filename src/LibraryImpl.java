import java.util.Objects;

public class LibraryImpl implements Library {
    public static Member[] members = new Member[100];
    static {
        for (int i = 0; i < 100; i++) {
            members[i] = new Member();
        }
    }

    public static Book[] books = new Book[100];
    static {
        for (int i = 0; i < 100; i++) {
            books[i] = new Book();
        }
    }

    private static int memberIdx = 0;
    private static int bookIdx = 0;

    public LibraryImpl() {}

    public static void setMemberIdx(int id) { memberIdx = id; }
    public static int getMemberIdx() { return memberIdx; }
    public static void setBookIdx(int id) { bookIdx = id; }
    public static int getBookIdx() { return bookIdx; }

    @Override
    public void save(Entity entity) throws EntityNotFoundException {
        if (entity instanceof Member) {
            members[memberIdx] = (Member) entity;
            memberIdx++;
        } else if (entity instanceof Book) {
            books[bookIdx] = (Book) entity;
            bookIdx++;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void update(Entity entity, int idx) throws EntityNotFoundException {
        if (entity instanceof Member) {
            members[idx] = (Member) entity;
        } else if (entity instanceof Book) {
            books[idx] = (Book) entity;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Entity entity, int idx) throws EntityNotFoundException {
        if (entity instanceof Member) {
            members[idx] = new Member();
            memberIdx--;
        } else if (entity instanceof Book) {
            books[idx] = new Book();
            bookIdx--;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Entity find(Entity example, String name) {
        if (example instanceof Member) {
            for (int i = 0; i < memberIdx; i++) {
                if (Objects.equals(members[i].getName(), name)) {
                    return members[i];
                }
            }
            return null;
        } else if (example instanceof Book) {
            for (int i = 0; i < bookIdx; i++) {
                if (Objects.equals(books[i].getName(), name)) {
                    return books[i];
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public void borrow(Member member, Book book) {

    }

    @Override
    public void giveBack(Member member, Book book) {

    }
}
