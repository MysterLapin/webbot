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
 
  /** 
   *  A partir d'une URL de requête sur le site de référence et d'une expression exp,
   *  retourne une liste de pages issues de la requête et satisfaisant l'expression
   * 
   * @param url l'URL de la requête
   * @param exp l'expression à vérifier sur les pages trouvées
   * @return la liste des couples (titre, ref) où ref est l'URL d'une page satisfaisant l'expression et titre est son titre
   */
  def resultats(url: String, exp: Expression): List[(String, String)] = {

    val notreHtml: Html = OutilsWebObjet.obtenirHtml(url)
    
    val listeURLs: List[String] = objFiltrageUrls.filtreAnnonce(notreHtml) // liste d'url
    
    var listeHtml: List[Html] = creerListeHtml(listeURLs)
    
   //println("liste Html" + listeHtml)
   
    var listeURLHtml: List[(String, Html)] = creerListeURLHtml(listeHtml, listeURLs, exp)
     println("listeURLHtml: " + listeURLHtml)
    return ("","")::Nil
  }
  
  /**
   * Après l'entrée de la requête, on construit une liste de couples (URL, Html) 
   * correspondant au résultat de la recherche de l'expression
   * 
   * 
   * @param lHtml une liste d'Html
   * @param lURLs une liste d'URLs 
   * @param exp 
   * @return une liste de couple (String, Html)
   */
  def creerListeURLHtml(lHtml: List[Html], lURLs: List[String], exp: Expression): List[(String, Html)] = {
    (lHtml, lURLs) match{
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (h1::t1, h2::t2) => if (objFiltrageHtml.filtreHtml(h1, exp)) {println("true"); return (h2,h1)::creerListeURLHtml(t1, t2, exp)} else return creerListeURLHtml(t1, t2, exp)
    }
  }
  
  /**
   * Creer une liste d'Html à partir de la liste de string
   * 
   * @param l List[String]
   * @return une liste d'Html
   */
  def creerListeHtml(l: List[String]): List[Html] = {
    l match{
      case Nil => Nil
      case h::t => OutilsWebObjet.obtenirHtml(h) :: creerListeHtml(t)
    }
  }
}