# jakarta-concurrency-demo

Requires Java. IBM Semuru Runtimes Java 21 can be obtained [here](https://developer.ibm.com/languages/java/semeru-runtimes/downloads/)

## Running the Sample
From inside the `jakarta-concurrency-demo-main` directory, build and start the application in Open Liberty with the following command: 

```shell
./mvnw liberty:dev
```

Once the server has started, the application is available at http://localhost:9080/jakarta-concurrency-demo/

Thus app will demo key features of Jakarta Concurrency - ManagedExecuterService, ContextService and ManagedThreadFactory.
