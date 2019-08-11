docker rmi $(docker images -qa 'sachingoyaldocker/baat-org-user')

./gradlew clean build bootJar
docker build --no-cache -t sachingoyaldocker/baat-org-user:1.0 . 
docker push sachingoyaldocker/baat-org-user:1.0
