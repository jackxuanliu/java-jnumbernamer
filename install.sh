#!/usr/bin/env bash

#Run as `sudo` to properly install this application.

if [ `whoami` != 'root' ]; then
  echo "Please try again as root. (i.e. `sudo ./install.sh`)"
  exit
fi

install -d /usr/share/java/jnumbernamer
install -m644 build/jnumbernamer.jar /usr/share/java/jnumbernamer/
install -m755 res/java-jnumbernamer /usr/bin/
