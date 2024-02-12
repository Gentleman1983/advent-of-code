package de.havox_design.aoc2018.day13;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MineCartMadness implements AoCFunctionality {
    private static final String TURN_LEFT = "LEFT";
    private static final String TURN_RIGHT = "RIGHT";
    private static final String TURN_STRAIGHT = "STRAIGHT";

    private final Mine input;

    public MineCartMadness(String fileName) {
        MineParser parser = new MineParser();
        input = parser.parse(readData(fileName));
    }

    public static String processTask1(String fileName) {
        MineCartMadness instance = new MineCartMadness(fileName);
        return instance.processTask1();
    }

    public static String processTask2(String fileName) {
        MineCartMadness instance = new MineCartMadness(fileName);
        return instance.processTask2();
    }

    public String processTask1() {
        Set<Cart> carts = new HashSet<>(input.carts());
        Map<Integer, Iterator<String>> nextTurns = input
                .carts()
                .stream()
                .collect(Collectors.toMap(Cart::id, c -> getTurns()));

        while (true) {
            Iterator<Cart> cartIterator = carts
                    .stream()
                    .sorted(getCartComparator())
                    .iterator();

            while (cartIterator.hasNext()) {
                Cart cart = cartIterator.next();

                carts.remove(cart);
                carts.add(propagateCart(cart, nextTurns.get(cart.id())));

                Coordinate collision = getCollision(carts);

                if (collision != null) {
                    return String.format("%d,%d", collision.getX(), collision.getY());
                }
            }
        }
    }

    public String processTask2() {
        Set<Cart> carts = new HashSet<>(input.carts());
        Map<Integer, Iterator<String>> nextTurns = input
                .carts()
                .stream()
                .collect(Collectors.toMap(Cart::id, c -> getTurns()));

        while (carts.size() > 1) {
            Iterator<Cart> cartIterator = carts
                    .stream()
                    .sorted(getCartComparator())
                    .iterator();

            while (cartIterator.hasNext()) {
                Cart cart = cartIterator.next();

                if (!carts.contains(cart)) {
                    continue;
                }

                carts.remove(cart);
                carts.add(propagateCart(cart, nextTurns.get(cart.id())));

                Coordinate collision = getCollision(carts);

                if (collision != null) {
                    carts.removeAll(carts
                            .stream()
                            .filter(e -> e.location().equals(collision))
                            .collect(Collectors.toSet()));
                }
            }
        }

        Cart cart = carts
                .stream()
                .findFirst()
                .orElseThrow();

        return String.format("%d,%d", cart.location().getX(), cart.location().getY());
    }

    private Coordinate getCollision(Set<Cart> carts) {
        for (Cart c : carts) {
            for (Cart d : carts) {
                if (c.equals(d)) {
                    continue;
                }
                if (c.location().equals(d.location())) {
                    return c.location();
                }
            }
        }
        return null;
    }

    private Comparator<Cart> getCartComparator() {
        return Comparator
                .<Cart>comparingInt(c -> c.location().getY())
                .thenComparingInt(c -> c.location().getX());
    }

    private Cart propagateCart(Cart cart, Iterator<String> turns) {
        FourDirectionsFlipped currentCartDirection = cart.direction();
        Coordinate nextLocation = new Coordinate(
                cart.location().getX() + currentCartDirection.dx(),
                cart.location().getY() + currentCartDirection.dy()
        );

        Path nextPath = input.paths()[nextLocation.getX()][nextLocation.getY()];
        FourDirectionsFlipped nextDirection;

        if (nextPath.equals(Path.CROSSING)) {
            nextDirection = switch (turns.next()) {
                case TURN_LEFT -> currentCartDirection.turnLeft();
                case TURN_RIGHT -> currentCartDirection.turnRight();
                case TURN_STRAIGHT -> currentCartDirection;
                default -> throw new IllegalStateException("This should never happen...");
            };
        } else {
            nextDirection = nextPath.directionMap.get(currentCartDirection);
        }

        return new Cart(cart.id(), nextLocation, nextDirection);
    }

    private Iterator<String> getTurns() {
        String[] turns = new String[]{TURN_LEFT, TURN_STRAIGHT, TURN_RIGHT};
        return IntStream
                .iterate(0, i -> (i + 1) % turns.length)
                .mapToObj(i -> turns[i])
                .iterator();
    }
}
