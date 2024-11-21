package de.havox_design.aoc2023.day24;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class NeverTellMeTheOddsJava implements AoCFunctionality {
    private static final int ID_HAIL_A = 0;
    private static final int ID_HAIL_B = 1;
    private static final int ID_HAIL_C = 2;
    private static final int PRECISION_100 = 100;

    private final List<Hail> input;

    public NeverTellMeTheOddsJava(String fileName) {
        input = readData(fileName)
                .stream()
                .map(Hail::read)
                .toList();
    }

    public static long solvePart2(String fileName) {
        NeverTellMeTheOddsJava instance = new NeverTellMeTheOddsJava(fileName);

        return instance.solvePart2();
    }

    public long solvePart2() {
        BigDecimal[][] matrix = createBigDecimalLinearMatrix(find3UsableHails(input));
        solve(matrix);
        BigDecimal x2 = matrix[0][6];
        BigDecimal y2 = matrix[1][6];
        BigDecimal z2 = matrix[2][6];

        return x2
                .add(y2)
                .add(z2)
                .setScale(0, RoundingMode.HALF_EVEN)
                .longValue();
    }

    @SuppressWarnings("java:S3776")
    private List<Hail> find3UsableHails(List<Hail> hails) {
        List<Hail> result = new ArrayList<>();

        for (int aIndex = 0; aIndex < hails.size() - 2 && result.size() < 3; ++aIndex) {
            Hail a = hails.get(aIndex);

            for (int bIndex = aIndex + 1; bIndex < hails.size() - 1 && result.size() < 3; ++bIndex) {
                Hail b = hails.get(bIndex);

                if (!a.velocity().equals(b.velocity())) {
                    for (int cIndex = bIndex + 1; cIndex < hails.size() && result.size() < 3; ++cIndex) {
                        Hail c = hails.get(cIndex);

                        if (
                                !a.velocity().equals(c.velocity()) &&
                                        !b.velocity().equals(c.velocity())
                        ) {
                            result.add(a);
                            result.add(b);
                            result.add(c);
                        }
                    }
                }
            }
        }

        if (result.size() < 3) {
            throw new NoSuchElementException("Can not find 3 usable hails");
        }
        return result;
    }

    private BigDecimal[][] createBigDecimalLinearMatrix(List<Hail> hails) {
        if (hails.size() != 3) {
            throw new IllegalArgumentException("It can only work with 3 hails; got: " + hails.size());
        }

        BigDecimal zero = BigDecimal.ZERO;

        BigDecimalCoordinate aPosition = BigDecimalCoordinate.fromCoordinate(hails.get(ID_HAIL_A).position());
        BigDecimalCoordinate aVelocity = BigDecimalCoordinate.fromCoordinate(hails.get(ID_HAIL_A).velocity());
        BigDecimalCoordinate bPosition = BigDecimalCoordinate.fromCoordinate(hails.get(ID_HAIL_B).position());
        BigDecimalCoordinate bVelocity = BigDecimalCoordinate.fromCoordinate(hails.get(ID_HAIL_B).velocity());
        BigDecimalCoordinate cPosition = BigDecimalCoordinate.fromCoordinate(hails.get(ID_HAIL_C).position());
        BigDecimalCoordinate cVelocity = BigDecimalCoordinate.fromCoordinate(hails.get(ID_HAIL_C).velocity());

        return new BigDecimal[][]{
                {
                        aVelocity
                                .y()
                                .subtract(bVelocity.y()),
                        aVelocity
                                .x()
                                .subtract(bVelocity.x())
                                .negate(),
                        zero,
                        aPosition
                                .y()
                                .subtract(bPosition.y())
                                .negate(),
                        aPosition
                                .x()
                                .subtract(bPosition.x()),
                        zero,
                        bPosition
                                .y()
                                .multiply(bVelocity.x())
                                .subtract(bPosition.x().multiply(bVelocity.y()))
                                .subtract(aPosition.y().multiply(aVelocity.x()).subtract(aPosition.x().multiply(aVelocity.y())))
                },
                {
                        aVelocity
                                .y()
                                .subtract(cVelocity.y()),
                        aVelocity
                                .x()
                                .subtract(cVelocity.x())
                                .negate(),
                        zero,
                        aPosition
                                .y()
                                .subtract(cPosition.y())
                                .negate(),
                        aPosition
                                .x()
                                .subtract(cPosition.x()),
                        zero,
                        cPosition
                                .y()
                                .multiply(cVelocity.x())
                                .subtract(cPosition.x().multiply(cVelocity.y()))
                                .subtract(aPosition.y().multiply(aVelocity.x()).subtract(aPosition.x().multiply(aVelocity.y())))
                },
                {
                        aVelocity
                                .z()
                                .subtract(bVelocity.z())
                                .negate(),
                        zero,
                        aVelocity
                                .x()
                                .subtract(bVelocity.x()),
                        aPosition
                                .z()
                                .subtract(bPosition.z()),
                        zero,
                        aPosition
                                .x()
                                .subtract(bPosition.x())
                                .negate(),
                        bPosition
                                .x()
                                .multiply(bVelocity.z())
                                .subtract(bPosition.z().multiply(bVelocity.x()))
                                .subtract(aPosition.x().multiply(aVelocity.z()).subtract(aPosition.z().multiply(aVelocity.x())))
                },
                {
                        aVelocity
                                .z()
                                .subtract(cVelocity.z())
                                .negate(),
                        zero,
                        aVelocity
                                .x()
                                .subtract(cVelocity.x()),
                        aPosition
                                .z()
                                .subtract(cPosition.z()),
                        zero,
                        aPosition
                                .x()
                                .subtract(cPosition.x())
                                .negate(),
                        cPosition
                                .x()
                                .multiply(cVelocity.z())
                                .subtract(cPosition.z().multiply(cVelocity.x()))
                                .subtract(aPosition.x().multiply(aVelocity.z()).subtract(aPosition.z().multiply(aVelocity.x())))
                },
                {
                        zero,
                        aVelocity
                                .z()
                                .subtract(bVelocity.z()),
                        aVelocity
                                .y()
                                .subtract(bVelocity.y())
                                .negate(),
                        zero,
                        aPosition
                                .z()
                                .subtract(bPosition.z())
                                .negate(),
                        aPosition
                                .y()
                                .subtract(bPosition.y()),
                        bPosition
                                .z()
                                .multiply(bVelocity.y())
                                .subtract(bPosition.y().multiply(bVelocity.z()))
                                .subtract(aPosition.z().multiply(aVelocity.y()).subtract(aPosition.y().multiply(aVelocity.z())))
                },
                {
                        zero,
                        aVelocity
                                .z()
                                .subtract(cVelocity.z()),
                        aVelocity
                                .y()
                                .subtract(cVelocity.y())
                                .negate(),
                        zero,
                        aPosition
                                .z()
                                .subtract(cPosition.z())
                                .negate(),
                        aPosition
                                .y()
                                .subtract(cPosition.y()),
                        cPosition
                                .z()
                                .multiply(cVelocity.y())
                                .subtract(cPosition.y().multiply(cVelocity.z()))
                                .subtract(aPosition.z().multiply(aVelocity.y()).subtract(aPosition.y().multiply(aVelocity.z())))
                }
        };
    }

    private void solve(BigDecimal[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            BigDecimal factor = matrix[row][row];
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = matrix[row][col].divide(factor, PRECISION_100, RoundingMode.HALF_EVEN);
            }

            for (int row2 = 0; row2 < matrix.length; row2++) {
                if (row2 != row) {
                    BigDecimal factor2 = matrix[row2][row].negate();
                    for (int col = 0; col < matrix[row2].length; col++) {
                        matrix[row2][col] = matrix[row2][col].add(factor2.multiply(matrix[row][col]));
                    }
                }
            }
        }
    }
}
