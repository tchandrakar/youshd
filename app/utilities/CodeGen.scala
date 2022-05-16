package utilities

import com.typesafe.config.ConfigFactory

object CodeGen extends App {
  val dbConfig = ConfigFactory.load().getConfig("slick.dbs.default")
  slick.codegen.SourceCodeGenerator.run(
    dbConfig.getString("profile"),
    dbConfig.getString("db.driver"),
    dbConfig.getString("db.url"),
    "/Users/tchandrakar/Desktop/learn_codebase/youshd/app/",
    "models", None, None, true, false
  )
}