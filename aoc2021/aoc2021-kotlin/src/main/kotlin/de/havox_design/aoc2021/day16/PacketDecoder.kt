package de.havox_design.aoc2021.day16

import java.nio.Buffer
import java.nio.CharBuffer

class PacketDecoder(private var filename: String) {
    private val ICON_EMPTY = ""
    private val ICON_ZERO = '0'

    private val RADIX_BINARY = 2
    private val RADIX_HEX = 16

    private val data = getResourceAsText(filename)

    fun processPart1(): Any {
        val binary = data
            .map { it.digitToInt(RADIX_HEX) }
            .joinToString(ICON_EMPTY) { it.toString(RADIX_BINARY).padStart(4, ICON_ZERO) }
        val buffer = CharBuffer.wrap(binary)
        val root = readPacket(buffer)

        fun Packet.sumVersions(): Int = version + when (body) {
            is Body.Literal -> 0
            is Body.Nested -> body.packets.sumOf { it.sumVersions() }
        }

        return root.sumVersions()
    }

    fun processPart2(): Any {
        val binary = data
            .map { it.digitToInt(RADIX_HEX) }
            .joinToString(ICON_EMPTY) { it.toString(RADIX_BINARY).padStart(4, ICON_ZERO) }
        val buffer = CharBuffer.wrap(binary)
        val root = readPacket(buffer)

        return root.value()
    }

    private fun readPacket(buffer: CharBuffer): Packet {
        val version = buffer
            .consume(3)
            .toInt(RADIX_BINARY)
        val type = buffer
            .consume(3)
            .toInt(RADIX_BINARY)

        val body = when (type) {
            Body.Literal.TYPE -> {
                val value = buildString {
                    do {
                        val chunk = buffer.consume(5)

                        append(chunk.drop(1))
                    } while (chunk.first() != ICON_ZERO)
                }

                Body.Literal(
                    number = value.toBigInteger(RADIX_BINARY),
                )
            }
            else -> {
                val lengthType = buffer
                    .consume(1)
                    .toInt(RADIX_BINARY)
                val packets: List<Packet> = when (lengthType) {
                    0 -> {
                        val toRead = buffer
                            .consume(15)
                            .toInt(RADIX_BINARY)
                        val targetPosition = buffer.position() + toRead

                        buildList {
                            while (buffer.position() < targetPosition) {
                                add(readPacket(buffer))
                            }
                        }
                    }
                    1 -> {
                        val packets = buffer
                            .consume(11)
                            .toInt(RADIX_BINARY)

                        (0..<packets)
                            .map { readPacket(buffer) }
                    }
                    else -> emptyList()
                }

                Body.Nested(
                    lengthType = lengthType,
                    packets = packets,
                    operation = Body.Nested.Operation.MAPPINGS.getValue(type),
                )
            }
        }

        return Packet(
            version = version,
            type = type,
            body = body,
        )
    }

    private fun CharBuffer.consume(size: Int): String {
        val returnValue = take(size).toString()

        advance(size)

        return returnValue
    }
    private fun Buffer.advance(by: Int) =
        position(position() + by)


    private fun getResourceAsText(path: String): String =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readText()
}
