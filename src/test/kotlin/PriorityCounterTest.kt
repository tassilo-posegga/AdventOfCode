import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PriorityCounterTest {

    @Test
    fun getSummedPriorities() {
        val input = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw",
        )

        assertEquals(157, PriorityCounter.getSummedPriorities(input))
    }
}