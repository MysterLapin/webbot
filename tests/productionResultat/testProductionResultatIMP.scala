package productionResultat

import org.junit.Assert._
import org.junit.Test
import library._

class testProductionResultatIMP {

  val test1: List[((String, String))] = Nil
  val test2: List[((String, String))] = List(("titre1", "url1"), ("titre2", "url2"), ("titre3", "url3"),
    ("titre4", "url4"), ("titre5", "url5"))

  val objectProductionResultatIMP = new ProductionResultatIMP

  @Test
  def testResultatVersHtml1 {
    assertEquals(
      Tag("html", List(), List(
        Tag("body", List(), List(Texte("Aucun résultat"))))), objectProductionResultatIMP.resultatVersHtml(test1))
  }

  @Test
  def testResultatVersHtml2 {
    assertEquals(
      objectProductionResultatIMP.resultatVersHtml(test2),
      Tag("html", List(), List(
        Tag("body", List(), List(
          Tag("a", List(("href", "url1")), List(Texte("titre1"))),
          Tag("a", List(("href", "url2")), List(Texte("titre2"))),
          Tag("a", List(("href", "url3")), List(Texte("titre3"))),
          Tag("a", List(("href", "url4")), List(Texte("titre4"))),
          Tag("a", List(("href", "url5")), List(Texte("titre5"))))))))
  }

}