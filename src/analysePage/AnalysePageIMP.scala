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
    
    var listeHtml: List[Html] = creerListeHtml(listeURLs)
    
   //println("liste Html" + listeHtml)
   
    var listeURLHtml: List[(String, Html)] = creerListeURLHtml(listeHtml, listeURLs, exp)
     println("listeURLHtml: " + listeURLHtml)
    return ("","")::Nil
  }
  
  def creerListeURLHtml(lHtml: List[Html], lURLs: List[String], exp: Expression): List[(String, Html)] = {
    (lHtml, lURLs) match{
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (h1::t1, h2::t2) => if (objFiltrageHtml.filtreHtml(h1, exp)) {println("true"); return (h2,h1)::creerListeURLHtml(t1, t2, exp)} else return creerListeURLHtml(t1, t2, exp)
    }
  }
  
  def creerListeHtml(l: List[String]): List[Html] = {
    l match{
      case Nil => Nil
      case h::t => OutilsWebObjet.obtenirHtml(h) :: creerListeHtml(t)
    }
  }
}