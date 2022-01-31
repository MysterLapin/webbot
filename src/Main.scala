import filtrageURLs.FiltrageURLsIMP
import library.Tag
import library.Texte
import filtrageHtml.FiltrageHtmlMP
import library.Mot
import library.Et
import library.Ou
import library.HtmlVersString
import htmlVersString.HtmlVersStringIMP


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
  val test1 = new HtmlVersStringIMP
  print(test1.traduire(exemple))
 
  
}