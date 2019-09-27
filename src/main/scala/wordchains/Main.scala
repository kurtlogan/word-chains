package wordchains
import scala.io.Source

object Main extends App {

  val lines = Source.fromResource("popular.txt").getLines().toList



}

object WordChain {
  def hammingDistance(str1: String, str2: String): Int = {
    str1.zip(str2).filterNot{case (c1,c2) => c1==c2}.size

  }

  def run(word1: String, word2: String, dictionary: List[String]): List[String] = {
    if(!dictionary.contains(word1) || !dictionary.contains(word2)) {
      Nil
    }
    else if(word1.size != word2.size) {
      Nil
    }
    else if (word1 == word2){
      List(word1)
    }
    else  {
      val updatedWord =word1.updated(0,word2(0))
      if(dictionary.contains(updatedWord)){
        List(word1, updatedWord)
      }



      else Nil
    }
  }
}