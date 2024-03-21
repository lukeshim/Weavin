package Weavin.Controllers;

import Weavin.Entities.Course;
import Weavin.Entities.Semester;
import Weavin.Entities.User;
import Weavin.Repositories.SemesterRepository;
import Weavin.Repositories.CourseRepository;
import Weavin.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SemesterController {

    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    // GET request to get a specific semester
    @GetMapping("/semesters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Semester findById(@PathVariable Integer id) {
        Optional<Semester> semesterOptional = this.semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        return semesterOptional.get();
    }

    // GET request to get semesters from a specific user
    @GetMapping("/users/{userId}/semesters")
    @ResponseStatus(HttpStatus.OK)
    public List<Semester> getAllSemestersByUserId(@PathVariable Integer userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        User user = userOptional.get();
        return user.getSemesterList();
    }

    // POST request to create a new semester
    @PostMapping("/semesters")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSemester(@RequestBody Semester semester) {
        this.semesterRepository.save(semester);
    }

    // DELETE request to remove a semester by id
    @DeleteMapping("/semesters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSemester(@PathVariable Integer id) {
        Optional<Semester> semesterOptional = this.semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        this.semesterRepository.delete(semesterOptional.get());
    }

    // PUT request to update semester
    @PutMapping("/semesters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSemester(@PathVariable Integer id, @RequestBody Semester newSemesterData) {
        Optional<Semester> semesterOptional = this.semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        Semester semester = semesterOptional.get();
        semester.setUser(newSemesterData.getUser());
        semester.setSeason(newSemesterData.getSeason());
        semester.setAcademicYear(newSemesterData.getAcademicYear());
        semester.setCourseList(newSemesterData.getCourseList());
        semester.setNumOfCourses(newSemesterData.getNumOfCourses());
        this.semesterRepository.save(semester);
    }

    @PostMapping("/{id}/add-course")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCourseToSemester(@PathVariable Integer id, @RequestBody Course course) {
        Optional<Semester> semesterOptional = semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        Semester semester = semesterOptional.get();
        course.setSemester(semester);

        if (semester.getCourseList() == null) {
            semester.setCourseList(new ArrayList<>());
        }
        semester.getCourseList().add(course);

        courseRepository.save(course);
        semesterRepository.save(semester);
    }

}
