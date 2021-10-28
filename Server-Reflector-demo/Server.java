import java.util.Random;

public class Server implements Runnable{
    private String name;
    public Server(String name){
        this.name = name;
    }

    public void run(){
        Comms client = new Comms(name);
        if (client.startListening(50000)){
            String challenge = Integer.toString(new Random().nextInt(100000)); //random number limited to 5 digits for readability
            client.sendMessage(challenge);
            System.out.println(name + " sent challenge: "+challenge +", expecting response: "+ShaHash.hash(challenge));
            boolean acceptedClient = false;
            while (!acceptedClient) {
                String response = client.getMessage();
                System.out.println(name+ " received: "+ response);
                if (response.equals(ShaHash.hash(challenge))) {
                    System.out.println(name+" accepted connection");
                    acceptedClient = true;
                } else {
                    System.out.println(name + " received challenge: "+ response + ", sending response: " + ShaHash.hash(response));
                    client.sendMessage(ShaHash.hash(response));
                }
            }
        }
    }
}
