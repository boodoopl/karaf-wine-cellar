## Introduction

This is an Apache Karaf demo project which shows how the Apache Karaf can be used for modern [RIA](https://en.wikipedia.org/wiki/Rich_Internet_application), especially for IoT devices.

The features presented:
- REST server with CRUD operations
- WebSocket server providing CRUD notifications
- Built in Derby database with CRUD DAO layer
- WAR container with AngularJS frontend
- Custom console commands
- Building final distribution

Inspiration: [Sample Application with Angular.js](http://coenraets.org/blog/2012/02/sample-application-with-angular-js/) by [Christophe Coenraets](http://coenraets.org/blog/)

## Used technologies

- [Apache Karaf](http://karaf.apache.org/) - the core
- [AngularJS](https://angularjs.org/) - the frontend
- [Twitter Boostrap](http://getbootstrap.com/) - the UI
- [Start Boostrap](http://startbootstrap.com/) - the template provider

## Building

<pre>mvn clean install -DskipTests</pre>

## Running

- Run *karaf-wine-cellar/assemblies/winecellar-distribution/target/assembly/bin/karaf.sh* or *karaf.bat*
- Wait a little bit
- Type *list* in Karaf console - if all bundles are in Active state it means the system is ready

![Karaf](/screens/karaf.png?raw=true "Karaf screenshot")

## Playing with wines

- Open [http://localhost:8181/angular-frontend/index.html](http://localhost:8181/angular-frontend/index.html) in your favorite browser
- Play with wines

![Karaf](/screens/UI.png?raw=true "Karaf screenshot")

## Watching more

- Open [http://localhost:8181/cxf](http://localhost:8181/cxf) to see RESTful services list
- Open [http://localhost:8181/cxf/wines](http://localhost:8181/cxf/wines) to get all wines entries
- Open [ws://localhost:9090/events](ws://localhost:9090/events) websocket (i.e. by [Chrome Extension](https://chrome.google.com/extensions/detail/pfdhoblngboilpfeibdedpjgfnlcodoo)) to see what data is transmitted when you play with wines
- Type in Karaf console <pre>query jdbc/derbyds "SELECT * FROM Wine"</pre> to see database state using SQL (don't write too much - use TAB whenever possible)
- Type some of wine related commands to play with wines manually, i.e. *deletewine x* (you need to find out the wineId first)

## The future - TBD

- JAAS based authentication
- Karaf console customization
- Karaf console auto complete
- DEB packaging
- More integrations tests
- Increasing Karaf version
- Switching to JPA 2.1 and injectable EntityListener
- Database migration

## Project structure:

  - *angular-frontend* - WAR
  - assemblies
   - *winecellar-feature* - Karaf feature descriptor
   - *winecellar-distribution* - final distribution
  - bundles
   - *dao* - dao abstraction
   - *dao-commands* - dao commands for Karaf console
   - *dao-impl* - JPA based dao implementation
   - *data-endpoint* - REST services interfaces
   - *data-endpoint-impl* - implantation of REST services with use of CXF
   - *data-initializer* - initial data loader
   - *derby-datasource* - Derby data source exporter
   - *event-broker* - internal message broker abstraction
   - *event-broker-impl* - OSGi Event Admin based broker implementation
   - *model* - entities
   -*websocket-notifer* - Camel based Event Admin to Websocket message forwareder
  - *itests* - Pax Exam based integration tests

## Known issues
- Integration tests fail.
