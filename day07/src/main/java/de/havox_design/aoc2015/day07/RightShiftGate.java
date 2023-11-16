package de.havox_design.aoc2015.day07;

import static de.havox_design.aoc2015.day07.LogicGates.NUMBER_OF_BITS;

import org.apache.commons.lang3.StringUtils;

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

  public RightShiftGate(String input, String shift, String output, LogicGates observer) {
    this(input,Integer.parseInt(shift), output, observer);
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

    boolean[] binary = new boolean[NUMBER_OF_BITS];
    boolean[] binaryInput = BooleanToStringConverter.getInstance().convert(value);

    for(int i = 0; i < NUMBER_OF_BITS; i++) {
      int destination = (i + shift) % NUMBER_OF_BITS;

      binary[destination] = binaryInput[i];
    }

    int newValue = BooleanToStringConverter.getInstance().convert(binary);

    updateVariable(output, newValue);
  }
}
