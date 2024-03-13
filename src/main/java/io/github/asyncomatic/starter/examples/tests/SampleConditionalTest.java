package io.github.asyncomatic.starter.examples.tests;

import io.github.asyncomatic.common.annotations.Schedule;
import io.github.asyncomatic.common.constants.Condition;
import io.github.asyncomatic.common.constants.Delay;
import io.github.asyncomatic.starter.examples.SampleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SampleConditionalTest {
    static Logger logger = LoggerFactory.getLogger(SampleConditionalTest.class);

    @Schedule(method = "nextOnSuccess", delay = 10, units = Delay.SECONDS, condition = Condition.SUCCESS)
    @Schedule(method = "nextOnFailure", delay = 10, units = Delay.SECONDS, condition = Condition.FAILURE)
    public void start(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleConditionalTest#start");
        if((new Random().nextInt() % 2) != 0) { throw new RuntimeException(); }
    }

    public void nextOnSuccess(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleConditionalTest#nextOnSuccess");
    }

    public void nextOnFailure(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleConditionalTest#nextOnFailure");
    }
}
