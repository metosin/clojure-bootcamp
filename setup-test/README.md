# Setup-test

This repo has a simple Clojure and ClojureScript application. The purpose of this repo is to make sure you have your environment ready for the training.

This project has a lot of dependencies. This simple setup application does not need them, but when you build this the Leiningen build tool will load and cache them to your local hard-drive.

## Prerequisites

You need:

* JDK 1.6 _(or later)_: http://java.oracle.com
* Leiningen 2.3.4 _(or later)_: http://leiningen.org

## Running application

```bash
git clone https://github.com/metosin/clojure-bootcamp
cd clojure-bootcamp
cd setup-test
lein deps
```

Leiningen shows how it downloads half of the internet (the better half) to your HD.

```bash
lein do cljsbuild once, ring server
```

This will compile the application, it might take a long time (like 30 sec or so). Don't worry, the normal development cycle is usually less than 1 sec. Once the app is running it should open your browser and show you a greeting message. If that's what you get you, and your machine, are ready for the training. If something did not work as intended, contact us for help.

You can stop the application with ```ctrl-c```.

## Setup your IDE

### General

The development process for Clojure is radically different from Java development process. Likewise, the features that you'll want from your IDE are also defferent.

The important features that you should look for in Clojure IDE are:

* REPL, the IDE should allow access to project REPL
* Evaluating from source, you should be able to select part of source code and send it to REPL for evaluation

### Eclipse

While you are in the ```setup-test``` folder, enter the following command:

```bash
lein eclipse
```

This command creates the ```.project``` and ```.classpath``` files for Eclipse.

Clojure plugin for Eclipse is Counterclockwise. Follow the instructions at http://doc.ccw-ide.org to install Counterclockwise plugin to your Eclipse. Once you have Counterclockwise installed you can continue.

In Eclipse, choose ```File``` -> ```Import...``` -> ```Existing projects into Workspace``` -> ```Next >``` -> Select the ```setup-test``` directory -> ```Finish```

The project is now opened as a *regular Clojure project* (similar to basic Java project). Since we are using Leiningen to manage our dependencies we need to convert this to Leiningen project (similar to Maven project). Right click the imported project and choose ```Configure``` -> ```Convert to Leiningen project```.

In the project, open the ```src/clj``` -> ```setup``` -> ```server.clj``` file, right click on the source file and ```Clojure``` -> ```Load file in REPL```. Eclipse now starts JVM, starts the Clojure REPL in it, and loads the ```server.clj``` file into it. You should now have the REPL window open. Type into it ```(run)``` and hit enter. You should see a message ```"App running..."```, the application is now ready and listening HTTP trafic on port 8080. Open your browser to http://localhost:8080/.

### IntelliJ IDEA with Cursive

IDEA has two Clojure plugins, the old La Clojure and the new and shiny Cursive. Follow the instuctions for installing Cursive in here https://cursiveclojure.com/userguide/

Import the project as decribed in this page https://cursiveclojure.com/userguide/leiningen.html

### Emacs

See https://github.com/clojure-emacs/cider

### Others

See at http://dev.clojure.org/display/doc/IDEs+and+Editors
