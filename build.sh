#!/bin/bash

install -d build/bin

javac -d build/bin site/jackl/number/cli/Main.java

cd build/bin
jar cfe ../jnumbernamer.jar site.jackl.number.cli.Main *
