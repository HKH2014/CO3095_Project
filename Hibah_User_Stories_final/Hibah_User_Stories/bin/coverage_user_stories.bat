@echo off


set JAVA_HOME=C:\Program Files\Java\jdk-17
set CLASSPATH=.;lib\*;src
javac -d bin src\\*.java
java -javaagent:lib\jacocoagent.jar MyApplication

java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore MyApplicationTest
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore DatabaseManagerTest
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore DBManagerTestTwo
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore SellerPropertiesTest
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore PropertyHistory
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore TermandConditionTest
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore ManageProfileTest
java -javaagent:lib\jacocoagent.jar org.junit.runner.JUnitCore BuyerRatingsTest


java -jar lib\jacococli.jar report jacoco.exec --classfiles bin --html .

