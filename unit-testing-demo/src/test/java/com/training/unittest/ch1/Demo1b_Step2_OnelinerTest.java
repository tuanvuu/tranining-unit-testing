package com.training.unittest.ch1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Demo1b_Step2_OnelinerTest {

    @Test
    void short_string_is_not_long() {
        boolean result = StringCheckerV2.isStringLong("abc");
        assertEquals(false, result);
    }
}
