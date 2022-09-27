# effective-couscous

### Features
- Allow uers to log-in/register into the DB
- Allow users to connect over a LAN/ Wifi/ common router and use the application to chat with each other
- User Intercation is done on a UI

### Applications
- allows users to chat within the LAN without having to access the internet, and hence with reletively better privacy

### Details of Development
#### Users Schema
![user schema](https://github.com/akankshaSha/effective-couscous/blob/main/image.png)
#### UserDTO  
A DTO (Data Transfer Object) carries data between processes, in this case between views and data base entries. 
While defining a DTO, we will try to write table Schema in form of a class

#### DAO
Data acess object is a pattern used for prsistent mechanism. It provides isolation of databse related activities in our project.
- commonDAO: establishes connection to the database
- userDAO: CRUD operation in users table of the database

#### Socket Programming
socket is a network endpoint that rests with poth, server machine and client machines   
**Server**: a machine that serves a purpose, services of multiuserchatapp is provided by the server    
**Client**: machines that uses the services of the server. In order to run the application, the Client needs to send requests on thne server's ip+ the port number of 
our application  
**Handshaking**: the action of client joining the server  
**Stream**: communication over network is sone in Streams of Bytes  

- ServerSocket(PORT): socket for server
- Socket(SERVER_IP, PORT): socket for client
- serverSocket.accept(): handshaking
