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

  override def filtreHtml(h: Html, e: Expression): Boolean = {
    (e,h) match {
      case(Mot(m),Texte(t)) => motDansText(m,decoupageDuString(t)) //m present dans t
      case(Ou(a,b), h) => filtreHtml(h, a) || filtreHtml(h, b)
      case(Et(a,b), h) => filtreHtml(h, a) && filtreHtml(h, b)
      case(e, Tag(_ ,_, Nil)) => false
      case(e, Tag(_, _, h :: r)) => filtreHtml(h, e)
    }
    
  }
  
  def decoupageDuString(s: String): List[String] = {
    val temp = s.split(" ").toList
    suppressionEspaces(temp)
  }

  def suppressionEspaces(s: List[String]): List[String] = {
    s match {
      case Nil    => Nil
      case h :: t => h.trim() :: suppressionEspaces(t)
    }
  }
  
  override def motDansText(mot : String , l : List[String]) : Boolean = {
    l match {
      case Nil => false
      case h :: Nil => h==mot
      case h :: t => h==mot || motDansText(mot, t)
    }
  }
  
  
  
  
  
}