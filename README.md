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


Initial requirements

• The application must use the console for input and output.

• Users submit commands to the application. There are four commands.
“posting”, “reading”, etc. are not part of the commands; commands
always start with the user’s name. posting: <user name> ->
<message> reading: <user name> following: <user name>
follows <another user> wall: <user name> wall

• If you have time then consider handling exceptions and/or invalid
commands. Otherwise just assume that the user will always type the
correct commands, focus on the sunny day scenarios.

• Don’t bother making it work over a network or across processes. It can all be
done in memory, assuming that users will all use the same terminal.

• Non-existing users should be created as they post their first message.
Application should not start with a predefined list of users.

• Provide instructions on how to run the application.

