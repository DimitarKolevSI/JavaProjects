import com.pluralsight.model.Speaker;
import com.pluralsight.repisotiry.HibernateSpeakerRepositoryImpl;
import com.pluralsight.repisotiry.SpeakerRepository;
import com.pluralsight.service.SpeakerService;
import com.pluralsight.service.SpeakerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfig {
    private String[] firstNames = new String[]{"Dimitar","Ivan","Petar"};
    private String[] lastNames = new String[]{"Dimitrov","Ivanov","Petrov"};

    @Bean(name = "speakerService")
    public SpeakerService getSpeakerService(){
        //Setter injection
        SpeakerServiceImpl service = new SpeakerServiceImpl();
        service.setRepository(getSpeakerRepository());
        return service;
    }

    @Bean(name = "speakerRepository")
    public SpeakerRepository getSpeakerRepository(){
        HibernateSpeakerRepositoryImpl repository = new HibernateSpeakerRepositoryImpl();
        repository.addSpeaker(generateSpeakers());
        return repository;
    }

    @Bean(name = "generateSpeaker")
    public Speaker generateSpeakers(){
        Random random = new Random();
        int firstNameIndex = random.nextInt(firstNames.length);
        int lastNameIndex = random.nextInt(lastNames.length);
        Speaker speaker = new Speaker();
        speaker.setFirstName(firstNames[firstNameIndex]);
        speaker.setLastName(lastNames[lastNameIndex]);
        return speaker;
    }
}
