package Weavin.Controllers;

import Weavin.Entities.User;
import Weavin.Enums.Presence;
import Weavin.Enums.ReportStatus;
import Weavin.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //GET request to get specific user information, including their posts, comments and classes
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User userToBeFound = userOptional.get();
        return userToBeFound;
    }

    //POST request to create a user
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private void createUser(@RequestBody User user) {
        this.userRepository.save(user);
    }

    //DELETE request to delete a user
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User userToBeDeleted = userOptional.get();
        this.userRepository.delete(userToBeDeleted);
    }

    //PUT request to update user's presence status
    @PutMapping("/{id}/presence/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePresence(@PathVariable Integer id, @PathVariable String status) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        if (status.equals("offline")) {
            user.setPresence(Presence.OFFLINE);
        } else if (status.equals("online")) {
            user.setPresence(Presence.ONLINE);
        } else if (status.equals("idle")) {
            user.setPresence(Presence.IDLE);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Presence type not found");
        }
    }

    //PUT request to update user's last-seen-at field
    @PutMapping("/{id}/last-seen-at")
    @ResponseStatus(HttpStatus.OK)
    public void updateLastSeen(@PathVariable Integer id, @RequestBody Date date) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        user.setLastSeenAt(date);
    }

    //PUT request to update user's password
    @PutMapping("/{id}/password/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable Integer id, @RequestBody String password) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        user.setPassword(password);
    }

    //PUT request to update user's username (can only be done once)

    @PutMapping("/{id}/username/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUsername(@PathVariable Integer id, @RequestBody String username) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        if (!user.isUsernameAlreadyChanged()) {
            user.setUsername(username);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User has already changed username once");
        }
    }

    //PUT request to update user's profile information
    @PutMapping("/{id}/profile")
    @ResponseStatus(HttpStatus.OK)
    public void updateProfile(@PathVariable Integer id, @RequestBody User newUser) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        user.setProfilePhoto(newUser.getProfilePhoto());
        user.setField(newUser.getField());
    }

    //PUT request to report a user
    @PutMapping("/{id}/report")
    @ResponseStatus(HttpStatus.OK)
    public void reportUser(@PathVariable Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        user.setReports(user.getReports() + 1);
        if (user.getReports() >= 15) {
            user.setReportStatus(ReportStatus.DANGEROUS);
        } else if (user.getReports() >= 7) {
            user.setReportStatus(ReportStatus.WARNING);
        }
    }

    //GET request to get user's current report status
    @GetMapping("/{id}/reportstatus")
    @ResponseStatus(HttpStatus.OK)
    public ReportStatus getReportStatus(@PathVariable Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = userOptional.get();
        return user.getReportStatus();
    }
}

