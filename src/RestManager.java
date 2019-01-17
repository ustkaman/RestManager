import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RestManager {
    private List<Table> tables;
    private List<ClientsGroup> queue;

    public RestManager(List<Table> tables, List<ClientsGroup> queue) {
        this.tables = tables;
        this.queue = queue;
    }

    public List<ClientsGroup> getQueue() {
        return queue;
    }

    // new client(s) show up
    public void onArrive(ClientsGroup group) {
        // TODO
        Table lookupTable = lookup(group);

        if (lookupTable != null) {
            lookupTable.addClientsGroup(group);
            Collections.sort(tables);
            queue.remove(group);
        } else {
            queue.add(group);
            System.out.println("Clients group " + group + " is in queue");
        }
    }

    // client(s) leave, either served or simply abandoning the queue
    public void onLeave(ClientsGroup group) {
        // TODO
        if(group != null && queue.contains(group)) {
            queue.remove(group);
            System.out.println("Clients group " + group + " has removed from queue");
        }
        else {
            for (Table table : tables) {
                if(table.getClientsGroupList() != null && table.getClientsGroupList().contains(group)) {
                    table.getClientsGroupList().remove(group);
                    System.out.println("Clients group " + group + " has removed from " + table);
                }
            }
            queueProcessing();
        }
    }

    // return table where a given client group is seated,
    // or null if it is still queuing or has already left
    public Table lookup(ClientsGroup group) {
        // TODO
        for (Table table : tables) {
            if (group.getSize() <= table.getFreeChairs())
                return table;
        }
        return null;
    }

    public void queueProcessing() {
        Collections.sort(tables);
        Iterator<ClientsGroup> it = queue.iterator();
        while (it.hasNext()) {
            ClientsGroup group = it.next();

            Table lookupTable = lookup(group);
            if (lookupTable != null) {
                lookupTable.addClientsGroup(group);
                Collections.sort(tables);
                it.remove();
            }
        }
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "Tables:\n";
        for (Table table : tables)
            ret += table + "\n";

        ret += "Queue:\n";
        for (ClientsGroup q : queue)
            ret += q + "\n";

        return ret;
    }

    public List<Table> getTablesWithClients() {
        List<Table> ret = new ArrayList<>();
        for (Table table : tables)
        {
            if(table.getClientsGroupList() != null && table.getClientsGroupList().size()>0)
                ret.add(table);
        }
        return ret;
    }
}