CIE:
10.10.31.208:5152   debug:45152 tomcat_car_cds
SIT:
10.10.32.84:10590 debug:40590 tomcat_car_cds
域名：public-api.cds.car.tuniu-sit.org


#!/bin/sh options when start tomcat
JAVA_HOME=/opt/tuniu/jdk18
JRE_HOME=/opt/tuniu/jdk18/jre
CATALINA_OPTS='-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=81 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=$TARDIS_CONTAINER_ADDR'
JAVA_OPTS="$JAVA_OPTS -Duser.timezone=GMT+8 -Dfile.encoding=UTF8 -Dsun.jnu.encoding=UTF8  -XX:PermSize=256M -XX:MaxPermSize=512m -Xms1536m -Xmx2048m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/tuniu/logs/tomcat/sys/tomcat_car_cds/"
CATALINA_OPTS="$CATALINA_OPTS -Dddg.sys.name=$DDG_NAME -Dddg.service.url=$DDG_URL -DenvName=$envName"
if [ $XDEBUG = on ];then
   CATALINA_OPTS="$CATALINA_OPTS -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=82"
fi
