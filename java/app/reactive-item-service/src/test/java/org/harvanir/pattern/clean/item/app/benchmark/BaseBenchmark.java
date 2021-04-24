package org.harvanir.pattern.clean.item.app.benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/** @author Harvan Irsyadi */
@SpringBootApplication
public class BaseBenchmark {

  public static void main(String[] args) throws IOException, RunnerException {
    Properties properties = PropertiesLoaderUtils.loadAllProperties("benchmark.properties");
    OptionsBuilder optionsBuilder = new OptionsBuilder();

    configureFork(properties, optionsBuilder);
    configureThreading(properties, optionsBuilder);
    configureResult(properties, optionsBuilder);
    configureJvmArgs(properties, optionsBuilder);
    configureInclude(properties, optionsBuilder);
    configureWarmUp(properties, optionsBuilder);
    configureMeasurement(properties, optionsBuilder);

    optionsBuilder.shouldDoGC(true).shouldFailOnError(true).shouldFailOnError(true).build();

    new Runner(optionsBuilder.build()).run();
  }

  private static void configureWarmUp(Properties properties, OptionsBuilder optionsBuilder) {
    int time = Integer.parseInt(properties.getProperty("benchmark.warmup.time", "1"));
    String timeUnit = properties.getProperty("benchmark.warmup.time-unit", "SECONDS");
    int iterations = Integer.parseInt(properties.getProperty("benchmark.warmup.iterations", "5"));
    TimeValue warmUpTime = new TimeValue(time, TimeUnit.valueOf(timeUnit));

    optionsBuilder.warmupIterations(iterations).warmupTime(warmUpTime);
  }

  private static void configureMeasurement(Properties properties, OptionsBuilder optionsBuilder) {
    int time = Integer.parseInt(properties.getProperty("benchmark.measurement.time", "1"));
    String timeUnit = properties.getProperty("benchmark.measurement.time-unit", "SECONDS");
    int iterations =
        Integer.parseInt(properties.getProperty("benchmark.measurement.iterations", "5"));
    TimeValue measurementTime = new TimeValue(time, TimeUnit.valueOf(timeUnit));

    optionsBuilder.measurementIterations(iterations).measurementTime(measurementTime);
  }

  private static void configureThreading(Properties properties, OptionsBuilder optionsBuilder) {
    int threads = Integer.parseInt(properties.getProperty("benchmark.threads.value", "1"));

    optionsBuilder.threads(threads);
  }

  private static void configureInclude(Properties properties, OptionsBuilder optionsBuilder) {
    String regexPattern =
        properties.getProperty("benchmark.global.class-regex-pattern", ".*Benchmark.*");

    optionsBuilder.include(regexPattern);
  }

  private static void configureResult(Properties properties, OptionsBuilder optionsBuilder) {
    String resultDirectory =
        properties.getProperty("benchmark.global.result-file-directory", "jmh-");
    String resultFilePrefix = properties.getProperty("benchmark.global.result-file-prefix", "jmh-");
    ResultFormatType resultsFileOutputType = ResultFormatType.JSON;

    optionsBuilder
        .resultFormat(resultsFileOutputType)
        .result(buildResultsFileName(resultDirectory, resultFilePrefix, resultsFileOutputType));
  }

  private static String buildResultsFileName(
      String resultDirectory, String resultFilePrefix, ResultFormatType resultType) {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss");

    String suffix;
    switch (resultType) {
      case CSV:
        suffix = ".csv";
        break;
      case SCSV:
        // Semi-colon separated values
        suffix = ".scsv";
        break;
      case LATEX:
        suffix = ".tex";
        break;
      case JSON:
      default:
        suffix = ".json";
        break;
    }

    return String.format(
        "%s%s%s%s", resultDirectory, resultFilePrefix, date.format(formatter), suffix);
  }

  private static void configureJvmArgs(Properties properties, OptionsBuilder optionsBuilder) {
    String jvmArgs = properties.getProperty("benchmark.jvm-args", "-server");
    optionsBuilder.jvmArgs(jvmArgs);
  }

  private static void configureFork(Properties properties, OptionsBuilder optionsBuilder) {
    int fork = Integer.parseInt(properties.getProperty("benchmark.fork.value", "1"));
    optionsBuilder.forks(fork);
  }
}
