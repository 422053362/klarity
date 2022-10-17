##请在 jdk8 环境下编译
mvn clean package install -Dmaven.test.skip=true --settings setttings.xml

docker-compose build

docker-compose up