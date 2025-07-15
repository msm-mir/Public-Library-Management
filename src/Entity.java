public interface Entity {
    Integer getID();
    void readFromConsole() throws BadEntityException;
    void showOnConsole();
}
