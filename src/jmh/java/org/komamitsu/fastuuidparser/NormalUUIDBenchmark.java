package org.komamitsu.fastuuidparser;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.komamitsu.fastuuidparser.FastUuidParserBenchmark.UUID_STR_LOWER_FS;
import static org.komamitsu.fastuuidparser.FastUuidParserBenchmark.UUID_STR_UPPER_FS;
import static org.komamitsu.fastuuidparser.FastUuidParserBenchmark.UUID_STR_ZEROS;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NormalUUIDBenchmark
{
    @Benchmark
    @OperationsPerInvocation(3)
    public void fromString()
    {
        UUID.fromString(UUID_STR_ZEROS);
        UUID.fromString(UUID_STR_LOWER_FS);
        UUID.fromString(UUID_STR_UPPER_FS);
    }
}
