#
docker build --build-arg CACHEBUST=$(date +%s) -t signos-app .
#
docker run -d -p 8084:8084 -p 9092:9092 --name signos-container signos-app
