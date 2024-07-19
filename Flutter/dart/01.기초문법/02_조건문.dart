void main() {
  // if 문
  int number = 100;
  if (number > 0) {
    print("양수입니다...");
  } else if (number < 0) {
    print("음수입니다...");
  } else {
    print("0입니다...");
  }

  // switch 문
  String grade = "B";
  switch (grade) {
    case "A":
      print("학점은 A입니다...");
      break;
    case "B":
      print("학점은 B입니다...");
      break;
    case "C":
      print("학점은 C입니다...");
      break;
    default:
      print("유효하지 않습니다...");
  }
}
