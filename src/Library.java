interface Library {
    void save(Entity entity);
    void update(Entity entity, int idx) throws EntityNotFoundException;
    void delete(Entity entity, int idx) throws EntityNotFoundException;
    Entity find(Entity example, String name);
    void borrow(Member member, Book book, int idx);
    boolean giveBack(Member member, Book book);
}
