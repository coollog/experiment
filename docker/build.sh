#! /bin/bash

set -x

docker build -t gcr.io/qingyangc-sandbox/kubemmo/player:random randomplayer
docker push gcr.io/qingyangc-sandbox/kubemmo/player:random
