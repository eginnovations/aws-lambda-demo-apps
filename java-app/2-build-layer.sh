#!/bin/bash
set -eo pipefail
gradle -q packageLibs
mv build/distributions/java-app.zip build/java-serverless-lib.zip