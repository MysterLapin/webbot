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
      case Tag(_, _, Texte(a) :: t)   => annalyseTexte(a.split(" ").toList, e) || fonctionAux(t, e)
      case Tag(_, _, Texte(a) :: Nil) => annalyseTexte(a.split(" ").toList, e)
      case _                          => print("chelou"); return false;
    }
  }

  def annalyseTexte(l: List[String], e: Expression): Boolean = {
    (e,l) match {
      case (_, Nil) => false
      case (Mot(_), h::t)  => return e.equals(Mot(h)) || annalyseTexte(t, e)
      case (Et(a, b), l) => annalyseTexte(l, a) && annalyseTexte(l, b)
      case (Ou(a, b), l) => annalyseTexte(l, a) || annalyseTexte(l, b)
    }
  }
  def fonctionAux(l: List[Html], e: Expression): Boolean = {
    l match {
      case Tag(x, y, z) :: Nil => return filtreHtml(Tag(x, y, z), e)
      case Tag(x, y, z) :: t   => return filtreHtml(Tag(x, y, z), e) || fonctionAux(t, e)
      case _                   => return false
    }
  }
}