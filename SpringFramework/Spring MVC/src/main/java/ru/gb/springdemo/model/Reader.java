package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  private final long id;
  private final String name;

  public Reader(String name) {
    this(sequence++, name);
  }

public void setId(long l) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setId'");
}

}
