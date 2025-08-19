import java.util.regex.Pattern;

public class JsonValueEmptySetter {
    public static void main(String[] args) {
        String input = "Some text \"name\": \"hello\" more text \"hjdsa\": \"0\" " +
                      "and \"kommunikationsersatz\": \"leistungsnachweis\" end.";
        
        // Regex pattern to match JSON-like key-value pairs
        String regex = "(\"[^\"]+\"\\s*:\\s*)\"[^\"]*\"";
        
        // Replacement keeps the key part but sets value to empty string
        String replacement = "$1\"\"";
        
        // Perform the replacement
        String modifiedString = input.replaceAll(regex, replacement);
        
        System.out.println("Original string:\n" + input);
        System.out.println("\nModified string:\n" + modifiedString);
    }
}