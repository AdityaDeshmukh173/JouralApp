package com.adityaprojectcompany.journalApp.scheduler;

import com.adityaprojectcompany.journalApp.Cache.AppCache;
import com.adityaprojectcompany.journalApp.Entity.JournalEntry;
import com.adityaprojectcompany.journalApp.Entity.User;
import com.adityaprojectcompany.journalApp.Repository.UserRepositoryImpl;
import com.adityaprojectcompany.journalApp.enums.Sentiment;
import com.adityaprojectcompany.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService ;

    @Autowired
    private UserRepositoryImpl userRepository ;

    @Autowired
    private AppCache appCache ;

//    @Scheduled(cron = "0 0 9 ? * SUN *")
    public void fetchUsersAndsendSAMail(){
        List<User> users = userRepository.getUsersForSA();
//            String entryjoined = String.join(" ", filteredEntries) ;
//            String sentiment = sentimentAnalysisService.getSentiment(entryjoined);
//            emailService.sendEmail(user.getEmail(),"Sentiment For Last 7 Days ", sentiment);

        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCount = new HashMap<>() ;
            for (Sentiment sentiment : sentiments){
                if (sentiment != null)
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1) ;
            }
            Sentiment mostFreqSentiment = null ;
            int maxCount = 0 ;
            for (Map.Entry<Sentiment,Integer> entry : sentimentCount.entrySet()){
                if (entry.getValue() > maxCount){
                    maxCount = entry.getValue() ;
                    mostFreqSentiment = entry.getKey() ;
                }
            }

            if (mostFreqSentiment != null){
                emailService.sendEmail(user.getEmail(), "Sentimets for last 7 Days ", mostFreqSentiment.toString());
            }
        }
    }

//    @Scheduled(cron = "0 0/10 0 ? * * *")
    public void clearAppCache(){
        appCache.init();
    }

}
