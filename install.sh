#!/usr/bin/env bash

#Run as `sudo` to properly install this application.

if [ "$EUID" -ne 0 ]; then
  printf "Please run again as root. (i.e. sudo \'./install.sh\')"
  exit
fi

install -d /usr/share/java/jnumbernamer
install -m644 build/jnumbernamer.jar /usr/share/java/jnumbernamer/
install -m755 res/java-jnumbernamer /usr/bin/
