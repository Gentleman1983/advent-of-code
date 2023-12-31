package de.havox_design.aoc2022.day17

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Tower size: ${PyroclasticFlow("day17.txt").processPart1()}")
            if(!args.contains("training")) {
                println("Tower size 2: ${PyroclasticFlow("day17.txt").processPart1(1000000000000)}")
            }
        }
    }
}
