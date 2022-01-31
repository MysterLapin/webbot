package htmlVersString
import library.HtmlVersString
import library.Html
import library.Texte
import library.Tag

class HtmlVersStringIMP extends HtmlVersString {
  
  /**
  * 
  * Transforme une liste de tuples de String en un String
  * @param List[(String,String)]
  * @return String
  */
  private def listeTupleStringtoString(lt : List[(String,String)] ) : String = {
    lt match {
      case List() => "" 
      case List(x) => " "+x._1 +"=\""+ x._2+"\""
    }
  }
 /**
  * Parcours une liste d'Html et les renvoies en String
  * @param List[Html]
  * @return String
  */
  private def ListHtoS(lh : List[Html] ) :String = {
    var res: String  = ""
      for( x <- lh){
        res.+=(traduire(x) )
      }
    res
  }
  /**
   * Produit la chaîne de caractère correspondant à un document Html
   *
   * @param h le document Html
   * @return la chaîne de caractère représentant h
   */
  def traduire(h: Html): String = {
    var s:String = ""
     h match {
       case Texte(x) => x
       case Tag(x,y,z) => s = "<" + x +  listeTupleStringtoString(y)  + ">" + ListHtoS(z) +  "</" + x +">"               
     }  
   
     s
  }

}