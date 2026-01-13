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
  // These overrides ensure Railway can see the app
  override def host = "0.0.0.0"
  override def port = sys.env.get("PORT").map(_.toInt).getOrElse(8080)

MyServer.main(args)