public interface Entity {
    int getId();
    Object readFromConsole(Object object) throws BadEntityException;
    void showOnConsole(String output);
}