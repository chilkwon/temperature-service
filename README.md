# temperature-service

1. Download all program files and import the project in Intellij.
2. Run and test in Intellij without docker image
3. open the browser and type http://localhost:8080/temperature?lat=43.66258321585993&lng=-79.39152689466948
{"temperature":3400} will be appearing.
4. Use "gradle clean build" to create libs folder and JAR files within the folder
5. see the Dockerfile (I have created the file) format
6. within the application root folder, type "docker build --build-arg JAR_FILE=build/libs/*.jar -t springboot-temperate-docker ." to create docker image file.
7. to run the docker image file, "docker run -p 8080:8080 springboot-temperate-docker"
8. open the browser and do step 3 again.


