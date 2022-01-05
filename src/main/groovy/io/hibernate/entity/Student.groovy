package io.hibernate.entity

import groovy.transform.Canonical

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="student")
@Canonical
class Student {

    @Id
    @Column(name="id")
    int id

    @Column(name="first_name")
    String firstName

    @Column(name="last_name")
    String lastName

    @Column(name="email")
    String email

    Student () {}

    Student(String firstName, String lastName, String email) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }


}
