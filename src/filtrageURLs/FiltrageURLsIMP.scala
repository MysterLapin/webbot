package filtrageURLs
import library.FiltrageURLs
import library.Html
import library.Tag
import library.Texte

class FiltrageURLsIMP extends FiltrageURLs {


  /** 
   *  A partir d'un document Html h, rend la liste des URLs accessibles à partir 
   *  de h (ces URLs sont des hyperliens h) tels que ces URLs sont tous des URLs
   *  d'annonces du site de référence
   *  
   *@param h le document Html
   *@return la liste des URLs d'annonces contenues dans h
   */
  def filtreAnnonce(h: Html): List[String] = {
    h match {
      //recherche du motif href designant une url
      case Tag(a, ("href", r ) :: _, l) => if (r.startsWith("https://search.vivastreet.com")) return r :: fonctionAux(l) else return fonctionAux(l)
      case Tag(a, _, l)                => return fonctionAux(l)
      case _                           => Nil
    }
  }
  
  //fonction servant dans les cas de liste de Html cette fonction appelle filtreAnnonce sur chaque element de la liste de Html puis retourne la liste creer
  /**
   * Fonction servant dans les cas de liste de Html cette fonction appelle filtreAnnonce sur 
   * chaque element de la liste de Html puis retourne la liste créée
   * 
   * @param l une liste d'Html
   * @return une liste d'URL
   */
  private def fonctionAux(l: List[Html]): List[String] = {
    l match {
      case Tag(x, y, z) :: t => return filtreAnnonce(Tag(x, y, z)) ++ fonctionAux(t)
      case _                 => return Nil
    }
  }
}