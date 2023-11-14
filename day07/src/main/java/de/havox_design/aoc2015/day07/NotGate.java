package de.havox_design.aoc2015.day07;

import org.apache.commons.lang3.StringUtils;

public class NotGate implements LogicGate
{
  private final LogicGates observer;
  private final String input;
  private final String output;

  public NotGate(String input, String output, LogicGates observer) {
    this.observer = observer;
    this.input = input;
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
