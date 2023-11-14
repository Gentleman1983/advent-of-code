package de.havox_design.aoc2015.day07;

public class RightShiftGate implements LogicGate
{
  private final LogicGates observer;
  private final String input;
  private final Integer shift;
  private final String output;

  public RightShiftGate(String input, Integer shift, String output, LogicGates observer) {
    this.observer = observer;
    this.input = input;
    this.shift = shift;
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
