#!/bin/bash

# Get the absolute path of the directory where this script is located
BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"

# Set the path to your specific JVM folder
export JAVA_HOME="$BASE_DIR/jvm/jdk-25.0.1+8"

# Add local bin and local java to the temporary PATH
export PATH="$BASE_DIR/bin:$JAVA_HOME/bin:$PATH"

echo "---------------------------------------"
echo "Scala Portable Environment Loaded (Linux)!"
echo "---------------------------------------"
echo "Java Home: $JAVA_HOME"
echo ""

# Verify Java is found
java -version
echo ""

# Verify Scala-CLI is found
scala-cli --version
echo "---------------------------------------"

# Start a new sub-shell to keep the environment active (equivalent to cmd /k)
exec $SHELL