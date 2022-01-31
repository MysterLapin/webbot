import filtrageURLs.FiltrageURLsIMP
import library.Tag
import library.Texte
import filtrageHtml.FiltrageHtmlMP
import library.Mot
import library.Et
import library.Ou
import library.HtmlVersString
import htmlVersString.HtmlVersStringIMP
import productionResultat.ProductionResultatIMP


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
  val test = new FiltrageURLsIMP
  val test1 = new ProductionResultatIMP
  val liste: List[(String,String)] = List(("test","test.html"),("test2","test2.html"))
 
   
}