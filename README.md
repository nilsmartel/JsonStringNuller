# JsonStringNuller

A step below proper fuzzing, I derive (possibly) invalid data from valid data,
in order to find flaws in my api.

Particularly in setting string values to empty string.

## This is deliberatly not a library

These java snippets are ment to be copy+pasted and slightly modified to fit my usecase.
Pooling them in a library would overcomplicate things,
as I expect to come back to these and make slight alterations anyway.

_just copy and paste the function in JFuzzer_

## Run the example

in order to quickly show case what's going on here,
just run the example using

`javac JFuzzer.java && java JFuzzer`
