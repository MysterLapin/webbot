import filtrageURLs.FiltrageURLsIMP
import library.Tag
import library.Texte
import filtrageHtml.FiltrageHtmlMP
import library.Mot
import library.Et
import library.Ou


object Main extends App {
   val exemple = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien")))))))))
  val test = new FiltrageHtmlMP
  print(test.annalyseTexte(List("caribou","bout","ficelle"), Ou(Mot("caribou"),Mot("boukt"))))
  
}