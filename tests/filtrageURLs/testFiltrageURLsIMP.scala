package filtrageURLs

import org.junit.Assert._
import org.junit.Test
import library._

class testFiltrageURLsIMP {

  val texteHtml1: Html = Texte("")

  val texteHtml2: Html = Texte("moto noire")

  val texteHtml3: Html = Tag("a", List(("href", "https://google.com")), List(Texte("Lien google")))

  val texteHtml4: Html = Tag("a", List(("href",
    "https://www.vivastreet.com/voiture-occasion/ballaison-74140/jolie-audi-a4-cabriolet/245147100")),
    List(Texte("Lien annonce Vivastreet")))

  val texteHtml5: Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("Annonces Vivastreet"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("p", List(),
            List(
                Texte("Annonces"),
                Tag("a", List(("href", "https://www.vivastreet.com/voiture-occasion/ballaison-74140/jolie-audi-a4-cabriolet/245147100")),
                    List(Texte("Audi"))),
                Tag("a", List(("href", "https://www.vivastreet.com/voiture-occasion/agon-coutainville-50230/bmw-418d-bva8-150-ch/245152674")),
                    List(Texte("BMW"))),
                Tag("a", List(("href", "https://www.vivastreet.com/voiture-occasion/eysines-33320/renault-espace-dci-160-energy-twin-turbo-initiale-paris-edc/244931212")),
                    List(Texte("Renault")))))))))))

  val objectFiltrageURLsIMP = new FiltrageURLsIMP

  @Test
  def testFiltreAnnonce1 {
    assertEquals(List(), objectFiltrageURLsIMP.filtreAnnonce(texteHtml1))
  }

  @Test
  def testFiltreAnnonce2 {
    assertEquals(List(), objectFiltrageURLsIMP.filtreAnnonce(texteHtml2))
  }

  @Test
  def testFiltreAnnonce3 {
    assertEquals(List(), objectFiltrageURLsIMP.filtreAnnonce(texteHtml3))
  }

  @Test
  def testFiltreAnnonce4 {
    assertEquals(
      List("https://www.vivastreet.com/voiture-occasion/ballaison-74140/jolie-audi-a4-cabriolet/245147100"),
      objectFiltrageURLsIMP.filtreAnnonce(texteHtml4))
  }
  
  @Test
  def testFiltreAnnonce5 {
    assertEquals(
      List("https://www.vivastreet.com/voiture-occasion/ballaison-74140/jolie-audi-a4-cabriolet/245147100",
          "https://www.vivastreet.com/voiture-occasion/agon-coutainville-50230/bmw-418d-bva8-150-ch/245152674",
          "https://www.vivastreet.com/voiture-occasion/eysines-33320/renault-espace-dci-160-energy-twin-turbo-initiale-paris-edc/244931212"),
      objectFiltrageURLsIMP.filtreAnnonce(texteHtml5))
  }
}