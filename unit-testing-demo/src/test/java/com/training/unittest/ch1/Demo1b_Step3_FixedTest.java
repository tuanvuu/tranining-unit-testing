package com.training.unittest.ch1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Demo1b_Step3_FixedTest {

    @Test
    void short_string_is_not_long() {
        assertFalse(StringCheckerV2.isStringLong("abc"));
    }

    @Test
    void long_string_is_long() {
        assertEquals(true, StringCheckerV2.isStringLong("longstring"));
    }

    @Test
    void side_effect_is_false_for_short_string() {
        StringCheckerWithSideEffect.isStringLong("abc");
        assertEquals(false, StringCheckerWithSideEffect.wasLastStringLong);
    }

    @Test
    void side_effect_is_true_for_long_string() {
        StringCheckerWithSideEffect.isStringLong("longstring");
        assertEquals(true, StringCheckerWithSideEffect.wasLastStringLong);
    }
}
