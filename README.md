# EmojiPetz Spring Webservice

Most of these answers rely on reading the main front-end repository for Emoji-Pets.  There you will find more about the project includes knowledge assumed to be known when reading here.

[EmojiPetz github io](https://emoji-pets.github.io/emoji-pets/)

### Motivation for the backend service:

To allow the front-end client support by providing a basic REST and SQL backend so players can save and get their information and follow other players.

### Inventory

SQL ddl, entity classes, controllers, a view for getting follower information

### Contributers

Primarily written by Nick, but with a lot of help from Lora doing many minor tasks like documentation, etc.

### Endpoints:

/players/id/{player_id}],methods=[GET]
/players/{player_oauthId}],methods=[GET]
/players/{player_oauthId}],methods=[PUT],consumes=[text/plain],produces=[text/plain]
/players/{playerId}],methods=[DELETE]
/players/{playerId}/unfollow/{playerId2}],methods=[DELETE]
/players],methods=[GET]
/players],methods=[POST],consumes=[application/json]
/players/{player_oauthId}],methods=[PUT],consumes=[application/json],produces=[text/plain]
/players/{player_oauthId}/follow/{other_player_oauthId}],methods=[POST]
/error]
/error],produces=[text/html]

### Readiness

This service is currently running on an AWS server.  It seems to be running smoothly so far.  We tested the endpoints for a few days.

It has more functionality that has not been implemented in the front-end, yet.  Like unfollowing people can be done here, but is not implemented in the frontend.

### Platforms

- Hibernate
- Jackson
- Google Oauth
- Spring Boot

### Stretch goals

- More power.  I mean, add stuff that captures useful user data.  Get that exhaust!

### ERD and DDL

[ERD](docs/emojiERD.pdf)
[DDL](docs/ddl.sql)

### Javadoc

[javadoc](docs/index.html)

### License

- [Jackson](https://github.com/FasterXML/jackson-core/blob/master/src/main/resources/META-INF/LICENSE)
- [Hibernate](http://hibernate.org/community/license/)
- [Google Oauth](https://developers.google.com/identity/protocols/OAuth2)
- [Spring Boot](https://github.com/spring-projects/spring-boot/blob/master/LICENSE.txt)
- [Derby](https://db.apache.org/derby/)

### Swagger Docs

Lora, please update and put them here! :)

### Building

Please run the service through Java.  Ports can be specified in application.properties