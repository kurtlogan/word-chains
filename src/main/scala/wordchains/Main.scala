package wordchains
import scala.io.Source

object Main extends App {

  val lines = Source.fromResource("popular.txt").getLines().toList

  WordChain.run("cat", "dog", lines).foreach(println(_))
}

object WordChain {

  def hammingDistance(str1: String, str2: String): Int = {

    val difference = (str1.length - str2.length).abs

    str1.zip(str2).filterNot{
      case (c1,c2) => c1==c2
    }.size + difference

  }

  def run(word1: String, word2: String, dictionary: List[String]): List[String] = {
    if(!dictionary.contains(word1) || !dictionary.contains(word2)) {
      Nil
    }
    else if(word1.length != word2.length) {
      Nil
    }
    else if (word1 == word2){
      List(word1)
    } else {
      val newWord = dictionary.filter { w =>
        hammingDistance(word1, w) == 1 &&
        hammingDistance(word1, word2) > hammingDistance(word2, w)
      }.head

      word1 :: run(newWord, word2, dictionary)
    }
  }
}