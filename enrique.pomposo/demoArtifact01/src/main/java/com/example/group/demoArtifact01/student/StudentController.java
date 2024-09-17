package com.example.group.demoArtifact01.student;

/*import com.example.group.demoArtifact01.student.openweather.Weather;*/
import com.example.group.demoArtifact01.student.openweather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final WeatherService weatherService;

    @Autowired
    public StudentController (StudentService studentService, WeatherService weatherService) {
        this.studentService = studentService;
        this.weatherService = weatherService;
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }



    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("running controller method registerNewStudent");
        System.out.println("student to insert: " + student);
        studentService.addNewStudent(student);
    }

    @PostMapping("/weather")
    public void storeWeather(@RequestParam Double lat, @RequestParam Double lon, @RequestParam String appid){
        System.out.println("running controller method storeWeather");

        System.out.println("lat: " + lat);
        System.out.println("lon: " + lon);
        System.out.println("appid: " + appid);
        System.out.println("weather to insert: "  );
        /*Double latitude= 19.45105d;
        Double longitude= -99.125519d;*/
        Double latitude= lat;
        Double longitude= lon;
        weatherService.getWeatherDetails( latitude,  longitude, appid);
    }


}
