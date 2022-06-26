FROM maven:3.6.0-jdk-8-alpine

COPY src /home/SeleniumTestFramework/src

COPY pom.xml /home/SeleniumTestFramework

COPY src/test/java/com/trendyol/suites/SingleThreadAllTests.xml /home/SeleniumTestFramework

RUN mvn -f /home/SeleniumTestFramework/pom.xml clean test -DskipTests=true

#first step : docker build -t myfirstdockerimage .
#second step : docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.1.1-20211217
#third step : docker run -d --network="host" myfirstdockerimage mvn -f /home/SeleniumTestFramework/pom.xml clean test -DsuiteXmlFile="SingleThreadAllTests.xml"