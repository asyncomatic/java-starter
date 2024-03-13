## java-starter


### prerequisites
The following are prerequisites for installing and running the **java-starter** project on your local machine:

- java / maven development stack
<pre>
    $ java --version
    openjdk 17.0.5 2022-10-18
    OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
    OpenJDK 64-Bit Server VM Temurin-17.0.5+8 (build 17.0.5+8, mixed mode, sharing)

</pre>
<pre>
    $ mvn -v
    Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
    Maven home: /Users/jcormier/Projects/jccormier/brewery/homebrew/Cellar/maven/3.9.6/libexec
    Java version: 17.0.5, vendor: Eclipse Adoptium, runtime: /Library/Java/JavaVirtualMachines/temurin-17.jdk/...
    Default locale: en_CA, platform encoding: UTF-8
    OS name: "mac os x", version: "12.6.9", arch: "x86_64", family: "mac"

</pre>

- docker execution environment
<pre>
    $ docker -v
    Docker version 20.10.21, build baeda1f

</pre>

- [devcloud](https://github.com/asyncomatic/devcloud/) installed and running on your local machine 
([instructions](https://github.com/asyncomatic/devcloud/blob/main/README.md))

&nbsp;
### installation
Installing the **java-starter** project is fairly straightforward once all the prerequisites have been satisfied:
<pre>
    $ git clone git@github.com:asyncomatic/java-starter.git
    $ cd java-starter
    $ mvn install

</pre>
&nbsp;

### running the executor (local ide)
The simplest way of running the executor during test development is directly in you IDE of choice; no additional 
configuration should be required if running [devcloud](https://github.com/asyncomatic/devcloud) in its default 
configuration.

![](https://github.com/asyncomatic/java-starter/blob/main/executor_service.png?raw=true)

&nbsp;

### running the executor (local terminal)
An alternative way of running the executor on your local machine (or perhaps a separate machine with no IDE) is to 
run the JAR from a terminal window. Simply open a terminal window and navigate to the project root directory:
<pre>
    .... (navigate to project root directory) ....

    $ mvn install
    $ java -cp target/java-starter-jar-with-dependencies.jar io.github.asyncomatic.starter.ExecutorService

</pre>

 at which point you should see log messages similar to those shown below
<pre>
    .... (log messages stream by) ....
    [main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 3.0.0
    [main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 8cb0a5e9d3441962
    [main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1710285022587
    [main] INFO org.apache.kafka.clients.consumer.KafkaConsumer - [Consumer instanceId=aom_default_cgi, clientId=consumer-....
    [main] INFO org.apache.kafka.clients.Metadata - [Consumer instanceId=aom_default_cgi, clientId=consumer-aom_default_cg-....
    [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer instanceId=aom_default_cgi, clientId=....
    [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer instanceId=aom_default_cgi, clientId=....
    [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer instanceId=aom_default_cgi, clientId=....
    [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer instanceId=aom_default_cgi, clientId=....
    [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer instanceId=aom_default_cgi, clientId=....
    [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer instanceId=aom_default_cgi, clientId=....
    .... (more log messages stream by) ....

</pre>

&nbsp;

### running the docker image
Finally, the project includes files ([Dockerfile](https://github.com/asyncomatic/java-starter/blob/main/Dockerfile), 
[docker-compose.yml](https://github.com/asyncomatic/java-starter/blob/main/docker-compose.yml) and 
[starter](https://github.com/asyncomatic/java-starter/blob/main/starter)) to build and run a custom Docker image with 
everything needed to run the executor. 

To build and install the custom Docker image, open a terminal window and navigate to the project root directory:
<pre>
    .... (navigate to project root directory) ....

    $ ./starter install

</pre>

To start the Docker image and confirm it is running:
<pre>
    $ ./starter start
    [+] Running 1/1
     ⠿ Container devcloud_executor  Started

</pre>
<pre>
    $ ./starter status
    NAMES                STATUS          PORTS
    devcloud_executor    Up 49 seconds   
    devcloud_scheduler   Up 33 hours     0.0.0.0:8080->8080/tcp
    devcloud_postgres    Up 33 hours     0.0.0.0:5432->5432/tcp
    devcloud_kafka       Up 33 hours     9092/tcp, 0.0.0.0:9094->9094/tcp
    devcloud_zookeeper   Up 33 hours     2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp

</pre>

To tail the test executor logs (to confirm test execution, for example):
<pre>
    $ ./starter logs

</pre>

at which point you should see log messages similar to those shown below
<pre>
    .... (log messages stream by) ....
    devcloud_executor  | [main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 3.0.0
    devcloud_executor  | [main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 8cb0a5e9d3441962
    devcloud_executor  | [main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1710285022587
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.KafkaConsumer - [Consumer instanceId=....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.Metadata - [Consumer instanceId=aom_default_cgi, ....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer ....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer ....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer ....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer ....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer ....
    devcloud_executor  | [main] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - [Consumer ....
    .... (more log messages stream by) ....

</pre>

Finally, to stop the Docker image:
<pre>
    $ ./starter stop

</pre>

&nbsp;

### scheduling a sample test
A simple way to test that everything is properly configured and up-and-running is to schedule one of the included sample 
tests for execution. Tests are scheduled via POST API call to the ```/jobs``` endpoint provided by the scheduler 
service; for local development, this service is provided as part of **devcloud**. 

Using ```curl``` in a separate terminal window, simply send the appropriate payload to the ```/jobs``` endpoint:
<pre>
    $ curl -i -X POST 127.0.0.1:8001/jobs -H 'Content-Type: application/json' \
      -d '{ \            
            "class":"io.github.asyncomatic.starter.examples.tests.SampleScheduledTest", \
            "method":"start", \
            "queue":"devcloud.default", \
            "delay": 15 \
         }'
    HTTP/1.1 201 Created
    Date: Tue, 15 Aug 2023 17:56:54 GMT

</pre>

If you configured devcloud to use authorization (by setting a value for SCHEDULER_AUTH_TOKEN), the above will need to 
be slightly modified to include the appropriate Authorization header:
<pre>
    $ curl -i -X POST 127.0.0.1:8001/jobs -H 'Content-Type: application/json' \
      -H "Authorization: Bearer <SCHEDULER_AUTH_TOKEN>" \
      -d '{ \
            "class":"io.github.asyncomatic.starter.examples.tests.SampleScheduledTest", \
            "method":"start", \
            "queue":"devcloud.default", \
            "delay": 15 \
         }'
    HTTP/1.1 201 Created
    Date: Tue, 15 Aug 2023 17:56:54 GMT

</pre>
&nbsp;

Returning to the terminal window where you are tailing the test executor logs, you should see something similar to the 
following log fragment:
<pre>
devcloud_executor  | [main] INFO i.g.a.w.b.BasicWorker - Calling test method: i.g.a.s.e.t.SampleScheduledTest#start
devcloud_executor  | [main] INFO i.g.a.s.e.t.SampleScheduledTest - Executing test method: i.g.a.s.e.t.SampleScheduledTest#start
devcloud_executor  | [main] INFO i.g.a.w.b.BasicWorker - Execution of test method (STATUS: FAILED): i.g.a.s.e.t.SampleScheduledTest#start
devcloud_executor  | [main] INFO i.g.a.w.b.BasicWorker - Scheduling test method (DELAY: 30): i.g.a.s.e.t.SampleScheduledTest#nextWithDelay
devcloud_executor  | [main] INFO i.g.a.w.b.BasicWorker - Calling test method: i.g.a.s.e.t.SampleScheduledTest#nextWithDelay
devcloud_executor  | [main] INFO i.g.a.s.e.t.SampleScheduledTest - Executing test method: i.g.a.s.e.t.SampleScheduledTest#nextWithDelay
devcloud_executor  | [main] INFO i.g.a.w.b.BasicWorker - Execution of test method (STATUS: PASSED): i.g.a.s.e.t.SampleScheduledTest#nextWithDelay

</pre>
&nbsp;

### next steps
Congratulations, you have successfully installed and configured the **java-starter** project! It's now 
time to:

- add your own test(s) to your local copy of the repo;
- rebuild the project to include your new tests;
- start / restart the executor; and
- schedule your new test(s) for execution (using curl or Postman)

Happy testing!


