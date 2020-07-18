**ARCHIVED** this is part of my older code tools - see https://kornysietsma.github.io/polyglot-tools-docs/ for latest stuff.

# pruneflare

A Clojure library designed to prune child paths from a flare format json file

This is one component in the tools I use to produce my [toxic code explorer visualisation](https://github.com/kornysietsma/toxic-code-explorer-demo)

It's probably like 2 lines of ruby or python, but all the other tools are in
clojure so I stuck to it.  Actually you could reimplement this with [jq](https://stedolan.github.io/jq/)
and you wouldn't even need a program.  This exercise is left to the reader.

## Usage
The easiest way to run this without needing clojure is to use an uberjar -
a bundled jar file of the program and all dependencies (including clojure).

You can download a `pruneflare.jar` file from this project's releases page
at https://github.com/kornysietsma/pruneflare/releases

Then run `java -jar pruneflare.jar -h` for usage:

```
Usage: java -jar pruneflare.jar [options] [list of directory names to remove]

Options:
  -i, --input filename   select an input flare-format json file - if you don't specify one, the program will try to read flare data from standard input for piping
  -o, --output filename  select an output file name (default is STDOUT)
  -h, --help
```

The list of directory names is (for now) simple case-sensitive exact matched to directories or files
so, for example, `java -jar prune2flare -i orig.json -o result.json vendor tmp junk`
will remove any `vendor` `tmp` or `junk` directories and their kids.

## License

Copyright © 2018 Kornelis Sietsma

Licensed under the Apache License, Version 2.0 - see LICENSE.txt for details
