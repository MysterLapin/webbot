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
import library.Html
import java.io.FileWriter
import library.ParserExpression.lireExpression



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
  val mot: String = ("yo ca va livre")
  
  val t = OutilsWebObjet.obtenirHtml("file:///private/student/e/ne/ylafontaine/workspace/TP345/lib/test.html")
  println(test.filtreHtml(t,Mot("livre")))
 //: val testListe = List("Coucou","truc","cafart")
 // println("test annalyse  " + test.annalyseTexte(testListe, Et(Mot("truc"),Mot("cafart"))))
  //println(test.decoupageDuString("test fonction decouper"))
  
  //println(test.filtreHtml(OutilsWebObjet.obtenirHtml("https://search.vivastreet.com/achat-vente-cd-dvd/fr?lb=new&search=1&start_field=1&keywords=velo&cat_1=81&geosearch_text=&searchGeoId=0&sp_common_price%5Bstart%5D=&sp_common_price%5Bend%5D="), Mot("livre")))
 
   
  val mot1 : String = ("Livre")

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