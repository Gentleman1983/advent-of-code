from random import shuffle

def solve():
    reps = [("Al", "ThF"), ("Al", "ThRnFAr"), ("B", "BCa"), ("B", "TiB"), ("B", "TiRnFAr"), ("Ca", "CaCa"), ("Ca", "PB"), ("Ca", "PRnFAr"), ("Ca", "SiRnFYFAr"), ("Ca", "SiRnMgAr"), ("Ca", "SiTh"), ("F", "CaF"), ("F", "PMg"), ("F", "SiAl"), ("H", "CRnAlAr"), ("H", "CRnFYFYFAr"), ("H", "CRnFYMgAr"), ("H", "CRnMgYFAr"), ("H", "HCa"), ("H", "NRnFYFAr"), ("H", "NRnMgAr"), ("H", "NTh"), ("H", "OB"), ("H", "ORnFAr"), ("Mg", "BF"), ("Mg", "TiMg"), ("N", "CRnFAr"), ("N", "HSi"), ("O", "CRnFYFAr"), ("O", "CRnMgAr"), ("O", "HP"), ("O", "NRnFAr"), ("O", "OTi"), ("P", "CaP"), ("P", "PTi"), ("P", "SiRnFAr"), ("Si", "CaSi"), ("Th", "ThCa"), ("Ti", "BP"), ("Ti", "TiTi"), ("e", "HF"), ("e", "NAl"), ("e", "OMg")]

    mol = "ORnPBPMgArCaCaCaSiThCaCaSiThCaCaPBSiRnFArRnFArCaCaSiThCaCaSiThCaCaCaCaCaCaSiRnFYFArSiRnMgArCaSiRnPTiTiBFYPBFArSiRnCaSiRnTiRnFArSiAlArPTiBPTiRnCaSiAlArCaPTiTiBPMgYFArPTiRnFArSiRnCaCaFArRnCaFArCaSiRnSiRnMgArFYCaSiRnMgArCaCaSiThPRnFArPBCaSiRnMgArCaCaSiThCaSiRnTiMgArFArSiThSiThCaCaSiRnMgArCaCaSiRnFArTiBPTiRnCaSiAlArCaPTiRnFArPBPBCaCaSiThCaPBSiThPRnFArSiThCaSiThCaSiThCaPTiBSiRnFYFArCaCaPRnFArPBCaCaPBSiRnTiRnFArCaPRnFArSiRnCaCaCaSiThCaRnCaFArYCaSiRnFArBCaCaCaSiThFArPBFArCaSiRnFArRnCaCaCaFArSiRnFArTiRnPMgArF"

    target = mol
    part2 = 0

    while target != 'e':
        tmp = target
        for a, b in reps:
            if b not in target:
                continue

            target = target.replace(b, a, 1)
            part2 += 1

        if tmp == target:
            target = mol
            part2 = 0
            shuffle(reps)

    return part2


result = solve()
print(f"Solution for part 2 via Python: {result}")
with open("../../../../aoc2015-python/src/main/resources/de/havox_design/aoc2015/day19/day19result_part2.txt", 'a') as out:
    print(result, file=out)
