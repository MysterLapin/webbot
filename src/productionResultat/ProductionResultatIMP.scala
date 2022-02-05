package productionResultat
import library.ProductionResultat
import library.Html
import library.Tag
import library.Texte

class ProductionResultatIMP extends ProductionResultat {

  /**
   *  @param l liste de couples solutions (titre, URL)
   *  @return le document Html listant les solutions
   */

  def reccResVersHtml(l: List[(String, String)]): List[Html] = {
    l match {
      case (titre, url) :: Nil => Tag("a", List(("href", url)), List(Texte(titre))) :: Nil
      case (titre, url) :: rest => Tag("a", List(("href", url)), List(
        Texte(titre))) :: reccResVersHtml(rest)
      case _ => Nil
    }
  }

  /**
   *  A partir d'une liste de couples (titre, URL), produit un document Html, qui
   *  liste les solutions sous la forme de liens cliquables
   *
   *  @param l la liste des couples solutions (titre, URL)
   *  @return le document Html listant les solutions
   */
  override def resultatVersHtml(l: List[(String, String)]): Html = {
    l match {
      case Nil => Tag("html", List(),
        List(
          Tag("body", List(), List(Texte("Aucun résultat")))))
      case _ => Tag("html", List(),
        List(
          Tag("body", List(), reccResVersHtml(l))))
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