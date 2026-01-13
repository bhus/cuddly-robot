

final class hello$_ {
def args = hello_sc.args$
def scriptPath = """projects\hello.sc"""
/*<script>*/
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
/*</script>*/ /*<generated>*//*</generated>*/
}

object hello_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new hello$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export hello_sc.script as `hello`

