package com.myEnocaChal.myEnocaChal.Repository;

import com.myEnocaChal.myEnocaChal.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e where e.age=:age and e.working_year=:working_year")
    List<Employee> getEmployeesByAgeAndWorkYear(@Param("age") Integer age , @Param("working_year") Integer working_year);


}
