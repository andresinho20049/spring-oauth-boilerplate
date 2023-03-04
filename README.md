# Spring Oauth2 - Boilerplate

[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/andresinho20049/spring-oauth-boilerplate/blob/master/README.pt-br.md)

### Rest API with Oauth2 authentication and documented using Swagger
This Spring boot project was developed with the default authentication settings in mind and documentation to serve as a basis for developing other projects.

About:
 - Role-based access control with JWT
 - technology used
    - Java 8
    - Spring Boot 2.7.0
    - Spring JPA latest
    - Spring Security 5.6.4     
    (I prefer to use the implementation with WebSecurityConfigurerAdapter as it is better to configure than the new implementation of SecurityFilterChain)
    - Spring Secutiry Oauth2 Autoconfigure 2.1.5
    - Springfox (Swagger) 3.0.0
    - H2 Database - test basis
    - Lombok - [Help setting up lombok](https://projectlombok.org/setup/eclipse)
    - Log4J
    - Project Maven
- Application.properties - Default
    - Port: 5000
    - Profile: dev
    - Base path: /api
    - Encrypt: bcrypt
    - Hibernate DDL: update
- ExceptionHandler
    - ProjectException:
        - Status: 400
        - Description: Exception provoked, n reasons, but mainly business rule
    - AuthorizationException:
        - Status: 403
        - Description: Access Denied with RuntimeException

## Getting Started
1. Git clone project
 ```git
    git clone https://github.com/andresinho20049/spring-oauth-boilerplate
 ```

2. Inside the project folder, run
```mvn
    mvn clean && mvn install package
```

> In this example I will consider using Eclipse, but just adapt to your use, ok.

3. This project uses lombok, if you haven't already installed this plugin in your eclipse, click:
Help > Install new software...
In the work with field, paste: https://projectlombok.org/p2
Check lombok and then finish

4. Optional step: Click Project > Update maven project > Ok

5. Press start, if you prefer, paste this command in your terminal
```mvn
mvn package spring-boot:run 
```

### Authentication
In initialUser of both profiles, it is configured to register a test user/rules

_The default is_:     
Username: admin@email.com   
Password: password@1234

With the project running, visit the Swagger page to test the endpoints
  - In the browser type the path: /swagger-ui/index.html
  - or If you prefer [click here](http://localhost:5000/api/swagger-ui/index.html)


## Model
### User
```json
{
  "id": 1,
  "name": "Admin",
  "email": "admin@email.com",
  "password": "****"
  "updatePassword": true,
  "active": true,
  "roles": [
    {
      "name": "ROLE_ADMIN"
    }
  ]
}
```

### Role
```json
[
  {
    "name": "ROLE_ADMIN"
  },
  {
    "name": "ROLE_VIEW_USER"
  },
  {
    "name": "ROLE_CREATE_USER"
  },
  {
    "name": "ROLE_UPDATE_USER"
  },
  {
    "name": "ROLE_DISABLE_USER"
  }
]
```

### JWT Payload Example
```json
{
  "aud": [
    "restservice"
  ],
  "updatePassword": true,
  "user_name": "admin@email.com",
  "scope": [
    "all"
  ],
  "name": "Admin",
  "exp": 1655404387,
  "authorities": [
    "ROLE_ADMIN"
  ],
  "jti": "7d12042b-856b-41a7-b1d9-6acb220840a7",
  "client_id": "52da334b25d96304a09901705846663fef41ce8f"
}
```

## Considerations
I developed this project in order to have a base, so in the future I won't waste time configuring authentication, documentation (Swagger) and package structure.

I also want to be able to share and evolve this project, I currently work as a full developer, and I know how much we can evolve every day.


> **Projeto:** Spring Oauth2 - Boilerplate      
> **Autor:** André Carlos [(andresinho20049)](https://github.com/andresinho20049)       
> **Resumo:** Default template for Spring project with Oauth2
