package filtrageHtml

import library.FiltrageHtml
import library.Html
import library.Expression
import library.Tag
import library.Texte
import library.Et
import library.Mot
import library.Ou

class FiltrageHtmlIMP extends FiltrageHtml {

  override def filtreHtml(h: Html, e: Expression): Boolean = {
    h match {
      case Texte(t) => annalyseTexte(decoupageDuString(t),e)
      case Tag(_, _, listHtml) => fonctionAux(listHtml, e)
    }
  }

  private def fonctionAux(l: List[Html], e: Expression): Boolean = {
    l match {
      case Nil => false
      case Texte(t) :: r => annalyseTexte(decoupageDuString(t), e) || fonctionAux(r, e)
      case Tag(_, _, listHtml) :: r => fonctionAux(listHtml, e) || fonctionAux(r, e)}
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
    val result = retour.filter(_ != "")
    result
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
}