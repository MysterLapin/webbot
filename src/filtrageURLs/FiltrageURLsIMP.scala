package filtrageURLs
import library.FiltrageURLs
import library.Html
import library.Tag
import library.Texte

class FiltrageURLsIMP extends FiltrageURLs {

  def filtreAnnonce(h: Html): List[String] = {
    h match {
      //recherche du motif href designant une url
      case Tag(a, ("href", r) :: _, l) => return r :: fonctionAux(l)
      case Tag(a, _, l)                => return fonctionAux(l)
      case _                           => Nil
    }
  }

  //fonction servant dans les cas de liste de Html cette fonction appelle filtreAnnonce sur chaque element de la liste de Html puis retourne la liste creer
  private def fonctionAux(l: List[Html]): List[String] = {
    l match {
      case Tag(x, y, z) :: t => return filtreAnnonce(Tag(x, y, z)) ++ fonctionAux(t)
      case _                 => return Nil
    }
  }
}