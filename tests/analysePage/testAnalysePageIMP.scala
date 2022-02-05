package analysePage

import org.junit.Assert._
import org.junit.Test
import library._

class testAnalysePageIMP {

  val texteHtml1: Html = Texte("")

  val texteHtml2: Html = Texte("moto noire")

  val texteHtml3: Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("Annonce moto 125cm cube"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("p", List(),
            List(Texte("Annonces pour une moto  d'occasion bleue  de 125cm cube")))))))))

  val texteHtml4: Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("title", List(), List(Texte("Annonce moto 50cm cube"))),
          Tag("meta", List(("charset", "utf-8")), List()))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("p", List(),
            List(Texte("  Annonces pour une  moto d'occasion verte de 55cm cube  ")))))))))

  val texteHtml5: Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("p", List(), List(Texte("Annonce voiture 125chevaux"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("title", List(),
            List(Texte("Annonces  pour une voiture d'occasion noire de 125chevaux")))))))))

  val objectAnalysePageIMP = new AnalysePageIMP

  @Test
  def testGetTitle1 {
    assertEquals("", objectAnalysePageIMP.getTitle(texteHtml1))
  }

  @Test
  def testGetTitle2 {
    assertEquals("", objectAnalysePageIMP.getTitle(texteHtml2))
  }

  @Test
  def testGetTitle3 {
    assertEquals("Annonce moto 125cm cube", objectAnalysePageIMP.getTitle(texteHtml3))
  }

  @Test
  def testGetTitle4 {
    assertEquals("Annonce moto 50cm cube", objectAnalysePageIMP.getTitle(texteHtml4))
  }

  @Test
  def testGetTitle5 {
    assertEquals("Annonces  pour une voiture d'occasion noire de 125chevaux", objectAnalysePageIMP.getTitle(texteHtml5))
  }

  @Test
  def testListURLsHtmlToRes1 {
    assertEquals(Nil, objectAnalysePageIMP.listURLsHtmlToRes(Nil))
  }
  
  @Test
  def testListURLsHtmlToRes2 {
    assertEquals(List(("url","")), objectAnalysePageIMP.listURLsHtmlToRes(List(("url", texteHtml1))))
  }
  
  @Test
  def testListURLsHtmlToRes3 {
    assertEquals(List(("url","Annonce moto 125cm cube")), objectAnalysePageIMP.listURLsHtmlToRes(List(("url", texteHtml3))))
  }
  
  @Test
  def testListURLsHtmlToRes4 {
    assertEquals(List(("url1","Annonce moto 125cm cube"), ("url2", "Annonces  pour une voiture d'occasion noire de 125chevaux")),
        objectAnalysePageIMP.listURLsHtmlToRes(List(("url1", texteHtml3), ("url2", texteHtml5))))
  }

}