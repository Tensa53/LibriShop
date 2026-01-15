# LibriShop
LibriShop is a website that simulate an e-commerce where you can buy some books.

## Install LibriShop with Docker
1. Clone this repo;
2. Make sure you have Docker and Docker Compose installed on your machine;
3. From the root folder of the project, run the command: `docker-compose up --build -d`;
4. Remove dangling images with the command: `docker image prune -f`;
5. Open your browser and go to the address: `http://localhost:8080/home`;
6. Enjoy the website.

To stop the containers, run the command: `docker-compose down`;

## Install Librishop for local development
The installation steps refers to a development setup using Intellij IDE, Maven, Apache Tomcat Server and MySQL.
1. Clone this repo;
2. Download Apache Tomcat;
3. Build the war with the command: `mvn clean package`;
4. Define a Run Configuration for Apache Tomcat: 
   1. On the Server Tab:
      2. Specify the Tomcat folder at the Application Server entry;
      3. Unpin the "After Launch" option;
   2. On the Deployment Tab:
      1. Add the first war option for the artifact to be deployed;
      2. Set the Application context to `/`;
5. Make sure to create the database with the sql script of the db folder;
6. Edit the ConPool class with the credentials of your MySQL user;
7. Start the Tomcat server from the Run Configuration through Intellij;
8. Open your browser and go to the address: `http://localhost:8080/home`;
9. Enjoy the website.

To stop the server, just stop the Run Configuration.

This project was realized for the Web Software Technologies class, you can find the project proposal (in italian)
inside this repository

