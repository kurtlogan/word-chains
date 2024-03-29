package wordchains

object WordChain {

  def hammingDistance(str1: String, str2: String): Int = {

    val difference = (str1.length - str2.length).abs

    str1.zip(str2).filterNot{
      case (c1,c2) => c1==c2
    }.size + difference

  }

  def buildChain(word1: String, word2: String, dictionary: List[String]): String =
    if (word1 == word2) {
      List(word1)
    } else {
      val newWord = dictionary.map { w =>
        hammingDistance(word1, w) == 1 &&
          hammingDistance(word1, word2) > hammingDistance(word2, w)
      }.head

      word1 :: buildChain(newWord, word2, dictionary)
    }

  def run(word1: String, word2: String, dictionary: List[String]): List[String] = {
    if(!dictionary.contains(word1)) {
      Nil
    }
    else {
      buildChain(word1, word2, filterDictionary(word1.length, dictionary))
    }
  }

  def filterDictionary(wordLength: Int, dictionary: List[String]): List[String] =
    dictionary.filter(_.length == wordLength)
}