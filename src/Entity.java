public interface Entity {
    int getId();
    void readFromConsole() throws BadEntityException;
    void showOnConsole();
}