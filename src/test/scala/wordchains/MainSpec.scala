package wordchains

import org.scalatest._

import scala.io.Source

class MainSpec extends WordSpec with Matchers {

  val testDictionary: List[String] = Source.fromResource("popular.txt").getLines().toList

  "WordChain.run" should {

    "cat to cat" in {

      WordChain.run("cat", "cat", testDictionary) shouldBe List("cat")
    }

    "cat to vat" in {
      WordChain.run("cat", "vat", List("cat", "vat")) shouldBe List("cat", "vat")
    }

    "cat to zat" in {
      WordChain.run("cat", "zat", testDictionary) shouldBe Nil
    }


    "returns an empty list when input and output do not match in size" in {
      WordChain.run("one", "three", testDictionary) shouldBe Nil
    }

    "returns an empty list when input does not exist in dictionary" in {
      WordChain.run("cat", "vat", List("vat")) shouldBe Nil
    }

    "returns an empty list when output does not exist in the dictionary" in {
      WordChain.run("cat", "car", List("cat", "vat")) shouldBe Nil
    }

    "cat to cot" in {
      WordChain.run("cat", "cot", List("cat", "cot")) shouldBe List("cat", "cot")
    }

    "cat to cot to dot" in {
      WordChain.run("cat", "dot", List("cat", "cot", "dot")) shouldBe List("cat", "cot", "dot")
    }
  }

  "WordChain.hammingDistance" should {

    "return 1 when the words have one difference" in {
      WordChain.hammingDistance("cat", "cot") shouldBe 1
    }

    "return 2 when the words have one difference" in {
      WordChain.hammingDistance("cat", "cod") shouldBe 2
    }

    "return 1 when one word is one character longer than the other" in {
      WordChain.hammingDistance("cat", "cats") shouldBe 1
    }
  }

  "WordChain.filterDictionary" should {

    "filter the dictionary to only include strings of the specified length" in {
      WordChain.filterDictionary(3, List("cat", "badger", "ferret", "lizard")) shouldBe List("cat")
    }
  }


}
