#!/bin/sh

set -x

sleep 5

curl http://game-server/join/${USERNAME}

while true; do
    CHOICE=$((1 + RANDOM % 4))

    if [ "$CHOICE" -eq "1" ]; then
       curl http://game-server/move/${USERNAME}/up
    fi
    if [ "$CHOICE" -eq "2" ]; then
       curl http://game-server/move/${USERNAME}/down
    fi
    if [ "$CHOICE" -eq "3" ]; then
       curl http://game-server/move/${USERNAME}/left
    fi
    if [ "$CHOICE" -eq "4" ]; then
       curl http://game-server/move/${USERNAME}/right
    fi

    sleep 0.3
done
