# Setup-test

This repo has a simple Clojure and ClojureScript application. The purpose of this repo is to make sure you have your environment ready for the training.

This project has a lot of dependencies. This simple setup application does not need them, but when you build this the Leiningen build tool will load and cache them to your local hard-drive.

## Running application

```bash
$ git clone https://github.com/metosin/clojure-bootcamp
$ cd clojure-bootcamp
$ cd setup-test
$ lein deps
```

Leiningen shows progress while it downloads half of the internet (the better half) to your HD.

```bash
$ lein ring server
```

This will compile the application, it might take a long time (like 30 sec or so). Don't worry, the normal development cycle is usually less than 1 sec. Once the app is running it should open your browser and show you a greeting message. If that's what you get you, and your machine, are ready for the training. If something did not work as intended, contact us for help.

