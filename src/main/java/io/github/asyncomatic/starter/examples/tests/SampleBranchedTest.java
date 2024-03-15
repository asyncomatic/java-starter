//  Copyright (c) 2024 JC Cormier
//  All rights reserved.
//  SPDX-License-Identifier: MIT
//  For full license text, see LICENSE file in the repo root or https://opensource.org/licenses/MIT

package io.github.asyncomatic.starter.examples.tests;

import io.github.asyncomatic.common.annotations.Schedule;
import io.github.asyncomatic.common.constants.Delay;
import io.github.asyncomatic.starter.examples.SampleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SampleBranchedTest {
    static Logger logger = LoggerFactory.getLogger(SampleBranchedTest.class);

    @Schedule(method = "nextPartOne", delay = 10, units = Delay.SECONDS)
    @Schedule(method = "nextPartTwo", delay = 10, units = Delay.SECONDS)
    public void start(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleBranchedTest#start");
        if((new Random().nextInt() % 2) != 0) { throw new RuntimeException(); }
    }

    public void nextPartOne(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleBranchedTest#nextPartOne");
    }
    public void nextPartTwo(SampleState state) {
        logger.info("Executing test method: io.github.asyncomatic.starter.examples.tests.SampleBranchedTest#nextPartTwo");
    }

}
