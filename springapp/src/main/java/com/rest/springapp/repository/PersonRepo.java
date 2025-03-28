
// package com.rest.springapp.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import java.util.List;
// import com.rest.springapp.model.Person;

// public interface PersonRepo extends JpaRepository<Person,Integer>{
//     @Modifying
//     @Query(value="insert into Person (age,first_name,last_name,gender,person_id) values(?,?,?,?,?)",nativeQuery = true)
//     void postPerson(int age,String firstName,String lastName,String gender);
//     @Query("Select a from Person a")
//     List<Person> getUsers();

//     @Query("select a from Person a where a.age=?1")
//     List<Person> getByAge(int age);
// } 
