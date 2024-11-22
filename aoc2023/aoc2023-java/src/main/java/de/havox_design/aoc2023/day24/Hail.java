package de.havox_design.aoc2023.day24;

public record Hail(Coordinate position, Coordinate velocity) {
    private static final int ID_POSITION = 0;
    private static final int ID_VELOCITY = 1;

    static Hail read(String description) {
        String[] parts = description.split(" @ ");

        return new Hail(
                Coordinate.read(parts[ID_POSITION]),
                Coordinate.read(parts[ID_VELOCITY])
        );
    }

    double slopeOnPlain() {
        double top = -velocity.y();
        double bottom = -velocity.x();

        return top / bottom;
    }

    double calcB() {
        return position.y() - slopeOnPlain() * position.x();
    }

    IntersectionCoordinate intersectOnPlain(Hail hail) {
        double m1 = slopeOnPlain();
        double m2 = hail.slopeOnPlain();
        double b1 = calcB();
        double b2 = hail.calcB();
        double x = (b1 - b2) / (m2 - m1);
        double y = m1 * x + b1;

        return new IntersectionCoordinate(x, y, 0.0);
    }

    boolean isIntersectionInFutureOnPlain(IntersectionCoordinate intersection) {
        return Math.signum(intersection.x() - position.x()) == Math.signum(velocity.x()) &&
                Math.signum(intersection.y() - position.y()) == Math.signum(velocity.y());
    }
}
