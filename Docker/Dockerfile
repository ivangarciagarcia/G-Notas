FROM mariadb:latest
ADD ddl/ /docker-entrypoint-initdb.d

ENV MARIADB_USER=usuario1
ENV MARIADB_PASSWORD=abc123.
ENV MARIADB_ROOT_PASSWORD=abc123.

EXPOSE 3306
