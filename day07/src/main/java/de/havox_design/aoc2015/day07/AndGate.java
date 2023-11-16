package de.havox_design.aoc2015.day07;

import static de.havox_design.aoc2015.day07.LogicGates.NUMBER_OF_BITS;

import org.apache.commons.lang3.StringUtils;

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
    int valueA;

    if( StringUtils.isNumeric( inputA ) ) {
      valueA = Integer.parseInt( inputA );
    }
    else if(observer.getValueForVariable( inputA ) != null) {
      valueA = observer.getValueForVariable( inputA );
    }
    else {
      return;
    }

    int valueB;

    if( StringUtils.isNumeric( inputB ) ) {
      valueB = Integer.parseInt( inputB );
    }
    else if(observer.getValueForVariable( inputB ) != null) {
      valueB = observer.getValueForVariable( inputB );
    }
    else {
      return;
    }

    boolean[] binary = new boolean[NUMBER_OF_BITS];
    boolean[] binaryA = BooleanToStringConverter.getInstance().convert(valueA);
    boolean[] binaryB = BooleanToStringConverter.getInstance().convert(valueB);

    for(int i = 0; i < NUMBER_OF_BITS; i++) {
      binary[i] = binaryA[i] && binaryB[i];
    }

    int newValue = BooleanToStringConverter.getInstance().convert(binary);

    updateVariable(output, newValue);
  }
}
