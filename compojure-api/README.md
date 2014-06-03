# compojure-api

FIXME

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

### Publishing on heroku

```
# Add heroku git remote
git remote add heroku-capi git@heroku.com:<your heroku repo>.git
# Push `compojure-api` directory to heroku remote
git subtree push --prefix compojure-api heroku-capi master
```

## License

Copyright © 2014 FIXME
