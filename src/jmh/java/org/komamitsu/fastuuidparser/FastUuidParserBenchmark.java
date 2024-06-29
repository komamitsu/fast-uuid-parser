package org.komamitsu.fastuuidparser;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FastUuidParserBenchmark
{
    private int index;

    @Benchmark
    public void fromString()
    {
        FastUuidParser.fromString(Uuids.UUIDS[index++ % Uuids.NUM_OF_UUIDS]);
    }
}
