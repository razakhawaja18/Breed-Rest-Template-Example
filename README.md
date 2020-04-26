BREED REST TEMPLATE EXAMPLE
====================================


#### Requirements
1. Java 8
2. Tomcat 8

> apache-maven-3.6.1


#### Intellij IDE Tips
1. Install Lombok Plugin
2. Enable Lombok Annotation Processing
    - File-> Settings
    - Expand `Build, Execution, Deployment`
    - Expand `Compiler`
    - In `Annotation Processors` check **Enable annotation processing**
    - _You may need to re-open the project to get the settings to take effect_.

#### Run it!
```
mvn clean install
```
```
mvn spring-boot:run
```

## Accessing Application
Based on the properties specified to start application, breeds can be accessed at:
```
http://localhost:8181/api/v1/breeds/{}
```

## Following are payload

##GET Get Breed Message By Breed

POST - http://localhost:8181/api/v1/breeds/bulldog

Expected Response Payload:

     {
            "breed": "bulldog",
               "subBreeds": [
                   "boston",
                   "english",
                   "french"
            ],
            "images": [
                 {
                    "url": "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg"
                },
                {
                    "url": "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10452.jpg"
                },
                {
                    "url": "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10596.jpg"
                },
                    ....
            ]
     }
       