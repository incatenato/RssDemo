FROM java:8

RUN mkdir /docker
WORKDIR /docker

ADD build/libs/event-0.0.1-SNAPSHOT.jar /docker
RUN ln -sf event-0.0.1-SNAPSHOT.jar event.jar

CMD java -jar -Dspring.prifiles.active=docker event.jar