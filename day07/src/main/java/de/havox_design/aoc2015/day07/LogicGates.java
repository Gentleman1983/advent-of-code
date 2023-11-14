package de.havox_design.aoc2015.day07;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.havox_design.aoc2015.utils.DataReader;

public class LogicGates
{
  private final List<String> input;
  private final Set<LogicGate> registeredGates = new HashSet<>();
  private final Map<String, Integer> variablesMap = new HashMap<>();

  public LogicGates(String fileName) {
    input = readData(fileName);
  }

  public static int solvePart1(String fileName) {
    LogicGates instance = new LogicGates(fileName);
    return instance.solvePart1();
  }

  public static int solvePart2(String fileName) {
    LogicGates instance = new LogicGates(fileName);
    return instance.solvePart2();
  }

  public int solvePart1() {
    return 0;
  }

  public int solvePart2() {
    return 0;
  }

  private void parseInput() {
    for(String input : this.input) {
      String[] parts = input.split( " " );
      registerGate( parseInput( parts ) );
    }
  }
  private LogicGate parseInput(String... data) {
    return null;
  }

  protected Integer getValueForVariable(String variable) {
    return variablesMap.get( variable );
  }

  protected void updateVariable(String variable, Integer value) {
    variablesMap.put( variable, value );
  }

  protected void unregisterGate(LogicGate gate) {
    registeredGates.remove( gate );
  }

  private void registerGate(LogicGate gate) {
    registeredGates.add( gate );
  }

  private List<String> readData(String fileName) {
    return DataReader.readData(fileName, MainClass.class);
  }
}
