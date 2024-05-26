package de.havox_design.aoc2021.day16

import java.math.BigInteger

sealed class Body {
    abstract fun value(): BigInteger

    data class Literal(val number: BigInteger) : Body() {
        override fun value(): BigInteger = number

        companion object {
            const val TYPE = 4
        }
    }

    data class Nested(val lengthType: Int, val packets: List<Packet>, val operation: Operation) : Body() {
        override fun value(): BigInteger = operation(packets)

        sealed interface Operation {
            operator fun invoke(packets: List<Packet>): BigInteger

            data object Sum : Operation {
                const val TYPE = 0

                override fun invoke(packets: List<Packet>): BigInteger {
                    return packets.sumOf { it.value() }
                }
            }

            data object Product : Operation {
                const val TYPE = 1

                override fun invoke(packets: List<Packet>): BigInteger {
                    return packets.map { it.value() }.reduce(BigInteger::multiply)
                }
            }

            data object Minimum : Operation {
                const val TYPE = 2

                override fun invoke(packets: List<Packet>): BigInteger {
                    return packets.minOf { it.value() }
                }
            }

            data object Maximum : Operation {
                const val TYPE = 3

                override fun invoke(packets: List<Packet>): BigInteger {
                    return packets.maxOf { it.value() }
                }
            }

            data object GreaterThan : Operation {
                const val TYPE = 5

                override fun invoke(packets: List<Packet>): BigInteger {
                    return if (packets[0].value() > packets[1].value()) BigInteger.ONE else BigInteger.ZERO
                }
            }

            data object LessThan : Operation {
                const val TYPE = 6

                override fun invoke(packets: List<Packet>): BigInteger {
                    return if (packets[0].value() < packets[1].value()) BigInteger.ONE else BigInteger.ZERO
                }
            }

            data object Equal : Operation {
                const val TYPE = 7

                override fun invoke(packets: List<Packet>): BigInteger {
                    return if (packets[0].value() == packets[1].value()) BigInteger.ONE else BigInteger.ZERO
                }
            }

            companion object {
                val MAPPINGS = mapOf(
                    Sum.TYPE to Sum,
                    Product.TYPE to Product,
                    Minimum.TYPE to Minimum,
                    Maximum.TYPE to Maximum,
                    GreaterThan.TYPE to GreaterThan,
                    LessThan.TYPE to LessThan,
                    Equal.TYPE to Equal,
                )
            }
        }
    }
}