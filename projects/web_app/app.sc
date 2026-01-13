//> using javaOpt --sun-misc-unsafe-memory-access=allow
//> using dep com.lihaoyi::cask:0.11.3
//> using dep com.lihaoyi::os-lib:0.11.6

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
  
  // Port 8080 is local default; sys.env.get("PORT") is for the cloud
  override def port = sys.env.get("PORT").map(_.toInt).getOrElse(8080)
  override def host = "0.0.0.0"

MyServer.main(args)