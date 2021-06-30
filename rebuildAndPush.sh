SERVICE=${PWD##*/}

# delete existing containers/images.
docker rmi $(docker images -qa 'sachingoyaldocker/baat-org-'$SERVICE)
echo ">>> Existing Images/Containers Deleted"

# build service
./gradlew clean build
echo ">>> Service Jar Built"

docker build --no-cache -t sachingoyaldocker/baat-org-$SERVICE:1.0 --build-arg SERVICE=$SERVICE .
echo ">>> New Docker Image Built"

docker push sachingoyaldocker/baat-org-$SERVICE:1.0
echo ">>> New Docker Image Pushed to Docker Hub"
