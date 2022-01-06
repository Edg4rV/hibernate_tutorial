package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Student
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ReadStudentDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")

        Session session = sessionFactory.getCurrentSession()

        try {

            println "Creating new Student object"

            Student tempStudent = new Student("Edgar", "Solomon", "Solomon@example.com")

            session.beginTransaction()

            println "Saving the student..."
            println tempStudent

            session.save(tempStudent)

            session.getTransaction().commit()

            println "Saved student. Generated id: ${tempStudent.getId()}"

            session = sessionFactory.getCurrentSession()

            session.beginTransaction()

            println "\nGetting student with id:  ${tempStudent.getId()}"

            Student myStudent = session.get(Student.class, tempStudent.getId())

            println "Get Complete:  ${myStudent}"

            session.getTransaction().commit()

            println "Done"
        } finally {
            sessionFactory.close()
        }
        context.close()
    }
}
