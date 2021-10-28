public class Reflector{
    public Reflector(String name){
        Comms server = new Comms(name);
        System.out.println(name+ " connecting to server");
        server.connectToReceiver(50000);
        String challenge = server.getMessage();
        System.out.println(name+" received challenge: " + challenge);
        server.sendMessage(challenge);
        System.out.println(name+" sent challenge: " + challenge);
        String response = server.getMessage();
        System.out.println(name + " received response: "+ response);
        server.sendMessage(response);
        System.out.println(name+" sent response:" + response);
    }
}