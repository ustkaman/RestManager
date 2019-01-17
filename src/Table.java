import java.util.ArrayList;
import java.util.List;

public class Table implements Comparable<Table> {
    private final int size; // number of chairs
    private List<ClientsGroup> clientsGroupList; // clients group at the table

    public Table(int size) {
        this.size = size;
        this.clientsGroupList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Table{ size=" + size + " " + clientsGroupList + '}';
    }

    public int getSize() {
        return size;
    }

    public List<ClientsGroup> getClientsGroupList() {
        return clientsGroupList;
    }

    public void addClientsGroup(ClientsGroup clientsGroup) {
        clientsGroupList.add(clientsGroup);
        System.out.println("Clients group " + clientsGroup + " seats at the table " + this);
    }

    @Override
    public int compareTo(Table o) {
        boolean isFreeTableO = o.getSize() - o.getFreeChairs() == 0;
        boolean isFreeTable = getSize() - getFreeChairs() == 0;

        if(getFreeChairs() == 0)
            return -1;
        else if(isFreeTable && !isFreeTableO)
            return -1;
        else if(!isFreeTable && isFreeTableO)
            return 1;
        else if(isFreeTable && isFreeTableO)
            return getSize() - o.getSize();
        else if(!isFreeTable && !isFreeTableO)
            return getFreeChairs() - o.getFreeChairs();
        else
            return -1;
    }

    public int getFreeChairs() {
        int freeChairs = getSize();

        for (ClientsGroup clientsGroup : getClientsGroupList())
            freeChairs -= clientsGroup.getSize();

        return freeChairs;
    }
}