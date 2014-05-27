# Setup-test

This repo has a simple Clojure and ClojureScript application. The purpose of this repo is to make sure you have your environment ready for the training.

This project has a lot of dependencies. This simple setup application does not need them, but when you build this the Leiningen build tool will load and cache them to your local hard-drive.

## Prerequisites

You need:

# JDK 1.6 _(or later)_: http://java.oracle.com
# Leiningen 2.3 _(or later)_: http://leiningen.org

Check:

```bash:
$ java -version
java version "1.8.0_05"
Java(TM) SE Runtime Environment (build 1.8.0_05-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.5-b02, mixed mode)
$ lein -version
Leiningen 2.3.4 on Java 1.8.0_05 Java HotSpot(TM) 64-Bit Server VM
```

## Running application

```bash
$ git clone https://github.com/metosin/clojure-bootcamp
$ cd clojure-bootcamp
$ cd setup-test
$ lein deps
```

Leiningen shows how it downloads half of the internet (the better half) to your HD.

```bash
$ lein do cljsbuild once, ring server
```

This will compile the application, it might take a long time (like 30 sec or so). Don't worry, the normal development cycle is usually less than 1 sec. Once the app is running it should open your browser and show you a greeting message. If that's what you get you, and your machine, are ready for the training. If something did not work as intended, contact us for help.
