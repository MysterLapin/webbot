package filtrageHtml

import org.junit.Assert._
import org.junit.Test
import library._

class testFiltrageHtmlIMP {
  
  val expr1 : Expression = Mot("moto")
  val expr2 : Expression = Et(Mot("moto"), Mot("noire"))
  val expr3 : Expression = Et(Mot("moto"), Mot("bleue"))
  val expr4 : Expression = Et(Et(Mot("moto"), Mot("noire")), Mot("125cm"))
  val expr5 : Expression = Et(Mot("moto"), Ou(Mot("50cm"),Mot("125cm")))
  val expr6 : Expression = Et(Et(Mot("voiture"), Mot("noire")), Mot("125chevaux"))
  
  val texteHtml1 : Html = Texte("")
  
  val texteHtml2 : Html = Texte("moto noire")
 
  val texteHtml3 : Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("Annonce moto 125cm cube"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("p", List(),
            List(Texte("Annonces pour une moto  d'occasion bleue  de 125cm cube")))))))))
            
  val texteHtml4 : Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("Annonce moto 50cm cube"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("p", List(),
            List(Texte("  Annonces pour une  moto d'occasion verte de 55cm cube  ")))))))))
            
  val texteHtml5 : Html = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("Annonce voiture 125chevaux"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("p", List(),
            List(Texte("Annonces  pour une voiture d'occasion noire de 125chevaux")))))))))
  
  val texteString1 : String = ""
  val texteString2 : String = "moto bleue d'occasion"
  val texteString3 : String = " voiture      "
  val texteString4 : String = "télévision 4k en parfait état"
  
  val objectFiltrageHtmlIMP = new FiltrageHtmlIMP
  
  @Test
  def testDecoupageDuString1 {
    assertEquals(List(), objectFiltrageHtmlIMP.decoupageDuString(texteString1))
  }
  
  @Test
  def testDecoupageDuString2 {
    assertEquals(List("moto", "bleue", "d'occasion"), objectFiltrageHtmlIMP.decoupageDuString(texteString2))
   }
  
  @Test
  def testDecoupageDuString3 {
    assertEquals(List("voiture"), objectFiltrageHtmlIMP.decoupageDuString(texteString3))
  }
  
  @Test
  def testDecoupageDuString4 {
    assertEquals(List("télévision", "4k", "en", "parfait", "état"), objectFiltrageHtmlIMP.decoupageDuString(texteString4))
  }
  
  val listString1 : List[String] = Nil
  val listString2 : List[String] = List("voiture")
  val listString3 : List[String] = List("moto")
  val listString4 : List[String] = List("moto", "noire")
  val listString5 : List[String] = List("voiture", "poele", "125cm", "livre", "noire", "moto")
  val listString6 : List[String] = List("voiture", "poele", "livre", "bleue", "bateau")
  val listString7 : List[String] = List("moto", "poele", "125cm", "bleue", "bateau")
  
  @Test
  def testAnalyseTexte1 {
    assertEquals(false, objectFiltrageHtmlIMP.annalyseTexte(listString1, expr1))
  }
  
  @Test
  def testAnalyseTexte2 {
    assertEquals(false, objectFiltrageHtmlIMP.annalyseTexte(listString2, expr1))
  }
  
  @Test
  def testAnalyseTexte3 {
    assertEquals(true, objectFiltrageHtmlIMP.annalyseTexte(listString3, expr1))
  }
  
  @Test
  def testAnalyseTexte4 {
    assertEquals(false, objectFiltrageHtmlIMP.annalyseTexte(listString3, expr2))
  }
  
  @Test
  def testAnalyseTexte5 {
    assertEquals(true, objectFiltrageHtmlIMP.annalyseTexte(listString4, expr2))
  }

  @Test
  def testAnalyseTexte6 {
    assertEquals(true, objectFiltrageHtmlIMP.annalyseTexte(listString5, expr4))
  }
  
  @Test
  def testAnalyseTexte7 {
    assertEquals(true, objectFiltrageHtmlIMP.annalyseTexte(listString5, expr5))
  }
  
  @Test
  def testAnalyseTexte8 {
    assertEquals(false, objectFiltrageHtmlIMP.annalyseTexte(listString6, expr5))
  }
  
  @Test
  def testAnalyseTexte9 {
    assertEquals(false, objectFiltrageHtmlIMP.annalyseTexte(listString7, expr4))
  }
  
  
  @Test
  def testFiltreHtml1 {
    assertEquals(false, objectFiltrageHtmlIMP.filtreHtml(texteHtml1, expr1))   
  }
  
  @Test
  def testFiltreHtml2 {
    assertEquals(true, objectFiltrageHtmlIMP.filtreHtml(texteHtml2, expr1))   
  }
  
  @Test
  def testFiltreHtml3 {
    assertEquals(false, objectFiltrageHtmlIMP.filtreHtml(texteHtml1, expr2))   
  }
  
  @Test
  def testFiltreHtml4 {
    assertEquals(true, objectFiltrageHtmlIMP.filtreHtml(texteHtml2, expr2))   
  }
  
  @Test
  def testFiltreHtml5 {
    assertEquals(false, objectFiltrageHtmlIMP.filtreHtml(texteHtml2, expr3))   
  }
  
  @Test
  def testFiltreHtml6 {
    assertEquals(false, objectFiltrageHtmlIMP.filtreHtml(texteHtml3, expr4))   
  }
  
  @Test
  def testFiltreHtml7 {
    assertEquals(true, objectFiltrageHtmlIMP.filtreHtml(texteHtml3, expr5))   
  }
  
  @Test
  def testFiltreHtml8 {
    assertEquals(true, objectFiltrageHtmlIMP.filtreHtml(texteHtml5, expr6))   
  }
  
}