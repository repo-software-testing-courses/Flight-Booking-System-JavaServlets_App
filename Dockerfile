FROM tomcat:8.5-jdk8

ENV JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms512m -Xmx1024m"

RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*

WORKDIR /app

RUN mkdir -p build/web/WEB-INF/classes build/web/WEB-INF/lib

COPY Project/TurkishAirlines/Libs/*.jar build/web/WEB-INF/lib/

RUN wget -O build/web/WEB-INF/lib/servlet-api.jar https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar

RUN wget -O build/web/WEB-INF/lib/webservices-rt.jar https://repo1.maven.org/maven2/org/glassfish/metro/webservices-rt/2.4.10/webservices-rt-2.4.10.jar

COPY Project/TurkishAirlines/src/java/ src/

COPY Project/TurkishAirlines/web/ web/

COPY Project/TurkishAirlines/src/java/airlines.accdb build/web/WEB-INF/classes/

RUN javac -cp "build/web/WEB-INF/lib/*" -d build/web/WEB-INF/classes src/*.java src/*/*.java

RUN cp -r web/* build/web/

COPY tomcat-users.xml $CATALINA_HOME/conf/

RUN mkdir -p $CATALINA_HOME/webapps/TurkishAirlines

RUN cp -r build/web/* $CATALINA_HOME/webapps/TurkishAirlines/

EXPOSE 8080

CMD ["catalina.sh", "run"]
