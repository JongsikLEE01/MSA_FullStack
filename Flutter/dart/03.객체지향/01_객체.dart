class Person {
  // 변수
  String name;
  int age;

  // 생성자
  Person(this.name, this.age);

  // 메소드
  void test() {
    print("나는 $name($age) 입니다.");
  }
}

void main() {
  // 객체 생성
  var person1 = Person("홍길순", 25);
  var person2 = Person("홍길동", 20);

  person1.test();
  person2.test();
}
