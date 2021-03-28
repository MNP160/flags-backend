package com.hackaton.flags.flagsbackend.controller;

import com.hackaton.flags.flagsbackend.model.Beach;
import com.hackaton.flags.flagsbackend.model.Outpost;
import com.hackaton.flags.flagsbackend.service.BeachService;
import com.hackaton.flags.flagsbackend.utility.AuthenticationRequest;
import com.hackaton.flags.flagsbackend.utility.AuthenticationResponse;
import com.hackaton.flags.flagsbackend.utility.FlagUpdateRequest;
import com.hackaton.flags.flagsbackend.utility.OutpostCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin()
@RequestMapping("api/")
public class BeachController {

    private final BeachService beachService;
    private static final Map<String, String> idStorage = new HashMap<>();

    public BeachController (BeachService beachService){

        this.beachService=beachService;
    }


    @GetMapping("beaches")
    public ResponseEntity<List<Beach>> getBeaches(){
        System.out.println(beachService.getBeaches());
        return ResponseEntity.ok(beachService.getBeaches());
    }

    @GetMapping("beaches/{id}")
    public ResponseEntity<Beach> getBeach(@PathVariable String id){

        String email = idStorage.get(id);
        List<Beach> beaches = beachService.getBeaches();

        for (Beach beach : beaches){
            if (beach.getEmail().equals(email)){
                return ResponseEntity.ok(beach);
            }
        }

        return ResponseEntity.badRequest().build();



    }



    @GetMapping("beaches/count")
    public ResponseEntity<Long> getBeachCount(){
      return ResponseEntity.ok(beachService.getBeachCount());
    }

    @GetMapping("outposts/all")
    public ResponseEntity<List<Outpost>> getOutposts(){
        List<Beach> beaches= beachService.getBeaches();
        List<Outpost> outposts = new ArrayList<>();

        for (Beach beach : beaches) {
            outposts.addAll(beach.getOutposts());
        }

        return ResponseEntity.ok(outposts);

    }

    @GetMapping("outposts/{id}")
    public ResponseEntity<Outpost> getOutpostById(@PathVariable int id){
        Outpost found = beachService.getOutpostById(id);
        if (found !=null){
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("outposts")
    public ResponseEntity<List<Outpost>> GetOutpostsOnBeach(@RequestHeader(name = "Authorization") String authorized){
        if (idStorage.containsKey(authorized)) {
            String email = idStorage.get(authorized);
            List<Beach> beaches = beachService.getBeaches();
            List<Outpost> outposts = new ArrayList<>();

            for (Beach beach : beaches) {
                if (beach.getEmail().equals(email)) {
                    outposts.addAll(beach.getOutposts());
                }
            }

            return ResponseEntity.ok(outposts);
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody AuthenticationRequest request){
        List<Beach> beaches= beachService.getBeaches();

        for (Beach beach : beaches) {
           if (beach.getEmail().equals(request.getEmail()) && beach.getPassword().equals(request.getPassword())){
               String key = UUID.randomUUID().toString();
               idStorage.put(key, beach.getEmail());

               return ResponseEntity.ok(new AuthenticationResponse(key));
           }
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping("outposts/create")
    public ResponseEntity<Beach> createOutpost(@RequestHeader(name = "Authorization") String authorized, @RequestBody OutpostCreateRequest create){
        if (idStorage.containsKey(authorized)) {
           String email = idStorage.get(authorized);
           List<Beach> beaches = beachService.getBeaches();
           for(Beach beach : beaches){
               if (beach.getEmail().equals(email)) {
                   Optional<Beach> updated = beachService.InsertOutpostById(beach, create);
                   if(updated.isPresent()) {
                       return ResponseEntity.ok(updated.get());
                   }
               }
           }



            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();

    }


    @PutMapping("update")
    public ResponseEntity<Beach> updateOutpost (@RequestHeader(name = "Authorization") String authorized, @RequestBody FlagUpdateRequest outpost){
        //System.out.println(authorized);
        if (idStorage.containsKey(authorized)) {

            Beach beach = beachService.updateOutpost(outpost, idStorage.get(authorized));
            if (beach != null) {
                return ResponseEntity.ok(beach);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }else {
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping("create")
    public ResponseEntity<Beach> createOutpost (@RequestBody Beach beach){
        return ResponseEntity.ok(beachService.createBeach(beach));
    }


}
