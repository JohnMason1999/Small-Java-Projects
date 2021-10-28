public class Start{
    public static void main(String[] args){
        Server s = new Server("SERVER");
        Thread t = new Thread(s);
        t.start();
        try {
            Thread.sleep(3000);
        } catch(Exception e){
            e.printStackTrace();
        }
        Reflector c = new Reflector("CLIENT");
    }
}