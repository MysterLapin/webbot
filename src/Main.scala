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
import analysePage.AnalysePageIMP
import library.OutilsWebObjet
import library.Expression
import library.ParserExpression.lireExpression
import analysePage.AnalysePageIMP
import library.Html
import java.io.FileWriter

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
  val mot: String = ("Livre")

  //Application
  val objectAnlysePageIMP = new AnalysePageIMP
  val objectProductionResultatIMP = new ProductionResultatIMP
  val objectHtmlVersStringIMP = new HtmlVersStringIMP

  val expr = lireExpression

  val url: String = "https://www.vivastreet.com/"

  val listTitreUrl: List[((String, String))] = objectAnlysePageIMP.resultats(url, expr)

  val resultat: Html = objectProductionResultatIMP.resultatVersHtml(listTitreUrl)

  val resHtml: String = objectHtmlVersStringIMP.traduire(resultat)

  val file = new FileWriter("ResultatRecherche.txt")
  try {
    file.write(resHtml)
  } 
  finally file.close()

}