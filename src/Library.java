interface Library {
    void save(Entity entity);
    void update(Entity entity) throws EntityNotFoundException;
    void delete(Entity entity) throws EntityNotFoundException;
    Entity[] find(Entity example);
    void borrow(Member member, Book book);
    void giveBack(Member member, Book book);

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
