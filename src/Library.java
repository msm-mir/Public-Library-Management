interface Library {
    void save(Entity entity) throws EntityNotFoundException;
    void update(Entity entity, int idx) throws EntityNotFoundException;
    void delete(Entity entity, int idx) throws EntityNotFoundException;
    Entity[] find(Entity example);
    void borrow(Member member, Book book);
    void giveBack(Member member, Book book);
}
