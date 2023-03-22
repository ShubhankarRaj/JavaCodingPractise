package InheritanceChapter;
public class InheritanceDemo
{
public static void main(String[] args)
{
	Person[] people = new Person[4];
	people[0] = new Undergraduate("Cot, Man", 49, 2);
	people[1] = new Undergraduate("Lick, ani", 9456, 1);
	people[2] = new Student("raj, swap",3);
	people[3] = new Undergraduate("jan, mah", 4367, 4);
	for (Person p : people)
	{
		p.writeOutput();
		System.out.println();
	}
//Student s = new Student();
//s.setName("Warren Peace");
//s.setStudentNumber(1234);
//s.writeOutput();
}
}