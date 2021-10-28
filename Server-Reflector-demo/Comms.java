import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Comms {
    private ServerSocket server;
    private Socket receiver;
    private PrintWriter pw;
    private BufferedReader br;
    private String name;

    public Comms(String name){
        this.name = name;
    }

    public boolean startListening(int port){
        try {
            server = new ServerSocket(port);
            System.out.println(name + " is starting as a server");
            receiver = server.accept();
            System.out.println(name + " has made a connection");
            server.close();
            pw = new PrintWriter(receiver.getOutputStream(),true);
            br = new BufferedReader(new InputStreamReader(receiver.getInputStream()));
            return true;
        } catch (IOException e){
            System.out.println("failed to create server socket for "+port);
            e.printStackTrace();
            return false;
        }
    }

    public boolean connectToReceiver(int port){
        try{
            receiver = new Socket(InetAddress.getLocalHost(),port);
            pw = new PrintWriter(receiver.getOutputStream(),true);
            br = new BufferedReader(new InputStreamReader(receiver.getInputStream()));
            return true;
        } catch(Exception e){
            System.out.println("failed to connect to client: "+port);
            e.printStackTrace();
            return false;
        }
    }

    public String getMessage(){
        if (receiver == null){
            return "not connected to receiver";
        } else {
            try {
                //System.out.println(name+ " is waiting for a message");
                String msg =  br.readLine();
                //System.out.println(name + " received message: "+msg);
                return msg;
            } catch(IOException e){
                return "error";
            }
        }
    }

    public boolean sendMessage(String msg){
        if (receiver == null){
            return false;
        } else {
            pw.println(msg);
            //System.out.println(name+ " is sending: " + msg);
            return true;
        }
    }
}