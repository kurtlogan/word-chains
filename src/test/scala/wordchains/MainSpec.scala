package wordchains

import org.scalatest._

import scala.io.Source

class MainSpec extends WordSpec with Matchers {

  val testDictionary = Source.fromResource("popular.txt").getLines().toList

  "cat to cat" in {

    WordChain.run("cat", "cat", testDictionary) shouldBe List("cat")
  }

  "cat to vat" in {
    WordChain.run("cat", "vat", testDictionary) shouldBe List("cat", "vat")
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
    WordChain.run("cat", "cot", testDictionary) shouldBe List("cat", "cot")
  }

  "hamming distance returns 1 when the words have one difference" in {
    WordChain.hammingDistance("cat", "cot") shouldBe 1
  }

  "hamming distance returns 2 when the words have one difference" in {
    WordChain.hammingDistance("cat", "cod") shouldBe 2
  }


}
