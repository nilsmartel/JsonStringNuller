import java.util.regex.*;

public class JsonNthValueEmptySetter {
    
    public static String setNthValueToEmpty(String input, int n) {
        // Regex pattern to match JSON-like key-value pairs
        Pattern pattern = Pattern.compile("(\"[^\"]+\"\\s*:\\s*)\"[^\"]*\"");
        Matcher matcher = pattern.matcher(input);
        
        StringBuffer result = new StringBuffer();
        int count = 0;
        
        while (matcher.find()) {
            count++;
            if (count == n) {
                // For the nth match, replace value with empty string
                matcher.appendReplacement(result, "$1\"\"");
            } else {
                // For other matches, keep as-is
                matcher.appendReplacement(result, matcher.group(0));
            }
        }
        matcher.appendTail(result);
        
        return result.toString();
    }

    public static void main(String[] args) {
        String input = "\"first\": \"value1\", \"second\": \"value2\", \"third\": \"value3\", \"fourth\": \"value4\"";
        
        System.out.println("Original: " + input);
        System.out.println("Set 2nd to empty: " + setNthValueToEmpty(input, 2));
        System.out.println("Set 3rd to empty: " + setNthValueToEmpty(input, 3));
    }
}