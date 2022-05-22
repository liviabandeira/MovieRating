# How to run
## Docker
> Go into the project directory and run `docker-compose up`. The container will be running on port 8080 and the database is reacheable on port 3306.
> 
> The database is currently starting with a empty database called movie-rating, and the csv import process should be performed manually.

## Maven
> Generate a fatJar using `mvn clean package`. After that, inside the `target` folder, you will find the fatJar.
> 
> Remember to change the database name, ip and the password in the `application.properties` file.
> 
> Run the fatJar with `java -jar movie-rating-0.0.1-SNAPSHOT.jar`.