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
    h match {
      case Tag(_, _, Texte(a) :: t) =>
        println(decoupageDuString(a)); annalyseTexte(decoupageDuString(a), e) || fonctionAux(t, e)
      case Tag(_, _, Texte(a) :: Nil) => annalyseTexte(decoupageDuString(a), e)
      case Texte(a) =>
        println(decoupageDuString(a)); annalyseTexte(decoupageDuString(a), e)
      case _ => false
    }
  }

  private def decoupageDuString(s: String): List[String] = {
    val temp = s.split(" ").toList
    suppressionEspaces(temp)
  }

  private def suppressionEspaces(s: List[String]): List[String] = {
    s match {
      case Nil    => Nil
      case h :: t => h.trim() :: suppressionEspaces(t)
    }
  }

  private def annalyseTexte(l: List[String], e: Expression): Boolean = {
    (e, l) match {
      case (_, Nil)         => false
      case (Mot(_), h :: t) => e.equals(Mot(h)) || annalyseTexte(t, e)
      case (Et(a, b), l)    => annalyseTexte(l, a) && annalyseTexte(l, b)
      case (Ou(a, b), l)    => annalyseTexte(l, a) || annalyseTexte(l, b)
    }
  }
  private def fonctionAux(l: List[Html], e: Expression): Boolean = {
    l match {
      case Tag(x, y, z) :: Nil => filtreHtml(Tag(x, y, z), e)
      case Tag(x, y, z) :: t   => filtreHtml(Tag(x, y, z), e) || fonctionAux(t, e)
      case _                   => false
    }
  }
}