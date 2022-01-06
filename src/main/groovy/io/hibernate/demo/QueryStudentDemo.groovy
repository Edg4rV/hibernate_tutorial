package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Student
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class QueryStudentDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")

        Session session = sessionFactory.getCurrentSession()

        try {
            session.beginTransaction()

            List<Student> theStudent = session.createQuery("from Student").getResultList()

            println theStudent

            theStudent = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList()

            theStudent.each { println "Students who have last name of Doe: $it"}

            theStudent =
                    session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName='Edgar' ").getResultList()

            theStudent.each { println it }

            println "\n\n Students who email ends with example.com"


            theStudent = session.createQuery("from Student s where s.email LIKE '%example.com'").getResultList()

            theStudent.each { println it }




            session.getTransaction().commit()

            println "Done"
        } finally {
            sessionFactory.close()
        }
        context.close()
    }
}
