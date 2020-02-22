package com.mavy0313.bankkata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

public class ClockShould {

  @Test
  public void return_todays_date_in_dd_MM_yyyy_format() {
    Clock clock = new TestableClock();

    String date = clock.todayAsString();

    assertThat(date, is("24/04/2015"));
  }

  private class TestableClock extends Clock {

    @Override
    protected LocalDate today() {
      return LocalDate.of(2015, 04, 24);
    }
  }
}