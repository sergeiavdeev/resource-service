
mvn clean package -Dmaven.test.skip=true
docker ps -aq --filter "name=resource-service" | xargs -r docker rm -f
docker system prune -a -f
docker build -t sergeiavdeev/resource-service:1-tst .
PROFILE=dev TAG=1-tst docker compose up -d