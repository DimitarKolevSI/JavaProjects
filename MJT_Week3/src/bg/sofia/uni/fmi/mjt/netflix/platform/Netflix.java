package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentNotFoundException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;

public class Netflix implements StreamingService {
    private IntegerStreamableArray viewings = new IntegerStreamableArray();
    private Account[] accounts;
    private Streamable[] streamableContent;

    public Netflix(Account[] accounts, Streamable[] streamableContent) {
        this.accounts = accounts;
        this.streamableContent = streamableContent;
    }

    @Override
    public void watch(Account user, String videoContentName) throws ContentUnavailableException {
        Account currentUser = this.getUserIndex(user);
        if(currentUser == null){
            throw new UserNotFoundException("The user was not found!");
        }
        Streamable streamable = findByName(videoContentName);
        if(streamable == null){
            throw new ContentNotFoundException("This content is not in the platform");
        }

        LocalDateTime l = currentUser.getBirthdate();
        LocalDate birth = LocalDate.of(l.getYear(),l.getMonth(),l.getDayOfYear());
        LocalDate today = LocalDate.of(2020,10,31);
        Period period = Period.between(birth,today);

        int age = period.getYears();
        if (streamable.getRating().equals(PgRating.PG13) && age < 14){
            throw new ContentUnavailableException("You are too young for this content");
        }
        else if (streamable.getRating().equals((PgRating.NC17)) && age < 18){
            throw new ContentUnavailableException("You are too young for this content");
        }
        viewings.addStreamable(streamable);

    }

    @Override
    public Streamable findByName(String videoContentName) {
        if(streamableContent.length == 0) return null;
        for(Streamable streamable: streamableContent){
            if(streamable.getTitle().equals(videoContentName)){
                return streamable;
            }
        }
        return null;
    }

    @Override
    public Streamable mostViewed() {
        return this.viewings.getMostWatched();
    }

    @Override
    public int totalWatchedTimeByUsers() {
        int totalTime = 0;
        for(IntegerStreamablePair pair: this.viewings.toArray()){
            totalTime += (pair.getFrequency() * pair.getStreamable().getDuration());
        }
        return totalTime;
    }

    private Account getUserIndex(Account user){
        if(accounts.length == 0) return null;
        for(Account account: accounts){
            if(account.getUsername().equals(user.getUsername())){
                return account;
            }
        }
        return null;
    }

    private int calculateAge(LocalDateTime time){
        long currentTime = LocalDateTime.of(2020,10,31,12,0,0)
                .toEpochSecond(ZoneOffset.UTC);
        long birthTime = time.toEpochSecond(ZoneOffset.UTC);
        long difference = currentTime - birthTime;
        Date date = new Date(difference);
        return date.getYear();
    }
}
