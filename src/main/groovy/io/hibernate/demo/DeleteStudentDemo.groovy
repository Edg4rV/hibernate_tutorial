package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Student
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class DeleteStudentDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")

        Session session = sessionFactory.getCurrentSession()

        try {


            int studentId = 1


            session.beginTransaction()

            println "\nGetting student with id: ${studentId}"

            Student myStudent = session.get(Student.class, studentId)

//            println "Deleting Student: ${myStudent}"

//            session.delete(myStudent)

            println "Deleting student id=2"

            session.createQuery("delete from Student where id=2").executeUpdate()

            session.getTransaction().commit()

            println "Done"
        } finally {
            sessionFactory.close()
        }
        context.close()
    }
}
