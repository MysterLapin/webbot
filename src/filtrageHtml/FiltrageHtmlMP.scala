package filtrageHtml

import library.FiltrageHtml
import library.Html
import library.Expression
import library.Tag
import library.Texte
import library.Et
import library.Mot
import library.Ou

class FiltrageHtmlMP extends FiltrageHtml {
  
  /** 
   *  A partir d'un document Html h et d'une requête e, dit si le document
   *  satisfait l'expression e
   *  
   * @param h le document Html
   * @param e l'expression
   * @return true si le document satisfait l'expression e
   */
  def filtreHtml(h: Html, e: Expression): Boolean = {
    h match {
      case Tag(_, _, Texte(a) :: Nil) => annalyseTexte(decoupageDuString(a), e)
      case Tag(_, _, Texte(a) :: t) =>
        println(decoupageDuString(a)); annalyseTexte(decoupageDuString(a), e) || fonctionAux(t, e)
      case Texte(a) =>
        println(decoupageDuString(a)); annalyseTexte(decoupageDuString(a), e)
    }
  }
  
  /** 
   *  On prend une chaine de caractères qu'on découpe puis
   *  on renvoie chaque élément découpé dans la liste de string
   *  
   * @param s une chaine de caractères
   * @return une liste de chaine de caractères
   */
  private def decoupageDuString(s: String): List[String] = {
    val temp = s.split(" ").toList
    suppressionEspaces(temp)
  }
  
  /**
   * On modifie une chaine de caractères en supprimant les espaces
   * puis on renvoie la chaine
   * 
   * @param s une liste de chaine de caractères
   * @return la liste de caractères
   */
  private def suppressionEspaces(s: List[String]): List[String] = {
    s match {
      case Nil    => Nil
      case h :: t => h.trim() :: suppressionEspaces(t)
    }
  }
  
  /**
   * Vérifie si la liste contient la requête avec Et et Ou
   * 
   * @param l une liste de chaine de caractères
   * @param e l'expression
   * @return un boolean
   */
  private def annalyseTexte(l: List[String], e: Expression): Boolean = {
    (e, l) match {
      case (_, Nil)         => false
      case (Mot(_), h :: t) => e.equals(Mot(h)) || annalyseTexte(t, e)
      case (Et(a, b), l)    => annalyseTexte(l, a) && annalyseTexte(l, b)
      case (Ou(a, b), l)    => annalyseTexte(l, a) || annalyseTexte(l, b)
    }
  }
  
  /**
   * Vérifie si le tag contient un 3e élément
   * 
   * @param l une liste d'Html
   * @param e une requête
   * @return un boolean
   */
  private def fonctionAux(l: List[Html], e: Expression): Boolean = {
    l match {
      case Tag(x, y, z) :: Nil => filtreHtml(Tag(x, y, z), e)
      case Tag(x, y, z) :: t   => filtreHtml(Tag(x, y, z), e) || fonctionAux(t, e)
      case _                   => false
    }
  }
}