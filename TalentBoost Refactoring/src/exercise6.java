//Wrong
public class Course {
	private List<Student> courseUsers;

	public void enrollStudent(Student s) {
		courseUsers.add(s);
	}
}

//Коментар:
//Според мен проблемът тук е, че не става ясно дали в този курс има само ученици или и други позиции
//Вариантът е да има един базов клас, който да бъде наследяван от ученици, организатори, учители и т.н.
// или просто член-данната courseUsers да стане на Student

//Right
public class Course {
	private List<Student> courseStudents;

	public void enrollStudent(Student s) {
		courseUsers.add(s);
	}
}