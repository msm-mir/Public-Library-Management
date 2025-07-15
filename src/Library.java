interface Library {
    void save(Entity entity);
    void update(Entity entity) throws EntityNotFoundException;
    void delete(Entity entity) throws EntityNotFoundException;
    Entity[] find(Entity example);
    void borrow(Member member, Book book);
    void giveBack(Member member, Book book);

    static Member[] members = null;
    static Book[] books = null;
}
