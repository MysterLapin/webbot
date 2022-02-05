package htmlVersString

import org.junit.Assert._
import org.junit.Test
import library._

class testHtmlVersStringIMP {

  val test1: Html = Texte("")
  val test2: Html = Texte("Hello")
  val test3: Html = Tag("body", List(("chaset", "utf-8")), Nil)
  val test4: Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien")))))))))

  val objectHtmlVersStringIMP = new HtmlVersStringIMP
  @Test
  def testTraduire1 {
    assertEquals(objectHtmlVersStringIMP.traduire(test1), "")
  }
  
  @Test
  def testTraduire2 {
    assertEquals(objectHtmlVersStringIMP.traduire(test1), "")
  }
  
  @Test
  def testTraduire3 {
    var res4: String = "<html><head><meta charset=\"utf-8\"></meta><title>My Page</title></head>"
  }
  
  @Test
  def testTraduire4 {
    var res4: String = "<html><head><meta charset=\"utf-8\"></meta><title>My Page</title></head>"
    res4 += "<body><center><a href=\"http://www.irisa.fr\">Lien</a></center></body></html>"

    assertEquals(objectHtmlVersStringIMP.traduire(test4), res4)
  }
   
}