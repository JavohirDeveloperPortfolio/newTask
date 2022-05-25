<br>

## Description:

Server API (JSON HTTP API)

Development tools: Java Framework: Spring boot 

DBMS: Postgresql

&#x20;Protocol: HTTP, port 80 Functionality (requests):

## Sign In

<br>

> <http://localhost:80/api/login>

```
{
    "username":"admin",
    "password":"root"
}
respond ->
{
	"accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiZXhwIjoxNjUzMzk3NzI1fQ.2D9aRINZWIkPDhFesYCFYS3OJcpPpwnvTSQyL1HcPas",
	"refreshToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NTk4ODgyNX0.KKAk6NyKSBNVSimdWUuTA7zdz0-Nn4fP-5cvuXctDxA"
}
```

**SignUp**

> <http://localhost:80/api/user/signUp>

```
{
    "name":"jonson",
    "username":"root",
    "password":"123",
    "email":"haqberdiyev99@bk.ru",
    "phoneNumber":"+998933453368",
    "status":"online",
    "attachmentId":1
}
respond
user id
```

<img src="https://github.com/JavohirDeveloperPortfolio/newTask/blob/master/files/Documentation1.png">

## SignUp with image

> <http://localhost:80/api/user/signUpWithImage>    Method POST

<img src="https://github.com/JavohirDeveloperPortfolio/newTask/blob/master/files/Documentation2.png">

## Upload image

> <http://localhost:80/api/attachment/upload>     Method POST

<img src="https://github.com/JavohirDeveloperPortfolio/newTask/blob/master/files/Documentation3.png">

## Download image

> <http://localhost:80/api/attachment/download/1>    Method GET

```
respond
{
    "id": 1,
    "name": "26e70a59-e960-440e-bb06-37b3c815328b.jpg",
    "originalName": "1.jpg",
    "size": 98979,
    "contentType": "image/jpeg"
}
```

## Swagger URL

> <http://localhost:80/swagger-ui/index.html>

## User information

> [http://localhost:80/api/user/get/](http://localhost:80/api/user/get/3){id}   Method GET (ROLE\_ADMIN,ROLEMANAGER)

```
{
    "id": 3,
    "name": "Test",
    "username": "Johnson",
    "password": "$2a$10$wFXZraHzZdU1bMJGNv.m4OFyEM8WGZy7sQIlHsHwkFvYd/j2tICqC",
    "email": "hjavohirir@gmail.com",
    "phoneNumber": "+998993453368",
    "status": "online",
    "createdDate": null,
    "updatedDate": null,
    "roles": [
        {
            "id": 1,
            "name": "ROLE_USER"
        }
    ],
    "attachment": {
        "id": 1,
        "name": "26e70a59-e960-440e-bb06-37b3c815328b.jpg",
        "originalName": "1.jpg",
        "size": 98979,
        "contentType": "image/jpeg"
    },
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "accountNonLocked": true
}
```

## Status edit

> <http://localhost:80/api/user/editStatus>     Method POST

```
request
{
    "id":3,
    "status":"offline"
}
respond
{
    "userId": 3,
    "newStatus": "offline",
    "oldStatus": "online"
}
```

## Statistics

> <http://localhost:80/api/user/statistics>   Method GET

```
respond
{
    "countOnline": 2,
    "countOffline": 2,
    "countUndefined": 1
}
```

<img src="https://github.com/JavohirDeveloperPortfolio/newTask/blob/master/files/Documentation4.png">

<br>
