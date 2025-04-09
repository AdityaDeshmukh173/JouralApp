package com.adityaprojectcompany.journalApp.Cache;

import com.adityaprojectcompany.journalApp.Entity.ConfigJournalAppEntity;
import com.adityaprojectcompany.journalApp.Repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API ;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository ;

    public Map<String,String> appCache;           // Everything(configurations) stored on the DataBase will be saved in this HashMap at once in single call no need to call everytime and additional latency

    @PostConstruct
    public void init(){
        appCache = new HashMap<>() ;        // For API reinitialization
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all){
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
