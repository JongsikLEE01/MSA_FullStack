void main() {
  // for문
  for (var i = 0; i < 5; i++) {
    print("count? $i");
  }

  // while
  int count = 0;
  while (count < 5) {
    print("while count? $count");
    count++;
  }

  // doWhile
  int number = 5;
  do {
    print("number? $number");
    number--;
  } while (number > 0);

  // 배열 반복
  List<int> numList = [1, 2, 3, 4, 5];
  for (var item in numList) {
    print("item? $item");
  }

  numList.forEach((item) {
    print("forEach item? $item");
  });
}
