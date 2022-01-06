package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Student
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class UpdateStudentDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")

        Session session = sessionFactory.getCurrentSession()

        try {


            int studentId = 1


            session.beginTransaction()

            println "\nGetting student with id: ${studentId}"

            Student myStudent = session.get(Student.class, studentId)

            println "Get complete:  ${myStudent}"

            println "Updating student...."

            myStudent.setFirstName("Isa")

            myStudent = session.get(Student.class, studentId)
            println myStudent

            session.getTransaction().commit()

            session = sessionFactory.getCurrentSession()
            session.beginTransaction()

            println " Update email for all students"

            session.createQuery("update Student set email='foo@gmail.com'")
                .executeUpdate()

            session.getTransaction().commit()

            println "Done"
        } finally {
            sessionFactory.close()
        }
        context.close()
    }
}
