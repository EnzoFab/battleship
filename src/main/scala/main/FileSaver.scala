package main
import java.io.{File, IOException, PrintWriter}

object FileSaver {
  def saveCSV(filename: String, text: String): Option[String] = {
    try{
      val writer = new PrintWriter(new File(s"$filename.csv"))

      writer.write(text)
      writer.close()
      Some("success")
    }catch {
      case _: IOException => None
    }

  }
}
