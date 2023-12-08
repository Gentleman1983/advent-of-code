package de.havox_design.aoc2023.day08

import java.util.regex.Pattern

class Day08(private var filename: String) {
    private val PATTERN_GROUP_NODE_NAME = "nodeName"
    private val PATTERN_GROUP_LEFT_NODE_NAME = "leftNodeName"
    private val PATTERN_GROUP_RIGHT_NODE_NAME = "rightNodeName"
    private val NODE_PATTERN = Pattern.compile(
        "^(?<${PATTERN_GROUP_NODE_NAME}>\\w+) = " +
                "\\((?<${PATTERN_GROUP_LEFT_NODE_NAME}>\\w+), (?<${PATTERN_GROUP_RIGHT_NODE_NAME}>\\w+)\\)$"
    )
    private val INSTRUCTIONS = ArrayList<Direction>()
    private val NODES = HashSet<Node>()
    private val START_NODE_NAME = "AAA"
    private val END_NODE_NAME = "ZZZ"

    fun solvePart1(): Long {
        convertInput()

        var steps = 0L
        var currentNode = NODES.first { node -> node.name == START_NODE_NAME }
        var instructionIndex = 0

        while(currentNode.name != END_NODE_NAME) {
            val currentInstruction = INSTRUCTIONS[instructionIndex]
            instructionIndex++
            instructionIndex %= INSTRUCTIONS.size
            currentNode = currentNode.getNode(currentInstruction)
            steps++
        }

        return steps
    }

    fun solvePart2(): Long =
        0L

    private fun convertInput() {
        val input = getResourceAsText(filename)

        parseInstructions(input[0])
        parseNodes(input)
    }

    private fun parseInstructions(input: String) {
        for (char in input.toCharArray()) {
            INSTRUCTIONS.add(Direction.from(char))
        }
    }

    private fun parseNodes(input: List<String>) {
        val nodeInput = ArrayList<Triple<String, String, String>>()

        for (index in input.indices) {
            if (index <= 1) {
                continue
            }

            val row = input[index]
            val matcher = NODE_PATTERN.matcher(row)
            matcher.matches()
            val nodeName = matcher.group(PATTERN_GROUP_NODE_NAME)
            val leftNodeName = matcher.group(PATTERN_GROUP_LEFT_NODE_NAME)
            val rightNodeName = matcher.group(PATTERN_GROUP_RIGHT_NODE_NAME)

            nodeInput.add(Triple(nodeName, leftNodeName, rightNodeName))
            NODES.add(Node(nodeName, null, null))
        }

        for(entry in nodeInput) {
            val nodeName = entry.first
            val leftName = entry.second
            val rightName = entry.third

            val node = NODES.first { n -> n.name == nodeName }
            val left = NODES.first { n -> n.name == leftName }
            val right = NODES.first { n -> n.name == rightName }

            node.setNodes(left, right)
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}