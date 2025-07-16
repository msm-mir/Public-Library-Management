public interface Entity {
    int getIdManager();
    void readFromConsole() throws BadEntityException;
    void showOnConsole();
}