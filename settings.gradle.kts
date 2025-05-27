plugins {
    id("com.gradle.develocity") version("4.0.2")
}

rootProject.name = "advent-of-code"

include("aoc2015")
include("aoc2015:aoc2015-java")
include("aoc2015:aoc2015-kotlin")

include("aoc2016")
include("aoc2016:aoc2016-java")
include("aoc2016:aoc2016-kotlin")

include("aoc2017")
include("aoc2017:aoc2017-java")
include("aoc2017:aoc2017-kotlin")

include("aoc2018")
include("aoc2018:aoc2018-java")
include("aoc2018:aoc2018-kotlin")

include("aoc2019")
include("aoc2019:aoc2019-java")
include("aoc2019:aoc2019-kotlin")

include("aoc2020")
include("aoc2020:aoc2020-kotlin")

include("aoc2021")
include("aoc2021:aoc2021-kotlin")

include("aoc2022")
include("aoc2022:aoc2022-java")
include("aoc2022:aoc2022-kotlin")

include("aoc2023")
include("aoc2023:aoc2023-java")
include("aoc2023:aoc2023-kotlin")

include("aoc2024")
include("aoc2024:aoc2024-kotlin")

include("aoc2025")
include("aoc2025:aoc2025-java")

include("meilisearch")

include("utils")
include("utils:utils-java")
include("utils:utils-kotlin")
