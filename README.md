# Spring Boot Redis Demo

    Start your redis server : `redis-server`
    
Test this project by these commands:
-

Save:

    mvn test -Dtest="com.demo.redisoperation.ApplicationTests#testSave"

Get:

    mvn test -Dtest="com.demo.redisoperation.ApplicationTests#testGet"

Delete one value from the List:

    mvn test -Dtest="com.demo.redisoperation.ApplicationTests#testDeleteOneValue"

Delete the Key:

    mvn test -Dtest="com.demo.redisoperation.ApplicationTests#testDeleteKey"