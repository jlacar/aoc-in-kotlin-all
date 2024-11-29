package lacar.junilu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ListExtensionsTest {
    @Nested
    inner class `productOfAll List extension` {

        @Nested
        inner class `when list is empty` {
            private val expectedExceptionType = UnsupportedOperationException::class.java

            @Test
            fun `throws UnsupportedOperationException when list is empty`() {
                assertThrows(expectedExceptionType) { emptyList<Int>().product() }
            }

            @Test
            fun `throws UnsupportedOperationException when all elements are filtered out`() {
                val nonEmptyList = (1..10).toList()
                val belowRange = { n: Int -> n < nonEmptyList.min() }
                val aboveRange = { n: Int -> n > nonEmptyList.max() }

                assertAll(
                    { assertThrows(expectedExceptionType) { nonEmptyList.filter(aboveRange).product() } },
                    { assertThrows(expectedExceptionType) { nonEmptyList.filter(belowRange).product() } },
                    { assertThrows(expectedExceptionType) { nonEmptyList.product(aboveRange) } },
                    { assertThrows(expectedExceptionType) { nonEmptyList.product(belowRange) } },
                )
            }
        }

        @ParameterizedTest(name = "productOfAll({0}) = {0}")
        @ValueSource(ints = [1, 2, 3, -1, 10, -5, 0, 100, -100])
        fun `returns same number when list contains only 1 element`(number: Int) {
            val list = listOf(number)
            assertEquals(number, list.product())
        }

        @Test
        fun `returns product of all elements when list contains multiple elements`() {
            val numbers = listOf(1, 2, 3, -1, 10, -5)
            assertEquals(300, numbers.product())
        }
    }
}