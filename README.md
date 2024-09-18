# LibriShop
LibriShop is a website that simulate an e-commerce where you can buy some books.

## Install Librishop
The installation steps refers to a development setup using Intellij IDE, Apache Tomcat Server and MySQL.
1. Clone this repo;
2. Download Apache Tomcat;
3. Define a Run Configuration for Apache Tomcat: 
   1. On the Server Tab, specify the Tomcat folder at the Application Server entry;
   2. On the Deployment Tab, add the war option for the artifact to be deployed;
4. Make sure to create the database with the sql script of the db folder;
5. Edit the ConPool class with the credentials of your MySQL user;
6. Run the server through the new defined configuration (it will automatically open the default browser web);
7. Enjoy the website.

This project was realized for the Web Software Technologies class, you can find the project proposal (in italian)
inside this repository

