//> using scala 3.5.0
//> using javaOpt --sun-misc-unsafe-memory-access=allow

val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

// 1. Filter for even numbers
// 2. Double them
// 3. Format into a nice string
val result = numbers
  .filter(_ % 2 == 0)
  .map(n => s"Double $n is ${n * 2}")

result.foreach(println)