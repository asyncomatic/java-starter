//  Copyright (c) 2024 JC Cormier
//  All rights reserved.
//  SPDX-License-Identifier: MIT
//  For full license text, see LICENSE file in the repo root or https://opensource.org/licenses/MIT

package io.github.asyncomatic.starter.examples.tests;

import io.github.asyncomatic.common.annotations.Retry;
import io.github.asyncomatic.common.annotations.Schedule;
import io.github.asyncomatic.common.constants.Condition;
import io.github.asyncomatic.common.constants.Delay;
import io.github.asyncomatic.starter.examples.SampleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SampleRetryTest {
    static Logger logger = LoggerFactory.getLogger(SampleRetryTest.class);

    @Retry(count = 3, delay = 10, units = Delay.SECONDS)
    @Schedule(method = "nextOnSuccess", delay = 10, units = Delay.SECONDS, condition = Condition.SUCCESS)
    @Schedule(method = "nextOnFailure", delay = 10, units = Delay.SECONDS, condition = Condition.FAILURE)
    public void start(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleRetryTest#start");
        if(state.getData().get("retries") == null) {
            state.getData().put("retries", 1);
            throw new RuntimeException();
        }else if( ((int) state.getData().get("retries") < 2) || ((new Random().nextInt() % 2) != 0) ) {
            state.getData().put("retries", (int) state.getData().get("retries") + 1);
            throw new RuntimeException();
        }
    }

    public void nextOnSuccess(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleRetryTest#nextOnSuccess");
    }

    public void nextOnFailure(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleRetryTest#nextOnFailure");
    }

}
