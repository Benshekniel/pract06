object StudentRecords {
  def main(args: Array[String]): Unit = {
    val studentRecord = getStudentInfoWithRetry()
    printStudentRecord(studentRecord)
  }

  // Function a: getStudentInfo
  def getStudentInfo(name: String, marks: Int, totalMarks: Int): (String, Int, Int, Double, Char) = {
    val percentage = (marks.toDouble / totalMarks) * 100
    val grade = percentage match {
      case p if p >= 90 => 'A'
      case p if p >= 75 => 'B'
      case p if p >= 50 => 'C'
      case _ => 'D'
    }
    (name, marks, totalMarks, percentage, grade)
  }

  // Function b: printStudentRecord
  def printStudentRecord(record: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, totalMarks, percentage, grade) = record
    println(s"Student Name: $name")
    println(s"Marks: $marks")
    println(s"Total Possible Marks: $totalMarks")
    println(f"Percentage: $percentage%.2f%%")
    println(s"Grade: $grade")
  }

  // Function c: validateInput
  def validateInput(name: String, marks: String, totalMarks: String): (Boolean, Option[String]) = {
    if (name.trim.isEmpty) {
      (false, Some("Name cannot be empty"))
    } else if (!marks.forall(_.isDigit) || marks.toInt < 0 || !totalMarks.forall(_.isDigit) || totalMarks.toInt <= 0) {
      (false, Some("Marks and total possible marks must be positive integers"))
    } else if (marks.toInt > totalMarks.toInt) {
      (false, Some("Marks cannot exceed total possible marks"))
    } else {
      (true, None)
    }
  }

  // Function d: getStudentInfoWithRetry
  def getStudentInfoWithRetry(): (String, Int, Int, Double, Char) = {
    var isValid = false
    var name, marks, totalMarks = ""
    var errorMessage: Option[String] = None

    while (!isValid) {
      println("Enter student's name:")
      name = scala.io.StdIn.readLine()
      println("Enter student's marks:")
      marks = scala.io.StdIn.readLine()
      println("Enter total possible marks:")
      totalMarks = scala.io.StdIn.readLine()

      val validation = validateInput(name, marks, totalMarks)
      isValid = validation._1
      errorMessage = validation._2

      if (!isValid) {
        println(s"Invalid input: ${errorMessage.getOrElse("")}. Please try again.")
      }
    }

    getStudentInfo(name, marks.toInt, totalMarks.toInt)
  }
}
