# Gatling Example for Neo4J

Overview - Spike using a performance framework like Gatling to test the load against
a Neo4J database.

Caveats: This test uses the HTTP API and may not represent actual performance of the bolt API.

#### Setup
To use this, we need an authentication header to be set in the requests.

See:
https://neo4j.com/docs/http-api/current/security/#http-api-authenticate-to-access-the-server

This can be generated like so:
```
$ echo -n 'neo4j:P@ssw0rd' | openssl base64
```

Include the encoded string in the http setup like so:
```
    .authorizationHeader("Basic bmVvNGo6UEBzc3cwcmQ")
```


#### Usage
Using SBT build plug-in
```
$ sbt gatling:test
```
