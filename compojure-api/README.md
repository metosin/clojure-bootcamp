# compojure-api

Metosin Clojure-bootcamp training: Intruduction to Compojure-api

## Usage

### Running

`lein ring server`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

### Publish at Heroku

```
# Add heroku git remote
git remote add heroku-capi git@heroku.com:<your heroku repo>.git
# Push `compojure-api` directory to heroku remote
git subtree push --prefix compojure-api heroku-capi master
```

## License

Copyright &copy; 2014 Metosin Oy

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
