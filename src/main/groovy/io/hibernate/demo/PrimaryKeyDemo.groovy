package io.hibernate.demo

import io.hibernate.config.HibernateConfig
import io.hibernate.entity.Student
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class PrimaryKeyDemo {
    static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean("sessionFactory")


        Session session = sessionFactory.getCurrentSession()

        try {

            println "Creating new Student object"

            Student tempStudent1 = new Student("John", "Doe", "Johnn@example.com")
            Student tempStudent2 = new Student("Daniel", "Vardanyan", "Daniel@example.com")
            Student tempStudent3 = new Student("Solomon", "Vardanyan", "Solomon@example.com")

            session.beginTransaction()

            println "Saving the student..."

            session.save(tempStudent1)
            session.save(tempStudent2)
            session.save(tempStudent3)

            session.getTransaction().commit()

            println "Done"
        } finally {
            sessionFactory.close()
        }
        context.close()

    }
}
