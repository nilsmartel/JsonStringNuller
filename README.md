# JsonStringNuller

A step below proper fuzzing, I derive (possibly) invalid data from valid data,
in order to find flaws in my api.

Particularly in setting string values to empty string.

## This is deliberatly not a library

These java snippets are ment to be copy+pasted and slightly modified to fit my usecase.
Pooling them in a library would overcomplicate things,
as I expect to come back to these and make slight alterations anyway.

_just copy and paste the function in JFuzzer_

For easy of use I've just linked it here as well:

```java
    public static String setNthStringValue(String input, int n, boolean setToNull) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        // that only matches string values in json like "key": "value"
        Pattern pattern = Pattern.compile("(\"[^\"]+\"\\s*:\\s*)(\"[^\"]*\")");
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        int count = 0;

        while (matcher.find()) {
            if (count == n) {
                String replacement = setToNull ? matcher.group(1) + "null" : matcher.group(1) + "\"\"";
                matcher.appendReplacement(result, replacement);
            } else {
                matcher.appendReplacement(result, matcher.group(0));
            }

            count++;
        }

        if (count <= n) {
            throw new IllegalArgumentException("n to large for input data");
        }

        matcher.appendTail(result);

        return result.toString();
    }

```

## Run the example

in order to quickly show case what's going on here,
just run the example using

`javac JFuzzer.java && java JFuzzer`
