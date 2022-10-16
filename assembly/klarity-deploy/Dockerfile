FROM vincentzczhang/alpine-jdk-1.8:latest

MAINTAINER opgames(opgames.cn@gmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/klarity

WORKDIR /opt/projects/klarity

EXPOSE 8086

ADD ./assembly/klarity-deploy/target/klarity-server.jar ./app.jar

CMD java -jar -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=docker app.jar