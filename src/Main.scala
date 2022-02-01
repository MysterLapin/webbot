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
            
  val test = new AnalysePageIMP
  val test1 = new ProductionResultatIMP
  print(test.resultats("https://search.vivastreet.com/achat-vente-cd-dvd/fr?lb=new&search=1&start_field=1&keywords=velo&cat_1=81&geosearch_text=&searchGeoId=0&sp_common_price%5Bstart%5D=&sp_common_price%5Bend%5D=", Mot("Livre")))
 
   
}