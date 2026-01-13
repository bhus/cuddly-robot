

final class app$_ {
def args = app_sc.args$
def scriptPath = """projects/web_app/app.sc"""
/*<script>*/
//> using dep com.lihaoyi::cask:0.10.2
//> using dep com.lihaoyi::os-lib:0.11.3

object MyServer extends cask.MainRoutes:

  // 1. Serve the main page at the root "/"
  @cask.get("/")
  def home() = 
    cask.Response(
      data = os.read(os.pwd / "projects" / "web_app" / "public" / "index.html"),
      headers = Seq("Content-Type" -> "text/html")
    )

  // 2. Automatically serve anything in the /public folder under the "/static" URL
  // This is where your images and future JS files will live
  @cask.staticFiles("/static")
  def staticFiles() = "projects/web_app/public"

  // Start the server on port 8080
  initialize()
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

