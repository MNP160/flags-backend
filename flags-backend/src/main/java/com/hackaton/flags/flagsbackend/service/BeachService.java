package com.hackaton.flags.flagsbackend.service;

import com.hackaton.flags.flagsbackend.model.Beach;
import com.hackaton.flags.flagsbackend.model.Outpost;
import com.hackaton.flags.flagsbackend.repository.BeachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeachService {

    private BeachRepository beachRepository;

    @Autowired
    public BeachService(BeachRepository beachRepository){
        this.beachRepository=beachRepository;
    }

    public List<Beach> getBeaches(){
        return beachRepository.findAll();
    }

    public Optional<Beach> getBeachId(String id){
        return beachRepository.findById(id);
    }

    public long getBeachCount(){
        return beachRepository.count();
    }

    public Beach createBeach(Beach beach){
        return beachRepository.insert(beach);
    }

    public Beach updateOutpost(Outpost outpost){
       List<Beach>beaches =  beachRepository.findAll();
       Beach updated= null;
      for (Beach beach : beaches){
          for (int i =0 ; i<beach.getOutposts().size()-1; i++){
              if (beach.getOutposts().get(i).getId()==outpost.getId()){
                  beach.getOutposts().get(i).setFlag(outpost.getFlag());
                  updated = beach;
              }
          }
      }
      beachRepository.saveAll(beaches);
      return updated;
    }
}
