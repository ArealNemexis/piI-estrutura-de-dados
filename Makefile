stop:
  docker stop rabbitmq;docker stop pi_es_db_1

remove:
  docker rm rabbitmq;docker rm pi_es_db_1

start:
  docker-compose build --no-cache;docker-compose up -d


general:
  docker stop rabbitmq;docker stop pi_es_db_1;docker rm rabbitmq;docker rm pi_es_db_1;docker-compose build --no-cache;docker-compose up -d