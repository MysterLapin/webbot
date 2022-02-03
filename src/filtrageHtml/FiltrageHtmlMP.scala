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

  def filtreHtml(h: Html, e: Expression): Boolean = {
    print(h)
    h match {
      case Tag(_, _, Texte(a) :: Nil) =>
        annalyseTexte(decoupageDuString(a), e)
      case Tag(_, _, Texte(a) :: Nil) =>
        annalyseTexte(decoupageDuString(a), e)
      case Tag(_, _, Texte(a) :: t) =>
        annalyseTexte(decoupageDuString(a), e) || fonctionAux(t, e)
      case Tag(_, _, x) => filtreHtml(decoupageHtml(x), e)
      case Texte(a)     => annalyseTexte(decoupageDuString(a), e)
    }
  }

  private def decoupageHtml(l: List[Html]): Html = {
    l match {
      case Nil               => Texte("")
      case Tag(x, y, z) :: t => Tag(x, y, z)
      case Texte(a) :: t     => Texte(a)
    }
  }

  def decoupageDuString(s: String): List[String] = {
    val temp: List[String] = s.split(" ").toList
    val retour: List[String] = suppressionEspaces(temp)
    return retour
  }

  def suppressionEspaces(s: List[String]): List[String] = {
    s match {
      case Nil    => Nil
      case h :: t => h.replaceAll("\\s", "") :: suppressionEspaces(t)
    }
  }

   def annalyseTexte(l: List[String], e: Expression): Boolean = {
    (e, l) match {
      case (_, Nil)         => false
      case (Mot(_), h :: t) => e.equals(Mot(h)) || annalyseTexte(t, e)
      case (Et(a, b), l)    => annalyseTexte(l, a) && annalyseTexte(l, b)
      case (Ou(a, b), l)    => annalyseTexte(l, a) || annalyseTexte(l, b)
    }
  }
  
  private def fonctionAux(l: List[Html], e: Expression): Boolean = {
    l match {
      case Nil => false
      case Tag(x, y, z) :: Nil => filtreHtml(Tag(x, y, z), e)
      case Tag(x, y, z) :: t   => filtreHtml(Tag(x, y, z), e) || fonctionAux(t, e)
      case Texte(x) ::  t => filtreHtml(Texte(x) , e ) || fonctionAux( t , e ) 
    }
  }
}