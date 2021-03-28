package com.hackaton.flags.flagsbackend.service;

import com.hackaton.flags.flagsbackend.model.Beach;
import com.hackaton.flags.flagsbackend.model.Outpost;
import com.hackaton.flags.flagsbackend.repository.BeachRepository;
import com.hackaton.flags.flagsbackend.utility.FlagUpdateRequest;
import com.hackaton.flags.flagsbackend.utility.OutpostCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeachService {

    private BeachRepository beachRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public BeachService(BeachRepository beachRepository, MongoTemplate mongoTemplate){
        this.beachRepository=beachRepository;
        this.mongoTemplate=mongoTemplate;
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

    public Beach updateOutpost(FlagUpdateRequest outpost, String email){

       List<Beach>beaches =  beachRepository.findAll();
       Beach updated= null;
      for (Beach beach : beaches){

          if (beach.getEmail().equals(email)){

              updated = beach;
          }
      }


      for(int i = 0; i<updated.getOutposts().size(); i++){

          if (outpost.getOutpost_id()==updated.getOutposts().get(i).getOutpost_id()){

              updated.getOutposts().get(i).setFlag(outpost.getFlag());
          }
      }

      beachRepository.save(updated);
      return updated;
    }

    public Optional<Beach> InsertOutpostById(Beach beach, OutpostCreateRequest create){
        Update update = new Update();
        List<Beach> beaches =  beachRepository.findAll();
        int lastIndex=0;
        for (Beach value : beaches) {
            for (int j = 0; j < value.getOutposts().size(); j++) {
                if (value.getOutposts().get(j).getOutpost_id() > lastIndex) {
                    lastIndex = value.getOutposts().get(j).getOutpost_id();
                }
            }
        }

        Outpost outpost = new Outpost();
        outpost.setOutpost_id(lastIndex+1);
        outpost.setFlag(create.getFlag());
        outpost.setLat(create.getLat());
        outpost.setLon(create.getLon());
        outpost.setName(create.getName());
        update.addToSet("outposts", outpost);
        Criteria criteria = Criteria.where("_id").is(beach.getId());
        mongoTemplate.updateFirst(Query.query(criteria),update, "beaches" );

        return beachRepository.findById(beach.getId());
    }

    public Outpost getOutpostById(int id){
        List<Beach> beaches = beachRepository.findAll();
        Outpost found = null;
        for(Beach beach  :beaches ){
            for (int i =0; i<beach.getOutposts().size(); i++){
                if (beach.getOutposts().get(i).getOutpost_id()==id){
                    found = beach.getOutposts().get(i);
                }
            }
        }

        return found;
    }
}
