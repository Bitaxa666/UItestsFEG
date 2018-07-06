#!/bin/bash
set -e

#Starting dbus daemon and exporting related environmental variables
export $(dbus-launch)

#Starting X server to be able to run firefox
Xvfb :1 -screen 0 1200x800x24 &

# Run tests
DISPLAY=:1.0 mvn clean test