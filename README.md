# littletwit


How to run

Assuming you have a command line opened in the littletwit directory, and that you have the Maven executable in your PATH,

> mvn exec:java

How to create executable jar (under littletwit/target)

> mvn package

How to run tests

> mvn test


Characteristics and limitations

The application handles as much as possible incorrect input from the user.

Posted messages cannot be more than 140 characters.

Usernames can contain letters and digits only.

Command are case-sensitive, for example `FOLLOWS` is not accepted in place of `follows`.

The application is single-threaded and cannot communicate over the network nor across processes.

Messages and users are not persistent between multiple executions.

The maximum number of messages that can be manipulated by the application (either by user of by wall) is limited by the biggest integer, which is (2^31)-1.

The application produces a log file (under littletwit/littletwit.log)

