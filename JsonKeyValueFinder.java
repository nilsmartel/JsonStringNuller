import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonKeyValueFinder {
    public static void main(String[] args) {
        String input = "This is a long string containing \"name\": \"hello\" and " +
                       "\"hjdsa\": \"0\" as well as \"kommunikationsersatz\": \"leistungsnachweis\" " +
                       "and maybe some other text.";
        
        // Regex pattern to match JSON-like key-value pairs
        Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(input);
        
        // Find all matches
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}