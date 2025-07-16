public interface Entity {
    int getID();
    void readFromConsole() throws BadEntityException;
    void showOnConsole();
}