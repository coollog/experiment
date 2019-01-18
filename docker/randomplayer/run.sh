#!/bin/sh

set -x

while true; do
    CHOICE=$((1 + RANDOM % 4))

    if [ "$CHOICE" -eq "1" ]; then
       curl http://game-server/move/me/up
    fi
    if [ "$CHOICE" -eq "2" ]; then
       curl http://game-server/move/me/down
    fi
    if [ "$CHOICE" -eq "3" ]; then
       curl http://game-server/move/me/left
    fi
    if [ "$CHOICE" -eq "4" ]; then
       curl http://game-server/move/me/right
    fi

    sleep 0.3
done
