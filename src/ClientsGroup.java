public class ClientsGroup {
    private final int size; // number of clients

    public ClientsGroup(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ClientsGroup{" +
                "size=" + size +
                '}';
    }
}