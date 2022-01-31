package productionResultat
import library.ProductionResultat
import library.Html
import library.Tag
import library.Texte

class ProductionResultatIMP extends ProductionResultat {
  
  def reccResVersHtml(l: List[(String, String)]): Html = {
      l match {
        case (titre, url) :: rest => Tag("a", List(("href",url)), List(
            Texte(titre),
            reccResVersHtml(rest)))
        case _ => Texte("")
      }
    }
  
  override def resultatVersHtml(l : List[(String,String)]): Html = {
    l match {
      case (titre, url) :: rest => Tag("html", List(),
          List(
            Tag("body", List(), List(
              Tag("a", List(("href", url)), List(Texte(titre))),
              reccResVersHtml(rest)))))
      case _ => Texte("Aucun rÃ©sultat")
    }
  }
}

/**
 * On veut un truc comme ca:
 *  <html>
 *    <body>
 *      <a href = url>titre</a>
 *    </body>
 *  </html>
 */
object HtmlExample2 {
  val exemple2 =
    Tag("html", List(),
      List(
        Tag("body", List(), List(
          Tag("a", List(("href", "url")), List(Texte("titre"))),
          Tag("a", List(("href", "url")), List(Texte("titre")))))))
}