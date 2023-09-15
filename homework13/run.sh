mvn clean package

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

TARGET_DIR="$SCRIPT_DIR/target"

TOMCAT_DIR="$SCRIPT_DIR/apache-tomcat-10.1.13"

TOMCAT_BIN_DIR="$TOMCAT_DIR/bin"

WAR_FILE="$TARGET_DIR/servlet_hw13.war"
if [ -f "$WAR_FILE" ]; then
    mv "$WAR_FILE" "$TOMCAT_DIR/webapps/"
else
    echo "File servlet_hw13.war is not in directory target."
    exit 1
fi

TOMCAT_CMD="./startup.sh"
if [[ "$OSTYPE" == "linux-gnu"* || "$OSTYPE" == "darwin"* ]]; then
    $TOMCAT_CMD
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "win32" ]]; then
    cd "$TOMCAT_BIN_DIR"
    $TOMCAT_CMD
else
    echo "Incorrect ОС: $OSTYPE"
    exit 1
fi