# Intruduction to ClojureScript

Metosin Clojure-bootcamp training: ClojureScript and Core.Async

### Running

```
lein repl
cljs-core-async.server.server=> (run)
```

`lein cljsbuild auto`

### Publish at Heroku

```
# Add heroku git remote
git remote add heroku-cljs git@heroku.com:<your heroku repo>.git
# Push `cljs-core-async` directory to heroku remote
git subtree push --prefix cljs-core-async heroku-cljs master
```

## License

Copyright &copy; 2014 Metosin Oy

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
