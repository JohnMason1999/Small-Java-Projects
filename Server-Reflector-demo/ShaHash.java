import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class ShaHash {
    public static String hash(String input){
        try {
            String result = Arrays.toString(MessageDigest.getInstance("SHA-256").digest(input.getBytes(StandardCharsets.UTF_8)));
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
