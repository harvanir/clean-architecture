package org.harvanir.pattern.clean.item.app.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

/** @author Harvan Irsyadi */
public class TestBenchmark {

  //  @Warmup(time = 1, iterations = 10) // Overridden by properties
  //  @Measurement(time = 1, iterations = 10) // Overridden by properties
  //  @Fork(value = 1) // Overridden by properties
  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public void nothing() {}
}
