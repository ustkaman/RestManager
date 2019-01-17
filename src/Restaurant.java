import java.util.*;

public class Restaurant {
    static final int TABLE_SIZE_MIN = 2;
    static final int TABLE_SIZE_MAX = 6;
    static final int CLIENTS_GROUP_SIZE_MIN = 1;
    static final int CLIENTS_GROUP_SIZE_MAX = 6;

    final static int TABLES_NUM = 8;
    static final int START_QUEUE_SIZE = 4;

    public static void main(String[] args) {
        RestManager restManager = new RestManager(tablesListGenerate(TABLES_NUM), clientsGroupQueueGenerate(START_QUEUE_SIZE));

        restManager.queueProcessing();

        Scanner scanner = new Scanner(System.in);
        boolean isNotQuit = true;
        while (isNotQuit) {
            System.out.println("1 - show tables and queue \n"
                    + "2 - show tables with clients \n"
                    + "3 - add new clients group\n"
                    + "4 - remove clients group from table\n"
                    + "5 - remove clients group from queue\n"
                    + "6 - quit\n");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println(restManager);
                    break;
                case 2:
                    System.out.println(restManager.getTablesWithClients());
                    break;
                case 3:
                    restManager.onArrive(new ClientsGroup(getRandomClientsGroupSize()));
                    break;
                case 4:
                    restManager.onLeave(getRandomClientsGroupWithTable(restManager.getTablesWithClients()));
                    break;
                case 5:
                    restManager.onLeave(getRandomClientsGroupInQueue(restManager.getQueue()));
                    break;
                case 6:
                    isNotQuit = false;
                    break;
                default:
                    System.out.println("Please select 1-5");
            }
        }
    }

    public static List<Table> tablesListGenerate(int numTables) {
        List<Table> ret = new ArrayList<>();
        for (int i = 0; i < numTables; i++)
            ret.add(new Table(getRandomTableSize()));

        return ret;
    }

    public static List<ClientsGroup> clientsGroupQueueGenerate(int numClientsGroup) {
        List<ClientsGroup> ret = new ArrayList<>();
        for (int i = 0; i < numClientsGroup; i++)
            ret.add(new ClientsGroup(getRandomClientsGroupSize()));
        return ret;
    }

    public static int getRandomTableSize() {
        return new Random().nextInt(TABLE_SIZE_MAX - TABLE_SIZE_MIN + 1) + TABLE_SIZE_MIN;
    }

    public static int getRandomClientsGroupSize() {
        return new Random().nextInt(CLIENTS_GROUP_SIZE_MAX - CLIENTS_GROUP_SIZE_MIN + 1) + CLIENTS_GROUP_SIZE_MIN;
    }

    public static ClientsGroup getRandomClientsGroupWithTable(List<Table> tablesWithClients) {

        if(tablesWithClients != null && tablesWithClients.size() > 0)
        {
            Table table = tablesWithClients.get(new Random().nextInt(tablesWithClients.size()));
            if (table.getClientsGroupList() != null && table.getClientsGroupList().size() > 0)
                return table.getClientsGroupList().get(new Random().nextInt(table.getClientsGroupList().size()));
        }
        return null;
    }

    public static ClientsGroup getRandomClientsGroupInQueue(List<ClientsGroup> queue) {
        return (queue != null && queue.size()>0) ? queue.get(new Random().nextInt(queue.size())) : null;
    }
}
