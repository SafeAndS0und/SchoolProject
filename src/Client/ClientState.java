package Client;

public class ClientState {
    String username;
    String ipAddress;
    String goodAnswers;
    private static ClientState clientState;


    private ClientState(){

    }

    public static ClientState getInstance() {
        if(clientState==null){
            clientState = new ClientState();
            System.out.println("Created ClientState");
        }
        return clientState;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getGoodAnswers() {
        return goodAnswers;
    }

    public void setGoodAnswers(String goodAnswers) {
        this.goodAnswers = goodAnswers;
    }
}
