year = "20xx"
day = "14"
part = 2

def solve():
    return 1


result = solve()
print(f"Solution for AoC {year} day {day} part {part} via Python: {result}")
with open(f"../../../src/main/resources/de/havox_design/aoc{year}/day{day}/day{day}result_part{part}.txt", 'a') as out:
    print(result, file=out)
