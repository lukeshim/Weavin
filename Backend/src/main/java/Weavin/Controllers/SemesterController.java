package Weavin.Controllers;

import Weavin.Entities.Course;
import Weavin.Entities.Semester;
import Weavin.Repositories.SemesterRepository;
import Weavin.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Semester findById(@PathVariable Integer id) {
        Optional<Semester> semesterOptional = this.semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        return semesterOptional.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createSemester(@RequestBody Semester semester) {
        this.semesterRepository.save(semester);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSemester(@PathVariable Integer id) {
        Optional<Semester> semesterOptional = this.semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        this.semesterRepository.delete(semesterOptional.get());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSemester(@PathVariable Integer id, @RequestBody Semester newSemesterData) {
        Optional<Semester> semesterOptional = this.semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        Semester semester = semesterOptional.get();
        semester.setUser(newSemesterData.getUser());
        semester.setPreSuGpa(newSemesterData.getPreSuGpa());
        semester.setPostSuGpa(newSemesterData.getPostSuGpa());
        semester.setCourseList(newSemesterData.getCourseList());
        this.semesterRepository.save(semester);
    }

    @PutMapping("/{id}/update-gpa")
    @ResponseStatus(HttpStatus.OK)
    public void updateGPA(@PathVariable Integer id, @RequestBody UpdateGPARequest gpaRequest) {
        Optional<Semester> semesterOptional = semesterRepository.findById(id);
        if (!semesterOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Semester not found.");
        }
        Semester semester = semesterOptional.get();
        semester.setPreSuGpa(gpaRequest.getPreSuGpa());
        semester.setPostSuGpa(gpaRequest.getPostSuGpa());
        semesterRepository.save(semester);
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

    static class UpdateGPARequest {
        private double preSuGpa;
        private double postSuGpa;
        public double getPreSuGpa() { return preSuGpa; }
        public void setPreSuGpa(double preSuGpa) { this.preSuGpa = preSuGpa; }
        public double getPostSuGpa() { return postSuGpa; }
        public void setPostSuGpa(double postSuGpa) { this.postSuGpa = postSuGpa; }
    }
}
