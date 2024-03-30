# Jakarta Concurrency Features Demo

Requires Java. IBM Semuru Runtimes Java 21 can be obtained [here](https://developer.ibm.com/languages/java/semeru-runtimes/downloads/)

## Environment Setup
To run this sample, first download zip or clone this repo - to clone:
```shell
git clone git@github.com:gauthamkrishnanibm/jakarta-concurrency-demo.git
```

## Running the Sample
From inside the `jakarta-concurrency-demo-main` directory, build and start the application in Open Liberty with the following command: 

```shell
./mvnw liberty:dev
```

Once the server has started, the application is available at http://localhost:9080/jakarta-concurrency-demo/

Thus app will demo key features of Jakarta Concurrency - ManagedExecuterService, ContextService and ManagedThreadFactory.
