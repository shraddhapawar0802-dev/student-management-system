import java.util.*;
import java.io.*;
class Student
{
    int rollNo;
    String name;
    String course;

    Student(int rollNo, String name, String course)
    {
        this.rollNo =rollNo;
        this.name= name;
        this.course= course;
    }
    void display()
    {
        System.out.println(rollNo+" |"+name+" |"+course);
    }
}
    class StudentManagement
    {
        static ArrayList<Student> students = new ArrayList<>();
        static final String FILE_NAME = "students.txt";

        public static void main(String [] args)
        {
            Scanner sc = new Scanner(System.in);
            loadFromFile();

            while(true)
            {
                System.out.println("---Student Management System---");
                System.out.println("1.Add Student");
                System.out.println("2.View Student");
                System.out.println("3.Remove Student");
                System.out.println("4.Search Student");
                System.out.println("5.Update Student");
                System.out.println("6.Exit");
                
                System.out.println("Enter Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch(choice)
                {
                    case 1:
                        addStudent(sc);
                        break;
                    case 2:
                        viewStudent();
                        break;
                    case 3:
                        removeStudent(sc);
                        break;
                    case 4:
                        searchStudent(sc);
                        break;
                    case 5:
                        updateStudent(sc);
                        break;
                    case 6:
                        saveToFile();
                        System.out.println("Data saved. Exiting...");
                        return;
                    default:
                        System.out.println("Invalid Choice!");
                }
            }
        }
        static void addStudent(Scanner sc)
        {
            System.out.println("Enter name: ");
            String name = sc.nextLine();

            System.out.println("Enter Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Course: ");
            String course = sc.nextLine();

            for(Student s : students)
            {
                if(s.rollNo== roll)
                {
                System.out.println("Student with his roll no already exists!");
                return;
                }
            }
            students.add(new Student(roll,name,course));
            System.out.println("Student added Successfully!");
        }

        static void viewStudent()
        {
            if(students.isEmpty())
            {
                System.out.println("No Student Found!");
                return;
            }
            System.out.println("\n---Student List---");
            System.out.println("Name | Roll No | Course");
            System.out.println("----------------------------");
            for(Student s:students)
            {
                s.display();
            }
        }
        static void removeStudent(Scanner sc)
        {
            System.out.println("Enter roll no to remove: ");
            int roll = sc.nextInt();

            boolean found = false;
            
            Iterator<Student> it = students.iterator();
            while(it.hasNext())
            {
                Student s= it.next();
                if(s.rollNo==roll)
                {
                  it.remove();
                  found = true;
                  System.out.println("Student remove!");
                  break;
                }
            }
            if(!found)
            {
                System.out.println("Student not found!");
            }
        }

        static void searchStudent(Scanner sc)
        {
            System.out.println("Enter name to Search: ");
            String name = sc.nextLine();

            boolean found = false;

            for(Student s: students)
            {
                if(s.name.equalsIgnoreCase(name))
                {
                    System.out.println("Found");
                    s.display();
                    found = true;
                }
            }

            if(!found)
            {
                System.out.println("Student not found!");
            }
        }
        static void updateStudent(Scanner sc)
        {
            System.out.println("Enter roll no to update: ");
            int roll = sc.nextInt();
            sc.nextLine();
            for(Student s: students)
            {
                if(s.rollNo==roll)
                {
                    System.out.println("Enter new name: ");
                    s.name = sc.nextLine();
                    System.out.println("Enter new Course: ");
                    s.course = sc.nextLine();
                    System.out.println("Student Updated!");
                    return;
                }
            }
            System.out.println("Student Not Found!");
        }
        static void saveToFile()
        {
            try{
                FileWriter fw = new FileWriter(FILE_NAME);

                for(Student s: students)
                {
                    fw.write(s.name+","+s.rollNo+","+s.course+"\n");
                }
            fw.close();
            }
        catch(IOException e)
        {
            System.out.println("Error saving data!");
        }
    }
    static void loadFromFile()
    {
        try{
            File file = new File(FILE_NAME);
            if(!file.exists())return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine())!=null)
            {
                String[] data = line.split(",");
                students.add(new Student(
                    Integer.parseInt(data[1]),
                    data[0],
                    data[2]
                ));
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Loading data!");
        }
    }
}

