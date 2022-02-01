package analysePage

import library.AnalysePage
import library.Expression
import library.OutilsWeb
import library.OutilsWebObjet
import filtrageURLs.FiltrageURLsIMP
import filtrageHtml.FiltrageHtmlMP
import library.FiltrageURLs
import library.Html
import library.FiltrageHtml

class AnalysePageIMP extends AnalysePage {
  
  val objFiltrageUrls: FiltrageURLs = new FiltrageURLsIMP
  val objFiltrageHtml: FiltrageHtml = new FiltrageHtmlMP
  
  def resultats(url: String, exp: Expression): List[(String, String)] = {

    val notreHtml: Html = OutilsWebObjet.obtenirHtml(url)
    
    val listeURLs: List[String] = objFiltrageUrls.filtreAnnonce(notreHtml) // liste d'url
    var listeHtml: List[Html] = Nil
    println(listeURLs)
     OutilsWebObjet.obtenirHtml(listeURLs(0)) +: listeHtml
    
    println(listeHtml)
    var listeURLHtml: List[(String, String)] = null
    for (i <- 0 to listeHtml.size-1) {
      if (objFiltrageHtml.filtreHtml(listeHtml(i), exp)) {
        (listeURLs(i), listeHtml(i)) :: listeURLHtml
      }
    }

    return listeURLHtml

  }
}