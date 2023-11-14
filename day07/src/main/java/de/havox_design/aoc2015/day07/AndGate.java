package de.havox_design.aoc2015.day07;

public class AndGate implements LogicGate
{
  private final LogicGates observer;
  private final String inputA;
  private final String inputB;
  private final String output;

  public AndGate(String inputA, String inputB, String output, LogicGates observer) {
    this.observer = observer;
    this.inputA = inputA;
    this.inputB = inputB;
    this.output = output;
  }
  @Override public LogicGates getObserver()
  {
    return observer;
  }

  @Override public String getOutput()
  {
    return output;
  }

  @Override public void process()
  {

  }
}
