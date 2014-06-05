# Workflow

Metosin Clojure-bootcamp training: Workflow

This project introduces an alternative to Lein ring based web development.
Instead you start the http server (jetty, http-kit) at your REPL and
evaluate new code as you write it.

If you do some massive edits and don't remember what files you should evaluate you
can call `(reset)` which uses [tools.namespace](https://github.com/clojure/tools.namespace)-library to reload
all namespaces with changes. Function will also restart db connection and http-server.

This project is not perfect...

- Now that Monger takes db connection as parameter instead of global variable it should be easy to
execute tests using different env which uses test db etc.

## Usage

```
$ lein repl
user=> (reset)
{:http-kit ... :mongo ... :db ...}
user=> (stop)
{}

;; If reset fails because some code had errors, namespace refresh fails
;; and user namespace will be left without reset function...
user=> (reset)
CompilerException java.lang.RuntimeException: Unable to resolve symbol: reset in this context, compiling:(/tmp/form-init7216442876641162636.c):1:1)
;; To fix:
user=> (use 'user)
nil
user=> (reset)
```

## License

Copyright &copy; 2014 Metosin Oy

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
