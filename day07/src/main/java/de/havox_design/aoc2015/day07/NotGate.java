package de.havox_design.aoc2015.day07;

import static de.havox_design.aoc2015.day07.LogicGates.NUMBER_OF_BITS;

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
    int value;

    if( StringUtils.isNumeric( input ) ) {
      value = Integer.parseInt( input );
    }
    else if(observer.getValueForVariable( input ) != null) {
      value = observer.getValueForVariable( input );
    }
    else {
      return;
    }

    boolean[] binary = BooleanToStringConverter.getInstance().convert(value);

    for(int i = 0; i < NUMBER_OF_BITS; i++) {
      binary[i] = !binary[i];
    }

    int newValue = BooleanToStringConverter.getInstance().convert(binary);

    updateVariable(output, newValue);
  }
}
