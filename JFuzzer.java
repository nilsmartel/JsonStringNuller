import java.util.regex.Pattern;
import java.util.regex.Matcher;

class JFuzzer {
    public static void main(String[] args) {
        String input = """
                {
                    "hello": 7,
                    "name": "Peter",
                    "age": 23,
                    "parents: ["Nadine", "Mike"],
                    "Nickname": "Petrölium"
                }
                """;

        System.out.println("input");
        System.out.println(input);

        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("" + i);
                System.out.println(setNthStringValue(input, i, false));
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }

    public static String setNthStringValue(String input, int n, boolean setToNull) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive (1-based index)");
        }

        // that only matches string values in json like "key": "value"
        Pattern pattern = Pattern.compile("(\"[^\"]+\"\\s*:\\s*)(\"[^\"]*\")");
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        int count = 0;

        while (matcher.find()) {
            count++;
            if (count == n) {
                String replacement = setToNull ? matcher.group(1) + "null" : matcher.group(1) + "\"\"";
                matcher.appendReplacement(result, replacement);
            } else {
                matcher.appendReplacement(result, matcher.group(0));
            }
        }

        if (count < n) {
            throw new IllegalArgumentException("n to large for input data");
        }

        matcher.appendTail(result);

        return result.toString();
    }
}
