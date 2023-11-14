package de.havox_design.aoc2015.day07;

import org.apache.commons.lang3.StringUtils;

public class SetGate implements LogicGate
{
  private final LogicGates observer;
  private final String input;
  private final String output;

  public SetGate(String input, String output, LogicGates observer) {
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
    if( StringUtils.isNumeric( input ) ) {
      observer.updateVariable( output, Integer.parseInt( input ) );
    }
    else if(observer.getValueForVariable( input ) != null) {
      observer.updateVariable( output, observer.getValueForVariable( input ) );
    }
  }
}
