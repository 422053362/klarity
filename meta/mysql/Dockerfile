FROM mariadb:latest

MAINTAINER pengcheng091(pengcheng091@qq.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./meta/mysql/klarity.sql /docker-entrypoint-initdb.d