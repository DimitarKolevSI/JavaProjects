import com.pluralsight.model.Speaker;
import com.pluralsight.repisotiry.HibernateSpeakerRepositoryImpl;
import com.pluralsight.repisotiry.SpeakerRepository;
import com.pluralsight.service.SpeakerService;
import com.pluralsight.service.SpeakerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.Random;

@Configuration
public class AppConfig {
    private String[] firstNames = new String[]{"Dimitar","Ivan","Petar"};
    private String[] lastNames = new String[]{"Dimitrov","Ivanov","Petrov"};

    @Bean(name = "speakerService")
    @Scope(value=BeanDefinition.SCOPE_SINGLETON)
    public SpeakerService getSpeakerService(){
        //Constructor injection
        SpeakerServiceImpl service = new SpeakerServiceImpl(getSpeakerRepository());
        //Setter injection
        //service.setRepository(getSpeakerRepository());
        return service;
    }

    @Bean(name = "speakerRepository")
    public SpeakerRepository getSpeakerRepository(){
        //Initializing new HibernateSpeakerRepositoryImpl
        HibernateSpeakerRepositoryImpl repository = new HibernateSpeakerRepositoryImpl();
        //Adding random generated speaker in the repository
        repository.addSpeaker(generateSpeakers());
        repository.addSpeaker(generateSpeakers());
        repository.addSpeaker(generateSpeakers());
        repository.addSpeaker(generateSpeakers());
        //Returning the hardcoded repository
        return repository;
    }

    /**
     * Simple function that generates two random indexes thus creating a speaker with names chosen
     * from the arrays
     * @return the new created speaker
     */
    //@Bean(name = "generateSpeaker")
    //If the function is bean than it will return the same speaker n times since by default is
    //using singleton
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
