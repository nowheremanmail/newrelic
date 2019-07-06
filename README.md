# coding-challenge

Coding Challenge Build Framework

## Starter build framework for the coding challenge

First, you do not need to use this starter framework for your project.
If you would rather use a different build system (maven, javac, ...)
you are free to so long as you provide clear commands to build your
project and start your server.  Failure to do so will invalidate your
submission.


## Install Java

This coding challenge is in Java so it is recommended you install Java
1.8 from Oracle.


## Gradle

The build framework provided here uses gradle to build your project
and manage your dependencies.  The `gradlew` command used here will
automatically download gradle for you so you shouldn't need to install
anything other than java.


### Project Layout

All source code should be located in the `src/main/java` folder.
If you wish to write any tests (not a requirement) they should be
located in the `src/test/java` folder.

A starter `Main.java` file has been provided in the `com/newrelic/codingchallenge` package under `src/main/java`.


### Dependencies

If your project has any dependencies you can list them in the
`build.gradle` file in the `dependencies` section.


### Building your project from the command line

To build the project on Linux or MacOS run the command `./gradlew build` in a shell terminal.  This will build the source code in
`src/main/java`, run any tests in `src/test/java` and create an output
jar file in the `build/libs` folder.

To clean out any intermediate files run `./gradlew clean`.  This will
remove all files in the `build` folder.


### Running your application from the command line

You first must create a shadow jar file.  This is a file which contains your project code and all dependencies in a single jar file.  To build a shadow jar from your project run `./gradlew shadowJar`.  This will create a `codeing-challenge-shadow.jar` file in the `build/libs` directory.

You can then start your application by running the command
`java -jar ./build/lib/coding-challenge-shadow.jar`

## IDEA

You are free to use whichever editor or IDE you want providing your
projects build does not depend on that IDE.  Most of the Java
developers at New Relic use IDEA from
[JetBrains](https://www.jetbrains.com/).  JetBrains provides
a community edition of IDEA which you can download and use without
charge.

If you are planning to use IDEA you can generate the IDEA project files
by running `./gradlew idea` and directly opening the project folder
as a project in idea.


## Requirements
==================================

Data Services Code Challenge
Write a server (“Application”) in Java that opens a socket and restricts input to at most 5 concurrent clients. Clients will connect to the Application and write any number of 9 digit numbers, and then close the connection. The Application must write a de-duplicated list of these numbers to a log file in no particular order.
Primary Considerations
•	The Application should work correctly as defined below in Requirements.
•	The overall structure of the Application should be simple.
•	The code of the Application should be descriptive and easy to read, and the build method and runtime parameters must be well-described and work.
•	The design should be resilient with regard to data loss.
•	The Application should be optimized for maximum throughput, weighed along with the other Primary Considerations and the Requirements below.
Requirements
1.	The Application must accept input from at most 5 concurrent clients on TCP/IP port 4000.
2.	Input lines presented to the Application via its socket must either be composed of exactly nine decimal digits (e.g.: 314159265 or 007007009) immediately followed by a server-native newline sequence; or a termination sequence as detailed in #9, below.
3.	Numbers presented to the Application must include leading zeros as necessary to ensure they are each 9 decimal digits.
4.	The log file, to be named "numbers.log”, must be created anew and/or cleared when the Application starts.
5.	Only numbers may be written to the log file. Each number must be followed by a server-native newline sequence.
6.	No duplicate numbers may be written to the log file.
7.	Any data that does not conform to a valid line of input should be discarded and the client connection terminated immediately and without comment.
8.	Every 10 seconds, the Application must print a report to standard output:
i.	The difference since the last report of the count of new unique numbers that have been received.
ii.	The difference since the last report of the count of new duplicate numbers that have been received.
iii.	The total number of unique numbers received for this run of the Application.
iv.	Example text for #8: Received 50 unique numbers, 2 duplicates. Unique total: 567231
9.	If any connected client writes a single line with only the word "terminate" followed by a server-native newline sequence, the Application must disconnect all clients and perform a clean shutdown as quickly as possible.
10.	Clearly state all of the assumptions you made in completing the Application.
Notes
•	You may write tests at your own discretion. Tests are useful to ensure your Application passes Primary Consideration A.
•	You may use common libraries in your project such as Apache Commons and Google Guava, particularly if their use helps improve Application simplicity and readability. However the use of large frameworks, such as Akka, is prohibited.
•	Your Application may not for any part of its operation use or require the use of external systems, for example Apache Kafka or Redis.
•	At your discretion, leading zeroes present in the input may be stripped—or not used—when writing output to the log or console.
•	Robust implementations of the Application typically handle more than 2M numbers per 10-second reporting period on a modern MacBook Pro laptop (e.g.: 16 GiB of RAM and a 2.5 GHz Intel i7 processor).

