# Fast-UUID-Parser
[<img src="https://travis-ci.org/komamitsu/fast-uuid-parser.svg?branch=master"/>](https://travis-ci.org/komamitsu/fast-uuid-parser) [![Coverage Status](https://coveralls.io/repos/komamitsu/fast-uuid-parser/badge.svg?branch=master&service=github)](https://coveralls.io/github/komamitsu/fast-uuid-parser?branch=master)

A UUID string parser that is more than 3 times faster than `java.util.UUID.fromString()` in Java 8.

![image](https://github.com/komamitsu/fast-uuid-parser/assets/59043/14ac393f-7534-4677-9ccf-1e2e31c502c0)

![image](https://github.com/komamitsu/fast-uuid-parser/assets/59043/951a6f61-0c5f-4b23-b89a-5c7076f76c4c)

But, in Java 11 or later, `java.util.UUID.fromString()` is faster, and I recommend to use the standard library.

## Usage

You can use `FastUuidParser.fromString()` like `java.util.UUID.fromString()`.

```java
UUID uuid = FastUuidParser.fromString("81c578f9-5e6d-4d78-8729-f32acefc144b");
```

