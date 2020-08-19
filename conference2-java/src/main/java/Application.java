import com.pluralsight.model.Speaker;
import com.pluralsight.service.SpeakerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.util.List;

public class Application {

    public static void main(String... args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //SpeakerService service = new SpeakerServiceImpl();

        SpeakerService service = applicationContext.getBean("speakerService", SpeakerService.class);

        System.out.println(service);

        /*
            SpeakerService service2 = applicationContext.getBean("speakerService", SpeakerService.class);
            System.out.println(service2);
        */

        List<Speaker> speakers = service.findAll();
        for(Speaker speaker:speakers){
            System.out.printf("The speaker is: %s %s%n", speaker.getFirstName(), speaker.getLastName());
        }
    }

}
