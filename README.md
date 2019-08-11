# user
User Service

```
export SERVICE=user &&
export VERSION=1.0 &&

./gradlew clean build bootJar &&

docker build --no-cache -t sachingoyaldocker/baat-org-${SERVICE}:${VERSION} . && 

docker push sachingoyaldocker/baat-org-${SERVICE}:${VERSION}
```
