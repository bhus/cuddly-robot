

final class app$_ {
def args = app_sc.args$
def scriptPath = """projects/web_app/app.sc"""
/*<script>*/
//> using dep com.lihaoyi::cask:0.11.3
//> using dep com.lihaoyi::os-lib:0.11.6
//> using dep com.lihaoyi::upickle:4.4.2

// 1. Define your routes in an object
object MyRoutes extends cask.Routes:
  
  @cask.get("/")
  def home() = 
    cask.Response(
      data = os.read(os.pwd / "projects" / "web_app" / "public" / "index.html"),
      headers = Seq("Content-Type" -> "text/html")
    )

  @cask.staticFiles("/static")
  def staticFiles() = "projects/web_app/public"

  initialize()

object MyServer extends cask.Main:
  val allRoutes = Seq(MyRoutes)

MyServer.main(args)
/*</script>*/ /*<generated>*//*</generated>*/
}

object app_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new app$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export app_sc.script as `app`

